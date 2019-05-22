package com.group04.dictionary04;

import android.app.Activity;
import android.os.Bundle;
import android.view.View;

import com.group04.dictionary04.database.DatabaseController;
import com.group04.dictionary04.model.default_Dictionary;

public class AdvancedTestViewActivity extends Activity implements View.OnClickListener {

    private default_Dictionary dict;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        DatabaseController dbController = new DatabaseController(this.getApplicationContext());
        dict = dbController.getCurrentDatabase();

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
    public void onClick(View v) {
    }
}
