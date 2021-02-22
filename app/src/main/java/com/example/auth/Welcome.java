package com.example.auth;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import com.google.android.material.textfield.TextInputEditText;
import com.hbb20.CountryCodePicker;

public class Welcome extends AppCompatActivity {
    CountryCodePicker ccp;
    Button button2;
    TextInputEditText t2;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_welcome);
        t2=findViewById(R.id.t2);
        ccp=findViewById(R.id.ccp);
        ccp.registerCarrierNumberEditText(t2);
        button2=(Button)findViewById(R.id.button2);

        button2.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent=new Intent(Welcome.this,Verification.class);
                intent.putExtra("mobile",ccp.getFullNumberWithPlus().replace(" ",""));
                Toast.makeText(getApplicationContext(),"Processing",Toast.LENGTH_LONG).show();
                startActivity(intent);
            }
        });
    }
}


