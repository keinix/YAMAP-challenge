package io.keinix.yamapchallenge.utils;

import android.content.Context;
import android.content.Intent;

import androidx.appcompat.app.AppCompatActivity;

public final class LaunchAndroidSettings {

    public static final int REQUEST_CODE_NETWORK_SETTINGS = 102;

    /**
     * Launchs the Android settings menu
     * @param context of {@link AppCompatActivity}
     */
    public static void launch(Context context) {
        if (context instanceof AppCompatActivity) {
            ((AppCompatActivity) context).startActivityForResult(new Intent(android.provider.Settings.ACTION_SETTINGS),
                    REQUEST_CODE_NETWORK_SETTINGS);
        }
    }
}
