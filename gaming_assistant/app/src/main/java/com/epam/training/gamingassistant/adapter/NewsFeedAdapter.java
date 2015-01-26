package com.epam.training.gamingassistant.adapter;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.ImageView;
import android.widget.TextView;

import com.epam.training.gamingassistant.R;
import com.epam.training.gamingassistant.bo.newsfeed.GetNewsFeedResponse;
import com.epam.training.gamingassistant.bo.newsfeed.Profile;
import com.epam.training.gamingassistant.bo.newsfeed.Group;
import com.epam.training.gamingassistant.tasks.BitmapLoadTask;


public class NewsFeedAdapter extends BaseAdapter {


    private GetNewsFeedResponse getNewsFeedResponse;
    private Context context;

    public NewsFeedAdapter(Context context , GetNewsFeedResponse getNewsFeedResponse) {
        this.context = context;
        this.getNewsFeedResponse = getNewsFeedResponse;

    }

    private static class Holder{
        ImageView source_avatar;
        TextView source_name;
        TextView source_text;
        ImageView copy_avatar;
        TextView copy_name;
        TextView copy_text;
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
            holder.source_avatar = (ImageView) convertView.findViewById(R.id.source_avatar);
            holder.source_name = (TextView) convertView.findViewById(R.id.source_name);
            holder.source_text = (TextView) convertView.findViewById(R.id.source_text);
            holder.copy_avatar = (ImageView) convertView.findViewById(R.id.copy_avatar);
            holder.copy_name = (TextView) convertView.findViewById(R.id.copy_name);
            holder.copy_text = (TextView) convertView.findViewById(R.id.copy_text);
            convertView.setTag(holder);
        }

        Holder h = (Holder) convertView.getTag();
        String sourceId = getNewsFeedResponse.getItems().get(position).getSource_id();

        if (sourceId.startsWith("-")){
            Group group = getNewsFeedResponse.getGroupInfoFromId(sourceId.substring(1));
            h.source_name.setText(group.getName());
            h.source_avatar.setTag(group.getPhoto_50());
            BitmapLoadTask bitmapLoadTask = new BitmapLoadTask(h.source_avatar);
            bitmapLoadTask.execute(group.getPhoto_50());
        } else {
            Profile profile = getNewsFeedResponse.getProfileInfoFromId(sourceId);
            h.source_name.setText(profile.getFirst_name() + " " + profile.getLast_name());
            h.source_avatar.setTag(profile.getPhoto_50());
            BitmapLoadTask bitmapLoadTask = new BitmapLoadTask(h.source_avatar);
            bitmapLoadTask.execute(profile.getPhoto_50());
        }
        h.source_text.setText(getNewsFeedResponse.getItems().get(position).getText());


        if (getNewsFeedResponse.getItems().get(position).getCopy_history() != null && getNewsFeedResponse.getItems().get(position).getCopy_history().size() > 0) {
            String copyOwnerId = getNewsFeedResponse.getItems().get(position).getCopy_history().get(0).getOwner_id();
            if (copyOwnerId.startsWith("-")){
                Group copyGroup = getNewsFeedResponse.getGroupInfoFromId(copyOwnerId.substring(1));
                h.copy_name.setText(copyGroup.getName());
                h.copy_name.setVisibility(View.VISIBLE);
                BitmapLoadTask bitmapLoadTask = new BitmapLoadTask(h.copy_avatar);
                bitmapLoadTask.execute(copyGroup.getPhoto_50());
                h.copy_avatar.setVisibility(View.VISIBLE);
            } else {
                Profile copyProfile = getNewsFeedResponse.getProfileInfoFromId(copyOwnerId);
                h.copy_name.setText(copyProfile.getFirst_name() + " " + copyProfile.getLast_name());
                h.copy_name.setVisibility(View.VISIBLE);
                BitmapLoadTask bitmapLoadTask = new BitmapLoadTask(h.copy_avatar);
                bitmapLoadTask.execute(copyProfile.getPhoto_50());
                h.copy_avatar.setVisibility(View.VISIBLE);
            }
            h.copy_text.setText(getNewsFeedResponse.getItems().get(position).getCopy_history().get(0).getText());
            h.copy_text.setVisibility(View.VISIBLE);
        } else{
            h.copy_name.setText("");
            h.copy_text.setText("");
            h.copy_avatar.setVisibility(View.INVISIBLE);
            h.copy_text.setVisibility(View.INVISIBLE);
            h.copy_name.setVisibility(View.INVISIBLE);
        }
        return convertView;
    }
}
