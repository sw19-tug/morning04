package com.group04.dictionary04;

import android.Manifest;
import android.app.Activity;
import android.content.Intent;

import android.content.pm.PackageManager;
import android.net.Uri;
import android.os.Build;
import android.os.Bundle;

import android.os.Environment;
import android.support.v4.app.ActivityCompat;
import android.support.v4.content.ContextCompat;
import android.support.v4.content.FileProvider;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.util.Log;
import android.view.MenuItem;
import android.view.View;
import com.group04.dictionary04.database.DatabaseController;
import com.group04.dictionary04.model.default_Dictionary;
import org.apache.commons.io.FileUtils;

import java.io.File;
import java.io.FileOutputStream;
import java.io.InputStream;
import java.io.OutputStream;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.nio.file.StandardCopyOption;
import java.text.SimpleDateFormat;
import java.util.Date;

public class BackupViewActivity extends AppCompatActivity {
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.backupview);
        setSupportActionBar((Toolbar)findViewById(R.id.myactionbar));
        getSupportActionBar().setDisplayHomeAsUpEnabled(true);
        getSupportActionBar().setDisplayShowHomeEnabled(true);

        int permissionCheck = ContextCompat.checkSelfPermission(this, Manifest.permission.WRITE_EXTERNAL_STORAGE);

        if (permissionCheck != PackageManager.PERMISSION_GRANTED) {
            ActivityCompat.requestPermissions(this, new String[]{Manifest.permission.WRITE_EXTERNAL_STORAGE}, 1);
        }
    }

    @Override
    public void onRequestPermissionsResult(int requestCode, String permissions[], int[] grantResults) {
        switch (requestCode) {
            case 1:
                if ((grantResults.length > 0) && (grantResults[0] == PackageManager.PERMISSION_GRANTED)) {
                    //readDataExternal();
                } else {
                    this.finish();
                }
                break;

            default:
                this.finish();
                break;
        }
    }

    public void backupData(View view) {
        DatabaseController dbController = new DatabaseController(this.getApplicationContext());
        dbController.backupDatabase();
    }

    public void restoreData(View view) {
        Intent intent = new Intent().setType("*/*").setAction(Intent.ACTION_GET_CONTENT);

        startActivityForResult(Intent.createChooser(intent, "Select a file"), 123);
    }

    @Override
    protected void onActivityResult(int requestCode, int resultCode, Intent data) {
        super.onActivityResult(requestCode, resultCode, data);
        if(requestCode == 123 && resultCode == RESULT_OK) {
            try {
                Uri selectedfile = data.getData(); //The uri with the location of the file
                File temp = new File(Environment.getExternalStorageDirectory(), "temp");
                InputStream stream = getContentResolver().openInputStream(selectedfile);
                FileUtils.copyInputStreamToFile(stream, temp);
                DatabaseController dbController = new DatabaseController(this.getApplicationContext());
                dbController.restoreDatabase(temp);
            } catch(Exception e) {
                e.printStackTrace();
            }
        }
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
        SimpleDateFormat fmtOut = new SimpleDateFormat("dd-MM-yyyy");
        DatabaseController dbController = new DatabaseController(this.getApplicationContext());
        Intent intentShareFile = new Intent(Intent.ACTION_SEND);
        dbController.backupDatabase();
        File cacheFile = new File(Environment.getExternalStorageDirectory(), "dict04-backup");
        intentShareFile.setType("application/text");
        Uri photoURI = FileProvider.getUriForFile(this, this.getApplicationContext().getPackageName() + ".fileprovider", cacheFile);
        intentShareFile.putExtra(Intent.EXTRA_STREAM, photoURI);
        intentShareFile.addFlags(Intent.FLAG_GRANT_READ_URI_PERMISSION);
        intentShareFile.putExtra(Intent.EXTRA_SUBJECT, "Backup of dictionary-" + fmtOut.format(new Date()));
        intentShareFile.putExtra(Intent.EXTRA_TEXT, "Backup of dictionary-" + fmtOut.format(new Date()));
        startActivity(Intent.createChooser(intentShareFile, "Backup-Dict-" + fmtOut.format(new Date())));
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
