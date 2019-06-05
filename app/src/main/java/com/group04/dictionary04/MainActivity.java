package com.group04.dictionary04;

import android.content.Intent;
import android.content.SharedPreferences;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;

public class MainActivity extends AppCompatActivity {


    public final static String PREFS = "PrefsFile";

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        //TODO with this we can enable system wide Dark-Mode
        //AppCompatDelegate.setDefaultNightMode(AppCompatDelegate.MODE_NIGHT_YES);
    }

    public void launchInputView(View view){
        Intent intent = new Intent(this, InputViewActivity.class);
        this.startActivity(intent);
    }

    public void launchADVView(View view){
        Intent intent = new Intent(this, ADVViewActivity.class);
        this.startActivity(intent);
    }

    public void launchBackupView(View view){
        Intent intent = new Intent(this, BackupViewActivity.class);
        this.startActivity(intent);
    }

    public void launchLearningView(View view){
        Intent intent = new Intent(this, LearningViewActivity.class);
        this.startActivity(intent);
    }

    public void launchRatingView(View view){
        Intent intent = new Intent(this, RatingViewActivity.class);
        this.startActivity(intent);
    }

    public void launchSaveTestView(View view){
        Intent intent = new Intent(this, SaveTestViewActivity.class);
        this.startActivity(intent);
    }

    public void launchTestView(View view){
        Intent intent = new Intent(this, TestViewActivity.class);
        this.startActivity(intent);
    }

    public void launchSettingsView(View view){
        Intent intent = new Intent(this, SettingsViewActivity.class);
        this.startActivity(intent);
    }


}
