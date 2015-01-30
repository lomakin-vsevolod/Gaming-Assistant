package com.epam.training.gamingassistant.bo.users;

import java.util.List;


public class User {
    private int id;
    private String first_name;
    private String last_name;
    private String deactivated;
    private int hidden;
    private String photo_id;
    private int verified;
    private int blacklisted;
    private int sex;
    private String bdate;

    private City city;

    private class City {
        private int id;
        private String title;
    }

    private Country country;

    private class Country {
        private int id;
        private String title;
    }

    private String home_town;
    private String photo_50;
    private String photo_100;
    private String photo_200_orig;
    private String photo_200;
    private String photo_400_orig;
    private String photo_max;
    private String photo_max_orig;
    private int online;
    private int online_mobile;
    private int online_app;
    private String lists;
    private String domain;
    private int has_mobile;

    private Contacts contacts;

    private class Contacts {
        private String mobile_phone;
        private String home_phone;
    }

    private String site;
    private int university;
    private String university_name;
    private int faculty;
    private String faculty_name;
    private int graduation;
    private String education_form;
    private String education_status;
    private List<University> universities;
    private List<School> schools;
    private String status;
    //private String status_audio;

    private Last_seen last_seen;

    private class Last_seen {
        private int time;
        private int platform;
    }

    private int followers_count;
    private int common_count;

    private Counters counters;

    private class Counters {
        private int albums;
        private int videos;
        private int audios;
        private int photos;
        private int notes;
        private int friends;
        private int groups;
        private int online_friends;
        private int mutual_friends;
        private int user_videos;
        private int followers;
    }

    private Occupation occupation;

    private class Occupation {
        private String type;
        private int id;
        private String name;
    }

    private String nickname;
    private List<Relative> relatives;
    private int relation;


    private int wall_comments;
    private String activities;
    private String interests;
    private String music;
    private String movies;
    private String tv;
    private String books;
    private String games;
    private String about;
    private String quotes;

    private int can_post;
    private int can_see_all_posts;
    private int can_see_audio;

    private int can_write_private_message;
    private int can_send_friend_request;
    private int is_favorite;
    private int timezone;
    private String screen_name;
    private String maiden_name;

    public int getId() {
        return id;
    }

    public String getFirst_name() {
        return first_name;
    }

    public String getLast_name() {
        return last_name;
    }

    public String getDeactivated() {
        return deactivated;
    }

    public int getHidden() {
        return hidden;
    }

    public String getPhoto_id() {
        return photo_id;
    }

    public int getVerified() {
        return verified;
    }

    public int getBlacklisted() {
        return blacklisted;
    }

    public int getSex() {
        return sex;
    }

    public String getBdate() {
        return bdate;
    }

    public City getCity() {
        return city;
    }

    public Country getCountry() {
        return country;
    }

    public String getHome_town() {
        return home_town;
    }

    public String getPhoto_50() {
        return photo_50;
    }

    public String getPhoto_100() {
        return photo_100;
    }

    public String getPhoto_200_orig() {
        return photo_200_orig;
    }

    public String getPhoto_200() {
        return photo_200;
    }

    public String getPhoto_400_orig() {
        return photo_400_orig;
    }

    public String getPhoto_max() {
        return photo_max;
    }

    public String getPhoto_max_orig() {
        return photo_max_orig;
    }

    public int getOnline() {
        return online;
    }

    public int getOnline_mobile() {
        return online_mobile;
    }

    public int getOnline_app() {
        return online_app;
    }

    public String getLists() {
        return lists;
    }

    public String getDomain() {
        return domain;
    }

    public int getHas_mobile() {
        return has_mobile;
    }

    public Contacts getContacts() {
        return contacts;
    }

    public String getSite() {
        return site;
    }

    public int getUniversity() {
        return university;
    }

    public String getUniversity_name() {
        return university_name;
    }

    public int getFaculty() {
        return faculty;
    }

    public String getFaculty_name() {
        return faculty_name;
    }

    public int getGraduation() {
        return graduation;
    }

    public String getEducation_form() {
        return education_form;
    }

    public String getEducation_status() {
        return education_status;
    }

    public List<University> getUniversities() {
        return universities;
    }

    public List<School> getSchools() {
        return schools;
    }

    public String getStatus() {
        return status;
    }

    // public String getStatus_audio() {
    //    return status_audio;
    // }

    public Last_seen getLast_seen() {
        return last_seen;
    }

    public int getFollowers_count() {
        return followers_count;
    }

    public int getCommon_count() {
        return common_count;
    }

    public Counters getCounters() {
        return counters;
    }

