package com.priti.Pharmeds;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import android.content.Intent;
import android.os.Bundle;
import android.text.TextUtils;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ProgressBar;
import android.widget.TextView;
import android.widget.Toast;

import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.auth.AuthResult;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;

public class Signup extends AppCompatActivity {
    EditText name, email, password;
    private FirebaseAuth mAuth;
    Button Signup;
    ProgressBar progressBar;
    TextView login;

    FirebaseDatabase rootNode;
    DatabaseReference reference;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_signup);

        Signup = findViewById(R.id.signup);
        name = findViewById(R.id.name);
        email = findViewById(R.id.email);
        password = findViewById(R.id.password);
        mAuth = FirebaseAuth.getInstance();
        login = findViewById(R.id.login);

        login.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                startActivity(new Intent(Signup.this, login.class));
            }
        });

        Signup.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                createUser();
                rootNode = FirebaseDatabase.getInstance();
                reference=rootNode.getReference();
                String userId=FirebaseAuth.getInstance().getCurrentUser().getUid();
                //get all the values
                String mname=name.getText().toString();
                String memail=email.getText().toString();
                String mpassword = password.getText().toString();


                UserModel userModel=new UserModel(mname,memail,mpassword);
                reference.child("Users").child(userId).setValue(userModel);
            }
        });
    }

    @Override
    protected void onStart() {
        super.onStart();

        // Check if user is signed in (non-null) and update UI accordingly.
        FirebaseUser currentUser = mAuth.getCurrentUser();
        if (currentUser != null) {
            Toast.makeText(this, "Already In " + currentUser.getEmail(), Toast.LENGTH_SHORT).show();

        }
    }

    @Override
    public void onBackPressed() {
        super.onBackPressed();
        finishAffinity();
    }

    private void createUser() {
        String name1 = name.getText().toString();
        String email1 = email.getText().toString();
        String password1 = password.getText().toString();

        if (TextUtils.isEmpty(email1)) {
            email.setError("Email cannot be Empty");
            email.requestFocus();
        } else if (TextUtils.isEmpty(password1)) {
            password.setError("Password cannot be Empty");
            password.requestFocus();
        } else if (TextUtils.isEmpty(name1)) {
            name.setError("Name cannot be Empty");
            name.requestFocus();
        } else {
            mAuth.createUserWithEmailAndPassword(email1, password1).addOnCompleteListener(new OnCompleteListener<AuthResult>() {
                @Override
                public void onComplete(@NonNull Task<AuthResult> task) {
                    if (task.isSuccessful()) {
                        Toast.makeText(Signup.this, "Registration Successful!", Toast.LENGTH_SHORT).show();
                        FirebaseUser user = mAuth.getCurrentUser();
                        Toast.makeText(Signup.this, "" + user.getUid(), Toast.LENGTH_SHORT).show();
                        // [START send_email_verification]
                        user.sendEmailVerification()
                                .addOnCompleteListener(Signup.this, new OnCompleteListener<Void>() {
                                    @Override
                                    public void onComplete(@NonNull Task<Void> task) {
                                        // Email sent
                                        Toast.makeText(Signup.this, "Verification email sent!", Toast.LENGTH_SHORT).show();
                                    }
                                });
                        // [END send_email_verification]
                    } else {
                        Toast.makeText(Signup.this, "Authentication Failed!", Toast.LENGTH_SHORT).show();
                    }
                }
            });
        }

    }
}