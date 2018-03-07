package com.gvm.androidclass3_activityfragment;

import android.app.Activity;
import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.os.PersistableBundle;
import android.support.annotation.Nullable;
import android.support.v7.app.AppCompatActivity;
import android.util.Log;

import butterknife.ButterKnife;
import butterknife.OnClick;


public class SecondActivity extends AppCompatActivity {

    public static Intent getIntent(Context context) {
        Intent intent = new Intent(context, SecondActivity.class);
        return intent;
    }

    public static Intent getIntent(Context context,
                                   String title,
                                   int pageView) {
        Intent intent = new Intent(context, SecondActivity.class);
        intent.putExtra("activity_title", title);
        intent.putExtra("page_view", pageView);
        return intent;
    }

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_second);
        ButterKnife.bind(this);

        String title = getIntent().getStringExtra("activity_title");
        int pageView = getIntent().getIntExtra(
                "page_view", -1);

        setTitle(title + " - " + pageView);
    }

    @Override
    protected void onResume() {
        super.onResume();
        Log.d("GVM", "onResume");
    }

    @Override
    protected void onPause() {
        super.onPause();
        Log.d("GVM", "onPause");
    }

    @Override
    protected void onDestroy() {
        super.onDestroy();
        Log.d("GVM", "onDestroy");
    }

    @Override
    public void onSaveInstanceState(Bundle outState,
                                    PersistableBundle outPersistentState) {
        super.onSaveInstanceState(outState, outPersistentState);
        Log.d("GVM", "onSavedInstanceState");
    }

    @Override
    protected void onRestoreInstanceState(Bundle savedInstanceState) {
        super.onRestoreInstanceState(savedInstanceState);
        Log.d("GVM", "onRestoreInstanceState");
    }

    @OnClick(R.id.btnFinishActivity)
    void onClickFinish() {
        Intent intent = new Intent();
        intent.putExtra("value", "Value from destroy activity.");
        setResult(Activity.RESULT_OK, intent);
        finish();
    }

}
