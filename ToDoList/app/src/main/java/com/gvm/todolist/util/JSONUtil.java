package com.gvm.todolist.util;


import android.content.Context;
import android.os.Build;

import com.google.gson.Gson;
import com.google.gson.GsonBuilder;
import com.google.gson.reflect.TypeToken;
import com.gvm.todolist.R;
import com.gvm.todolist.model.ItemModel;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.io.StringWriter;
import java.io.Writer;
import java.util.ArrayList;
import java.util.List;

public class JSONUtil {

    public static List<ItemModel> readJsonFile(Context context)
            throws IOException, JSONException {

        InputStream resourceReader = context.getResources().openRawResource(R.raw.mock_data);
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

}
