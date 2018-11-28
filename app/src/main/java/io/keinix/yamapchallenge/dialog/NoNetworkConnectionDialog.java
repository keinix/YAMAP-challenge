package io.keinix.yamapchallenge.dialog;

import android.app.AlertDialog;
import android.content.Context;
import android.content.Intent;

import androidx.appcompat.app.AppCompatActivity;
import io.keinix.yamapchallenge.R;

public final class NoNetworkConnectionDialog {

    public static final int REQUEST_CODE_NETWORK_SETTINGS = 102;

    public static void show(Context context) {
        AlertDialog.Builder builder = new AlertDialog.Builder(context);
        AlertDialog dialog = builder
                .setTitle(R.string.dialog_no_network_title)
                .setMessage(R.string.dialog_no_network_message)
                .setPositiveButton(R.string.dialog_no_network_positive,
                        (dialogInterface, i) -> {})
                .setNegativeButton(R.string.dialog_no_network_negative,
                        (dialogInterface, i) -> sendUserToSettings(context))
                .create();
        dialog.show();
    }

    private static void sendUserToSettings(Context context) {
        if (context instanceof AppCompatActivity) {
            ((AppCompatActivity) context).startActivityForResult(new Intent(android.provider.Settings.ACTION_SETTINGS),
                    REQUEST_CODE_NETWORK_SETTINGS);
        }
    }
}
