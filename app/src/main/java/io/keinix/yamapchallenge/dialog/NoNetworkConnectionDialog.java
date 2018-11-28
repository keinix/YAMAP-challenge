package io.keinix.yamapchallenge.dialog;

import android.app.AlertDialog;
import android.content.Context;
import android.content.Intent;

import io.keinix.yamapchallenge.R;

public class NoNetworkConnectionDialog {

    public void show(Context context) {
        AlertDialog.Builder builder = new AlertDialog.Builder(context);
        AlertDialog dialog = builder
                .setPositiveButton(R.string.dialog_no_network_positive, (dialogInterface, i) -> {})
                .setNegativeButton(R.string.dialog_no_network_negative, (dialogInterface, i) -> sendUserToSettings(context))
                .create();
        dialog.show();
    }

    private void sendUserToSettings(Context context) {
        context.startActivity(new Intent(android.provider.Settings.ACTION_SETTINGS));
    }
}
