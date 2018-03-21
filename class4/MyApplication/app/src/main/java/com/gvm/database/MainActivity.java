package com.gvm.database;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.widget.TextView;

import java.util.ArrayList;
import java.util.List;
import java.util.Random;

import butterknife.BindView;
import butterknife.ButterKnife;

public class MainActivity extends AppCompatActivity {

    TextView tvLog;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        ButterKnife.bind(this);

        tvLog = findViewById(R.id.tv_log);

        SupplierModel supplierModel = new SupplierModel();
        supplierModel.setName("PT. Sumber Makmur Sejahtera");
        supplierModel.setPhone("021-7327489");
        supplierModel.setAddress("Komplek Kebun Raya No. 19");

        ItemModel itemModel = new ItemModel();
        itemModel.setSupplier(supplierModel);
        itemModel.setName("Bayam Organik " + (new Random()).nextInt());
        itemModel.setPrice((new Random()).nextInt());
        itemModel.setStock(100);

        GroceriesDatabaseHelper groceriesDatabaseHelper = GroceriesDatabaseHelper.getInstance(this);
        groceriesDatabaseHelper.addItem(itemModel);

        List<ItemModel> items = new ArrayList<>();
        items.addAll(groceriesDatabaseHelper.getItems());

        for(ItemModel item : items) {
            tvLog.append(item.toString() + "\n");
        }
    }
}
