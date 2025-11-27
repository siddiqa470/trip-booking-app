package com.example.toursandtravels;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import androidx.appcompat.app.AppCompatActivity;

public class registration_page extends AppCompatActivity {
    DatabaseHelper myDb;
    EditText name,email,contact,password;
    Button signUpBtn;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_registration_page);
        myDb = new DatabaseHelper(this);
        name = (EditText) findViewById(R.id.name_edittxt);
        email = (EditText) findViewById(R.id.email_edittxt);
        contact = (EditText) findViewById(R.id.contact_edittxt);
        password = (EditText) findViewById(R.id.passwd_edittxt);
        signUpBtn = (Button) findViewById(R.id.signUp);
        signUpBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                insertData();
            }
        });
    }
    public void insertData() {
        boolean inserted = myDb.insertData(name.getText().toString(), email.getText().toString(), contact.getText().toString(), password.getText().toString());
        if (inserted) {
            String insertedName = name.getText().toString();
            String insertedEmail = email.getText().toString();
            String insertedContact = contact.getText().toString();
            String insertedPassword = password.getText().toString();
            Toast.makeText(registration_page.this, "Login Successfully", Toast.LENGTH_SHORT).show();
            Intent intent = new Intent(getApplicationContext(), MainActivity.class);
            startActivity(intent);
        } else {
            Toast.makeText(registration_page.this, "UnSuccessfully", Toast.LENGTH_SHORT).show();
        }
    }
}