    public Occupation getOccupation() {
        return occupation;
    }

    public String getNickname() {
        return nickname;
    }

    public List<Relative> getRelatives() {
        return relatives;
    }

    public int getRelation() {
        return relation;
    }

    public int getWall_comments() {
        return wall_comments;
    }

    public String getActivities() {
        return activities;
    }

    public String getInterests() {
        return interests;
    }

    public String getMusic() {
        return music;
    }

    public String getMovies() {
        return movies;
    }

    public String getTv() {
        return tv;
    }

    public String getBooks() {
        return books;
    }

    public String getGames() {
        return games;
    }

    public String getAbout() {
        return about;
    }

    public String getQuotes() {
        return quotes;
    }

    public int getCan_post() {
        return can_post;
    }

    public int getCan_see_all_posts() {
        return can_see_all_posts;
    }

    public int getCan_see_audio() {
        return can_see_audio;
    }

    public int getCan_write_private_message() {
        return can_write_private_message;
    }

    public int getCan_send_friend_request() {
        return can_send_friend_request;
    }

    public int getIs_favorite() {
        return is_favorite;
    }

    public int getTimezone() {
        return timezone;
    }

    public String getScreen_name() {
        return screen_name;
    }

    public String getMaiden_name() {
        return maiden_name;
    }

    public User(int id, String first_name, String last_name, String deactivated, int hidden, String photo_id, int verified, int blacklisted, int sex, String bdate, City city, Country country, String home_town, String photo_50, String photo_100, String photo_200_orig, String photo_200, String photo_400_orig, String photo_max, String photo_max_orig, int online, int online_mobile, int online_app, String lists, String domain, int has_mobile, Contacts contacts, String site, int university, String university_name, int faculty, String faculty_name, int graduation, String education_form, String education_status, List<University> universities, List<School> schools, String status, String status_audio, Last_seen last_seen, int followers_count, int common_count, Counters counters, Occupation occupation, String nickname, List<Relative> relatives, int relation, int wall_comments, String activities, String interests, String music, String movies, String tv, String books, String games, String about, String quotes, int can_post, int can_see_all_posts, int can_see_audio, int can_write_private_message, int can_send_friend_request, int is_favorite, int timezone, String screen_name, String maiden_name) {
        this.id = id;
        this.first_name = first_name;
        this.last_name = last_name;
        this.deactivated = deactivated;
        this.hidden = hidden;
        this.photo_id = photo_id;
        this.verified = verified;
        this.blacklisted = blacklisted;
        this.sex = sex;
        this.bdate = bdate;
        this.city = city;
        this.country = country;
        this.home_town = home_town;
        this.photo_50 = photo_50;
        this.photo_100 = photo_100;
        this.photo_200_orig = photo_200_orig;
        this.photo_200 = photo_200;
        this.photo_400_orig = photo_400_orig;
        this.photo_max = photo_max;
        this.photo_max_orig = photo_max_orig;
        this.online = online;
        this.online_mobile = online_mobile;
        this.online_app = online_app;
        this.lists = lists;
        this.domain = domain;
        this.has_mobile = has_mobile;
        this.contacts = contacts;
        this.site = site;
        this.university = university;
        this.university_name = university_name;
        this.faculty = faculty;
        this.faculty_name = faculty_name;
        this.graduation = graduation;
        this.education_form = education_form;
        this.education_status = education_status;
        this.universities = universities;
        this.schools = schools;
        this.status = status;
        //this.status_audio = status_audio;
        this.last_seen = last_seen;
        this.followers_count = followers_count;
        this.common_count = common_count;
        this.counters = counters;
        this.occupation = occupation;
        this.nickname = nickname;
        this.relatives = relatives;
        this.relation = relation;
        this.wall_comments = wall_comments;
        this.activities = activities;
        this.interests = interests;
        this.music = music;
        this.movies = movies;
        this.tv = tv;
        this.books = books;
        this.games = games;
        this.about = about;
        this.quotes = quotes;
        this.can_post = can_post;
        this.can_see_all_posts = can_see_all_posts;
        this.can_see_audio = can_see_audio;
        this.can_write_private_message = can_write_private_message;
        this.can_send_friend_request = can_send_friend_request;
        this.is_favorite = is_favorite;
        this.timezone = timezone;
        this.screen_name = screen_name;
        this.maiden_name = maiden_name;
    }
/*connections
    exports
     private Personal personal;
    private class Personal {
        private int political;
        private List<String> langs;
        private String religion;
        private String inspired_by;
        private int people_main;
        private int life_main;
        private int smoking;
        private int alcohol;
    }*/
}
