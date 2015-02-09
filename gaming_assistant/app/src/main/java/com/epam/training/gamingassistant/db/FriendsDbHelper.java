package com.epam.training.gamingassistant.db;


import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;

import com.epam.training.gamingassistant.CoreApplication;
import com.epam.training.gamingassistant.bo.friends.Friend;

import java.util.LinkedList;
import java.util.List;

public class FriendsDbHelper extends SQLiteOpenHelper {

    public static final String KEY = "FriendsDbHelper";

    public static FriendsDbHelper get(Context context) {
        return CoreApplication.get(context, KEY);
    }

    public FriendsDbHelper(Context context) {
        super(context, DbConstants.DB_NAME, null, DbConstants.DB_VERSION);
    }

    @Override
    public void onCreate(SQLiteDatabase db) {
        db.execSQL(DbConstants.CREATE_TABLE_FRIENDS);
    }

    @Override
    public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {

    }

    public Void insertFriend(Friend friend) {
        SQLiteDatabase db = this.getWritableDatabase();
        ContentValues contentValues = new ContentValues();
        contentValues.put(DbConstants.COLUMN_FRIENDS_ID, friend.getId());
        contentValues.put(DbConstants.COLUMN_FRIENDS_FIRST_NAME, friend.getFirstName());
        contentValues.put(DbConstants.COLUMN_FRIENDS_LAST_NAME, friend.getLastName());
        contentValues.put(DbConstants.COLUMN_FRIENDS_PHOTO_100, friend.getPhoto100());
        db.insert(DbConstants.TABLE_FRIENDS, null, contentValues);
        db.close();
        return null;
    }

    public Void insertFriends(List<Friend> friends) {
        SQLiteDatabase db = this.getWritableDatabase();
        for (Friend friend : friends) {
            ContentValues contentValues = new ContentValues();
            contentValues.put(DbConstants.COLUMN_FRIENDS_ID, friend.getId());
            contentValues.put(DbConstants.COLUMN_FRIENDS_FIRST_NAME, friend.getFirstName());
            contentValues.put(DbConstants.COLUMN_FRIENDS_LAST_NAME, friend.getLastName());
            contentValues.put(DbConstants.COLUMN_FRIENDS_PHOTO_100, friend.getPhoto100());
            db.insert(DbConstants.TABLE_FRIENDS, null, contentValues);
        }
        db.close();
        return null;
    }

    public List<Friend> getFriends() {
        List<Friend> friendList = new LinkedList<Friend>();
        SQLiteDatabase db = this.getReadableDatabase();
        Cursor cursor = db.query(DbConstants.TABLE_FRIENDS, new String[]{DbConstants.COLUMN_FRIENDS_ID, DbConstants.COLUMN_FRIENDS_FIRST_NAME, DbConstants.COLUMN_FRIENDS_LAST_NAME, DbConstants.COLUMN_FRIENDS_PHOTO_100}, null, null, null, null, DbConstants.COLUMN_FRIENDS_FIRST_NAME);
        if (cursor == null) {
            db.close();
            return friendList;
        }
        if (cursor.moveToFirst()) {
            while (!cursor.isAfterLast()) {
                Friend friend = new Friend(cursor.getLong(0), cursor.getString(1), cursor.getString(2), cursor.getString(3));
                friendList.add(friend);
                cursor.moveToNext();
            }
        }
        db.close();
        cursor.close();
        return friendList;
    }

    public void refreshTable() {
        SQLiteDatabase db = this.getWritableDatabase();
        db.execSQL(DbConstants.DELETE_TABLE_FRIENDS);
        db.execSQL(DbConstants.CREATE_TABLE_FRIENDS);
        db.close();
    }
}















