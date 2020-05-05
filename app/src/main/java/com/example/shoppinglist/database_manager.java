package com.example.shoppinglist;

import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;
import java.util.ArrayList;

public class database_manager extends SQLiteOpenHelper {

    public database_manager(Context context){
        super(context, "Shopping List", null, 1);
    }

    @Override
    public void onCreate(SQLiteDatabase db){
        String strSQL = "create table ShoppingList(id integer primary key autoincrement, store text, item text, quantity integer)";
        db.execSQL(strSQL);
    }

    @Override
    public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion){}

    public void insert(String strStore, String strItem, int intQuantity){
        SQLiteDatabase db = getWritableDatabase();
        String strSQL = "insert into ShoppingList (store, item, quantity) values ('" + strStore + "', '" + strItem + "', '" + intQuantity + "')";
        db.execSQL(strSQL);
        db.close();
    }

    public ArrayList<String> getAllItems(){
        ArrayList<String> alItems = new ArrayList<>();
        SQLiteDatabase db = getReadableDatabase();
        String strSQL = "select store, item, quantity from ShoppingList";
        Cursor cursor = db.rawQuery(strSQL, null);
        while(cursor.moveToNext()){
            String strItem = cursor.getString(0);
            alItems.add(strItem);
        }
        cursor.close();
        db.close();
        return alItems;
    }

    public ArrayList<String> getItemsByStore(String strStore){
        SQLiteDatabase db = getReadableDatabase();
        String strSQL = "select store, item, quantity from ShoppingList where store = '" + strStore + "'";
        Cursor cursor = db.rawQuery(strSQL, null);
        ArrayList<String> storeItemList = new ArrayList<>();
        if(cursor.moveToFirst()){
            String strItem = cursor.getString(2);
            String strQuantity = String.valueOf(cursor.getInt(3));
            storeItemList.add(0, strStore);
            storeItemList.add(1, strItem);
            storeItemList.add(2, strQuantity);
        }
        cursor.close();
        db.close();
        return storeItemList;
    }

    public ArrayList<String> getStores(){
        SQLiteDatabase db = getReadableDatabase();
        ArrayList<String> alStores = new ArrayList<>();
        String strSQL = "select store from ShoppingList";
        Cursor cursor = db.rawQuery(strSQL, null);
        while(cursor.moveToNext()){
            String strStore = cursor.getString(1);
            alStores.add(strStore);
        }
        cursor.close();
        db.close();
        return alStores;
    }

    public ArrayList<String> getItem(String strItem){
        SQLiteDatabase db = getReadableDatabase();
        ArrayList<String> alItems = new ArrayList<>();
        String strSQL = "select store, item, quantity from ShoppingList where item = '" + strItem + "'";
        Cursor cursor = db.rawQuery(strSQL, null);
        while(cursor.moveToNext()){
            String strStore = cursor.getString(1);
            String strQuantity = String.valueOf(cursor.getInt(2));
            alItems.add(0, strStore);
            alItems.add(1, strItem);
            alItems.add(2, strQuantity);
        }
        cursor.close();
        db.close();
        return alItems;
    }

    public void updateByItem(String strStore, String strItem, int intQuantity){
        SQLiteDatabase db = getWritableDatabase();
        String strSQL = "update ShoppingList set store = '" + strStore + "' where item = '" + strItem + "'";
        db.execSQL(strSQL);
        strSQL = "update ShoppingList set quantity = '" + intQuantity + "' where item = '" + strItem + "'";
        db.execSQL(strSQL);
        db.close();
    }

    public void delete(String strItem){
        SQLiteDatabase db = getWritableDatabase();
        String strSQL = "delete from ShoppingList where item = '" + strItem + "'";
        db.execSQL(strSQL);
        db.close();
    }
}
