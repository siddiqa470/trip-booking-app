// ProfilePage.java

package com.example.toursandtravels;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

public class ProfilePage extends AppCompatActivity {
    DatabaseHelper myDb;
    EditText name, email, contact, password;
    TextView userShortName,username;
    Button update_info;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_profile_page);

        myDb = new DatabaseHelper(this);
        userShortName = (TextView) findViewById(R.id.userShortName);
        username = (TextView) findViewById(R.id.username);
        update_info = (Button) findViewById(R.id.update_info);

        name = findViewById(R.id.name_edittxt);
        email = findViewById(R.id.email_edittxt);
        contact = findViewById(R.id.contact_edittxt);
        password = findViewById(R.id.passwd_edittxt);

        UserData userData = myDb.getUserData();
        if (userData != null) {
            name.setText(userData.getName());
            email.setText(userData.getEmail());
            contact.setText(userData.getContact());
            password.setText(userData.getPassword());
        }
        if (userData != null && userData.getName().length() >= 2) {
            String shortName = userData.getName().substring(0, 2);
            userShortName.setText(shortName);
        } else {
            userShortName.setText("N/A");
        }
        username.setText(userData.getName());
        update_info.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                String newName = name.getText().toString().trim();
                String newEmail = email.getText().toString().trim(); // Remove redundant toString() call
                String newContact = contact.getText().toString().trim();
                String newPassword = password.getText().toString().trim();

                boolean updated = myDb.updateUserData(newName, newEmail, newContact, newPassword);
                if (updated) {
                    Toast.makeText(getApplicationContext(), "User Info is Updated", Toast.LENGTH_SHORT).show();
                } else {
                    Toast.makeText(getApplicationContext(), "User Info is not Updated", Toast.LENGTH_SHORT).show();
                }
            }
        });

    }
}
