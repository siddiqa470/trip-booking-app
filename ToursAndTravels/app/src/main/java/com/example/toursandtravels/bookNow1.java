package com.example.toursandtravels;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.AutoCompleteTextView;

import com.google.android.material.textfield.TextInputLayout;

public class bookNow1 extends AppCompatActivity {
    String[] days={"1D/1N","2D/1N","3D/2N","4D/3N","5D/4N","6D/5N","7D/6N"};
    TextInputLayout txtlayoutInput;

    AutoCompleteTextView actv;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_book_now1);
        actv =(AutoCompleteTextView) findViewById(R.id.auto_comp_txtV);
        txtlayoutInput = (TextInputLayout) findViewById(R.id.daysSelect);
        ArrayAdapter<String> adapter = new ArrayAdapter<>(this,R.layout.dropdown_list,days);
        actv.setAdapter(adapter);
        actv.setThreshold(2);
       actv.setOnItemClickListener(new AdapterView.OnItemClickListener() {
           @Override
           public void onItemClick(AdapterView<?> parent, View view, int position, long l) {
            String day = parent.getItemAtPosition(position).toString();
               txtlayoutInput.getEditText().setText(day);
           }
       });
    }
}