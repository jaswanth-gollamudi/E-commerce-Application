package com.example.auth;

import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.os.Bundle;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.TextView;
import java.util.ArrayList;
import java.util.List;

public class dashboard extends AppCompatActivity {
    TextView CompanyName,Description;
    ImageView Settings,Cart,Banner;
    EditText Search;
    RecyclerView mList1;
    List<App> appList;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_dashboard);
        CompanyName=findViewById(R.id.companyName);
        Description=findViewById(R.id.description);
        Settings = findViewById(R.id.settings);
        Cart=findViewById(R.id.cart);
        Banner=findViewById(R.id.banner);
        Search=findViewById(R.id.search);
        mList1 = findViewById(R.id.view);
        appList = new ArrayList<>();

        appList.add(new App(R.drawable.resizes1));
        appList.add(new App(R.drawable.resize2));
        appList.add(new App(R.drawable.resize3));
        appList.add(new App(R.drawable.resize4));
        appList.add(new App(R.drawable.resizes1));
        appList.add(new App(R.drawable.resize2));

        LinearLayoutManager manager1 = new LinearLayoutManager(this);
        manager1.setOrientation(LinearLayoutManager.HORIZONTAL);
        mList1.setLayoutManager(manager1);


        CustomAdaptor adaptor1 = new CustomAdaptor(this,appList);
        mList1.setAdapter(adaptor1);


    }
}