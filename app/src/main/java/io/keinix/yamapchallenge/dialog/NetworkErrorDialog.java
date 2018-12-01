package io.keinix.yamapchallenge.dialog;

import android.content.Context;

import androidx.appcompat.app.AlertDialog;
import androidx.appcompat.app.AppCompatActivity;
import io.keinix.yamapchallenge.R;
import io.keinix.yamapchallenge.utils.LaunchAndroidSettings;

public final class NetworkErrorDialog {

    /**
     * Shows an {@link AlertDialog} notifying the user that there is no
     * Network Connection. Negative option takes user to Android settings menu
     * @param context of {@link AppCompatActivity}
     */
    public static void show(Context context) {
        AlertDialog.Builder builder = new AlertDialog.Builder(context);
        AlertDialog dialog = builder
                .setTitle(R.string.dialog_no_network_title)
                .setMessage(R.string.dialog_no_network_message)
                .setPositiveButton(R.string.dialog_no_network_positive,
                        (dialogInterface, i) -> {})
                .setNegativeButton(R.string.dialog_no_network_negative,
                        (dialogInterface, i) -> LaunchAndroidSettings.launch(context))
                .create();
        dialog.show();
    }

    /**
     * Use this method if the current state of the AlertDialog needs to be monitored
     * @param context of {@link AppCompatActivity}
     * @return an {@link AlertDialog} notifying user of network error
     */
    public static AlertDialog getAlertDialog(Context context) {
        AlertDialog.Builder builder = new AlertDialog.Builder(context);
        return builder.setTitle(R.string.dialog_no_network_title)
                .setMessage(R.string.dialog_no_network_message)
                .setPositiveButton(R.string.dialog_no_network_positive,
                        (dialogInterface, i) -> {})
                .setNegativeButton(R.string.dialog_no_network_negative,
                        (dialogInterface, i) -> LaunchAndroidSettings.launch(context))
                .create();
    }
}