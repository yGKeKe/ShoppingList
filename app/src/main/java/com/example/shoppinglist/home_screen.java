package com.example.shoppinglist;

import android.content.Intent;
import android.os.Bundle;
import android.view.MenuItem;
import android.view.View;
import android.widget.LinearLayout;
import android.widget.PopupMenu;
import android.widget.TextView;
import androidx.appcompat.app.AppCompatActivity;
import java.util.ArrayList;

public class home_screen extends AppCompatActivity {

    private LinearLayout llItemList;

    @Override
    protected void onCreate(Bundle savedInstanceState){
        super.onCreate(savedInstanceState);
        setContentView(R.layout.home_screen);
        this.llItemList = findViewById(R.id.llItemList);
        populateItemList();
    }

    @Override
    protected void onRestart(){
        super.onRestart();
        populateItemList();
    }

    private void populateItemList(){
        llItemList.removeAllViews();
        database_manager DBM = new database_manager(this);
        ArrayList<String> alItemList = DBM.getAllItems();
        for(String s : alItemList){
            TextView tvItem = tvItemGenerator(s);
            tvItem.setOnClickListener((v) -> {
                Intent intent = new Intent(getApplicationContext(), item_update.class );
                intent.putExtra("ITEM", ((TextView) v).getText().toString());
                startActivity(intent);
            });
            llItemList.addView(tvItem);
        }
    }

    private void populateItemListByStore(String strSelectedStore){
        llItemList.removeAllViews();
        database_manager DBM = new database_manager(this);
        ArrayList<String> alItemList = DBM.getItemsByStore(strSelectedStore);
        for(String s : alItemList){
            TextView tvItem = tvItemGenerator(s);
            tvItem.setOnClickListener((v) -> {
                Intent intent = new Intent(getApplicationContext(), item_update.class );
                intent.putExtra("ITEM", ((TextView) v).getText().toString());
                startActivity(intent);
            });
            llItemList.addView(tvItem);
        }
    }

    private TextView tvItemGenerator(String strItem){
        TextView tvItem = new TextView(this);
        tvItem.setText(strItem);
        tvItem.setTextSize(15);
        tvItem.setPadding(15, 15, 15, 15);
        return tvItem;
    }

    public void btnAddEntry(View v){
        Intent intent = new Intent(this, add_item.class);
        startActivity(intent);
    }

    public void btnDisplayByStore(View v){
        PopupMenu puStoreSelect = new PopupMenu(home_screen.this, findViewById(R.id.btnSearchByStore));
        database_manager DBM = new database_manager(this);
        ArrayList<String> alStores = DBM.getStores();
        for(int i = 0; i < alStores.size(); i++){
            MenuItem item = puStoreSelect.getMenu().add(alStores.get(i));
            item.setOnMenuItemClickListener((nItem) -> {
                    populateItemListByStore(nItem.getTitle().toString());
                    return false;
                });
        }
        puStoreSelect.getMenuInflater().inflate(alStores.size(), puStoreSelect.getMenu());
        puStoreSelect.show();
    }
}
