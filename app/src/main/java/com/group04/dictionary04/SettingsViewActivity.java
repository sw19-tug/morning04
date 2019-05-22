package com.group04.dictionary04;

import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.util.Log;
import android.view.MenuItem;
import android.view.View;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.Spinner;
import android.widget.TextView;
import android.widget.Toast;

import com.group04.dictionary04.database.DatabaseController;
import com.group04.dictionary04.model.default_Dictionary;

import java.util.Set;

public class SettingsViewActivity extends AppCompatActivity {
    private default_Dictionary dict = null;



    public final static String PREFS = "PrefsFile";

    private SharedPreferences settings = null;
    private SharedPreferences.Editor editor = null;

    @Override
    public void onCreate(Bundle savedInstanceState){
      super.onCreate(savedInstanceState);
      setContentView(R.layout.settingsview);

      setSupportActionBar((Toolbar)findViewById(R.id.myactionbar));
      getSupportActionBar().setDisplayHomeAsUpEnabled(true);
      getSupportActionBar().setDisplayShowHomeEnabled(true);

      //Fill Spinner with numbers
      final Spinner spinner_days = (Spinner) findViewById(R.id.spinner_days);
      Integer[] days = new Integer[]{1, 2, 3, 4, 5, 6, 7};
      ArrayAdapter<Integer> adapter = new ArrayAdapter<Integer>(this,android.R.layout.simple_spinner_item, days);
      spinner_days.setAdapter(adapter);

      final Button btnEnableDays = (Button) findViewById(R.id.btn_days);

     btnEnableDays.setOnClickListener(new View.OnClickListener() {
         @Override
         public void onClick(View v) {

             int days = spinner_days.getSelectedItemPosition();

             //long time = System.currentTimeMillis() + 60000L; //Zum testen- nach einer Minute disabled
             long time = System.currentTimeMillis() + 86400000L * days;

             enableNotificationTime(v, time);

             Toast.makeText(SettingsViewActivity.this,spinner_days.getSelectedItem().toString(), Toast.LENGTH_SHORT).show();

         }
     });



      settings = getSharedPreferences(PREFS, MODE_PRIVATE);
      editor = settings.edit();


      if (!settings.contains("checkLast"))
           enableNotification(null);
      else
        recordRunTime();

      //this start the NotificationActivityService
      startService(new Intent(this,  NotificationActivity.class));



        //used to display current state of notifications
        //TODO: design, maybe add and option for the user to select the timer for Notification?(1-7 days?)

        boolean check = settings.getBoolean("enabled", true);
        TextView tv = findViewById(R.id.textView2);
        if(check){
            tv.setText("on");
        }
        if(!check)
        {
            tv.setText("off");
        }


    }

    public void recordRunTime() {
      editor.putLong("checkLast", System.currentTimeMillis());
      editor.commit();
    }


    public void enableNotification(View v) {
        editor.putLong("checkLast", System.currentTimeMillis());
        editor.putBoolean("enabled", true);
        editor.commit();
    }

    public void disableNotification(View v) {
        editor.putBoolean("enabled", false);
        editor.commit();
    }

    public void enableNotificationTime(View v, long time) {
        editor.putLong("enabled_time", time);
        editor.putBoolean("enabled", true);
        editor.commit();
    }



    @Override
    public void onResume() {
        super.onResume();

        DatabaseController dbController = new DatabaseController(this.getApplicationContext());
        dict = dbController.getCurrentDatabase();
    }

    @Override
    public void onPause() {
        super.onPause();

        DatabaseController dbController = new DatabaseController(this.getApplicationContext());
        dbController.saveCurrentDatabase(dict);
    }

    @Override
    public void onStop() {
        super.onStop();

        DatabaseController dbController = new DatabaseController(this.getApplicationContext());
        dbController.saveCurrentDatabase(dict);
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        int id = item.getItemId();

        if (id == android.R.id.home) {
            onBackPressed();  return true;
        }

        return super.onOptionsItemSelected(item);
    }


}
