package io.keinix.yamapchallenge.data.source.remote;

import java.util.List;

import io.keinix.yamapchallenge.data.Diary;
import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;
import retrofit2.http.GET;
import retrofit2.Call;

public final class RetrofitApiHelper {

    // Base URL for remote API
    private static final String BASE_URL = "https://s3-ap-northeast-1.amazonaws.com/file.yamap.co.jp/android/";

    // End point for remote API
    private static final String END_POINT_DIARIES = "diaries.json";

    public interface RetrofitApi {
        @GET(END_POINT_DIARIES)
        Call<List<Diary>> getFeed();
    }

    public static RetrofitApi getApi() {
        return new Retrofit.Builder()
                .baseUrl(BASE_URL)
                .addConverterFactory(GsonConverterFactory.create())
                .build()
                .create(RetrofitApi.class);
    }
}
