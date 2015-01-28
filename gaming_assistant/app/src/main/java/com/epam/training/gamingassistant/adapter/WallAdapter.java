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
import com.epam.training.gamingassistant.tasks.BitmapLoadTask;


public class WallAdapter extends BaseAdapter {


    private GetWallResponse getWallResponse;
    private Context context;

    public WallAdapter( Context context,GetWallResponse getWallResponse) {
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
        String ownerId = getWallResponse.getItems().get(position).getFrom_id();

        if (ownerId.startsWith("-")) {
            Group group = getWallResponse.getGroupInfoFromId(ownerId.substring(1));
            h.sourceName.setText(group.getName());
            BitmapLoadTask bitmapLoadTask = new BitmapLoadTask(h.sourceAvatar);
            bitmapLoadTask.execute(group.getPhoto_50());
        } else {
            Profile profile = getWallResponse.getProfileInfoFromId(ownerId);
            h.sourceName.setText(profile.getFirst_name() + " " + profile.getLast_name());
            BitmapLoadTask bitmapLoadTask = new BitmapLoadTask(h.sourceAvatar);
            bitmapLoadTask.execute(profile.getPhoto_50());
        }
        h.sourceText.setText(getWallResponse.getItems().get(position).getText());


        if (getWallResponse.getItems().get(position).getCopy_history() != null && getWallResponse.getItems().get(position).getCopy_history().size() > 0) {
            String copyOwnerId = getWallResponse.getItems().get(position).getCopy_history().get(0).getOwner_id();
            h.copyLayout.setVisibility(View.VISIBLE);
            if (copyOwnerId.startsWith("-")) {
                Group copyGroup = getWallResponse.getGroupInfoFromId(copyOwnerId.substring(1));
                h.copyName.setText(copyGroup.getName());
                BitmapLoadTask bitmapLoadTask = new BitmapLoadTask(h.copyAvatar);
                bitmapLoadTask.execute(copyGroup.getPhoto_50());
            } else {
                Profile copyProfile = getWallResponse.getProfileInfoFromId(copyOwnerId);
                h.copyName.setText(copyProfile.getFirst_name() + " " + copyProfile.getLast_name());
                BitmapLoadTask bitmapLoadTask = new BitmapLoadTask(h.copyAvatar);
                bitmapLoadTask.execute(copyProfile.getPhoto_50());
            }
            h.copyText.setText(getWallResponse.getItems().get(position).getCopy_history().get(0).getText());
        } else {
            h.copyLayout.setVisibility(View.INVISIBLE);
        }
        return convertView;

    }
}
