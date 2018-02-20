package com.gvm.todolist;


import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.google.gson.Gson;
import com.google.gson.GsonBuilder;
import com.google.gson.reflect.TypeToken;

import org.json.JSONException;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.io.StringWriter;
import java.io.Writer;
import java.util.ArrayList;
import java.util.List;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnClick;

public class ItemsFragment extends Fragment {

    @BindView(R.id.rv_items)
    RecyclerView rvItems;

    private ItemAdapter mItemAdapter;

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
    public void onViewCreated(@NonNull View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);
        mItemAdapter = new ItemAdapter();
        LinearLayoutManager linearLayoutManager = new LinearLayoutManager(getContext());
        rvItems.setLayoutManager(linearLayoutManager);
        rvItems.setAdapter(mItemAdapter);
        rvItems.addItemDecoration(new ItemDecorView());
        try {
            mItemAdapter.insertItem(readJsonFile());
        } catch (IOException e) {
            e.printStackTrace();
        } catch (JSONException e) {
            e.printStackTrace();
        }
    }

    private List<ItemModel> readJsonFile() throws IOException, JSONException {
        InputStream resourceReader = getResources().openRawResource(R.raw.mock_data);
        Writer writer = new StringWriter();
        List<ItemModel> items = new ArrayList<>();
        try {
            BufferedReader reader = new BufferedReader(new InputStreamReader(resourceReader, "UTF-8"));
            String line = reader.readLine();
            while (line != null) {
                writer.write(line);
                line = reader.readLine();
            }

            Gson gson = new GsonBuilder()
                    .setDateFormat("dd/mm/yyyy").create();
            items = gson.fromJson(writer.toString(), new TypeToken<List<ItemModel>>(){}.getType());
        } catch (Exception e) {
            e.printStackTrace();
        } finally {
            try {
                resourceReader.close();
            } catch (Exception e) {
                e.printStackTrace();
            }
        }

        return items;
    }

    @OnClick(R.id.fab_create_item)
    void onClickFabCreateItem() {
        startActivity(AddItemActivity.newActivity(getContext()));
    }
}
