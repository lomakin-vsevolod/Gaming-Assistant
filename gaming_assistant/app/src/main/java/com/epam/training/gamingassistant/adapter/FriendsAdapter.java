package com.epam.training.gamingassistant.adapter;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.TextView;

import com.epam.training.gamingassistant.R;
import com.epam.training.gamingassistant.bo.Friend;

import java.util.List;


public class FriendsAdapter extends BaseAdapter {

    private List<Friend> friends;
    private Context context;

    private static class Holder {
        TextView name;
    }

    public FriendsAdapter(Context context, List<Friend> list) {
        this.friends = list;
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

    @Override
    public View getView(int position, View convertView, ViewGroup parent) {
        if (convertView == null) {
            LayoutInflater inflater = (LayoutInflater) context.getSystemService(Context.LAYOUT_INFLATER_SERVICE);
            convertView = inflater.inflate(R.layout.item_friend, parent, false);
            Holder holder = new Holder();
            holder.name = (TextView) convertView.findViewById(R.id.name);
            convertView.setTag(holder);
        }

        Holder h = (Holder) convertView.getTag();
        h.name.setText(friends.get(position).getDisplayName());

        return convertView;
    }
}
