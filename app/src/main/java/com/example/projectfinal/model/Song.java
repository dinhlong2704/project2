package com.example.projectfinal.model;

import android.net.Uri;

public class Song {
    public String title,path,album,artist;
    public Uri cover;

    public Song(String title, String path, String album, String artist,Uri cover) {
        this.title = title;
        this.path = path;
        this.album = album;
        this.artist = artist;
        this.cover = cover;

    }
}
