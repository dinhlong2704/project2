package com.example.projectfinal.model;

public class Hospital {
    public final String name;
    public final String desc;

    public final String linkPhoto;
    public final double lat;
    public final double lgn;

    public Hospital(String name,  String linkPhoto,String desc,double lat,double lgn) {
        this.name = name;
        this.desc = desc;
        this.linkPhoto = linkPhoto;
        this.lat = lat;
        this.lgn = lgn;
    }

    @Override
    public String toString() {
        return "Hospital{" +
                "name='" + name + '\'' +
                ", desc='" + desc + '\'' +
                ", linkPhoto='" + linkPhoto + '\'' +
                '}';
    }
}
