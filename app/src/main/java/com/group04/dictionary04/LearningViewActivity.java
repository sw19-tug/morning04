package com.group04.dictionary04;

import android.app.Activity;
import android.os.Bundle;
import android.util.Log;
import com.group04.dictionary04.database.DatabaseController;
import com.group04.dictionary04.interfaces.Dictionary;

import com.group04.dictionary04.model.default_Dictionary;


public class LearningViewActivity extends Activity {


    private default_Dictionary dict = null;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.learningview);
    }

    @Override
    public void onResume() {
        super.onResume();

        DatabaseController dbController = new DatabaseController(this.getApplicationContext());
        dbController.saveTestDatabase();
        dict = dbController.getCurrentDatabase();

        Log.d("log", "Currently there are " + dict.getEntries().size() + " entries in this dict");
    }

    @Override
    public void onPause() {
        super.onPause();

        DatabaseController dbController = new DatabaseController(this.getApplicationContext());
        dbController.saveCurrentDatabase(dict);
    }
}
