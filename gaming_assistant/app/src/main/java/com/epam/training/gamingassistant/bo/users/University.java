package com.epam.training.gamingassistant.bo.users;

public class University {
    private int id;

    public University(int id, int country, int city, String name, int faculty, String faculty_name, int chair, String chair_name, int graduation) {
        this.id = id;
        this.country = country;
        this.city = city;
        this.name = name;
        this.faculty = faculty;
        this.faculty_name = faculty_name;
        this.chair = chair;
        this.chair_name = chair_name;
        this.graduation = graduation;
    }

    private int country;

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

    public int getFaculty() {
        return faculty;
    }

    public String getFaculty_name() {
        return faculty_name;
    }

    public int getChair() {
        return chair;
    }

    public String getChair_name() {
        return chair_name;
    }

    public int getGraduation() {
        return graduation;
    }

    private int city;
    private String name;
    private int faculty;
    private String faculty_name;
    private int chair;
    private String chair_name;
    private int graduation;

}
