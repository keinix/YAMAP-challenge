package io.keinix.yamapchallenge.utils;

import android.accounts.NetworkErrorException;
import android.content.Context;
import android.net.ConnectivityManager;
import android.net.NetworkInfo;

import io.keinix.yamapchallenge.R;

public final class NetworkConnection {

    public static boolean isConnected(Context context) throws NetworkErrorException {
        ConnectivityManager cm =
                (ConnectivityManager)context.getSystemService(Context.CONNECTIVITY_SERVICE);
        if (cm != null) {
            NetworkInfo activeNetwork = cm.getActiveNetworkInfo();
            return activeNetwork != null && activeNetwork.isConnectedOrConnecting();
        }
        throw new NetworkErrorException(context.getString(R.string.exception_connectivity_manager_null));
    }
}
