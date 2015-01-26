package com.epam.training.gamingassistant.api;

/**
 * Created by NuclearOK on 23.01.2015.
 */
public class VkApi {
    private static final String METHOD_URL ="https://api.vk.com/method/";
    private static final String VERSION = "v=5.27";
    private static final String ORDER = "order=";
    private static final String FIELDS = "fields=";
    private static final String FILTERS = "filters=";
    private static final String COUNT = "count=";
    private static final String ACCESS_TOKEN = "access_token=";


    private static final String METHOD_FRIENDS_GET = METHOD_URL+"friends.get?";
    private static final String ORDER_FRIENDS_GET_VALUE = ORDER + "name";
    private static final String FIELDS_FRIENDS_GET_VALUE = FIELDS + "domain,photo_100";
    public static final String FRIENDS_GET_URI = METHOD_FRIENDS_GET+VERSION+"&"+ORDER_FRIENDS_GET_VALUE+"&"+FIELDS_FRIENDS_GET_VALUE+"&"+ACCESS_TOKEN;

    private static final String METHOD_NEWS_FEED_GET =METHOD_URL+"newsfeed.get?";
    private static final String FILTERS_NEWS_FEED_GET_VALUE = FILTERS + "post";
    private static final String COUNT_NEWS_FEED_GET_VALUE = COUNT + "5";
    public static final String NEWS_FEED_GET_URI = METHOD_NEWS_FEED_GET +VERSION+"&"+ FILTERS_NEWS_FEED_GET_VALUE +"&"+ COUNT_NEWS_FEED_GET_VALUE +"&"+ACCESS_TOKEN;



}
