package io.keinix.yamapchallenge.utils;

import android.accounts.NetworkErrorException;
import android.content.Context;
import android.net.ConnectivityManager;
import android.net.NetworkInfo;

import io.keinix.yamapchallenge.R;

public final class NetworkConnection {

    // This checks if user is connected to a network. It is also possible that the
    // user is connected to a network that does not have access to the internet,
    // that case is handled in DiaryRepository.
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
