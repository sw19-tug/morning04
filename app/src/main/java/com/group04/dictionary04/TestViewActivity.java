package com.group04.dictionary04;

import android.app.Activity;
import android.os.Bundle;
import android.view.View;
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

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        DatabaseController dbController = new DatabaseController(this.getApplicationContext());
        dict = dbController.getCurrentDatabase();

        setContentView(R.layout.testview);
        lang1 = findViewById(R.id.textView3);
        lang2 = findViewById(R.id.textView5);
        givenVocab = findViewById(R.id.textView4);

        default_Exam exam = dict.generateExam(null);

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

    @Override
    public void onClick(View v) {
        switch (v.getId()){
            case R.id.button3:

                break;
        }

    }
}
