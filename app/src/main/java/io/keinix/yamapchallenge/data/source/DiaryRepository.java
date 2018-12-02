package io.keinix.yamapchallenge.data.source;

import android.util.Log;

import java.util.List;

import androidx.lifecycle.LiveData;
import androidx.lifecycle.MutableLiveData;
import io.keinix.yamapchallenge.data.Diary;
import io.keinix.yamapchallenge.data.source.remote.NetworkError;
import io.keinix.yamapchallenge.data.source.remote.RetrofitApiHelper;
import io.keinix.yamapchallenge.data.source.remote.YamapService;
import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

/**
 * Handles retrieving {@link Diary} data
 */
public class DiaryRepository {

    private static final String TAG = DiaryRepository.class.getSimpleName();

    private MutableLiveData<List<Diary>> mDiaryMutableLiveData;
    private MutableLiveData<NetworkError> mErrorLiveData;
    private YamapService mYamapService;
    private boolean mNetworkCallInProgress;

    // -------------------Public-------------------

    public DiaryRepository() {
        mYamapService = RetrofitApiHelper.getYamapService();
        mErrorLiveData = new MutableLiveData<>();
    }

    // This method will also check for locally saved data when
    // data persistence is implemented
    public LiveData<List<Diary>> getDiaries() {
        // LiveData needs to be cleared each time so cached
        // data is not returned
        mDiaryMutableLiveData = new MutableLiveData<>();
        getDiariesFromNetwork();
        return mDiaryMutableLiveData;
    }

    public LiveData<NetworkError> listenForNetworkError() {
        return mErrorLiveData;
    }

    public boolean isNetworkCallInProgress() {
        return mNetworkCallInProgress;
    }

    // -------------------Private-------------------

    private void getDiariesFromNetwork() {
        mNetworkCallInProgress = true;
        mYamapService.getDiaries().enqueue(new Callback<List<Diary>>() {
            @Override
            public void onResponse(Call<List<Diary>> call, Response<List<Diary>> response) {
                mNetworkCallInProgress = false;
                if (response.body() != null) {
                     updateLiveData(response.body());
                } else if (response.code() >= 500) {
                    postError(NetworkError.SERVER_ERROR);
                } else {
                    postError(NetworkError.GENERAL_NETWORK_ERROR);
                }
            }

            @Override
            public void onFailure(Call<List<Diary>> call, Throwable t) {
                mNetworkCallInProgress = false;
                Log.d(TAG, "Retrofit Call Failed: " + t.getMessage());
                postError(NetworkError.GENERAL_NETWORK_ERROR);
            }
        });
    }

    private void updateLiveData(List<Diary> diaries) {
        mDiaryMutableLiveData.setValue(diaries);
    }

    private void postError(NetworkError error) {
        mErrorLiveData.setValue(error);
    }
}