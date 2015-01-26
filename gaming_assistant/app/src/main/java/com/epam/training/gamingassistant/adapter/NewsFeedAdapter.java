package com.epam.training.gamingassistant.adapter;

import android.content.Context;
import android.graphics.PointF;
import android.provider.ContactsContract;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.ImageView;
import android.widget.TextView;

import com.epam.training.gamingassistant.R;
import com.epam.training.gamingassistant.bo.newsfeed.GetNewsFeedResponse;
import com.epam.training.gamingassistant.bo.newsfeed.News;
import com.epam.training.gamingassistant.bo.newsfeed.Profile;
import com.epam.training.gamingassistant.bo.newsfeed.Group;
import com.epam.training.gamingassistant.tasks.BitmapLoadTask;

import org.w3c.dom.Text;


import java.util.List;

/**
 * Created by NuclearOK on 23.01.2015.
 */
public class NewsFeedAdapter extends BaseAdapter {


    private GetNewsFeedResponse getNewsFeedResponse;
    private Context context;

    public NewsFeedAdapter(Context context , GetNewsFeedResponse getNewsFeedResponse) {
        this.context = context;
        this.getNewsFeedResponse = getNewsFeedResponse;

    }

    private static class Holder{
        ImageView source_avatar;
        TextView user_name;
        TextView text;
        ImageView repost_avatar;
        TextView repost_name;
        TextView text_repost;
    }
    @Override
    public int getCount() {
        return getNewsFeedResponse.getItems().size();
    }

    @Override
    public Object getItem(int position) {
        return getNewsFeedResponse.getItems().get(position);
    }

    @Override
    public long getItemId(int position) {
        return getNewsFeedResponse.getItems().get(position).hashCode();
    }

    @Override
    public View getView(int position, View convertView, ViewGroup parent) {
        if (convertView == null) {
            LayoutInflater inflater = (LayoutInflater) context.getSystemService(Context.LAYOUT_INFLATER_SERVICE);
            convertView = inflater.inflate(R.layout.item_news,parent,false);
            Holder holder = new Holder();
            holder.text = (TextView) convertView.findViewById(R.id.text);
            holder.source_avatar = (ImageView) convertView.findViewById(R.id.source_avatar);
            holder.text_repost = (TextView) convertView.findViewById(R.id.text_repost);
            holder.user_name = (TextView) convertView.findViewById(R.id.user_name);
            holder.repost_avatar = (ImageView) convertView.findViewById(R.id.repost_avatar);
            holder.repost_name = (TextView) convertView.findViewById(R.id.repost_name);
            convertView.setTag(holder);
        }

        Holder h = (Holder) convertView.getTag();
        String id = getNewsFeedResponse.getItems().get(position).getSource_id();

        if (id.startsWith("-")){
            Group group = getNewsFeedResponse.getGroupInfoFromId(id.substring(1));
            h.user_name.setText(group.getName());
            BitmapLoadTask bitmapLoadTask = new BitmapLoadTask(h.source_avatar);
            bitmapLoadTask.execute(group.getPhoto_50());
        } else {
            Profile profile = getNewsFeedResponse.getProfileInfoFromId(id);
            h.user_name.setText(profile.getFirst_name()+ " "+profile.getLast_name());
            BitmapLoadTask bitmapLoadTask = new BitmapLoadTask(h.source_avatar);
            bitmapLoadTask.execute(profile.getPhoto_50());
        }
        h.text.setText(getNewsFeedResponse.getItems().get(position).getText());


        if (getNewsFeedResponse.getItems().get(position).getCopy_history() != null && getNewsFeedResponse.getItems().get(position).getCopy_history().size() > 0) {
            String ownerId = getNewsFeedResponse.getItems().get(position).getCopy_history().get(0).getOwner_id();

            if (ownerId.startsWith("-")){
                Group groupRepost = getNewsFeedResponse.getGroupInfoFromId(ownerId.substring(1));
                h.repost_name.setText(groupRepost.getName());
                h.repost_avatar.setVisibility(View.VISIBLE);
                BitmapLoadTask bitmapLoadTask = new BitmapLoadTask(h.repost_avatar);
                bitmapLoadTask.execute(groupRepost.getPhoto_50());
            } else {
                Profile profileRepost = getNewsFeedResponse.getProfileInfoFromId(ownerId);
                h.repost_name.setText(profileRepost.getFirst_name()+ " "+profileRepost.getLast_name());
                h.repost_avatar.setVisibility(View.VISIBLE);
                BitmapLoadTask bitmapLoadTask = new BitmapLoadTask(h.repost_avatar);
                bitmapLoadTask.execute(profileRepost.getPhoto_50());
            }

            h.text_repost.setText(getNewsFeedResponse.getItems().get(position).getCopy_history().get(0).getText());
        } else{
            h.repost_name.setText("");
            h.text_repost.setText("");
            h.repost_avatar.setVisibility(View.INVISIBLE);
        }

        return convertView;
    }
}
