package com.epam.training.gamingassistant.api;

public class VkApiConstants {
    private static final String METHOD_URL = "https://api.vk.com/method/";
    private static final String VERSION = "v=5.28";
    private static final String ORDER = "order=";
    private static final String FIELDS = "fields=";
    private static final String FILTERS = "filters=";
    private static final String COUNT = "count=";
    private static final String ACCESS_TOKEN = "access_token=";
    private static final String EXTENDED = "extended=";
    private static final String MESSAGE = "message=";

    private static final String METHOD_USERS_GET = METHOD_URL + "users.get?";
    private static final String FIELDS_USERS_GET_VALUE = FIELDS + "sex,bdate,city,country,photo_50,photo_100,photo_200_orig,photo_200,photo_400_orig,photo_max,photo_max_orig,photo_id,online,online_mobile,domain,has_mobile,contacts,connections,site,education,universities,schools,can_post,can_see_all_posts,can_see_audio,can_write_private_message,status,last_seen,common_count,relation,relatives,counters,screen_name,maiden_name,timezone,occupation,activities,interests,music,movies,tv,books,games,about,quotes,personal";
    public static final String USERS_GET_URI = METHOD_USERS_GET + VERSION + "&" + FIELDS_USERS_GET_VALUE + "&" + ACCESS_TOKEN;

    private static final String METHOD_WALL_GET = METHOD_URL + "wall.get?";
    private static final String FILTERS_WALL_GET_VALUE = FILTERS + "all";
    private static final String EXTENDED_WALL_GET_VALUE = EXTENDED + "1";
    public static final String WALL_GET_URI = METHOD_WALL_GET + VERSION + "&" + FILTERS_WALL_GET_VALUE + "&" + EXTENDED_WALL_GET_VALUE + "&" + ACCESS_TOKEN;

    private static final String METHOD_WALL_POST = METHOD_URL + "wall.post?";
    public static final String WALL_POST_URI = METHOD_WALL_POST + VERSION + "&" + ACCESS_TOKEN;

    private static final String METHOD_FRIENDS_GET = METHOD_URL + "friends.get?";
    private static final String ORDER_FRIENDS_GET_VALUE = ORDER + "name";
    private static final String FIELDS_FRIENDS_GET_VALUE = FIELDS + "domain,photo_100";
    public static final String FRIENDS_GET_URI = METHOD_FRIENDS_GET + VERSION + "&" + ORDER_FRIENDS_GET_VALUE + "&" + FIELDS_FRIENDS_GET_VALUE + "&" + ACCESS_TOKEN;

    private static final String METHOD_NEWS_FEED_GET = METHOD_URL + "newsfeed.get?";
    private static final String FILTERS_NEWS_FEED_GET_VALUE = FILTERS + "post";
    private static final String COUNT_NEWS_FEED_GET_VALUE = COUNT + "10";
    public static final String NEWS_FEED_GET_URI = METHOD_NEWS_FEED_GET + VERSION + "&" + FILTERS_NEWS_FEED_GET_VALUE + "&" + COUNT_NEWS_FEED_GET_VALUE + "&" + ACCESS_TOKEN;


}
