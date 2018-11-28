package io.keinix.yamapchallenge.details;

import androidx.appcompat.app.AppCompatActivity;
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

    // ---------------------Override---------------------

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_details);
        ButterKnife.bind(this);
        setTitle(R.string.details_title);
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
                if (!titleIsEmpty()) saveTitleChangeAndExit();
        }
        return super.onOptionsItemSelected(item);
    }

    // ---------------------Private---------------------

    private void setUpView() {
        String title =  getIntent().getStringExtra((MainActivity.EXTRA_DIARY_TITLE));
        if (title != null) mTitleEditText.setText(title);
    }

    private void saveTitleChangeAndExit() {
        Intent intent = new Intent();
        String newTitle = mTitleEditText.getText().toString();
        int diaryId = getIntent().getIntExtra(MainActivity.EXTRA_DIARY_ID, -1);
        intent.putExtra(MainActivity.EXTRA_DIARY_TITLE, newTitle);
        intent.putExtra(MainActivity.EXTRA_DIARY_ID, diaryId);
        setResult(RESULT_OK, intent);
        finish();
    }

    private boolean titleIsEmpty() {
        boolean isEmpty = mTitleEditText.getText().toString().length() < 1;
        if (isEmpty) notifyUserOfEmptyTitle();
        return isEmpty;
    }

    private void notifyUserOfEmptyTitle() {
        Toast.makeText(this, mEmptyTitleToastString, Toast.LENGTH_SHORT).show();
        mTitleEditText.setHint(mEmptyTitleHintString);
        mTitleEditText.setHintTextColor(mErrorColor);
    }


}
