package com.gvm.todolist;


import android.support.v7.widget.RecyclerView;
import android.view.View;
import android.widget.TextView;

import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.Locale;

import butterknife.BindView;
import butterknife.ButterKnife;

public class ItemViewHolder extends RecyclerView.ViewHolder {

    @BindView(R.id.tv_title)
    TextView tvTitle;

    @BindView(R.id.tv_start_date)
    TextView tvStartDate;

    @BindView(R.id.tv_finish_date)
    TextView tvFinishDate;

    public ItemViewHolder(View itemView) {
        super(itemView);
        ButterKnife.bind(this, itemView);
    }

    public void bind(ItemModel itemModel) {
        tvTitle.setText(itemModel.getTitle());
        tvStartDate.setText(itemView.getContext().getString(R.string.startDate, formatDate(itemModel.getStartDate())));
        tvFinishDate.setText(itemView.getContext().getString(R.string.deadline,
                formatDate(itemModel.getFinishDate())));
    }

    private String formatDate(Date value) {
        SimpleDateFormat simpleDate =  new SimpleDateFormat("dd/MM/yyyy", Locale.ENGLISH);
        return simpleDate.format(value);
    }
}
