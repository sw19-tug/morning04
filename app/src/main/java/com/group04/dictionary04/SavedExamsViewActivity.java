package com.group04.dictionary04;

import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.view.MenuItem;
import android.view.View;
import android.widget.ArrayAdapter;
import android.widget.ListView;

import com.group04.dictionary04.database.DatabaseController;
import com.group04.dictionary04.model.default_Dictionary;
import com.group04.dictionary04.model.default_Exam;
import com.group04.dictionary04.model.default_Vocabulary;

public class SavedExamsViewActivity extends AppCompatActivity {

    private default_Dictionary dict = null;
    private ListView vocList = null;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.savedexams);
        setSupportActionBar((Toolbar)findViewById(R.id.myactionbar));
        getSupportActionBar().setDisplayHomeAsUpEnabled(true);
        getSupportActionBar().setDisplayShowHomeEnabled(true);
        DatabaseController dbController = new DatabaseController(this.getApplicationContext());
        dict = dbController.getCurrentDatabase();
        vocList = (ListView) findViewById(R.id.vocList);

        ArrayAdapter<default_Exam> dataAdapter = new ArrayAdapter<default_Exam>(this, android.R.layout.simple_selectable_list_item, dict.getExams());
        vocList.setAdapter(dataAdapter);

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
