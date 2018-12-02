package io.keinix.yamapchallenge.data.source.remote;

import android.app.AlertDialog;
import android.content.Context;
import android.util.Log;

import androidx.lifecycle.LifecycleOwner;
import androidx.lifecycle.LiveData;
import androidx.lifecycle.Observer;
import io.keinix.yamapchallenge.dialog.NetworkErrorDialog;
import io.keinix.yamapchallenge.dialog.ServerErrorDialog;

public class NetworkErrorListener {

    private LiveData<NetworkError> mErrorLiveData;
    private LifecycleOwner mLifecycleOwner;
    private Context mContext;

    // ----------------------Public----------------------

    /**
     * Used to prompt the user with network error dialogs.
     * @param context for launching {@link AlertDialog}
     * @param lifecycleOwner for monitoring the errorLiveData
     * @param errorLiveData where network error are posted
     */
    public NetworkErrorListener(Context context, LifecycleOwner lifecycleOwner,
                                LiveData<NetworkError> errorLiveData) {
        mLifecycleOwner = lifecycleOwner;
        mContext = context;
        mErrorLiveData = errorLiveData;
    }

    /**
     * Listens for any network error and shows the correct {@link AlertDialog}
     */
    public void start() {
        mErrorLiveData.observe(mLifecycleOwner, networkError -> {
            switch (networkError) {
                case SERVER_ERROR:
                    handelServerError();
                    break;
                case GENERAL_NETWORK_ERROR:
                    handelGeneralNetworkError();
                    break;
            }
        });
    }

    /**
     * Optional method: add and extra observer that triggers when a network
     * error is posted. For use of adding additional interactions with a
     * specific activity / UI.
     * @param observer to add extra functionally when a network error is posted
     */
    public void addObserver(Observer<NetworkError> observer) {
        mErrorLiveData.observe(mLifecycleOwner, observer);
    }

    // ----------------------Private----------------------

    /**
     * shows {@link AlertDialog}  in the event of
     * HTTP Server errors & Cloudflare errors
     */
    private void handelServerError() {
        ServerErrorDialog.show(mContext);
    }

    /**
     * shows {@link AlertDialog} when the user is connected to
     * a network but does not have access to the internet
     */
    private void handelGeneralNetworkError() {
        NetworkErrorDialog.show(mContext);
    }
}