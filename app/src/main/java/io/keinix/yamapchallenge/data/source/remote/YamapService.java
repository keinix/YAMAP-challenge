package io.keinix.yamapchallenge.data.source.remote;

import java.util.List;

import io.keinix.yamapchallenge.data.Diary;
import retrofit2.Call;
import retrofit2.http.GET;

public interface YamapService {
    String END_POINT_DIARIES = "diaries.json";

    @GET(END_POINT_DIARIES)
    Call<List<Diary>> getDiaries();
}
