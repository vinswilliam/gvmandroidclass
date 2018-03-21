package com.gvm.database;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;
import android.util.Log;

import java.util.ArrayList;
import java.util.List;


public class GroceriesDatabaseHelper extends SQLiteOpenHelper {

    private static final String TAG = "WILLIAM";

    private static final String DATABASE_NAME = "GroceriesDB";
    private static final int DATABASE_VERSION = 1;

    private static final String TABLE_ITEM = "item";
    private static final String TABLE_SUPPLIER = "supplier";

    private static final String KEY_ITEM_ID = "id_item";
    private static final String KEY_ITEM_NAME = "item_name";
    private static final String KEY_ITEM_PRICE = "item_price";
    private static final String KEY_ITEM_STOCK = "item_stock";
    private static final String KEY_ITEM_SUPPLIER_FK = "supplier_id_fk";

    private static final String KEY_SUPPLIER_ID = "id_supplier";
    private static final String KEY_SUPPLIER_NAME = "supplier_name";
    private static final String KEY_SUPPLIER_PHONE = "supplier_phone";
    private static final String KEY_SUPPLIER_ADDRESS = "supplier_address";

    private static GroceriesDatabaseHelper sInstance;

    public static synchronized GroceriesDatabaseHelper getInstance(Context context) {
        if (sInstance == null) {
            sInstance = new GroceriesDatabaseHelper(context);
        }

        return sInstance;
    }

    public GroceriesDatabaseHelper(Context context) {
        super(context, DATABASE_NAME, null, DATABASE_VERSION);
    }

    @Override
    public void onConfigure(SQLiteDatabase db) {
        super.onConfigure(db);
        db.setForeignKeyConstraintsEnabled(true);
    }

    @Override
    public void onCreate(SQLiteDatabase db) {
        String CREATE_SUPPLIER_TABLE = "CREATE TABLE " + TABLE_SUPPLIER +
                "(" +
                KEY_SUPPLIER_ID + " INTEGER PRIMARY KEY AUTOINCREMENT NOT NULL," +
                KEY_SUPPLIER_NAME + " TEXT," +
                KEY_SUPPLIER_PHONE + " TEXT," +
                KEY_SUPPLIER_ADDRESS + " TEXT" +
                ")";

        String CREATE_ITEM_TABLE = "CREATE TABLE " + TABLE_ITEM +
                "(" +
                KEY_ITEM_ID + " INTEGER PRIMARY KEY AUTOINCREMENT NOT NULL," +
                KEY_ITEM_NAME + " TEXT," +
                KEY_ITEM_PRICE + " INTEGER," +
                KEY_ITEM_STOCK + " INTEGER," +
                KEY_ITEM_SUPPLIER_FK + " INTEGER REFERENCES " + TABLE_SUPPLIER +
                ")";

        db.execSQL(CREATE_SUPPLIER_TABLE);
        db.execSQL(CREATE_ITEM_TABLE);
    }

    @Override
    public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {
        if (oldVersion != newVersion) {
            // Simplest implementation is to drop all old tables and recreate them
            db.execSQL("DROP TABLE IF EXISTS " + TABLE_ITEM);
            db.execSQL("DROP TABLE IF EXISTS " + TABLE_SUPPLIER);
            onCreate(db);
        }
    }

    public void addItem(ItemModel itemModel) {
        SQLiteDatabase db = getWritableDatabase();
        db.beginTransaction();
        try {
            long supplierId = addOrUpdateSupplier(itemModel.getSupplier());
            ContentValues values = new ContentValues();
            values.put(KEY_ITEM_SUPPLIER_FK, supplierId);
            values.put(KEY_ITEM_NAME, itemModel.getName());
            values.put(KEY_ITEM_PRICE, itemModel.getPrice());
            values.put(KEY_ITEM_STOCK, itemModel.getStock());

            db.insertOrThrow(TABLE_ITEM, null, values);
            db.setTransactionSuccessful();
        } catch (Exception e) {
            Log.e(TAG, e.getMessage());
            e.printStackTrace();
        } finally {
            db.endTransaction();
        }
    }

