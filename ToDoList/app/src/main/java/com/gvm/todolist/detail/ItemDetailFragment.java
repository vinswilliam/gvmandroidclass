package com.gvm.todolist.detail;


import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import com.gvm.todolist.R;
import com.gvm.todolist.model.ItemModel;
import com.gvm.todolist.util.DateUtil;

import org.parceler.Parcels;

import butterknife.BindView;
import butterknife.ButterKnife;

public class ItemDetailFragment extends Fragment {

    @BindView(R.id.tv_detail_title)
    TextView tvTitle;

    @BindView(R.id.tv_detail_description)
    TextView tvDesc;

    @BindView(R.id.tv_detail_startdate)
    TextView tvStartDate;

    @BindView(R.id.tv_detail_finishdate)
    TextView tvFinishDate;

    private ItemModel mItemModel;

    public static ItemDetailFragment newInstance(ItemModel item) {
        Bundle bundle = new Bundle();
        bundle.putParcelable("detailItem", Parcels.wrap(item));
        ItemDetailFragment fragment = new ItemDetailFragment();
        fragment.setArguments(bundle);
        return fragment;
    }

    public ItemDetailFragment() {

    }

    @Override
    public void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        mItemModel = Parcels.unwrap(getArguments().getParcelable("detailItem"));
    }

    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container,
                             @Nullable Bundle savedInstanceState) {
        View v = inflater.inflate(R.layout.item_detail_layout, container, false);
        ButterKnife.bind(this, v);
        return v;
    }

    @Override
    public void onViewCreated(@NonNull View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);
        tvTitle.setText(mItemModel.getTitle());
        tvStartDate.setText(DateUtil.formatDate(mItemModel.getStartDate()));
        tvFinishDate.setText(DateUtil.formatDate(mItemModel.getFinishDate()));
        tvDesc.setText(getString(R.string.preview_todo));

    }
}
