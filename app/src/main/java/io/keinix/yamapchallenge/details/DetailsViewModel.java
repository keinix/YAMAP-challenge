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

    // If title is equal, nothing will change when exiting the activity.
    // used to prevent unnecessary network calls and UI changes
    boolean newTitleIsSameAsOld(String newTitle) {
        if (mOriginalTitle != null) {
            return mOriginalTitle.equals(newTitle);
        } else {
            // DetailsActivity was destroyed so mOriginalTitle is null
            return false;
        }

    }
}