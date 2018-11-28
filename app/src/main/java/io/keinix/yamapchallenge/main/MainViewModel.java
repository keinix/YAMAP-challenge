package io.keinix.yamapchallenge.main;

import java.util.List;

import androidx.lifecycle.LiveData;
import androidx.lifecycle.ViewModel;
import io.keinix.yamapchallenge.data.Diary;
import io.keinix.yamapchallenge.data.source.DiaryRepository;

public class MainViewModel extends ViewModel {

    private DiaryRepository mRepository;
    // used to persists current RecyclerViewData through Config changes
    private LiveData<List<Diary>> mCachedDiaries;

    public MainViewModel() {
        mRepository = new DiaryRepository();
    }

    /**
     * Returns previously cached diaries if available
     * otherwise makes a network call for the diaries
     * @return list of diaries to be displayed in RecyclerView
     */
    LiveData<List<Diary>> getDiaries() {
        if (mCachedDiaries == null) mCachedDiaries = mRepository.getDiaries();
        return mCachedDiaries;
    }

    /**
     * Forces a network call even if diaries have been cached
     * @return list of diaries to be displayed in RecyclerView
     */
    LiveData<List<Diary>> refreshDiaries() {
        mCachedDiaries = mRepository.getDiaries();
        return mCachedDiaries;
    }

}
