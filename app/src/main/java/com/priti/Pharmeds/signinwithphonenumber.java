package com.priti.Pharmeds;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ProgressBar;
import android.text.TextWatcher;
import android.content.Intent;
import android.os.Bundle;
import android.text.Editable;
import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.FirebaseException;
import com.google.firebase.auth.AuthResult;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.PhoneAuthCredential;
import com.google.firebase.auth.PhoneAuthOptions;
import com.google.firebase.auth.PhoneAuthProvider;

public class signinwithphonenumber extends AppCompatActivity {
    String getotpbackend;
    String phonenumber;
    String otpid;
    FirebaseAuth mAuth;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_signinwithphonenumber);
        EditText ed1 = (EditText) findViewById(R.id.otp1);
        EditText ed2 = (EditText) findViewById(R.id.otp2);
        EditText ed3 = (EditText) findViewById(R.id.otp3);
        EditText ed4 = (EditText) findViewById(R.id.otp4);
        EditText ed5 = (EditText) findViewById(R.id.otp5);
        EditText ed6 = (EditText) findViewById(R.id.otp6);
        final Button b2 = (Button) findViewById(R.id.verify);
        mAuth = FirebaseAuth.getInstance();

        getotpbackend = getIntent().getStringExtra("backendotp");
        phonenumber = getIntent().getStringExtra("mobile".toString());

    }
}