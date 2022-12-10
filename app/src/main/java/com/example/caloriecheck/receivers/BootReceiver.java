package com.example.caloriecheck.receivers;

import android.content.BroadcastReceiver;
import android.content.Context;
import android.content.Intent;

import com.example.caloriecheck.Activitiy.Preferences;
import com.example.caloriecheck.services.StepCounter;


/**
 * Created by Paito Anderson on 2014-04-27.
 */
public class BootReceiver extends BroadcastReceiver {
    @Override
    public void onReceive(Context context, Intent intent) {

        // Clear Steps
        Preferences.clearStepCount(context);

        // Check if the Step Counter service was running?
        if (Preferences.shouldServiceRun(context))
        {
            // Start Step Counter service
            Intent myIntent = new Intent(context, StepCounter.class);
            context.startService(myIntent);
        }
    }
}
