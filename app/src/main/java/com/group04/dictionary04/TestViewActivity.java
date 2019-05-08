package com.group04.dictionary04;

import android.app.Activity;
import android.app.AlertDialog;
import android.content.DialogInterface;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;

import com.group04.dictionary04.database.DatabaseController;
import com.group04.dictionary04.model.default_Dictionary;
import com.group04.dictionary04.model.default_Exam;

public class TestViewActivity extends Activity implements View.OnClickListener {

    private default_Dictionary dict = null;

    int index = 0;

    TextView lang1;
    TextView lang2;
    TextView givenVocab;
    default_Exam exam;
    Button quit_yes = null;
    Button quit_no = null;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        DatabaseController dbController = new DatabaseController(this.getApplicationContext());
        dict = dbController.getCurrentDatabase();

        setContentView(R.layout.testview);
        lang1 = findViewById(R.id.textView3);
        lang2 = findViewById(R.id.textView5);
        givenVocab = findViewById(R.id.textView4);

        exam = dict.generateExam(null);

        lang1.setText(exam.getVocsToTest().get(index).getId1().getLangString());
        lang2.setText(exam.getVocsToTest().get(index).getId2().getLangString());
        givenVocab.setText(exam.getVocsToTest().get(0).getId1().getValue());
    }

    @Override
    public void onResume() {
        super.onResume();

        DatabaseController dbController = new DatabaseController(this.getApplicationContext());
        dbController.saveTestDatabase();
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

    public void checkButtonHandler(){
        AlertDialog.Builder dialog=new AlertDialog.Builder(this);
        dialog.setTitle("Is your Answer correct?");
        dialog.setNegativeButton("no", new DialogInterface.OnClickListener() {
            @Override
            public void onClick(DialogInterface dialog, int which) {

            }
        });
        dialog.setPositiveButton("yes", new DialogInterface.OnClickListener() {
            @Override
            public void onClick(DialogInterface dialog, int which) {
                exam.getVocsToTest().remove(index);
                nextButtonHandler();
            }
        });
        AlertDialog alertDialog=dialog.create();
        alertDialog.setMessage("Correct Answer:\n" + exam.getVocsToTest().get(index).getId2().getValue());
        alertDialog.show();

    }

    public void prevButtonHandler(){
        index--;
        if(!(index > 0)){
            index = exam.countVocsToTest() - 1;
        }
        lang1.setText(exam.getVocsToTest().get(index).getId1().getLangString());
        lang2.setText(exam.getVocsToTest().get(index).getId2().getLangString());
        givenVocab.setText(exam.getVocsToTest().get(index).getId1().getValue());

    }


    public void quitButtonHandler(){
        AlertDialog.Builder dialog=new AlertDialog.Builder(this);
        dialog.setTitle("Are you sure you want to quit?");
        dialog.setNegativeButton("stay", null);
        //issue with quit
        dialog.setPositiveButton("quit", new DialogInterface.OnClickListener() {
            @Override
            public void onClick(DialogInterface dialog, int which) {
                setContentView(R.layout.activity_main);
            }
        });
        AlertDialog alertDialog=dialog.create();
        alertDialog.show();
    }


    public void nextButtonHandler(){
        index++;
        if(!(index < exam.countVocsToTest())){
            index = 0;
        }
        lang1.setText(exam.getVocsToTest().get(index).getId1().getLangString());
        lang2.setText(exam.getVocsToTest().get(index).getId2().getLangString());
        givenVocab.setText(exam.getVocsToTest().get(index).getId1().getValue());

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
                quitButtonHandler();
                break;
            case R.id.button5:
                nextButtonHandler();
                break;
        }

    }
}
