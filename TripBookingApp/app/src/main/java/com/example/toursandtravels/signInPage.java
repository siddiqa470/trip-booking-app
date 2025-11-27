package com.example.toursandtravels;

import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import androidx.appcompat.app.AppCompatActivity;

public class signInPage extends AppCompatActivity {
    Button loginBtn;
    EditText emailAddress,passwordText;
    DatabaseHelper myDb;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_sign_in_page);
        loginBtn = (Button) findViewById(R.id.s_logInBtn);
        myDb = new DatabaseHelper(this);
        emailAddress = (EditText) findViewById(R.id.emailAdress);
        passwordText = (EditText) findViewById(R.id.passwordTxt);
        loginBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
              String email = emailAddress.getText().toString().trim();
              String password = passwordText.getText().toString().trim();
              boolean result = myDb.checkUser(email,password);
              if(result){
                  // Save login state
                  SharedPreferences sharedPreferences = getSharedPreferences("user_session", MODE_PRIVATE);
                  SharedPreferences.Editor editor = sharedPreferences.edit();
                  editor.putBoolean("is_logged_in", true);
                  editor.putString("user_email", email);
                  editor.apply();

                  Toast.makeText(signInPage.this, "Login Successful", Toast.LENGTH_SHORT).show();
                  Intent intent1 = new Intent(getApplicationContext(), MainActivity2.class);
                  // Clear all previous activities from the stack
                  intent1.addFlags(Intent.FLAG_ACTIVITY_CLEAR_TOP | Intent.FLAG_ACTIVITY_NEW_TASK);
                  startActivity(intent1);
                  finish(); // Finish signInPage
              } else {
                  // User doesn't exist or invalid credentials, show error message
                  Toast.makeText(signInPage.this, "Invalid email or password", Toast.LENGTH_SHORT).show();
              }
            }
        });
    }
}