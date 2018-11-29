package io.keinix.yamapchallenge.data.source.remote;

import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;

/**
 * Provides a {@link YamapService} for making network API
 * calls with Retrofit.
 */
public final class RetrofitApiHelper {

    // Base URL for remote API
    private static final String BASE_URL = "https://s3-ap-northeast-1.amazonaws.com/file.yamap.co.jp/android/";

    public static YamapService getYamapService() {
        return new Retrofit.Builder()
                .baseUrl(BASE_URL)
                .addConverterFactory(GsonConverterFactory.create())
                .build()
                .create(YamapService.class);
    }
}