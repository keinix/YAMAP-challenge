package io.keinix.yamapchallenge.dialog;

import android.content.Context;

import androidx.appcompat.app.AlertDialog;
import androidx.appcompat.app.AppCompatActivity;
import io.keinix.yamapchallenge.R;

public final class ServerErrorDialog {

        /**
         * Shows an {@link AlertDialog} notifying the user that there was a
         * Server Problem. Negative option takes user to Android settings menu
         * @param context of {@link AppCompatActivity}
         */
        public static void show(Context context) {
            AlertDialog.Builder builder = new AlertDialog.Builder(context);
            AlertDialog dialog = builder
                    .setTitle(context.getString(R.string.dialog_server_error_title))
                    .setMessage(context.getString(R.string.dialog_server_error_message))
                    .setPositiveButton(R.string.dialog_no_network_positive,
                            (dialogInterface, i) -> {})
                    .create();
            dialog.show();
        }
}