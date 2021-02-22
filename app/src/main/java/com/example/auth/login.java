package com.example.auth;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import android.app.AlertDialog;
import android.app.ProgressDialog;
import android.content.Intent;
import android.os.Bundle;
import android.util.Patterns;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import com.google.android.gms.tasks.OnFailureListener;
import com.google.android.gms.tasks.OnSuccessListener;
import com.google.firebase.auth.AuthResult;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseAuthInvalidCredentialsException;
import com.google.firebase.auth.FirebaseAuthInvalidUserException;
import com.google.firebase.auth.FirebaseUser;

public class login extends AppCompatActivity {
    FirebaseAuth mAuth;
    TextView not_registered;
    EditText userEmail, userPassword;
    Button btnLogin;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_login);
        not_registered=findViewById(R.id.notRegistered);
        userEmail=findViewById(R.id.userName);
        userPassword=findViewById(R.id.password);
        btnLogin=findViewById(R.id.appCompatButton);
        mAuth = FirebaseAuth.getInstance();

        not_registered.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent=new Intent(login.this,Register.class);
                startActivity(intent);
            }
        });
        btnLogin.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                startLogin();
            }
        });
    }

    private void startLogin() {
        String email = userEmail.getText().toString().trim();
        String password = userPassword.getText().toString().trim();
        if (email.isEmpty()){
            createAlert("Error", "Please enter your email!", "OK");
        }else if(!Patterns.EMAIL_ADDRESS.matcher(email).matches()){
            createAlert("Error", "Please enter a valid email!", "OK");
        }else if(password.isEmpty()){
            createAlert("Error", "Please enter your password!", "OK");
        }else{
            final ProgressDialog progressDialog = new ProgressDialog(this);
            progressDialog.setMessage("Please wait...");
            progressDialog.setCancelable(false);
            progressDialog.setCanceledOnTouchOutside(false);
            progressDialog.show();
            mAuth.signInWithEmailAndPassword(email, password).addOnSuccessListener(new OnSuccessListener<AuthResult>() {
                @Override
                public void onSuccess(AuthResult authResult) {
                    progressDialog.dismiss();
                    FirebaseUser user = authResult.getUser();
                    startDashboardActivity();
                }
            }).addOnFailureListener(new OnFailureListener() {
                @Override
                public void onFailure(@NonNull Exception e) {
                    progressDialog.dismiss();
                    if (e instanceof FirebaseAuthInvalidUserException){
                        createAlert("Error", "This email is not registered with us!", "OK");
                    }else if(e instanceof FirebaseAuthInvalidCredentialsException){
                        createAlert("Error", "Invalid Password! Please try again.", "OK");
                    }else{
                        Toast.makeText(login.this, "Unable to login! Please try after some time.", Toast.LENGTH_SHORT).show();
                        e.printStackTrace();
                    }
                }
            });
        }
    }


    private void createAlert(String alertTitle, String alertMessage, String positiveText){
        AlertDialog.Builder builder = new AlertDialog.Builder(this);
        builder.setTitle(alertTitle)
                .setMessage(alertMessage)
                .setPositiveButton(positiveText, null)
                .create().show();
    }

    public void startDashboardActivity() {
        Intent intent = new Intent(this, dashboard.class);
        startActivity(intent);
    }

}





