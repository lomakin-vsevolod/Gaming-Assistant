package com.epam.training.gamingassistant.imageloader;


import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.support.v4.util.LruCache;
import android.widget.ImageView;

import com.epam.training.gamingassistant.os.CustomAsyncTask;

import java.io.IOException;
import java.io.InputStream;
import java.lang.ref.WeakReference;
import java.net.URL;
import java.util.HashSet;
import java.util.Set;
import java.util.concurrent.LinkedBlockingQueue;

public class ImageLoader {
    private static ImageLoader imageLoader;

    //TODO
    public static ImageLoader getImageLoader() {
        if (imageLoader == null) {
            imageLoader = new ImageLoader();
        }
        return imageLoader;
    }

    private class Task {
        WeakReference<ImageView> imageView;
        String url;
    }

    private Set<ImageView> stopSet = new HashSet<ImageView>();
    private LinkedBlockingQueue<Task> queue = new LinkedBlockingQueue<Task>();
    private boolean isLoading = false;
    private boolean isStop = false;
    private final int maxMemory = (int) (Runtime.getRuntime().maxMemory() / 1024);
    private final int cacheSize = maxMemory / 8;
    private LruCache<String, Bitmap> memoryCache = new LruCache<String, Bitmap>(cacheSize) {
        @Override
        protected int sizeOf(String key, Bitmap bitmap) {
            return bitmap.getByteCount() / 1024;
        }
    };

    public void start() {
        isStop = false;
        for (ImageView imageView : stopSet) {
            if (imageView.getTag() != null) {
                loadImage(imageView.getTag().toString(), imageView);
            }
        }
        stopSet.clear();
    }


    public void stop() {
        isStop = true;
    }

    private ImageLoader() {

    }

    public void loadImage(String url, ImageView imageView) {
        Bitmap bitmap = memoryCache.get(url);
        imageView.setImageBitmap(bitmap);
        imageView.setTag(url);
        if (bitmap != null) {
            return;
        }
        Task task = new Task();
        task.imageView = new WeakReference<ImageView>(imageView);
        task.url = url;
        imageView.setTag(url);
        if (isStop) {
            stopSet.add(imageView);
        } else {
            try {
                queue.put(task);
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
            if (!isLoading) {
                isLoading = true;
                nextTask();
            }
        }
    }

    private void nextTask() {
        if (queue.size() > 0) {
            Task task = queue.poll();
            //TODO create executor
            BitmapLoadTask bitmapLoadTask = new BitmapLoadTask(task);
            bitmapLoadTask.execute();
        } else {
            isLoading = false;
        }
    }

    public class BitmapLoadTask extends CustomAsyncTask<Void, Void, Bitmap> {
        private Task task;

        public BitmapLoadTask(Task task) {
            this.task = task;
        }

        @Override
        protected Bitmap doInBackground(Void... params) {
            URL url = null;
            InputStream inputStream = null;
            try {
                url = new URL(task.url);
                inputStream = url.openStream();
                return BitmapFactory.decodeStream(inputStream);
            } catch (IOException e) {
                e.printStackTrace();
            } finally {
                try {
                    if (inputStream != null) {
                        inputStream.close();
                    }
                } catch (IOException e) {
                    e.printStackTrace();
                }
            }
            return null;
        }

        @Override
        protected void onPostException(Exception e) {

        }

        @Override
        protected void onPostExecute(Bitmap bitmap) {
            if (task.imageView != null && bitmap != null) {
                final ImageView imageView = task.imageView.get();
                if (imageView != null) {
                    if (imageView.getTag() == null || imageView.getTag().equals(task.url)) {
                        imageView.setImageBitmap(bitmap);
                    }
                }
                memoryCache.put(task.url, bitmap);
            }
            nextTask();
        }
    }


}
