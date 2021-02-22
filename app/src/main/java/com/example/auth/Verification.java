package com.example.auth;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;
import com.google.android.material.textfield.TextInputEditText;
import com.google.firebase.FirebaseException;
import com.google.firebase.auth.AuthResult;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.PhoneAuthCredential;
import com.google.firebase.auth.PhoneAuthOptions;
import com.google.firebase.auth.PhoneAuthProvider;

import java.util.concurrent.TimeUnit;

public class Verification extends AppCompatActivity {
    TextInputEditText t1;
    Button button3;
    String phoneNumber;
    String otpid;
    FirebaseAuth mAuth;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_verification);

        t1=findViewById(R.id.t1);
        button3=findViewById(R.id.button3);
        phoneNumber=getIntent().getStringExtra("mobile").toString();
        mAuth=FirebaseAuth.getInstance();
        initiateOtp(phoneNumber);

        button3.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

                if(t1.getText().toString().isEmpty())
                    Toast.makeText(getApplicationContext(),"Blank Field can not be processed",Toast.LENGTH_LONG).show();
                else if(t1.getText().toString().length()!=6)
                    Toast.makeText(getApplicationContext(),"INvalid OTP",Toast.LENGTH_LONG).show();
                else
                {
                    PhoneAuthCredential credential=PhoneAuthProvider.getCredential(otpid,t1.getText().toString());
                    signInWithPhoneAuthCredential(credential);
                }

            }
        });
    }


    private void initiateOtp(String phoneNumber) {
        PhoneAuthOptions options =
                PhoneAuthOptions.newBuilder(mAuth)
                        .setPhoneNumber(phoneNumber)       // Phone number to verify
                        .setTimeout(60L, TimeUnit.SECONDS) // Timeout and unit
                        .setActivity(this)                 // Activity (for callback binding)
                        .setCallbacks(mCallbacks)          // OnVerificationStateChangedCallbacks
                        .build();
        PhoneAuthProvider.verifyPhoneNumber(options);
    }

    PhoneAuthProvider.OnVerificationStateChangedCallbacks mCallbacks =
                new PhoneAuthProvider.OnVerificationStateChangedCallbacks()
                {
                    @Override
                    public void onCodeSent(String s, PhoneAuthProvider.ForceResendingToken forceResendingToken)
                    {
                        otpid=s;
                    }

                    @Override
                    public void onVerificationCompleted(PhoneAuthCredential phoneAuthCredential)
                    {
                        signInWithPhoneAuthCredential(phoneAuthCredential);
                    }

                    @Override
                    public void onVerificationFailed(FirebaseException e) {
                        Toast.makeText(getApplicationContext(),e.getMessage(),Toast.LENGTH_LONG).show();
                    }
                };

    private void signInWithPhoneAuthCredential(PhoneAuthCredential credential) {
        mAuth.signInWithCredential(credential)
                .addOnCompleteListener(this, new OnCompleteListener<AuthResult>() {
                    @Override
                    public void onComplete(@NonNull Task<AuthResult> task) {
                        if (task.isSuccessful())
                        {
                            startActivity(new Intent(Verification.this,dashboard.class));
                            finish();

                        } else {
                            Toast.makeText(getApplicationContext(),"SignIn Code Error",Toast.LENGTH_LONG).show();
                        }
                    }
                });
    }

}





















