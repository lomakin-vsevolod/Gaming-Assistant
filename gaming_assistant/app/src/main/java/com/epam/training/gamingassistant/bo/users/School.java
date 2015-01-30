package com.epam.training.gamingassistant.bo.users;


public class School {
    public School(int id, int country, int city, String name, int year_from, int year_to, int year_graduated, String speciality, int type, String type_str) {
        this.id = id;
        this.country = country;
        this.city = city;
        this.name = name;
        this.year_from = year_from;
        this.year_to = year_to;
        this.year_graduated = year_graduated;
        this.speciality = speciality;
        this.type = type;
        this.type_str = type_str;
    }

    private int id;
    private int country;
    private int city;
    private String name;
    private int year_from;
    private int year_to;
    private int year_graduated;

    public int getId() {
        return id;
    }

    public int getCountry() {
        return country;
    }

    public int getCity() {
        return city;
    }

    public String getName() {
        return name;
    }

    public int getYear_from() {
        return year_from;
    }

    public int getYear_to() {
        return year_to;
    }

    public int getYear_graduated() {
        return year_graduated;
    }

    public String getSpeciality() {
        return speciality;
    }

    public int getType() {
        return type;
    }

    public String getType_str() {
        return type_str;
    }

    private String speciality;
    private int type;
    private String type_str;

    //private String class;
}
