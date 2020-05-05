package com.example.shoppinglist;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.EditText;
import androidx.appcompat.app.AppCompatActivity;
import java.util.ArrayList;

public class item_update extends AppCompatActivity {

    private EditText etItem, etStore, etQuantity;
    private ArrayList<String> alItems;

    @Override
    protected void onCreate(Bundle savedInstanceBundle){
        super.onCreate(savedInstanceBundle);
        setContentView(R.layout.item_update);
        initFields();
        Intent intentItem = getIntent();
        String sIntentItem = intentItem.getStringExtra("ITEM");
        database_manager DBM = new database_manager(this);
        alItems = DBM.getItem(sIntentItem);
        String strStore = alItems.get(0);
        String strItem = alItems.get(1);
        int intQuantity = Integer.parseInt(alItems.get(2));
        setEditTextFields(strStore, strItem, intQuantity);
    }

    private void initFields(){
        etItem = findViewById(R.id.editItemName);
        etStore = findViewById(R.id.editStoreName);
        etQuantity = findViewById(R.id.editQuantity);
    }

    private void setEditTextFields(String strStore, String strItem, int intQuantity){
        etStore.setText(strStore);
        etItem.setText(strItem);
        etQuantity.setText(intQuantity);
    }

    public void btnDecrement(View v){
        int intQuantity = Integer.parseInt(etQuantity.getText().toString());
        intQuantity--;
        etQuantity.setText(intQuantity);
    }

    public void btnIncrement(View v){
        int intQuantity = Integer.parseInt(etQuantity.getText().toString());
        intQuantity++;
        etQuantity.setText(intQuantity);
    }

    public void btnSubmitEdit(View v){
        String strStore = etStore.getText().toString();
        String strItem = etItem.getText().toString();
        int intQuantity = Integer.parseInt(etQuantity.getText().toString());
        database_manager DBM = new database_manager(this);
        DBM.updateByItem(strStore, strItem, intQuantity);
        finish();
    }
}
