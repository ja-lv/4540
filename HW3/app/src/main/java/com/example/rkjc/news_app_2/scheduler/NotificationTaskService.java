package com.example.rkjc.news_app_2.scheduler;

import android.app.IntentService;
import android.app.Notification;
import android.app.NotificationChannel;
import android.app.NotificationManager;
import android.app.PendingIntent;
import android.content.Context;
import android.content.Intent;
import android.content.res.Resources;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.os.Build;
import android.support.v4.app.NotificationCompat;
import android.support.v4.content.ContextCompat;

import com.example.rkjc.news_app_2.R;

public class NotificationTaskService extends IntentService {


    public NotificationTaskService() {
        super("NotificationTaskService");
    }

    @Override
    protected void onHandleIntent(Intent intent) {
        NotificationUtils.notificationReminder(this);
    }

}
