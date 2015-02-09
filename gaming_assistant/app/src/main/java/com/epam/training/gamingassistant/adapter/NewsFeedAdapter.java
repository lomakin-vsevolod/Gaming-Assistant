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
        Long sourceId = getNewsFeedResponse.getItems().get(position).getSourceId();

        if (sourceId < 0) {
            Group group = getNewsFeedResponse.getGroupInfoFromId(sourceId);
            h.sourceName.setText(group.getName());
            ImageLoader.getImageLoader().loadImage(group.getPhoto50(), h.sourceAvatar);
        } else {
            Profile profile = getNewsFeedResponse.getProfileInfoFromId(sourceId);
            h.sourceName.setText(profile.getFirstName() + " " + profile.getLastName());
            ImageLoader.getImageLoader().loadImage(profile.getPhoto50(), h.sourceAvatar);
        }
        h.sourceText.setText(getNewsFeedResponse.getItems().get(position).getText());


        if (getNewsFeedResponse.getItems().get(position).getCopyHistory() != null && getNewsFeedResponse.getItems().get(position).getCopyHistory().size() > 0) {
            Long copyOwnerId = getNewsFeedResponse.getItems().get(position).getCopyHistory().get(0).getOwnerId();
            h.copyLayout.setVisibility(View.VISIBLE);
            if (copyOwnerId < 0) {
                Group copyGroup = getNewsFeedResponse.getGroupInfoFromId(copyOwnerId);
                h.copyName.setText(copyGroup.getName());
                ImageLoader.getImageLoader().loadImage(copyGroup.getPhoto50(), h.copyAvatar);
            } else {
                Profile copyProfile = getNewsFeedResponse.getProfileInfoFromId(copyOwnerId);
                h.copyName.setText(copyProfile.getFirstName() + " " + copyProfile.getLastName());
                ImageLoader.getImageLoader().loadImage(copyProfile.getPhoto50(), h.copyAvatar);
            }
            h.copyText.setText(getNewsFeedResponse.getItems().get(position).getCopyHistory().get(0).getText());
        } else {
            h.copyLayout.setVisibility(View.GONE);
        }
        return convertView;
    }
}
