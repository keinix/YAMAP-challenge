package io.keinix.yamapchallenge.details;

import androidx.lifecycle.ViewModel;

public class DetailsViewModel extends ViewModel {

    private String mOriginalTitle;

    String getOriginalTitle() {
        return mOriginalTitle;
    }

    void setOriginalTitle(String originalTitle) {
        mOriginalTitle = originalTitle;
    }

    boolean newTitleIsSameAsOld(String newTitle) {
        return mOriginalTitle.equals(newTitle);
    }
}
