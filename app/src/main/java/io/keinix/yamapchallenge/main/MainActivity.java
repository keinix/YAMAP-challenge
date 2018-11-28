package io.keinix.yamapchallenge.main;

import android.accounts.NetworkErrorException;
import android.content.Intent;
import android.os.Bundle;

import java.util.List;

import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;
import androidx.lifecycle.LiveData;
import androidx.lifecycle.ViewModelProviders;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;
import butterknife.BindView;
import butterknife.ButterKnife;
import io.keinix.yamapchallenge.R;
import io.keinix.yamapchallenge.data.Diary;
import io.keinix.yamapchallenge.details.DetailsActivity;
import io.keinix.yamapchallenge.dialog.NoNetworkConnectionDialog;
import io.keinix.yamapchallenge.utils.NetworkConnection;

public class MainActivity extends AppCompatActivity implements MainAdapter.DiaryClickedListener {

    @BindView(R.id.recycler_view_main) RecyclerView mRecyclerView;

    public static final String EXTRA_DIARY_ID = "EXTRA_DIARY_ID";
    public static final String EXTRA_DIARY_TITLE = "EXTRA_DIARY_TITLE";
    public static final int REQUEST_CODE_EDIT_TITLE = 101;

    private MainViewModel mViewModel;
    private MainAdapter mAdapter;
    private LiveData<List<Diary>> mDiariesLiveData;

    // ---------------------Override---------------------
    @Override
    public void onDiaryClicked(int diaryId, String diaryTitle) {
        launchDetailsActivity(diaryId, diaryTitle);
    }

    @Override
    protected void onActivityResult(int requestCode, int resultCode, @Nullable Intent data) {
        super.onActivityResult(requestCode, resultCode, data);
        if (resultCode == RESULT_OK && requestCode == REQUEST_CODE_EDIT_TITLE) {
            if (data != null) {
                int id = data.getIntExtra(EXTRA_DIARY_ID, -1);
                String newTitle = data.getStringExtra(EXTRA_DIARY_TITLE);
                mAdapter.updateTitle(id, newTitle);
            }
        } else if (requestCode == NoNetworkConnectionDialog.REQUEST_CODE_NETWORK_SETTINGS) {
            refreshDiaries();
        }
    }

    // ---------------------Lifecycle---------------------

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_feed);
        ButterKnife.bind(this);
        setTitle(R.string.main_title);
        mViewModel = ViewModelProviders.of(this).get(MainViewModel.class);
        setUpRecyclerView();
        displayDiaries();
    }

    // ----------------------Private----------------------

    private void setUpRecyclerView() {
        mAdapter = new MainAdapter(this);
        mRecyclerView.setAdapter(mAdapter);
        mRecyclerView.setLayoutManager(new LinearLayoutManager(this));
    }

    // will return cached data before making network call
    private void displayDiaries() {
        if (isConnectedToNetwork()) {
            LiveData<List<Diary>> liveData = mViewModel.getDiaries();
            liveData.observe(this, mAdapter::showDiaries);
        }
    }

    // forces network call
    private void refreshDiaries() {
        if (mDiariesLiveData != null && mDiariesLiveData.hasObservers()) {
            mDiariesLiveData.removeObservers(this);
        }
        if (isConnectedToNetwork()) {
            mDiariesLiveData = mViewModel.refreshDiaries();
            mDiariesLiveData.observe(this, mAdapter::showDiaries);
        }
    }

    private void launchDetailsActivity(int id, String diaryTitle) {
        Intent intent = new Intent(MainActivity.this, DetailsActivity.class);
        intent.putExtra(EXTRA_DIARY_ID, id);
        intent.putExtra(EXTRA_DIARY_TITLE, diaryTitle);
        startActivityForResult(intent, REQUEST_CODE_EDIT_TITLE);
    }

    /**
     * Checks network status with {@link android.net.ConnectivityManager}
     * @return true if connected to the internet
     */
    private boolean isConnectedToNetwork() {
        boolean isConnected = false;
        try {
           isConnected = NetworkConnection.isConnected(this);
        } catch (NetworkErrorException e) {
            e.printStackTrace();
        }
        if (!isConnected) NoNetworkConnectionDialog.show(this);
        return isConnected;
    }
}
