package com.group04.dictionary04;

import android.app.Activity;
import android.content.Intent;

import android.net.Uri;
import android.os.Bundle;

import android.support.v4.content.FileProvider;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.util.Log;
import android.view.MenuItem;
import android.view.View;
import com.group04.dictionary04.database.DatabaseController;
import com.group04.dictionary04.model.default_Dictionary;

import java.io.File;

public class BackupViewActivity extends AppCompatActivity {
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.backupview);
        setSupportActionBar((Toolbar)findViewById(R.id.myactionbar));
        getSupportActionBar().setDisplayHomeAsUpEnabled(true);
        getSupportActionBar().setDisplayShowHomeEnabled(true);
    }

    public void backupData(View view) {
        DatabaseController dbController = new DatabaseController(this.getApplicationContext());
        dbController.backupDatabase();
    }

    public void restoreData(View view) {
        DatabaseController dbController = new DatabaseController(this.getApplicationContext());
        dbController.restoreDatabase();
    }

    public void clearTesting(View view) {
        DatabaseController dbController = new DatabaseController(this.getApplicationContext());
        dbController.clearTesting();
    }

    public void clearAll(View view) {
        DatabaseController dbController = new DatabaseController(this.getApplicationContext());
        dbController.clearDatabase();
    }

    public void shareData(View view) {
        DatabaseController dbController = new DatabaseController(this.getApplicationContext());
        dbController.backupDatabase();
        File file = new File("dict04-backup");
        //Uri contentUri = FileProvider.getUriForFile(this, "com.your.app.package", file);
        Intent intentShareFile = new Intent(Intent.ACTION_SEND);

        Log.d("as", "aaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaa");
        intentShareFile.setType("application/text");
        intentShareFile.putExtra(Intent.EXTRA_STREAM, Uri.parse("file://"+"dict04-backup"));

        intentShareFile.putExtra(Intent.EXTRA_SUBJECT,
                "Sharing File...");
        intentShareFile.putExtra(Intent.EXTRA_TEXT, "Sharing File...");

        startActivity(Intent.createChooser(intentShareFile, "Share File"));

        //dbController.clearDatabase();
    }

    @Override
    public void onResume() {
        super.onResume();
    }

    @Override
    public void onPause() {
        super.onPause();
    }

    @Override
    public void onStop() {
        super.onStop();
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
