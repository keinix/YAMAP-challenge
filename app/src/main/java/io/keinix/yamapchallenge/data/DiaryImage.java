package io.keinix.yamapchallenge.data;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

public class DiaryImage {

    @Expose
    @SerializedName("id")
    private int mImageId;

    @Expose
    @SerializedName("imageUrl")
    private String mImageUrl;

    @Expose
    @SerializedName("thumbnailUrl")
    private String mThumbnailUrl;

    public int getImageId() {
        return mImageId;
    }

    public void setImageId(int imageId) {
        mImageId = imageId;
    }

    public String getImageUrl() {
        return mImageUrl;
    }

    public void setImageUrl(String imageUrl) {
        mImageUrl = imageUrl;
    }

    public String getThumbnailUrl() {
        return mThumbnailUrl;
    }

    public void setThumbnailUrl(String thumbnailUrl) {
        mThumbnailUrl = thumbnailUrl;
    }
}
