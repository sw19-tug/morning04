package com.group04.dictionary04;

import android.app.Activity;
import android.app.AlertDialog;
import android.content.DialogInterface;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.EditText;
import android.widget.TextView;

import com.group04.dictionary04.database.DatabaseController;
import com.group04.dictionary04.model.default_Dictionary;
import com.group04.dictionary04.model.default_Entry;
import com.group04.dictionary04.model.default_Exam;

public class TestViewActivity extends Activity implements View.OnClickListener {

    private default_Dictionary dict = null;

    int index = 0;
    int hintNum = 0;

    TextView lang1;
    TextView lang2;
    TextView givenVocab;
    default_Exam exam;
    EditText input;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        DatabaseController dbController = new DatabaseController(this.getApplicationContext());
        dict = dbController.getCurrentDatabase();

        setContentView(R.layout.testview);
        lang1 = findViewById(R.id.textView3);
        lang2 = findViewById(R.id.textView5);
        givenVocab = findViewById(R.id.textView4);
        input = findViewById(R.id.editText2);

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

        hintNum = 0;
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

    public void hintButtonHandler(final int hintNum){
        AlertDialog.Builder dialog = new AlertDialog.Builder(this);
        dialog.setTitle("Hint is the first letter of the answer:");
        int answerLength = exam.getVocsToTest().get(index).getId2().getValue().length();
        String answerHint = "";
        if (hintNum < answerLength) {


            //for (int i =  hintNum; i < answerLength; i++){
            answerHint = exam.getVocsToTest().get(index).getId2().getValue().substring(0, hintNum);

            //};
            dialog.setNegativeButton("more help", new DialogInterface.OnClickListener() {
                @Override
                public void onClick(DialogInterface dialog, int which) {
                    int newHint = hintCounter();
                    Log.d("HintNum", Integer.toString(newHint));
                    hintButtonHandler(hintCounter());
                }
            });
            dialog.setPositiveButton("Try it", new DialogInterface.OnClickListener() {
                @Override
                public void onClick(DialogInterface dialog, int which) {
                    dialog.dismiss();
                }
            });
        }
        else{
            answerHint = "Sorry, you have used all your hints";
        }
        AlertDialog alertDialog=dialog.create();
        alertDialog.setMessage(answerHint);
        alertDialog.show();
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
            case R.id.button1:
                hintButtonHandler(hintCounter());
                break;
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

    public int hintCounter(){
        hintNum++;
        return hintNum;
    }
}
