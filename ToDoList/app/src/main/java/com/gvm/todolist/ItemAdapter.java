package com.gvm.todolist;


import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import java.util.ArrayList;
import java.util.List;

public class ItemAdapter extends RecyclerView.Adapter<RecyclerView.ViewHolder> {

    List<ItemModel> mItems;

    ItemAdapter() {
        mItems = new ArrayList<>();
    }

    @Override
    public RecyclerView.ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        RecyclerView.ViewHolder vh;
        View v;
        switch (viewType) {
            default: {
                v = LayoutInflater.from(parent.getContext()).inflate(R.layout.row_item, parent, false);
                vh = new ItemViewHolder(v);
            }
        }
        return vh;
    }

    @Override
    public void onBindViewHolder(RecyclerView.ViewHolder holder, int position) {
        ItemViewHolder defaultViewHolder = (ItemViewHolder) holder;
        defaultViewHolder.bind(mItems.get(position));
    }

    @Override
    public int getItemCount() {
        return mItems.size();
    }

    void insertItem(List<ItemModel> items) {
        int currentTotal = getItemCount();
        mItems.addAll(items);
        notifyItemRangeInserted(currentTotal, getItemCount() - 1);
    }
}
