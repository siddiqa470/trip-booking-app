package com.example.toursandtravels;

import androidx.appcompat.app.AppCompatActivity;

import android.app.DatePickerDialog;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.AutoCompleteTextView;
import android.widget.Button;
import android.widget.DatePicker;
import android.widget.EditText;
import android.widget.RadioButton;
import android.widget.RadioGroup;
import android.widget.Toast;

import com.google.android.material.textfield.TextInputLayout;

import java.util.Calendar;

public class book_now2 extends AppCompatActivity {
    String[] countries={"India","Pakistan","America","China","Russia","Brazil","France","Japan","Germany","Australia"};
    String[] days={"1D/1N","2D/1N","3D/2N","4D/3N","5D/4N","6D/5N","7D/6N"};
    AutoCompleteTextView actv1,actv2;
    EditText full_name,contact_no,check_in,check_out,adults,child,rooms;
    RadioGroup gender_rgp,payment_rgp;
    TextInputLayout txtlayoutInput1,txtlayoutInput2;
    BookingDatabaseHelper bkDb;
    DatabaseHelper userDb; // For getting the current user's email
    Button booked;
    String placeName; // To store the incoming place name

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_book_now2);

        // Get the place name from the intent
        Intent intent = getIntent();
        placeName = intent.getStringExtra("PLACE_NAME");

        actv1= (AutoCompleteTextView) findViewById(R.id.auto_comp_txtV1);
        actv2 = (AutoCompleteTextView) findViewById(R.id.auto_comp_txtV);
        txtlayoutInput1 = (TextInputLayout) findViewById(R.id.daysSelect);
        txtlayoutInput2 = (TextInputLayout) findViewById(R.id.countrySelect);
        booked = (Button) findViewById(R.id.booked);
        full_name = (EditText) findViewById(R.id.full_name);
        contact_no = (EditText) findViewById(R.id.contact_no);
        check_in = (EditText) findViewById(R.id.check_in);
        check_out = (EditText) findViewById(R.id.check_out);
        adults = (EditText) findViewById(R.id.adults);
        child = (EditText) findViewById(R.id.childs);
        rooms = (EditText) findViewById(R.id.rooms);
        gender_rgp = (RadioGroup) findViewById(R.id.gender_rgp);
        payment_rgp = (RadioGroup) findViewById(R.id.payment_rgp);

        ArrayAdapter<String> daysArrayAdapter = new ArrayAdapter<>(this,R.layout.dropdown_list,days);
        ArrayAdapter<String> countryArrayAdapter = new ArrayAdapter<>(this,R.layout.dropdown_list,countries);
        actv1.setAdapter(countryArrayAdapter);
        actv2.setAdapter(daysArrayAdapter);

        bkDb = new BookingDatabaseHelper(this);
        userDb = new DatabaseHelper(this); // Initialize userDb

        check_in.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Calendar calendar = Calendar.getInstance();
                int year = calendar.get(Calendar.YEAR);
                int month = calendar.get(Calendar.MONTH);
                int dayOfMonth = calendar.get(Calendar.DAY_OF_MONTH);
                DatePickerDialog datePickerDialog = new DatePickerDialog(book_now2.this, new DatePickerDialog.OnDateSetListener() {
                    @Override
                    public void onDateSet(DatePicker view, int year, int month, int dayOfMonth) {
                    check_in.setText(dayOfMonth+" - "+(month+1)+" - "+year);
                    }
                },year, month, dayOfMonth);
                datePickerDialog.show();
            }
        });

        check_out.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Calendar calendar = Calendar.getInstance();
                int year = calendar.get(Calendar.YEAR);
                int month = calendar.get(Calendar.MONTH);
                int dayOfMonth = calendar.get(Calendar.DAY_OF_MONTH);
                DatePickerDialog datePickerDialog = new DatePickerDialog(book_now2.this, new DatePickerDialog.OnDateSetListener() {
                    @Override
                    public void onDateSet(DatePicker view, int year, int month, int dayOfMonth) {
                        check_out.setText(dayOfMonth+" - "+(month+1)+" - "+year);
                    }
                },year, month, dayOfMonth);
                datePickerDialog.show();
            }
        });

        actv1.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> parent, View view, int position, long l) {
                String country =  parent.getItemAtPosition(position).toString();
                txtlayoutInput2.getEditText().setText(country);
            }
        });
        actv2.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> parent, View view, int position, long l) {
                String day = parent.getItemAtPosition(position).toString();
                txtlayoutInput1.getEditText().setText(day);
            }
        });
       booked.setOnClickListener(new View.OnClickListener() {
           @Override
           public void onClick(View view) {
            insertData();
           }
       });
    }
    public void insertData(){
        UserData currentUser = userDb.getUserData();
        if (currentUser == null) {
            Toast.makeText(getApplicationContext(),"Error: Could not find logged-in user.",Toast.LENGTH_SHORT).show();
            return;
        }

        String fullName = full_name.getText().toString().trim();
        String emailAddress = currentUser.getEmail(); // Get email from logged-in user
        String contactNo = contact_no.getText().toString().trim();
        String countryName = actv1.getText().toString().trim();
        String genderValue = getSelectedGender();
        String travellingDetails = actv2.getText().toString();
        String checkIn = check_in.getText().toString().trim();
        String checkOut = check_out.getText().toString().trim();
        String adultsValue = adults.getText().toString().trim();
        String childValue = child.getText().toString().trim();
        String roomsValue = rooms.getText().toString().trim();
        String paymentMethod = getPaymentMethod();

        boolean inserted = bkDb.insertData(fullName,emailAddress,contactNo,countryName,genderValue,travellingDetails,checkIn,checkOut,adultsValue,childValue,roomsValue,paymentMethod, placeName);
        if(inserted){
            Toast.makeText(getApplicationContext(),"Data is Enter into the Database",Toast.LENGTH_SHORT).show();
        } else{
            Toast.makeText(getApplicationContext(),"Something is wrong ",Toast.LENGTH_SHORT).show();
        }
    }
    private String getSelectedGender() {
        int selectedId = gender_rgp.getCheckedRadioButtonId();
        if (selectedId != -1) {
            RadioButton selectedRadioButton = findViewById(selectedId);
            return selectedRadioButton.getText().toString().trim();
        } else {
            return "Not selected"; // Default value when no gender is selected
        }
    }

    private String getPaymentMethod() {
        int selectedId = payment_rgp.getCheckedRadioButtonId();
        if (selectedId != -1) {
            RadioButton selectedPaymentMethods = findViewById(selectedId);
            return selectedPaymentMethods.getText().toString().trim();
        } else {
            return "Not selected"; // Default value when no payment method is selected
        }
    }
}
