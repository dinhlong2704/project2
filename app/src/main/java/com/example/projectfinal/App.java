package com.example.projectfinal;

import android.app.Application;

import androidx.room.Room;

import com.example.projectfinal.db.DatabaseApp;

public class App extends Application {
    private static App Instance;
    private Storage storage;
    private DatabaseApp db;

    public static App getInstance() {
        return Instance;
    }

    @Override
    public void onCreate() {
        super.onCreate();
        Instance = this;
        storage = new Storage();
        initDB();
    }

    private void initDB() {
      db = Room.databaseBuilder(getApplicationContext(), DatabaseApp.class, "dataApp").build();
    }
//.createFromAsset("db/data.db")
    public DatabaseApp getDb() {
        return db;
    }

    public Storage getStorage() {
        return storage;
    }
}
