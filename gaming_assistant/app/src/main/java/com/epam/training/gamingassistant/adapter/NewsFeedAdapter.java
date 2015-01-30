package com.epam.training.gamingassistant.adapter;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;

import com.epam.training.gamingassistant.R;
import com.epam.training.gamingassistant.bo.extended.Group;
import com.epam.training.gamingassistant.bo.extended.Profile;
import com.epam.training.gamingassistant.bo.newsfeed.GetNewsFeedResponse;
import com.epam.training.gamingassistant.imageloader.ImageLoader;


public class NewsFeedAdapter extends BaseAdapter {


    private GetNewsFeedResponse getNewsFeedResponse;
    private Context context;

    public NewsFeedAdapter(Context context, GetNewsFeedResponse getNewsFeedResponse) {
        this.context = context;
        this.getNewsFeedResponse = getNewsFeedResponse;

    }

    private class Holder {
        ImageView sourceAvatar;
        TextView sourceName;
        TextView sourceText;
        LinearLayout copyLayout;
        ImageView copyAvatar;
        TextView copyName;
        TextView copyText;
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
            convertView = inflater.inflate(R.layout.item_news, parent, false);
            Holder holder = new Holder();
            holder.sourceAvatar = (ImageView) convertView.findViewById(R.id.source_avatar);
            holder.sourceName = (TextView) convertView.findViewById(R.id.source_name);
            holder.sourceText = (TextView) convertView.findViewById(R.id.source_text);
            holder.copyLayout = (LinearLayout) convertView.findViewById(R.id.copy_layout);
            holder.copyAvatar = (ImageView) convertView.findViewById(R.id.copy_avatar);
            holder.copyName = (TextView) convertView.findViewById(R.id.copy_name);
            holder.copyText = (TextView) convertView.findViewById(R.id.copy_text);
            convertView.setTag(holder);
        }

        Holder h = (Holder) convertView.getTag();
        String sourceId = getNewsFeedResponse.getItems().get(position).getSource_id();

        if (sourceId.startsWith("-")) {
            Group group = getNewsFeedResponse.getGroupInfoFromId(sourceId.substring(1));
            h.sourceName.setText(group.getName());
            h.sourceAvatar.setImageResource(R.drawable.ic_launcher);
            ImageLoader.getImageLoader().loadImage(group.getPhoto_50(), h.sourceAvatar);
//            if (!group.getPhoto_50().equals(h.sourceAvatar.getTag())) {
//                h.sourceAvatar.setTag(group.getPhoto_50());
//                h.sourceAvatar.setImageResource(R.drawable.ic_launcher);
//                BitmapLoadTask bitmapLoadTask = new BitmapLoadTask(h.sourceAvatar);
//                bitmapLoadTask.execute(group.getPhoto_50());
//            }
        } else {
            Profile profile = getNewsFeedResponse.getProfileInfoFromId(sourceId);
            h.sourceName.setText(profile.getFirst_name() + " " + profile.getLast_name());
            h.sourceAvatar.setImageResource(R.drawable.ic_launcher);
            ImageLoader.getImageLoader().loadImage(profile.getPhoto_50(), h.sourceAvatar);
//            if (!profile.getPhoto_50().equals(h.sourceAvatar.getTag())) {
//                h.sourceAvatar.setTag(profile.getPhoto_50());
//                h.sourceAvatar.setImageResource(R.drawable.ic_launcher);
//                BitmapLoadTask bitmapLoadTask = new BitmapLoadTask(h.sourceAvatar);
//                bitmapLoadTask.execute(profile.getPhoto_50());
//            }
        }
        h.sourceText.setText(getNewsFeedResponse.getItems().get(position).getText());


        if (getNewsFeedResponse.getItems().get(position).getCopy_history() != null && getNewsFeedResponse.getItems().get(position).getCopy_history().size() > 0) {
            String copyOwnerId = getNewsFeedResponse.getItems().get(position).getCopy_history().get(0).getOwner_id();
            h.copyLayout.setVisibility(View.VISIBLE);
            if (copyOwnerId.startsWith("-")) {
                Group copyGroup = getNewsFeedResponse.getGroupInfoFromId(copyOwnerId.substring(1));
                h.copyName.setText(copyGroup.getName());
                h.copyAvatar.setImageResource(R.drawable.ic_launcher);
                ImageLoader.getImageLoader().loadImage(copyGroup.getPhoto_50(), h.copyAvatar);

            } else {
                Profile copyProfile = getNewsFeedResponse.getProfileInfoFromId(copyOwnerId);
                h.copyName.setText(copyProfile.getFirst_name() + " " + copyProfile.getLast_name());
                h.copyAvatar.setImageResource(R.drawable.ic_launcher);
                ImageLoader.getImageLoader().loadImage(copyProfile.getPhoto_50(), h.copyAvatar);
            }
            h.copyText.setText(getNewsFeedResponse.getItems().get(position).getCopy_history().get(0).getText());
        } else {
            h.copyLayout.setVisibility(View.GONE);
        }
        return convertView;
    }
}
