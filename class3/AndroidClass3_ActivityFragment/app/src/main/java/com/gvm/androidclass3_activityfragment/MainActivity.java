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

    @Override
    public void onClickBtnInFragment(int value) {
        Log.d("william",
                "value from fragment: " + value);
    }

    @Override
    public void onFinishActivity() {
        finish();
    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        ButterKnife.bind(this);

        bindingFirstFragment();
    }

    private void bindingFirstFragment() {
        FirstFragment firstFragment = (FirstFragment) getFragmentManager()
                .findFragmentByTag("FIRST_FRAGMENT");
        if (firstFragment == null) {
            firstFragment = FirstFragment.newInstance("Hai");
        }
        getFragmentManager()
                .beginTransaction()
                .add(R.id.container_first_fragment,
                        firstFragment,
                        "FIRST_FRAGMENT")
                .commit();
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
