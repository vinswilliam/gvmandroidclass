package com.gvm.todolist.list;


import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentTransaction;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.gvm.todolist.R;
import com.gvm.todolist.add.AddItemActivity;
import com.gvm.todolist.detail.ItemDetailActivity;
import com.gvm.todolist.detail.ItemDetailFragment;
import com.gvm.todolist.util.JSONUtil;

import org.json.JSONException;

import java.io.IOException;
import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnClick;

public class ItemsFragment extends Fragment implements ITodoListListener {

    @BindView(R.id.rv_items)
    RecyclerView rvItems;

    private ItemAdapter mItemAdapter;

    private int mCurCheckPosition = 0;
    private boolean mDualPane;

    @Override
    public void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
    }

    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container,
                             @Nullable Bundle savedInstanceState) {
        View v = inflater.inflate(R.layout.items_fragment, container, false);
        ButterKnife.bind(this, v);
        return v;
    }

    @Override
    public void onActivityCreated(@Nullable Bundle savedInstanceState) {
        super.onActivityCreated(savedInstanceState);
        setTodoList();

        View detailsFrame = getActivity().findViewById(R.id.container_detail);
        mDualPane = detailsFrame != null && detailsFrame.getVisibility() == View.VISIBLE;

        if (savedInstanceState != null) {
            mCurCheckPosition = savedInstanceState.getInt("curChoice", 0);
        }

        if (mDualPane) {
            showDetails(mCurCheckPosition);
        }
    }

    private void setTodoList() {
        mItemAdapter = new ItemAdapter(this);
        LinearLayoutManager linearLayoutManager = new LinearLayoutManager(getContext());
        rvItems.setLayoutManager(linearLayoutManager);
        rvItems.setAdapter(mItemAdapter);
        rvItems.addItemDecoration(new ItemDecorView());
        try {
            mItemAdapter.insertItem(JSONUtil.readJsonFile(getContext()));
        } catch (IOException e) {
            e.printStackTrace();
        } catch (JSONException e) {
            e.printStackTrace();
        }
    }

    private void showDetails(int position) {
        mCurCheckPosition = position;
        if (mDualPane) {
            ItemDetailFragment detail = ItemDetailFragment.newInstance(mItemAdapter.getItem(position));
            FragmentTransaction ft = getFragmentManager().beginTransaction();
            ft.replace(R.id.container_detail, detail);
            ft.setTransition(FragmentTransaction.TRANSIT_FRAGMENT_FADE);
            ft.commit();

        } else {
            startActivity(ItemDetailActivity.newActivity(getContext(), mItemAdapter.getItem(position)));
        }
    }

    @Override
    public void onSaveInstanceState(@NonNull Bundle outState) {
        super.onSaveInstanceState(outState);
        outState.putInt("curChoice", mCurCheckPosition);
    }

    @OnClick(R.id.fab_create_item)
    void onClickFabCreateItem() {
        startActivity(AddItemActivity.newActivity(getContext()));
    }

    @Override
    public void onClickItem(int pos) {
        showDetails(pos);
    }
}