    public long addOrUpdateSupplier(SupplierModel supplierModel) {
        SQLiteDatabase db = getWritableDatabase();
        long supplierId = -1;

        db.beginTransaction();
        try {
            ContentValues values = new ContentValues();
            values.put(KEY_SUPPLIER_NAME, supplierModel.getName());
            values.put(KEY_SUPPLIER_PHONE, supplierModel.getPhone());
            values.put(KEY_SUPPLIER_ADDRESS, supplierModel.getAddress());

            int rows = db.update(TABLE_SUPPLIER, values, KEY_SUPPLIER_NAME + "= ?",
                    new String[]{supplierModel.getName()});

            if (rows == 1) {
                String supplieSelectQuery = String.format("SELECT %s FROM %S WHERE %s = ?",
                        KEY_SUPPLIER_ID, TABLE_SUPPLIER, KEY_SUPPLIER_NAME);
                Cursor cursor = db.rawQuery(supplieSelectQuery, new String[] {
                        String.valueOf(supplierModel.getName())});
                try {
                    if (cursor.moveToFirst()) {
                        supplierId = cursor.getInt(0);
                        db.setTransactionSuccessful();
                    }
                } finally {
                    if (cursor != null && !cursor.isClosed()) {
                        cursor.close();
                    }
                }
            } else {
                supplierId = db.insertOrThrow(TABLE_SUPPLIER, null, values);
                db.setTransactionSuccessful();
            }
        } catch (Exception e) {
            e.printStackTrace();
        } finally {
            db.endTransaction();
        }
        return supplierId;
    }

    public ItemModel getItem(ItemModel itemModel) {
        return null;
    }

    public List<ItemModel> getItems() {
        List<ItemModel> items = new ArrayList<>();
        String ITEMS_SELECT_QUERY =
                String.format("SELECT * FROM %s LEFT OUTER JOIN %s ON %s.%s = %s.%s",
                        TABLE_ITEM, TABLE_SUPPLIER,
                        TABLE_ITEM, KEY_ITEM_SUPPLIER_FK,
                        TABLE_SUPPLIER, KEY_SUPPLIER_ID);

        SQLiteDatabase db = getReadableDatabase();
        Cursor cursor = db.rawQuery(ITEMS_SELECT_QUERY, null);
        try {
            if (cursor.moveToFirst()) {
                do {
                    ItemModel item = new ItemModel();
                    item.setId(cursor.getString(cursor.getColumnIndex(KEY_ITEM_ID)));
                    item.setName(cursor.getString(cursor.getColumnIndex(KEY_ITEM_NAME)));
                    item.setPrice(cursor.getInt(cursor.getColumnIndex(KEY_ITEM_PRICE)));
                    item.setStock(cursor.getInt(cursor.getColumnIndex(KEY_ITEM_STOCK)));
                    SupplierModel supplier = new SupplierModel();
                    supplier.setId(cursor.getString(cursor.getColumnIndex(KEY_SUPPLIER_ID)));
                    supplier.setName(cursor.getString(cursor.getColumnIndex(KEY_SUPPLIER_NAME)));
                    supplier.setAddress(cursor.getString(cursor.getColumnIndex(KEY_SUPPLIER_ADDRESS)));
                    supplier.setPhone(cursor.getString(cursor.getColumnIndex(KEY_SUPPLIER_PHONE)));
                    item.setSupplier(supplier);
                    items.add(item);
                } while (cursor.moveToNext());
            }
        } catch (Exception e) {
            Log.e(TAG, e.getMessage());
            e.printStackTrace();
        } finally {
            if (cursor != null && !cursor.isClosed()) {
                cursor.close();
            }
        }
        return items;
    }
}
