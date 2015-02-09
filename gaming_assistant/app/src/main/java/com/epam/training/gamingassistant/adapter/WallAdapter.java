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
import com.epam.training.gamingassistant.bo.wall.GetWallResponse;
import com.epam.training.gamingassistant.imageloader.ImageLoader;


public class WallAdapter extends BaseAdapter {


    private GetWallResponse getWallResponse;
    private Context context;

    public WallAdapter(Context context, GetWallResponse getWallResponse) {
        this.context = context;
        this.getWallResponse = getWallResponse;
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
        return getWallResponse.getItems().size();
    }

    @Override
    public Object getItem(int position) {
        return getWallResponse.getItems().get(position);
    }

    @Override
    public long getItemId(int position) {
        return getWallResponse.getItems().get(position).hashCode();
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
        Long ownerId = getWallResponse.getItems().get(position).getFromId();

        if (ownerId < 0) {
            Group group = getWallResponse.getGroupInfoFromId(ownerId);
            h.sourceName.setText(group.getName());
            ImageLoader.getImageLoader().loadImage(group.getPhoto50(), h.sourceAvatar);
        } else {
            Profile profile = getWallResponse.getProfileInfoFromId(ownerId);
            h.sourceName.setText(profile.getFirstName() + " " + profile.getLastName());
            ImageLoader.getImageLoader().loadImage(profile.getPhoto50(), h.sourceAvatar);
        }
        h.sourceText.setText(getWallResponse.getItems().get(position).getText());


        if (getWallResponse.getItems().get(position).getCopyHistory() != null && getWallResponse.getItems().get(position).getCopyHistory().size() > 0) {
            Long copyOwnerId = getWallResponse.getItems().get(position).getCopyHistory().get(0).getOwnerId();
            h.copyLayout.setVisibility(View.VISIBLE);
            if (copyOwnerId < 0) {
                Group copyGroup = getWallResponse.getGroupInfoFromId(copyOwnerId);
                h.copyName.setText(copyGroup.getName());
                ImageLoader.getImageLoader().loadImage(copyGroup.getPhoto50(), h.copyAvatar);
            } else {
                Profile copyProfile = getWallResponse.getProfileInfoFromId(copyOwnerId);
                h.copyName.setText(copyProfile.getFirstName() + " " + copyProfile.getLastName());
                ImageLoader.getImageLoader().loadImage(copyProfile.getPhoto50(), h.copyAvatar);
            }
            h.copyText.setText(getWallResponse.getItems().get(position).getCopyHistory().get(0).getText());
        } else {
            h.copyLayout.setVisibility(View.GONE);
        }
        return convertView;

    }
}
