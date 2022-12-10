package com.example.caloriecheck.Activitiy;

import android.content.Context;
import android.content.SharedPreferences;

/**
 * Created by Paito Anderson on 2014-04-26.
 */
public class Preferences {

    public final static String TODAY = "today";

    // Identify Shared Preference Store
    public final static String PREFS_NAME = "stepcounter_prefs";

    public static void setTimeToday(Context context ,String value) {
        SharedPreferences prefs = context.getSharedPreferences(PREFS_NAME, 0);
        SharedPreferences.Editor prefsEditor = prefs.edit();
        prefsEditor.putString(TODAY,value);
        prefsEditor.apply();
    }

    public static String getTimeToday(Context context) {
        SharedPreferences prefs = context.getSharedPreferences(PREFS_NAME, 0);
        return prefs.getString(TODAY,"");
    }

    // Should the Step Counting Service be running?
    public static boolean shouldServiceRun(Context context) {
        SharedPreferences prefs = context.getSharedPreferences(PREFS_NAME, 0);
        return prefs.getBoolean("serviceRunning", false);
    }

    // Should the Step Counting Service be running?
    public static void setServiceRun(Context context, boolean running) {
        SharedPreferences prefs = context.getSharedPreferences(PREFS_NAME, 0);
        SharedPreferences.Editor prefsEditor = prefs.edit();
        prefsEditor.putBoolean("serviceRunning", running);
        prefsEditor.apply();
    }

    // How many steps have I walked?
    public static String getStepCount(Context context) {
        SharedPreferences prefs = context.getSharedPreferences(PREFS_NAME, 0);
        return String.format("%,d", (prefs.getInt("stepCount", 0) - prefs.getInt("stepCountSubtract", 0)));
    }

    // Set how many steps I have walked.
    public static void setStepCount(Context context, Integer steps) {
        SharedPreferences prefs = context.getSharedPreferences(PREFS_NAME, 0);
        SharedPreferences.Editor prefsEditor = prefs.edit();
        prefsEditor.putInt("stepCount", steps);
        prefsEditor.apply();
    }

    // Set Subtract Step Count (Reset)
    public static void resetStepCount(Context context) {
        SharedPreferences prefs = context.getSharedPreferences(PREFS_NAME, 0);
        SharedPreferences.Editor prefsEditor = prefs.edit();
        prefsEditor.putInt("stepCountSubtract", prefs.getInt("stepCount", 0));
        prefsEditor.apply();
    }

    // Reset the Subtract Step Count (On Boot)
    public static void clearStepCount(Context context) {
        SharedPreferences prefs = context.getSharedPreferences(PREFS_NAME, 0);
        SharedPreferences.Editor prefsEditor = prefs.edit();
        prefsEditor.putInt("stepCountSubtract", 0);
        prefsEditor.putInt("stepCount", 0);
        prefsEditor.apply();
    }
}