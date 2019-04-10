package com.group04.dictionary04;

import android.app.Activity;
import android.os.Bundle;
import android.widget.TextView;

import com.group04.dictionary04.database.DatabaseController;
import com.group04.dictionary04.model.default_Dictionary;
import com.group04.dictionary04.model.default_Exam;

public class TestViewActivity extends Activity {

    private default_Dictionary dict = null;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        DatabaseController dbController = new DatabaseController(this.getApplicationContext());
        dict = dbController.getCurrentDatabase();

        setContentView(R.layout.testview);
        TextView lang1 = findViewById(R.id.textView3);
        TextView lang2 = findViewById(R.id.textView5);
        TextView givenVocab = findViewById(R.id.textView4);
        lang1.setText("Language 1");
        default_Exam exam = dict.generateExam(null);

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
}
