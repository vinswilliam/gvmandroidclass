package com.gvm.androidclass3_activityfragment;

import android.app.Activity;
import android.app.Fragment;
import android.content.Context;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import java.util.Random;

import butterknife.ButterKnife;
import butterknife.OnClick;


public class FirstFragment extends Fragment {

    private IFirstFragmentListener mListener;

    public static FirstFragment newInstance() {
        FirstFragment fragment = new FirstFragment();
        return fragment;
    }

    public static FirstFragment newInstance(String content) {
        FirstFragment fragment = new FirstFragment();
        Bundle bundle = new Bundle();
        bundle.putString("content", content);
        fragment.setArguments(bundle);
        return fragment;
    }

    @Override
    public void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        String content = getArguments().getString("content");
        Log.d("GVM", "content passed: " + content);
    }

    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container,
                             Bundle savedInstanceState) {
        View v = inflater.inflate(R.layout.fragment_first,
                container, false);
        ButterKnife.bind(this, v);
        return v;
    }

    @Override
    public void onViewCreated(View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);
    }

    @Override
    public void onAttach(Context context) {
        super.onAttach(context);
        if (context instanceof IFirstFragmentListener) {
            mListener = (IFirstFragmentListener) context;
        } else {
            throw new RuntimeException(context.toString()
                    + " must implement IFirstFragmetListener");
        }
    }

    @OnClick(R.id.btn_first_fragment)
    void onClickFirstFragment() {
//        mListener.onClickBtnInFragment((new Random()).nextInt());
        mListener.onFinishActivity();
    }

    public interface IFirstFragmentListener {
        void onClickBtnInFragment(int value);
        void onFinishActivity();
    }

}








