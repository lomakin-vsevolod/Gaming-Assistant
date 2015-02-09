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

    private static final String DB_NAME = "greenvk.db";
    private static final int DB_VERSION = 1;
    private static final String TABLE_FRIENDS = "FRIENDS";
    private static final String COLUMN_FRIENDS_ID = "_ID";
    private static final String COLUMN_FRIENDS_FIRST_NAME = "FIRST_NAME";
    private static final String COLUMN_FRIENDS_LAST_NAME = "LAST_NAME";
    private static final String COLUMN_FRIENDS_PHOTO_100 = "PHOTO_100";
    private static final String CREATE_TABLE_FRIENDS = "create table FRIENDS (_ID integer primary key autoincrement, FIRST_NAME text, LAST_NAME text, PHOTO_100 text)";
    private static final String DELETE_TABLE_FRIENDS = "DROP TABLE FRIENDS";

    public static FriendsDbHelper get(Context context) {
        return CoreApplication.get(context, KEY);
    }

    public FriendsDbHelper(Context context) {
        super(context, DB_NAME, null, DB_VERSION);
    }

    @Override
    public void onCreate(SQLiteDatabase db) {
        db.execSQL(CREATE_TABLE_FRIENDS);
    }

    @Override
    public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {

    }

    public Void insertFriend(Friend friend) {
        SQLiteDatabase db = this.getWritableDatabase();
        ContentValues contentValues = new ContentValues();
        contentValues.put(COLUMN_FRIENDS_ID, friend.getId());
        contentValues.put(COLUMN_FRIENDS_FIRST_NAME, friend.getFirstName());
        contentValues.put(COLUMN_FRIENDS_LAST_NAME, friend.getLastName());
        contentValues.put(COLUMN_FRIENDS_PHOTO_100, friend.getPhoto100());
        db.insert(TABLE_FRIENDS, null, contentValues);
        db.close();
        return null;
    }

    public Void insertFriends(List<Friend> friends) {
        SQLiteDatabase db = this.getWritableDatabase();
        for (Friend friend : friends) {
            ContentValues contentValues = new ContentValues();
            contentValues.put(COLUMN_FRIENDS_ID, friend.getId());
            contentValues.put(COLUMN_FRIENDS_FIRST_NAME, friend.getFirstName());
            contentValues.put(COLUMN_FRIENDS_LAST_NAME, friend.getLastName());
            contentValues.put(COLUMN_FRIENDS_PHOTO_100, friend.getPhoto100());
            db.insert(TABLE_FRIENDS, null, contentValues);
        }
        db.close();
        return null;
    }

    public List<Friend> getFriends() {
        List<Friend> friendList = new LinkedList<Friend>();
        SQLiteDatabase db = this.getReadableDatabase();
        Cursor cursor = db.query(TABLE_FRIENDS, new String[]{COLUMN_FRIENDS_ID, COLUMN_FRIENDS_FIRST_NAME, COLUMN_FRIENDS_LAST_NAME, COLUMN_FRIENDS_PHOTO_100}, null, null, null, null, COLUMN_FRIENDS_FIRST_NAME);
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
        return friendList;
    }

    public void refreshTable() {
        SQLiteDatabase db = this.getWritableDatabase();
        db.execSQL(DELETE_TABLE_FRIENDS);
        db.execSQL(CREATE_TABLE_FRIENDS);
        db.close();
    }
}















