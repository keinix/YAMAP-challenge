package io.keinix.yamapchallenge.data;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

/**
 * Root Data Class for user Diary
 */
public class Diary {

    @Expose
    @SerializedName("id")
    private int mId;

    @Expose
    @SerializedName("title")
    private String mTitle;

    @Expose
    @SerializedName("image")
    private DiaryImage mDiaryImage;


    public int getId() {
        return mId;
    }

    public void setId(int id) {
        mId = id;
    }

    public String getTitle() {
        return mTitle;
    }

    public void setTitle(String title) {
        mTitle = title;
    }

    public DiaryImage getDiaryImage() {
        return mDiaryImage;
    }

    public void setDiaryImage(DiaryImage diaryImage) {
        mDiaryImage = diaryImage;
    }

    @Override
    public String toString() {
        return "Diary{" +
                "mId=" + mId +
                ", mTitle='" + mTitle + '\'' +
                ", mDiaryImage=" + mDiaryImage +
                '}';
    }
}