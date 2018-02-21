package com.gvm.todolist.list;


import android.support.v7.widget.RecyclerView;
import android.view.View;
import android.widget.TextView;

import com.gvm.todolist.R;
import com.gvm.todolist.model.ItemModel;
import com.gvm.todolist.util.DateUtil;

import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.Locale;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnClick;

public class ItemViewHolder extends RecyclerView.ViewHolder {

    @BindView(R.id.tv_title)
    TextView tvTitle;

    @BindView(R.id.tv_start_date)
    TextView tvStartDate;

    @BindView(R.id.tv_finish_date)
    TextView tvFinishDate;

    private ITodoListListener mListener;

    public ItemViewHolder(View itemView, ITodoListListener listener) {
        super(itemView);
        ButterKnife.bind(this, itemView);
        mListener = listener;
    }

    @OnClick(R.id.container_item_listtodo)
    void onClickItem() {
        mListener.onClickItem(getLayoutPosition());
    }

    public void bind(ItemModel itemModel) {
        tvTitle.setText(itemModel.getTitle());
        tvStartDate.setText(itemView.getContext().getString(R.string.startDate,
                DateUtil.formatDate(itemModel.getStartDate())));
        tvFinishDate.setText(itemView.getContext().getString(R.string.deadline,
                DateUtil.formatDate(itemModel.getFinishDate())));
    }

}
