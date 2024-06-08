package com.example.projectfinal;

import android.app.Activity;
import android.content.Context;
import android.database.sqlite.SQLiteDatabase;

import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStream;

public class Database {
    public static SQLiteDatabase initDatabase(Activity activity, String databaseName) {
        try {
            String outFileName = activity.getApplicationInfo().dataDir + "/databases/" + databaseName;
            File f = new File(outFileName);
            if (!f.exists()) {
                InputStream myInput = activity.getAssets().open(databaseName);
                File fodel = new File(activity.getApplicationInfo().dataDir + "/databases/");
                if (!fodel.exists()) {
                    fodel.mkdir();
                }


                FileOutputStream myOnput = new FileOutputStream(outFileName);
                byte[] buffer = new byte[1024];
                int length;

                while ((length = myInput.read(buffer)) > 0) {
                    myOnput.write(buffer, 0, length);
                }
                myOnput.flush();
                myOnput.close();
                myInput.close();
            }

        } catch (IOException e) {
            e.printStackTrace();
        }
        return activity.openOrCreateDatabase(databaseName, Context.MODE_PRIVATE,null);
    }
}