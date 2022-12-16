package com.example.mysubway;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;

import android.annotation.SuppressLint;
import android.app.ProgressDialog;
import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import com.google.android.gms.auth.api.signin.GoogleSignIn;
import com.google.android.gms.auth.api.signin.GoogleSignInAccount;
import com.google.android.gms.auth.api.signin.GoogleSignInClient;
import com.google.android.gms.auth.api.signin.GoogleSignInOptions;
import com.google.android.gms.common.api.ApiException;
import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;
import com.google.android.material.button.MaterialButton;
import com.google.firebase.auth.AuthCredential;
import com.google.firebase.auth.AuthResult;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;
import com.google.firebase.auth.GoogleAuthProvider;

public class MainActivity extends AppCompatActivity {

    TextView username,password,signuptxt2;
    MaterialButton loginbtn;
    GoogleSignInClient mGoogleSignInClient;
    EditText input_password,input_email;
    String emailPattern = "[a-zA-z-1-9]+@[a-z]+\\.+[a-z]+";
    //    String usernamePattern="^[A-Za-z][A-Za-z0-9_]{7,29}$";
    ProgressDialog progressDialog;
    FirebaseAuth mAuth;
    FirebaseUser mUser;

    ImageView btngoogle;
    ImageView btnfacebook;


    @SuppressLint("MissingInflatedId")
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        username = findViewById(R.id.username);
        password = findViewById(R.id.password);
        loginbtn = findViewById(R.id.loginbtn);
        signuptxt2 = findViewById(R.id.signuptext);
        input_password = findViewById(R.id.password);
        input_email = findViewById(R.id.username);
        progressDialog = new ProgressDialog(this);

        btngoogle = findViewById(R.id.google_logo_login);
        btnfacebook = findViewById(R.id.facebook_logo_login);

        mAuth =FirebaseAuth.getInstance();
        mUser = mAuth.getCurrentUser();
        


        signuptxt2.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(MainActivity.this,signuppage.class);
                startActivity(intent);
                finish();
            }
        });

        loginbtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                performlogin();

            }
        });

        btngoogle.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(MainActivity.this,GoogleSignInActivity.class);
                startActivity(intent);
            }
        });
        btnfacebook.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(MainActivity.this,facebookauthactivity.class);
                startActivity(intent);
            }
        });



    }

    public void performlogin() {
        String email = input_email.getText().toString();
        String password = input_password.getText().toString();;

        if(!email.matches(emailPattern)){
            input_email.setError("Enter correct Email");
        }
        else if(password.isEmpty()){
            input_password.setError("enter the password");
        }
        else if(password.length()<8){
            input_password.setError("minimum 8 character required");
        }
        else {
            progressDialog.setMessage("please wait while Login..");
            progressDialog.setTitle("Login");
            progressDialog.setCanceledOnTouchOutside(false);
            progressDialog.show();

            mAuth.signInWithEmailAndPassword(email, password).addOnCompleteListener(new OnCompleteListener<AuthResult>() {
                @Override
                public void onComplete(@NonNull Task<AuthResult> task) {
                    if (task.isSuccessful()) {

                        sendusertonextactivity();
                        Toast.makeText(MainActivity.this, "Login successful", Toast.LENGTH_SHORT).show();
                        progressDialog.dismiss();
                    } else {

                        Toast.makeText(MainActivity.this, "" + task.getException(), Toast.LENGTH_SHORT).show();
                        progressDialog.dismiss();
                    }
                }
            });
        }
        Toast.makeText(this, "this is working", Toast.LENGTH_SHORT).show();
    }
    private void sendusertonextactivity() {
        Intent intent = new Intent(MainActivity.this,dashboard.class);
        intent.setFlags(Intent.FLAG_ACTIVITY_CLEAR_TASK | Intent.FLAG_ACTIVITY_NEW_TASK);
        startActivity(intent);
    }
}