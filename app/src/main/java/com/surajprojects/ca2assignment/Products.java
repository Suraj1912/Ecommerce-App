package com.surajprojects.ca2assignment;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.GridView;
import android.widget.TextView;
import android.widget.Toast;

import java.util.ArrayList;

public class Products extends AppCompatActivity {

    GridView gridView;
    TextView textView;
    ArrayAdapter adapter;
    ArrayList total_prod = new ArrayList();

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_products);

        gridView = findViewById(R.id.gridview);
        textView = findViewById(R.id.tv);
        final String[] products = {"Samsung Galaxy M21", "OnePlus 8T 5G", "iPhone 11", "OnePlus Nord 5G", "Redmi 9A",
                               "Redmi Note 9 Pro", "Syska 20000 PowerBank", "Fossil Sport Watch", "Fastrack Watch",
                                "Casio G-Shock Watch", "Vivo Y20", "Vivo Y50", "Oppo Reno3 Pro", "Mi 10 5G"};

        adapter = new ArrayAdapter(this, android.R.layout.simple_expandable_list_item_1, products);
        gridView.setAdapter(adapter);

        gridView.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
                String product = adapter.getItem(position).toString();

                if(!check(product)) {
                    Toast.makeText(getApplicationContext(), product + " " + getString(R.string.added), Toast.LENGTH_LONG).show();
                    total_prod.add(product);
                }
                else {
                    Toast.makeText(getApplicationContext(), product + " " + getString(R.string.not_added), Toast.LENGTH_LONG).show();
                }
            }
        });
    }

    boolean check(String product){

        boolean flag = false;
        if(!total_prod.isEmpty()){
            for(int i = 0; i < total_prod.size(); i++){
                if(total_prod.get(i).toString() == product){
                    flag = true;
                    break;
                }
            }
        }
        return flag;
    }

    @Override
    public void onBackPressed() {
        Intent intent = new Intent();

//        intent.putStringArrayListExtra("Result", total_prod);
        intent.putExtra("Result", total_prod);
        setResult(RESULT_OK, intent);
        finish();
        super.onBackPressed();
    }
}