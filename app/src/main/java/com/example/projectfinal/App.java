package com.example.projectfinal;

import android.app.Application;

import androidx.room.Room;

import com.example.projectfinal.db.DatabaseApp;

public class App extends Application {
    private static App Instance;
    //private Message message;
    private Storage storage;
    private DatabaseApp db;

    public static App getInstance() {
        return Instance;
    }

    @Override
    public void onCreate() {
        super.onCreate();
        Instance = this;
        //message= new Message(message.msg);
        storage = new Storage();
        initDB();
    }

    private void initDB() {
      db = Room.databaseBuilder(getApplicationContext(), DatabaseApp.class, "Account.db")
              .createFromAsset("db/Account.db")
              .build();
    }

    public DatabaseApp getDb() {
        return db;
    }

    public Storage getStorage() {
        return storage;
    }

//    public Message getMessage() {
//        return message;
//    }
}
