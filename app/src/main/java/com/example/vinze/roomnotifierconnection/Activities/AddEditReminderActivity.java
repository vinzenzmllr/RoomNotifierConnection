package com.example.vinze.roomnotifierconnection.Activities;

import android.app.AlarmManager;
import android.app.PendingIntent;
import android.content.Context;
import android.content.Intent;
import android.os.SystemClock;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.MenuItem;
import android.widget.EditText;
import android.widget.Switch;
import android.widget.Toast;

import com.example.vinze.roomnotifierconnection.NotificationManager.AlarmNotificationReceiver;
import com.example.vinze.roomnotifierconnection.NotificationManager.AlarmSetter;
import com.example.vinze.roomnotifierconnection.NotificationManager.AlertReceiver;
import com.example.vinze.roomnotifierconnection.R;

import java.sql.Time;
import java.util.Calendar;
import java.util.Date;
import java.util.EventListener;

public class AddEditReminderActivity extends AppCompatActivity {

    public static final String EXTRA_ID = "com.vinzenz.EXTRA_ID";
    public static final String EXTRA_MEDICATIONNAME = "com.vinzenz.EXTRA_MEDICATIONNAME";
    public static final String EXTRA_MORNINGTIME = "com.vinzenz.EXTRA_MORNINGTIME";
    public static final String EXTRA_NOONTIME = "com.vinzenz.EXTRA_NOONTIME";
    public static final String EXTRA_EVENINGTIME = "com.vinzenz.EXTRA_EVENINGTIME";

    private static EditText editTexMedicationName;
    private EditText editTextmorningTime;
    private EditText editTextNoonTime;
    private EditText editTextEveningTime;
    private Switch switchMorning;
    private Switch switchNoon;
    private Switch switchEvening;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_add_edit_reminder);

        editTexMedicationName = findViewById(R.id.et_medicationName);
        editTextmorningTime = findViewById(R.id.et_morning);
        editTextNoonTime = findViewById(R.id.et_noon);
        editTextEveningTime = findViewById(R.id.et_evening);
        switchMorning = findViewById(R.id.sw_morning);
        switchNoon = findViewById(R.id.sw_noon);
        switchEvening = findViewById(R.id.sw_evening);

        getSupportActionBar().setHomeAsUpIndicator(R.drawable.ic_close);

        Intent intent = getIntent();

        if (intent.hasExtra(EXTRA_ID)) {
            setTitle("Edit Reminder");
           editTexMedicationName.setText(intent.getStringExtra(EXTRA_MEDICATIONNAME));
           editTextmorningTime.setText(intent.getStringExtra(EXTRA_MORNINGTIME));
           editTextNoonTime.setText(intent.getStringExtra(EXTRA_NOONTIME));
           editTextEveningTime.setText(intent.getStringExtra(EXTRA_EVENINGTIME));
        } else {
            setTitle("Add Reminder");
        }
    }

    public static String getMedicationName(){

        return editTexMedicationName.getText().toString();
    }

    private void saveNote() {

        String medicationName = editTexMedicationName.getText().toString();
        String morningTime = editTextmorningTime.getText().toString();
        String noonTime = editTextNoonTime.getText().toString();
        String eveningTime = editTextEveningTime.getText().toString();

        if (medicationName.trim().isEmpty()) {
            Toast.makeText(this, "Bitte geben Sie einen Medikamentennamen an!", Toast.LENGTH_SHORT).show();
            return;
        }

        if(checkTime(morningTime) == false || checkTime(noonTime) == false || checkTime(eveningTime) == false)
        {
            Toast.makeText(this, "Bitte geben Sie korrekte Zeiten ein! [hh:mm]", Toast.LENGTH_SHORT).show();
            return;
        }

        Intent data = new Intent();
        data.putExtra(EXTRA_MEDICATIONNAME, medicationName);
        data.putExtra(EXTRA_MORNINGTIME, morningTime);
        data.putExtra(EXTRA_NOONTIME, noonTime);
        data.putExtra(EXTRA_EVENINGTIME, eveningTime);


        setResult(RESULT_OK, data);
        finish();
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        MenuInflater menuInflater = getMenuInflater();
        menuInflater.inflate(R.menu.add_reminder_menu, menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        switch (item.getItemId()) {
            case R.id.save_note:
                saveNote();
                return true;
            default:
                return super.onOptionsItemSelected(item);
        }
    }

    public boolean checkTime(String timeToCheck){
        if(timeToCheck.length() < 5 || timeToCheck.length() > 5 || Integer.parseInt(timeToCheck.substring(0,1)) >= 24
                || Integer.parseInt(timeToCheck.substring(3,4)) >= 60 || timeToCheck.length() == 0) //TODO > 24 und > 59 geht noch ned
        {
            return false;
        }
        return true;
    }


}

