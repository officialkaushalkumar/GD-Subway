package com.example.mysubway;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import android.app.ProgressDialog;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;
import com.google.android.material.button.MaterialButton;
import com.google.firebase.auth.AuthResult;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;

public class signuppage extends AppCompatActivity {

    TextView logintext;
    EditText input_username,input_password,input_email,input_confirmpassword;
    MaterialButton register;
    String emailPattern = "[a-zA-z-1-9]+@[a-z]+\\.+[a-z]+";
//    String usernamePattern="^[A-Za-z][A-Za-z0-9_]{7,29}$";
    ProgressDialog progressDialog;
    FirebaseAuth mAuth;
    FirebaseUser mUser;



    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_signuppage);
        logintext = findViewById(R.id.logintext);

        register = findViewById(R.id.signupbtn);
        input_username=findViewById(R.id.usernamesignup);
        input_password = findViewById(R.id.passwordsignup);
        input_email = findViewById(R.id.emailsignup);
        input_confirmpassword = findViewById(R.id.confirmpasswordsignup);
        progressDialog = new ProgressDialog(this);

        mAuth =FirebaseAuth.getInstance();
        mUser = mAuth.getCurrentUser();


        logintext.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent=new Intent(signuppage.this,MainActivity.class);
                startActivity(intent);
                finish();
            }
        });

        register.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                performauthentication();
            }
        });

    }

    private void performauthentication() {
        String email = input_email.getText().toString();
        String username = input_username.getText().toString();
        String password = input_password.getText().toString();;
        String confirm_pass = input_confirmpassword.getText().toString();
        String index_first = String.valueOf(username.charAt(0));

        if(username.isEmpty()){
            input_username.setError("username cannot be empty");
        }
        else if(username.length()<7 || username.length()>15){
            input_username.setError("username should be greater than 7 and less than 20");
        }
        else if( index_first=="0"|| index_first=="1" || index_first=="2" ||index_first=="3" || index_first=="4" || index_first=="5" || index_first=="6" || index_first=="7"||index_first=="8"||index_first=="9"){
            input_username.setError("first letter cannot be number");
        }
        else if(!email.matches(emailPattern)){
            input_email.setError("Enter correct Email");
        }
        else if(password.isEmpty()){
            input_password.setError("enter the password");
        }
        else if(password.length()<8){
            input_password.setError("minimum 8 character required");
        }
        else if(confirm_pass.isEmpty()){
            input_confirmpassword.setError("confirm password should not be empty");
        }
        else if(!password.equals(confirm_pass)){
            input_confirmpassword.setError("password does not matches");
        }
        else{
            progressDialog.setMessage("please wait while Registration...");
            progressDialog.setTitle("Registration");
            progressDialog.setCanceledOnTouchOutside(false);
            progressDialog.show();
            mAuth.createUserWithEmailAndPassword(email,password).addOnCompleteListener(new OnCompleteListener<AuthResult>() {
                @Override
                public void onComplete(@NonNull Task<AuthResult> task) {
                    if(task.isSuccessful()){
                        progressDialog.dismiss();
                        sendusertonextactivity();
                        Toast.makeText(signuppage.this, "Registration successful", Toast.LENGTH_SHORT).show();
                    }
                    else{
                        progressDialog.dismiss();
                        Toast.makeText(signuppage.this, ""+task.getException() , Toast.LENGTH_SHORT).show();
                    }
                }
            });
        }


    }

    private void sendusertonextactivity() {
        Intent intent = new Intent(signuppage.this,MainActivity.class);
        intent.setFlags(Intent.FLAG_ACTIVITY_CLEAR_TASK | Intent.FLAG_ACTIVITY_NEW_TASK);
        startActivity(intent);
    }
}