package com.example.rkjc.news_app_2.scheduler;


import android.arch.lifecycle.ViewModelProviders;
import android.content.Context;
import android.os.AsyncTask;
import android.util.Log;
import android.widget.Toast;

import com.example.rkjc.news_app_2.NewsItemViewModel;
import com.example.rkjc.news_app_2.Repository;
import com.firebase.jobdispatcher.JobParameters;
import com.firebase.jobdispatcher.JobService;

import java.util.List;

public class NotificationFirebaseJobService extends JobService {
    private Repository mRepository;
    private AsyncTask mBackgroundTask;

    @Override
    public boolean onStartJob(final JobParameters jobParameters) {
        Log.d("TEbiggtes", "YYYYYYYYYYYYYYYYYYY Background running......");

        mBackgroundTask = new AsyncTask() {

            @Override
            protected void onPreExecute() {
                mRepository = new Repository(getApplication());
                super.onPreExecute();
            }

            @Override
            protected Object doInBackground(Object[] objects) {
                Log.d("Tbiggtes", "Background running......");
                mRepository.updateAll();

                Context context = NotificationFirebaseJobService.this;
                NotificationUtils.notificationReminder(context);

                return null;
            }

            @Override
            protected void onPostExecute(Object o) {
                Log.d("Ybiggtes", "Background finished......");
                jobFinished(jobParameters, false);
            }
        };

        // COMPLETED (9) Execute the AsyncTask
        mBackgroundTask.execute();
        return true;
    }

    @Override
    public boolean onStopJob(JobParameters jobParameters) {
        // COMPLETED (12) If mBackgroundTask is valid, cancel it
        // COMPLETED (13) Return true to signify the job should be retried
        if (mBackgroundTask != null) mBackgroundTask.cancel(true);
        return true;
    }
}
