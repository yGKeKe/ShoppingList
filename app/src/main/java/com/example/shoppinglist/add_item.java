package com.example.shoppinglist;

import android.os.Bundle;
import android.view.View;
import android.widget.EditText;
import androidx.appcompat.app.AppCompatActivity;

public class add_item extends AppCompatActivity {

    private EditText etItem, etStore, etQuantity;

    @Override
    protected void onCreate(Bundle savedInstanceState){
        super.onCreate(savedInstanceState);
        setContentView(R.layout.add_item);
        initFields();
    }

    private void initFields(){
        etItem = findViewById(R.id.editItemName);
        etStore = findViewById(R.id.editStoreName);
        etQuantity = findViewById(R.id.editQuantity);
    }

    public void btnAddEntry(View v){
        database_manager DBM = new database_manager(this);
        String strItem = etItem.getText().toString();
        String strStore = etStore.getText().toString();
        int intQuantity = Integer.parseInt(etQuantity.getText().toString());
        DBM.insert(strItem, strStore, intQuantity);
        finish();
    }
}
