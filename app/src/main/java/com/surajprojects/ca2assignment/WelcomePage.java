package com.surajprojects.ca2assignment;

import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.content.res.Resources;
import android.net.Uri;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;
import android.widget.Toast;

import java.util.ArrayList;

public class WelcomePage extends AppCompatActivity {

    Button shop, cart, contact, back;
    TextView tv, welcm_name;
    int total_num = 0;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_welcome_page);

        shop = findViewById(R.id.shop);
        cart = findViewById(R.id.count);
        contact = findViewById(R.id.contact);
        back = findViewById(R.id.back);
        tv = findViewById(R.id.total_items);
        welcm_name = findViewById(R.id.welcm_name);

        Intent intent = getIntent();
        Bundle b = intent.getExtras();
        String name = b.getString("Name");
        name = getString(R.string.welcome) + " " + name.toUpperCase();
        welcm_name.setText(name);

        shop.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(WelcomePage.this, Products.class);
                startActivityForResult(intent, 1);
            }
        });

        cart.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                String mes = getString(R.string.items)  + " " + Integer.toString(total_num);
                tv.setText(mes);
            }
        });

        contact.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(Intent.ACTION_DIAL);
                intent.setData(Uri.parse("tel:+919845678943"));
                startActivity(intent);
            }
        });

        back.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(WelcomePage.this, MainActivity.class);
                startActivity(intent);
            }
        });

    }

    @Override
    protected void onActivityResult(int requestCode, int resultCode, @Nullable Intent data) {
        super.onActivityResult(requestCode, resultCode, data);
        if(requestCode == 1 && resultCode == RESULT_OK) {
            Bundle bundle = data.getExtras();
//            ArrayList arrayList = data.getStringArrayListExtra("Result");
            ArrayList arrayList = bundle.getStringArrayList("Result");
            total_num = arrayList.size();

        }

    }
}