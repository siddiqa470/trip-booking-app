package com.example.toursandtravels;

import androidx.appcompat.app.AppCompatActivity;

import android.database.Cursor;
import android.os.Bundle;
import android.view.ContextMenu;
import android.view.MenuItem;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ListView;
import android.widget.SimpleCursorAdapter;
import android.widget.TextView;
import android.widget.Toast;

public class bookingsDetails extends AppCompatActivity {

    private BookingDatabaseHelper bookingDb;
    private DatabaseHelper userDb;
    private ListView bookingListView;
    private SimpleCursorAdapter adapter;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_bookings_details);

        bookingDb = new BookingDatabaseHelper(this);
        userDb = new DatabaseHelper(this);
        bookingListView = findViewById(R.id.booking_listVw);

        setupAdapter();
        refreshBookingsList();
        registerForContextMenu(bookingListView);
    }

    private void setupAdapter() {
        String[] fromColumns = {
                BookingDatabaseHelper.COL_13, // PLACE_NAME
                BookingDatabaseHelper.COL_6, // TRAVEL_INFO
        };

        int[] toViews = {
                R.id.booking_place_name,
                R.id.booking_dates
        };

        adapter = new SimpleCursorAdapter(this, R.layout.booking_list_item, null, fromColumns, toViews, 0);

        adapter.setViewBinder(new SimpleCursorAdapter.ViewBinder() {
            @Override
            public boolean setViewValue(View view, Cursor cursor, int columnIndex) {
                if (view.getId() == R.id.booking_dates) {
                    TextView textView = (TextView) view;
                    String travelInfo = cursor.getString(cursor.getColumnIndexOrThrow(BookingDatabaseHelper.COL_6));
                    String checkIn = cursor.getString(cursor.getColumnIndexOrThrow(BookingDatabaseHelper.COL_7));
                    String checkOut = cursor.getString(cursor.getColumnIndexOrThrow(BookingDatabaseHelper.COL_8));
                    textView.setText(travelInfo + " | From: " + checkIn + " To: " + checkOut);
                    return true;
                } else if (view.getId() == R.id.booking_place_name) {
                    TextView textView = (TextView) view;
                    textView.setText(cursor.getString(cursor.getColumnIndexOrThrow(BookingDatabaseHelper.COL_13)));
                    return true;
                }
                return false;
            }
        });

        bookingListView.setAdapter(adapter);
    }

    private void refreshBookingsList() {
        UserData currentUser = userDb.getUserData();
        if (currentUser != null) {
            String email = currentUser.getEmail();
            Cursor newCursor = bookingDb.getData(email);

            Toast.makeText(this, "Found " + newCursor.getCount() + " bookings", Toast.LENGTH_SHORT).show();

            adapter.changeCursor(newCursor);
        } else {
            adapter.changeCursor(null);
            Toast.makeText(this, "Please log in to see your bookings", Toast.LENGTH_SHORT).show();
        }
    }

    @Override
    public void onCreateContextMenu(ContextMenu menu, View v, ContextMenu.ContextMenuInfo menuInfo) {
        super.onCreateContextMenu(menu, v, menuInfo);
        menu.add(0, v.getId(), 0, "Cancel Booking");
    }

    @Override
    public boolean onContextItemSelected(MenuItem item) {
        AdapterView.AdapterContextMenuInfo info = (AdapterView.AdapterContextMenuInfo) item.getMenuInfo();
        if (item.getTitle().equals("Cancel Booking")) {
            long bookingId = info.id;
            Integer deletedRows = bookingDb.deleteData(String.valueOf(bookingId));
            if (deletedRows > 0) {
                Toast.makeText(getApplicationContext(), "Booking Cancelled", Toast.LENGTH_SHORT).show();
                refreshBookingsList();
            } else {
                Toast.makeText(getApplicationContext(), "Cancellation Failed", Toast.LENGTH_SHORT).show();
            }
            return true;
        } else {
            return super.onContextItemSelected(item);
        }
    }
}
