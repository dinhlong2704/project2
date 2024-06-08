package com.example.projectfinal.service;

import android.app.Notification;
import android.app.NotificationChannel;
import android.app.NotificationManager;
import android.app.PendingIntent;
import android.app.Service;
import android.content.Intent;
import android.os.Build;
import android.os.Handler;
import android.os.IBinder;
import android.os.Message;
import android.widget.RemoteViews;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.core.app.NotificationCompat;

import com.example.projectfinal.R;
import com.example.projectfinal.model.Song;
import com.example.projectfinal.view.act.media.Mp3Player;

public class MediaService extends Service {
    private static final String CHANNEL_ID = "m4u_channel";
    private static final String KEY_EVENT = "KEY_EVENT";
    private static final String PLAY_EVENT = "PLAY_EVENT";
    private static final String NEXT_EVENT = "NEXT_EVENT";
    private static final String BACK_EVENT = "BACK_EVENT";
    RemoteViews views;
    private Song song;
    private boolean appRunning = false;
    private Notification notify;


    @Nullable
    @Override
    public IBinder onBind(Intent intent) {
        return null;
    }


    @Override
    public void onCreate() {
        super.onCreate();
        initService();
    }

    private void initService() {
        song = Mp3Player.getInstance().getCurrentSong();
        createNotificationChannel();

        NotificationCompat.Builder builder = new NotificationCompat.Builder(this, CHANNEL_ID);
        views = new RemoteViews(getPackageName(), R.layout.item_notify_media);
        views.setTextViewText(R.id.tv_name, song.title);
        views.setTextViewText(R.id.tv_album, song.album);

        Intent intentPlay = new Intent(this, MediaService.class);
        intentPlay.putExtra(KEY_EVENT, PLAY_EVENT);
        PendingIntent piPlay = PendingIntent.getService(this, 105, intentPlay, PendingIntent.FLAG_UPDATE_CURRENT);
        views.setOnClickPendingIntent(R.id.im_play, piPlay);

        Intent intentBack = new Intent(this, MediaService.class);
        intentBack.putExtra(KEY_EVENT, BACK_EVENT);
        PendingIntent piBack = PendingIntent.getService(this, 106, intentBack, PendingIntent.FLAG_UPDATE_CURRENT);
        views.setOnClickPendingIntent(R.id.im_back, piBack);

        Intent intentNext = new Intent(this, MediaService.class);
        intentNext.putExtra(KEY_EVENT, NEXT_EVENT);
        PendingIntent piNext = PendingIntent.getService(this, 107, intentNext, PendingIntent.FLAG_UPDATE_CURRENT);
        views.setOnClickPendingIntent(R.id.im_next, piNext);

        appRunning = true;
        new Thread(() -> updateSeekBar()).start();

        builder.setSmallIcon(R.id.im_music);
        builder.setAutoCancel(false);
        builder.setOngoing(true);
        builder.setOnlyAlertOnce(true);
        builder.setChannelId(CHANNEL_ID);
        builder.setCustomBigContentView(views);
        notify = builder.build();
        startForeground(1001, notify);
    }

    private Handler handler = new Handler(new Handler.Callback() {
        @Override
        public boolean handleMessage(@NonNull Message msg) {
            song = Mp3Player.getInstance().getCurrentSong();
            views.setTextViewText(R.id.tv_name, song.title);
            views.setTextViewText(R.id.tv_album, song.album);

            String currentTimeText = Mp3Player.getInstance().getCurrentTimeText();
            String totalTimeText = Mp3Player.getInstance().getTotalTimeText();
            int currentTime = Mp3Player.getInstance().getCurrentTime();
            int totalTime = Mp3Player.getInstance().getTotalTime();

            views.setProgressBar(R.id.sb_time, totalTime, currentTime, false);
            views.setTextViewText(R.id.tv_duration, String.format("%s/%s", currentTimeText, totalTimeText));
            if (Mp3Player.getInstance().getState() == Mp3Player.STATE_PLAYING) {
                views.setImageViewResource(R.id.im_play, R.drawable.bt_pause);
            } else {
                views.setImageViewResource(R.id.im_play, R.drawable.bt_play);
            }
            //update notify again
            startForeground(1001, notify);
            return false;
        }
    });

    private void updateSeekBar() {
        while (appRunning) {
            try {
                Thread.sleep(500);
                Message.obtain(handler).sendToTarget();
            } catch (InterruptedException e) {
                throw new RuntimeException(e);
            }
        }
    }

    private void createNotificationChannel() {
        String description = "Enjoy music";
        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.O) {
            NotificationChannel channel;
            int importance = NotificationManager.IMPORTANCE_DEFAULT;
            channel = new NotificationChannel(CHANNEL_ID, CHANNEL_ID, importance);
            channel.setDescription(description);
            NotificationManager notificationManager = getSystemService(NotificationManager.class);
            notificationManager.createNotificationChannel(channel);
        }
    }

    @Override
    public int onStartCommand(Intent intent, int flags, int startId) {
        if (intent != null) {
            String key = intent.getStringExtra(KEY_EVENT);
            if (key != null && key.equals(PLAY_EVENT)) {
                Mp3Player.getInstance().play();
                Message.obtain(handler).sendToTarget();
            } else if (key != null && key.equals(BACK_EVENT)) {
                Mp3Player.getInstance().back();
                Message.obtain(handler).sendToTarget();
            } else if (key != null && key.equals(NEXT_EVENT)) {
                Mp3Player.getInstance().next();
                Message.obtain(handler).sendToTarget();
            }
        }
        return super.onStartCommand(intent, flags, startId);
    }

    @Override
    public void onDestroy() {
        appRunning = false;
        stopForeground(true);
        super.onDestroy();
    }
}
