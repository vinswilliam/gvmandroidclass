package com.gvm.todolist.detail;


import android.content.Context;
import android.content.Intent;
import android.content.res.Configuration;
import android.os.Bundle;
import android.os.Parcel;
import android.support.annotation.Nullable;
import android.support.v4.app.FragmentTransaction;
import android.support.v7.app.AppCompatActivity;

import com.gvm.todolist.R;
import com.gvm.todolist.model.ItemModel;

import org.parceler.Parcels;

public class ItemDetailActivity extends AppCompatActivity {

    public static Intent newActivity(Context context, ItemModel itemModel) {
        Intent intent = new Intent(context, ItemDetailActivity.class);
        intent.putExtra("item", Parcels.wrap(itemModel));
        return intent;
    }

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.item_detail_activity);

        if (getResources().getConfiguration().orientation == Configuration.ORIENTATION_LANDSCAPE) {
            finish();
        }

        ItemModel itemModel = Parcels.unwrap(getIntent().getParcelableExtra("item"));
        if (itemModel != null) {
            ItemDetailFragment fragment = ItemDetailFragment.newInstance(itemModel);
            FragmentTransaction transaction = getSupportFragmentManager().beginTransaction();
            transaction.replace(R.id.item_detail_fragment, fragment).commit();
        } else {
            finish();
        }

    }
}
