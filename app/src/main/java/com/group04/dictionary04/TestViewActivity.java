package com.group04.dictionary04;

import android.app.Activity;
import android.app.AlertDialog;
import android.app.AppComponentFactory;
import android.content.DialogInterface;
import android.content.Intent;
import android.os.Build;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.util.Log;
import android.view.MenuItem;
import android.view.View;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import com.google.gson.Gson;
import com.group04.dictionary04.database.DatabaseController;
import com.group04.dictionary04.interfaces.Exam;
import com.group04.dictionary04.model.default_Dictionary;
import com.group04.dictionary04.model.default_Entry;
import com.group04.dictionary04.model.default_Exam;

import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Date;
import java.util.TimeZone;

public class TestViewActivity extends AppCompatActivity implements View.OnClickListener {

    private default_Dictionary dict = null;

    int index = 0;

    TextView lang1;
    TextView lang2;
    TextView givenVocab;
    default_Exam exam;
    EditText input;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.testview);
        setSupportActionBar((Toolbar)findViewById(R.id.myactionbar));
        getSupportActionBar().setDisplayHomeAsUpEnabled(true);
        getSupportActionBar().setDisplayShowHomeEnabled(true);
        DatabaseController dbController = new DatabaseController(this.getApplicationContext());
        dict = dbController.getCurrentDatabase();
       //--------------------------------------------------------
        Intent intent = getIntent();
        String data = intent.getStringExtra("data");
        boolean valid = true;
        if(data != null){

            Gson gson = new Gson();
            exam = gson.fromJson(data, default_Exam.class);
            Log.d("asdf", data);
        }
        else{
            if(dict.getEntries().size() == 0)
            {
                Toast.makeText(getApplicationContext(), "no data available", Toast.LENGTH_SHORT).show();
                valid = false;
                Intent myIntent = new Intent(TestViewActivity.this, MainActivity.class);
                startActivity(myIntent);
                finishAffinity();
            } else
                exam = dict.generateExam(null);
        }
        //--------------------------------------------------------
        if(valid) {
            lang1 = findViewById(R.id.textView3);
            lang2 = findViewById(R.id.textView5);
            givenVocab = findViewById(R.id.textView4);
            input = findViewById(R.id.editText2);

            if(exam.getVocsToTest().size() > 0) {
                lang1.setText(exam.getVocsToTest().get(index).getId1().getLangString());
                lang2.setText(exam.getVocsToTest().get(index).getId2().getLangString());
                givenVocab.setText(exam.getVocsToTest().get(0).getId1().getValue());
            }
        }


    }

    @Override
    public void onResume() {
        super.onResume();

        DatabaseController dbController = new DatabaseController(this.getApplicationContext());
//        dbController.saveTestDatabase();
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

    public void refreshContent(){

        if(exam.getVocsToTest().size() == 0 || index < 0 || index >= exam.getVocsToTest().size())
        {
            AlertDialog.Builder notification=new AlertDialog.Builder(this);
            notification.setTitle("Your Exam has been saved!");
            notification.setNeutralButton("Ok", null);
            return;
        }

        lang1.setText(exam.getVocsToTest().get(index).getId1().getLangString());
        lang2.setText(exam.getVocsToTest().get(index).getId2().getLangString());
        givenVocab.setText(exam.getVocsToTest().get(index).getId1().getValue());
        input.getText().clear();

    }

    public void displayPopUp(String title, String NeutralButton){
        AlertDialog.Builder notification=new AlertDialog.Builder(this);
        notification.setTitle(title);
        notification.setMessage(NeutralButton);
        notification.setNeutralButton("OK", null);
        notification.show();
    }

    public void removeEntry(){
        exam.getVocsToTest().remove(index);
        if(index == exam.getVocsToTest().size() && index != 0)
        {
            index--;
        }else if(index == exam.getVocsToTest().size() && index == 0)
        {
            saveButtonHandler();
        }
    }

    public void checkButtonHandler(){
        AlertDialog.Builder dialog=new AlertDialog.Builder(this);
        dialog.setTitle("Is your Answer correct?");
        String answer = exam.getVocsToTest().get(index).getId2().getValue();
        dialog.setNegativeButton("no", new DialogInterface.OnClickListener() {
            @Override
            public void onClick(DialogInterface dialog, int which) {
                default_Entry entry = exam.getVocsToTest().get(index);
                exam.getFailedVocs().add(entry);
                removeEntry();
            }
        });
        dialog.setPositiveButton("yes", new DialogInterface.OnClickListener() {
            @Override
            public void onClick(DialogInterface dialog, int which) {
                    removeEntry();
            }
        });
        AlertDialog alertDialog=dialog.create();
        alertDialog.setMessage("Correct Answer:\n" + answer);
        alertDialog.show();
        refreshContent();

    }

    public void prevButtonHandler(){
        if (index > 0)
            index--;
        else {
            displayPopUp("Notification", "You are already at the first Vocab.");
        }

        refreshContent();
    }

    public void nextButtonHandler(){
        if(index < exam.getVocsToTest().size() - 1)
        {
            index ++;
        }else {
            displayPopUp("Notification", "You are already at the last Vocab.");
        }
        refreshContent();
    }

    public void saveButtonHandler(){
        AlertDialog.Builder dialog=new AlertDialog.Builder(this);
        dialog.setTitle("Done...");
        dialog.setMessage("Do you want to save your exam progress?");
        dialog.setNegativeButton("yes", new DialogInterface.OnClickListener() {
            @Override
            public void onClick(DialogInterface dialog, int which) {
                exam.setResult(getCurrentDate() + "  " + getCurrentTime());
                dict.getExams().add(exam);
            }
        });

        dialog.setPositiveButton("no", new DialogInterface.OnClickListener() {
            @Override
            public void onClick(DialogInterface dialog, int which) {
            }
        });
        AlertDialog alertDialog=dialog.create();
        alertDialog.show();
    }



    @Override
    public void onClick(View v) {
        switch (v.getId()){
            case R.id.button0:
                checkButtonHandler();
                break;
            case R.id.button3:
                prevButtonHandler();
                break;
            case R.id.button4:
                saveButtonHandler();
                break;
            case R.id.button5:
                nextButtonHandler();
                break;
        }

    }


    public static final String DATE_FORMAT_1 = "hh:mm a";

    public static String getCurrentTime() {
        SimpleDateFormat dateFormat = new SimpleDateFormat(DATE_FORMAT_1);
        dateFormat.setTimeZone(TimeZone.getTimeZone("UTC"));
        Date today = Calendar.getInstance().getTime();
        return dateFormat.format(today);
    }

    public static String getCurrentDate() {
        Date c = Calendar.getInstance().getTime();
        SimpleDateFormat df = new SimpleDateFormat("dd-MMM-yyyy");
        return df.format(c);
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
