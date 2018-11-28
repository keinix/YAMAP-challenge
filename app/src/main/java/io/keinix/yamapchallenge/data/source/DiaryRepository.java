package io.keinix.yamapchallenge.data.source;

import android.content.Context;
import android.net.ConnectivityManager;
import android.net.NetworkInfo;
import android.util.Log;

import java.util.List;

import androidx.lifecycle.LiveData;
import androidx.lifecycle.MutableLiveData;
import io.keinix.yamapchallenge.data.Diary;
import io.keinix.yamapchallenge.data.source.remote.RetrofitApiHelper;
import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class DiaryRepository {

    private static final String TAG = DiaryRepository.class.getSimpleName();

    private MutableLiveData<List<Diary>> mDiaryMutableLiveData;
    private RetrofitApiHelper.RetrofitApi mRetrofitApi;



    public DiaryRepository() {
        mDiaryMutableLiveData = new MutableLiveData<>();
        mRetrofitApi = RetrofitApiHelper.getApi();
    }

    public LiveData<List<Diary>> getDiaries() {
        getFeedFromNetwork();
        return mDiaryMutableLiveData;
    }

    private void getFeedFromNetwork() {
        mRetrofitApi.getFeed().enqueue(new Callback<List<Diary>>() {
            @Override
            public void onResponse(Call<List<Diary>> call, Response<List<Diary>> response) {
                List<Diary> diaries = null;
                if (response.body() != null) diaries = response.body();
                if (diaries != null) updateLiveData(diaries);
            }

            @Override
            public void onFailure(Call<List<Diary>> call, Throwable t) {
                Log.d(TAG, "Retrofit Call Failed: " + t.getMessage());
            }
        });
    }

    private void updateLiveData(List<Diary> diaries) {
        mDiaryMutableLiveData.setValue(diaries);
    }
}
