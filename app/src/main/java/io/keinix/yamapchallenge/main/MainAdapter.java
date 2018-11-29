package io.keinix.yamapchallenge.main;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import com.bumptech.glide.Glide;
import com.bumptech.glide.request.RequestOptions;

import java.util.List;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;
import butterknife.BindView;
import butterknife.ButterKnife;
import io.keinix.yamapchallenge.R;
import io.keinix.yamapchallenge.data.Diary;

class MainAdapter extends RecyclerView.Adapter<MainAdapter.DiaryViewHolder> {

    private static final int DIARY_NOT_FOUND = -1;

    private List<Diary> mDiaries;
    private DiaryClickedListener mDiaryClickedListener;

    /**
     * relays Diary click events to Activity or use case
     */
    interface DiaryClickedListener {
        void onDiaryClicked(int diaryId, String diaryTitle);
    }

    MainAdapter(DiaryClickedListener diaryClickedListener) {
        mDiaryClickedListener = diaryClickedListener;
    }

    // -------------------Override-------------------

    @NonNull
    @Override
    public DiaryViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext())
                .inflate(R.layout.item_diary, parent, false);
        return new DiaryViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull DiaryViewHolder holder, int position) {
        holder.bindView(position);
    }

    @Override
    public int getItemCount() {
        return mDiaries != null ? mDiaries.size() : 0;
    }

    // -------------------Protected-------------------

    void showDiaries(List<Diary> diaries) {
        mDiaries = diaries;
        notifyDataSetChanged();
    }

    /**
     * Updates a diary's title if the under changed it in
     * {@link io.keinix.yamapchallenge.details.DetailsActivity}
     * @param diaryId Id of diary to be updated
     * @param newTitle new title for diary
     */
    void updateTitle(int diaryId, String newTitle) {
        int position = findDiaryPosition(diaryId);
        if (position != DIARY_NOT_FOUND) {
            mDiaries.get(position).setTitle(newTitle);
            notifyItemChanged(position);
        }
    }
    // -------------------Private-------------------

    /**
     * @param id of the {@link Diary} you with to locate in mDiaries
     * @return the position of the Diary in mDiaries or -1 if not found
     */
    private int findDiaryPosition(int id) {
        if (id >= 0) {
            for (int i = 0; i < mDiaries.size(); i++) {
                if (mDiaries.get(i).getId() == id) return i;
            }
        }
        return DIARY_NOT_FOUND;
    }


    // -------------------ViewHolders-------------------

    class DiaryViewHolder extends RecyclerView.ViewHolder implements View.OnClickListener {

        @BindView(R.id.text_view_diary_title) TextView diaryTitleTextView;
        @BindView(R.id.image_view_diary_thumbnail) ImageView diaryThumbnailImageView;

        Diary mDiary;

        DiaryViewHolder(@NonNull View itemView) {
            super(itemView);
            itemView.setOnClickListener(this);
            ButterKnife.bind(this, itemView);
        }

        void bindView(int position) {
            mDiary = mDiaries.get(position);
            diaryTitleTextView.setText(mDiary.getTitle());
            loadImage();
        }

        private void loadImage() {
            if (mDiary.getDiaryImage() != null) {
                String thumbnailUrl = mDiary.getDiaryImage().getThumbnailUrl();
                if (thumbnailUrl.length() > 0) {
                    Glide.with(diaryThumbnailImageView)
                            .load(mDiary.getDiaryImage().getThumbnailUrl())
                            .apply(RequestOptions.placeholderOf(R.drawable.image_placeholder))
                            .into(diaryThumbnailImageView);
                }
            } else {
                diaryThumbnailImageView.setImageResource(0);
            }
        }

        @Override
        public void onClick(View view) {
            mDiaryClickedListener.onDiaryClicked(mDiary.getId(), mDiary.getTitle());
        }
    }
}
