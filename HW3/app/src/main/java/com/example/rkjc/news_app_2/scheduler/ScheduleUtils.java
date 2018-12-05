package com.example.rkjc.news_app_2.scheduler;

import android.content.Context;
import android.support.annotation.NonNull;
import android.util.Log;

import com.firebase.jobdispatcher.Constraint;
import com.firebase.jobdispatcher.Driver;
import com.firebase.jobdispatcher.FirebaseJobDispatcher;
import com.firebase.jobdispatcher.GooglePlayDriver;
import com.firebase.jobdispatcher.Job;
import com.firebase.jobdispatcher.Lifetime;
import com.firebase.jobdispatcher.Trigger;

//.setTrigger(Trigger.executionWindow(
//        REMINDER_INTERVAL_SECONDS,
//        REMINDER_INTERVAL_SECONDS + SYNC_FLEXTIME_SECONDS))

public class ScheduleUtils {
    private static final int REMINDER_INTERVAL_SECONDS = 3;
    private static final int SYNC_FLEXTIME_SECONDS = REMINDER_INTERVAL_SECONDS;

    private static final String REMINDER_JOB_TAG = "hydration_reminder_tag";
    private static boolean sInitialized;

    synchronized public static void scheduleUpdates(@NonNull final Context context) {
        if (sInitialized) return;
        Log.d("EREbiggtes", "EEEEEEEEEEEEEEEEEEEEEEEEEEE Background running......");

        Driver driver = new GooglePlayDriver(context);
        FirebaseJobDispatcher dispatcher = new FirebaseJobDispatcher(driver);
        Job constraintReminderJob = dispatcher.newJobBuilder()
                .setService(NotificationFirebaseJobService.class)
                .setTag(REMINDER_JOB_TAG)
                .setConstraints(Constraint.ON_ANY_NETWORK)
                .setLifetime(Lifetime.UNTIL_NEXT_BOOT)
                .setRecurring(true)
                .setTrigger(Trigger.executionWindow(1, 10))
                .setReplaceCurrent(true)
                .build();
        dispatcher.schedule(constraintReminderJob);
        sInitialized = true;
    }
}
