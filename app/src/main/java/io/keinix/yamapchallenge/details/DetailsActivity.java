package io.keinix.yamapchallenge.details;

import androidx.appcompat.app.AppCompatActivity;
import androidx.lifecycle.ViewModelProviders;
import butterknife.BindColor;
import butterknife.BindString;
import butterknife.BindView;
import butterknife.ButterKnife;
import io.keinix.yamapchallenge.R;
import io.keinix.yamapchallenge.main.MainActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuItem;
import android.widget.EditText;
import android.widget.Toast;

public class DetailsActivity extends AppCompatActivity {

    @BindView(R.id.edit_text_details_title) EditText mTitleEditText;

    @BindColor(R.color.errorColor) int mErrorColor;
    @BindString(R.string.details_title_empty_toast) String mEmptyTitleToastString;
    @BindString(R.string.details_title_empty_hint) String mEmptyTitleHintString;

    private DetailsViewModel mViewModel;

    // ---------------------Override---------------------

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_details);
        ButterKnife.bind(this);
        setTitle(R.string.details_title);
        mViewModel = ViewModelProviders.of(this).get(DetailsViewModel.class);
        setUpView();
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        getMenuInflater().inflate(R.menu.menu_details, menu);
        return super.onCreateOptionsMenu(menu);
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        switch (item.getItemId()) {
            case R.id.action_save_title_edit:
                String newTitle = mTitleEditText.getText().toString();
                if (!titleIsEmpty(newTitle)) saveTitleChangeAndExit(newTitle);
        }
        return super.onOptionsItemSelected(item);
    }

    // ---------------------Private---------------------

    private void setUpView() {
        mViewModel.setOriginalTitle(getIntent().getStringExtra((MainActivity.EXTRA_DIARY_TITLE)));
        if (mViewModel.getOriginalTitle() != null) mTitleEditText.setText(mViewModel.getOriginalTitle());
    }

    private void saveTitleChangeAndExit(String newTitle) {
        if (!mViewModel.newTitleIsSameAsOld(newTitle)) {
            Intent intent = new Intent();
            int diaryId = getIntent().getIntExtra(MainActivity.EXTRA_DIARY_ID, -1);
            intent.putExtra(MainActivity.EXTRA_DIARY_TITLE, newTitle);
            intent.putExtra(MainActivity.EXTRA_DIARY_ID, diaryId);
            setResult(RESULT_OK, intent);
        }
        finish();
    }

    private boolean titleIsEmpty(String newTitle) {
        boolean isEmpty = newTitle.length() < 1;
        if (isEmpty) notifyUserOfEmptyTitle();
        return isEmpty;
    }

    private void notifyUserOfEmptyTitle() {
        Toast.makeText(this, mEmptyTitleToastString, Toast.LENGTH_SHORT).show();
        mTitleEditText.setHint(mEmptyTitleHintString);
        mTitleEditText.setHintTextColor(mErrorColor);
    }


}
