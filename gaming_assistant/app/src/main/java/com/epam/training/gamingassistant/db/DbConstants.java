package com.epam.training.gamingassistant.db;


public class DbConstants {

    public static final String DB_NAME = "greenvk.db";
    public static final int DB_VERSION = 1;

    public static final String TABLE_FRIENDS = "FRIENDS";
    public static final String COLUMN_FRIENDS_ID = "_ID";
    public static final String COLUMN_FRIENDS_FIRST_NAME = "FIRST_NAME";
    public static final String COLUMN_FRIENDS_LAST_NAME = "LAST_NAME";
    public static final String COLUMN_FRIENDS_PHOTO_100 = "PHOTO_100";

    public static final String CREATE_TABLE_FRIENDS = "create table FRIENDS (" + COLUMN_FRIENDS_ID + " integer primary key autoincrement, " + COLUMN_FRIENDS_FIRST_NAME + " text, " + COLUMN_FRIENDS_LAST_NAME + " text, " + COLUMN_FRIENDS_PHOTO_100 + " text)";
    public static final String DELETE_TABLE_FRIENDS = "DROP TABLE FRIENDS";


}
