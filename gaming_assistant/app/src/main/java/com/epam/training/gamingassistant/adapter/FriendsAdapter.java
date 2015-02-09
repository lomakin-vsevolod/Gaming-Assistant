package com.epam.training.gamingassistant.adapter;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.ImageView;
import android.widget.TextView;

import com.epam.training.gamingassistant.R;
import com.epam.training.gamingassistant.bo.friends.Friend;
import com.epam.training.gamingassistant.imageloader.ImageLoader;

import java.util.List;


public class FriendsAdapter extends BaseAdapter {

    private List<Friend> friends;
    private Context context;

    private static class Holder {
        ImageView friendAvatar;
        TextView friendName;
    }

    public FriendsAdapter(Context context, List<Friend> friends) {
        this.friends = friends;
        this.context = context;
    }

    @Override
    public int getCount() {
        return friends.size();
    }

    @Override
    public Object getItem(int position) {
        return friends.get(position);
    }

    @Override
    public long getItemId(int position) {
        return friends.get(position).hashCode();
    }

    public long getId(int position) {
        return friends.get(position).getId();
    }

    @Override
    public View getView(int position, View convertView, ViewGroup parent) {
        if (convertView == null) {
            LayoutInflater inflater = (LayoutInflater) context.getSystemService(Context.LAYOUT_INFLATER_SERVICE);
            convertView = inflater.inflate(R.layout.item_friend, parent, false);
            Holder holder = new Holder();
            holder.friendName = (TextView) convertView.findViewById(R.id.friend_name);
            holder.friendAvatar = (ImageView) convertView.findViewById(R.id.friend_avatar);
            convertView.setTag(holder);
        }
        Holder h = (Holder) convertView.getTag();
        h.friendName.setText(friends.get(position).getFirstName() + " " + friends.get(position).getLastName());
        ImageLoader.getImageLoader().loadImage(friends.get(position).getPhoto100(), h.friendAvatar);
        return convertView;
    }


}
