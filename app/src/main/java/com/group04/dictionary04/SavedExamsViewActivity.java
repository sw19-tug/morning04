package com.group04.dictionary04;

import android.app.AlertDialog;
import android.content.DialogInterface;
import android.content.Intent;
import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.util.Log;
import android.view.MenuItem;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.ListView;
import android.widget.Toast;

import com.google.gson.Gson;
import com.group04.dictionary04.database.DatabaseController;
import com.group04.dictionary04.interfaces.Exam;
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
//        dbController.saveTestDatabase();

        ArrayAdapter<default_Exam> dataAdapter = new ArrayAdapter<default_Exam>(this, android.R.layout.simple_selectable_list_item, dict.getExams());
        vocList.setAdapter(dataAdapter);


        vocList.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
                default_Exam exam = (default_Exam) parent.getItemAtPosition(position);
                showSavedExam(exam);
            }
        });
    }

    private void showSavedExam(final default_Exam exam) {

        AlertDialog.Builder builder = new AlertDialog.Builder(this);
        builder.setTitle("Saved Exams");
        builder.setNeutralButton("Retry Test", new DialogInterface.OnClickListener() {
                    public void onClick(DialogInterface dialog, int which) {
                        retryTest(exam);
                    }
                });
        builder.setNegativeButton("Delete Test", new DialogInterface.OnClickListener() {
            public void onClick(DialogInterface dialog, int which) {
                deleteTest(exam);
            }
        });
        builder.setIcon(android.R.drawable.ic_dialog_alert);
        builder.show();
    }

    public void deleteTest(default_Exam exam) {
        dict.deleteExam(exam);
        ArrayAdapter<default_Exam> dataAdapter = new ArrayAdapter<default_Exam>(this, android.R.layout.simple_selectable_list_item, dict.getExams());
        vocList.setAdapter(dataAdapter);
    }

    public void retryTest(default_Exam exam) {
        Gson gson = new Gson();

        Intent myIntent = new Intent(SavedExamsViewActivity.this, TestViewActivity.class);
        myIntent.putExtra("data", gson.toJson(exam));
        startActivity(myIntent);

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
