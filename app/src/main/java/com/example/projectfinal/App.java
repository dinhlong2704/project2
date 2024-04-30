package com.example.projectfinal;

import android.app.Application;

import androidx.room.Room;

import com.example.projectfinal.db.DatabaseApp;

public class App extends Application {
    private static App Instance;
    private Storage storage;
    private DatabaseApp db;

    @Override
    public void onCreate() {
        super.onCreate();
        Instance = this;
        storage = new Storage();
        initDB();
    }
    private void initDB() {
        db = Room.databaseBuilder(getApplicationContext(), DatabaseApp.class, "dataApp").createFromAsset("db/data.db").build();
    }

    public DatabaseApp getDb() {
        return db;
    }

    public static App getInstance() {
        return Instance;
    }
    public Storage getStorage() {
        return storage;
    }
}
