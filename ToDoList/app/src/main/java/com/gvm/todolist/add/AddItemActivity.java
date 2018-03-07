package com.gvm.todolist.add;


import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.os.PersistableBundle;
import android.support.annotation.Nullable;
import android.support.v7.app.AppCompatActivity;
import android.view.Menu;
import android.view.MenuInflater;

import com.gvm.todolist.R;

import butterknife.ButterKnife;
import butterknife.OnClick;

public class AddItemActivity extends AppCompatActivity {

    public static Intent newActivity(Context context) {
        return new Intent(context, AddItemActivity.class);
    }

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.add_item_layout);
        ButterKnife.bind(this);
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        MenuInflater inflater = getMenuInflater();
        inflater.inflate(R.menu.menu_add_todo, menu);
        return super.onCreateOptionsMenu(menu);
    }

   @OnClick(R.id.btn_save_item)
    void onClickBtnSave() {
        finish();
   }

    @Override
    protected void onRestoreInstanceState(Bundle savedInstanceState) {
        super.onRestoreInstanceState(savedInstanceState);
        savedInstanceState.getInt("data1");
    }

    @Override
    public void onSaveInstanceState(Bundle outState, PersistableBundle outPersistentState) {
        super.onSaveInstanceState(outState, outPersistentState);
        outState.putInt("data1", 1);
    }
}
