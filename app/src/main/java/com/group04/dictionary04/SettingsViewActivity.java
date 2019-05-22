package com.group04.dictionary04;

import android.content.Intent;
import android.content.SharedPreferences;
import android.graphics.Color;
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
    public int delay = 1;

    @Override
    public void onCreate(Bundle savedInstanceState){
      super.onCreate(savedInstanceState);
      setContentView(R.layout.settingsview);

      setSupportActionBar((Toolbar)findViewById(R.id.myactionbar));
      getSupportActionBar().setDisplayHomeAsUpEnabled(true);
      getSupportActionBar().setDisplayShowHomeEnabled(true);


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
            tv.setTextColor(Color.parseColor("#00FF00"));

        }
        if(!check)
        {
            tv.setText("off");
            tv.setTextColor(Color.parseColor("#FF0000"));

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
