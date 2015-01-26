package com.epam.training.gamingassistant.tasks;

import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.os.AsyncTask;
import android.widget.ImageView;

import java.io.IOException;
import java.io.InputStream;
import java.lang.ref.WeakReference;
import java.net.MalformedURLException;
import java.net.URL;

/**
 * Created by NuclearOK on 23.01.2015.
 */
public class BitmapLoadTask extends AsyncTask<String, Void, Bitmap> {
    private final WeakReference<ImageView> imageViewReference;

    public BitmapLoadTask(ImageView imageView){
        imageViewReference = new WeakReference<ImageView>(imageView);
    }

    @Override
    protected Bitmap doInBackground(String... params) {
        URL url = null;
        try {
            url = new URL(params[0]);
        } catch (MalformedURLException e) {
            e.printStackTrace();
        }
        InputStream inputStream = null;
        try {
            if (url != null) {
                inputStream = url.openStream();
            }
        } catch (IOException e) {
            e.printStackTrace();
        } finally {

        }
        return BitmapFactory.decodeStream(inputStream);

    }

    @Override
    protected void onPostExecute(Bitmap bitmap) {
        if (imageViewReference != null && bitmap != null) {
            final ImageView imageView = imageViewReference.get();
            if (imageView != null) {
                imageView.setImageBitmap(bitmap);
            }
        }
    }
}
