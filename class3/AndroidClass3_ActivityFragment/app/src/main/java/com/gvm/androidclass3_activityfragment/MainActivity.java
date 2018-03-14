package com.gvm.androidclass3_activityfragment;

import android.app.Activity;
import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;

import java.util.Random;

import butterknife.ButterKnife;
import butterknife.OnClick;

public class MainActivity extends AppCompatActivity
    implements FirstFragment.IFirstFragmentListener {

    private SecondFragment secondFragment;

    @Override
    public void onFinishActivity() {
        finish();
    }

    @Override
    public void onGenerateRandomValue(int value) {
        secondFragment.showGenerateRandomNumber(value);
    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        ButterKnife.bind(this);

        setTitle("Happy Coding");

        bindingFirstFragment();
        bindingSecondFragment();
    }

    private void bindingFirstFragment() {
        FirstFragment firstFragment = (FirstFragment) getFragmentManager()
                .findFragmentByTag("FIRST_FRAGMENT");
        if (firstFragment == null) {
            firstFragment = FirstFragment.newInstance("Hai");
        }
        if (!firstFragment.isAdded()) {
            getFragmentManager()
                    .beginTransaction()
                    .add(R.id.container_first_fragment,
                            firstFragment,
                            "FIRST_FRAGMENT")
                    .commit();
        }
    }

    private void bindingSecondFragment() {
        secondFragment = (SecondFragment) getFragmentManager()
                .findFragmentByTag("SECOND_FRAGMENT");
        if (secondFragment == null) {
            secondFragment = SecondFragment.newInstance();
        }
        if (!secondFragment.isAdded()) {
            getFragmentManager()
                    .beginTransaction()
                    .add(R.id.container_second_fragment,
                            secondFragment,
                            "SECOND_FRAGMENT")
                    .commit();
        }
    }

    @OnClick(R.id.btnNext)
    void onClickBtnNext() {
        Intent intent = SecondActivity.getIntent(this);
        startActivity(intent);
    }

    @OnClick(R.id.btnNextWithResult)
    void onclickBtnNextWithResultActivity() {
        Intent intent = SecondActivity.getIntent(this,
                "Title", (new Random()).nextInt());
        startActivityForResult(intent, 1001);
    }

    @Override
    protected void onActivityResult(int requestCode, int resultCode,
                                    Intent data) {
        super.onActivityResult(requestCode, resultCode, data);
        if (requestCode == 1001) {
            if (resultCode == Activity.RESULT_OK) {
                Log.d("GVM", "result ok");
            } else {
                Log.d("GVM", "result canceled");
            }
        }
    }
}
