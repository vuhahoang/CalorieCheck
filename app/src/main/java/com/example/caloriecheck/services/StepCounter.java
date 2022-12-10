package com.example.caloriecheck.services;


import android.app.Notification;
import android.app.NotificationManager;

import android.app.Service;
import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.hardware.Sensor;
import android.hardware.SensorEvent;
import android.hardware.SensorEventListener;
import android.hardware.SensorManager;
import android.os.IBinder;
import android.util.Log;
import android.widget.Toast;

import androidx.core.app.NotificationCompat;

import com.example.caloriecheck.Activitiy.Pedometer;
import com.example.caloriecheck.Activitiy.Preferences;
import com.example.caloriecheck.R;

import java.util.logging.Logger;


public class StepCounter extends Service implements SensorEventListener {

    // Notifications
    private static final Integer NOTIFICATION_ID = 7837;
    private Notification.Builder mBuilder;
    private NotificationManager mNotificationManager;

    @Override
    public int onStartCommand(Intent intent, int flags, int startId) {
        super.onStartCommand(intent, flags, startId);

        // Get Notification Manager
        mBuilder = new Notification.Builder(this);
        mNotificationManager = (NotificationManager) getSystemService(Context.NOTIFICATION_SERVICE);

        // Setup Step Counter
        SensorManager sensorManager = (SensorManager) getSystemService(Context.SENSOR_SERVICE);
        Sensor countSensor = sensorManager.getDefaultSensor(Sensor.TYPE_STEP_COUNTER);
        if (countSensor != null) {
            Toast.makeText(this, "Started Counting Steps", Toast.LENGTH_LONG).show();
            sensorManager.registerListener(this, countSensor, SensorManager.SENSOR_DELAY_UI);
        } else {
            Toast.makeText(this, "Device not Compatible!", Toast.LENGTH_LONG).show();
            this.stopSelf();
        }

        // Setup First Notification

        // Setup Shared Preference Change Listener
        SharedPreferences sharedPreferences = getSharedPreferences("stepcounter_prefs", MODE_PRIVATE);
        SharedPreferences.OnSharedPreferenceChangeListener sharedPreferenceChangeListener = new SharedPreferences.OnSharedPreferenceChangeListener() {
            @Override
            public void onSharedPreferenceChanged(SharedPreferences prefs, String key) {
                // Update Notification Bar
                Log.e("dungkm","cái gì đó thay đổi");

            }
        };
        sharedPreferences.registerOnSharedPreferenceChangeListener(sharedPreferenceChangeListener);

        // Restart the service if its killed
        return START_STICKY;
    }

    @Override
    public void onSensorChanged(SensorEvent event) {
        Log.e("dungkm","senso chenage");
        // Record Step Count

        Preferences.setStepCount(this, (int) event.values[0]);

        if (Pedometer.getInstance() != null) {
            Pedometer.getInstance().setTextPredo();
        }
    }

    @Override
    public void onAccuracyChanged(Sensor sensor, int accuracy) {
    }

    @Override
    public IBinder onBind(Intent intent) {
        return null;
    }


}
