package com.group04.dictionary04;

import android.app.Activity;
import android.os.Bundle;
import android.util.Log;
import com.group04.dictionary04.database.DatabaseController;
import com.group04.dictionary04.interfaces.Dictionary;

public class LearningViewActivity extends Activity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.learningview);

        //DatabaseController dbController = new DatabaseController(this.getApplicationContext());
        //dbController.saveTestDatabase();
        //Dictionary d = dbController.getCurrentDatabase();

        //Log.d("log", "Currently there are " + d.getEntries().size() + " entries in this dict");
    }
}
