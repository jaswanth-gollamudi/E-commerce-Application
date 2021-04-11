package com.example.auth;

import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.TextView;


import com.example.auth.views.ProductMain;

import java.util.ArrayList;
import java.util.List;

public class dashboard extends AppCompatActivity implements CustomAdaptor.OnNoteListener {
    TextView CompanyName,Description;
    ImageView Settings,Cart,Banner;
    EditText Search;
    RecyclerView mList1;
    List<App> appList;
    private static final String TAG = "dashboard";

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_dashboard);
        CompanyName=findViewById(R.id.companyName);
        Description=findViewById(R.id.description);
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


        CustomAdaptor adaptor1 = new CustomAdaptor(this,appList,this::onNoteClick);
        mList1.setAdapter(adaptor1);


    }

    @Override
    public void onNoteClick(int position) {
        Log.d(TAG, "onNoteClick: clicked.");
        Intent intent = new Intent(this, ProductMain.class);
        startActivity(intent);

    }
}