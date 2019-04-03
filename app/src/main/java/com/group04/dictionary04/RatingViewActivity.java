package com.group04.dictionary04;

import android.app.Activity;
import android.os.Bundle;
import android.widget.ArrayAdapter;
import android.widget.Spinner;

import com.group04.dictionary.enums.LanguageIdentifier;
import com.group04.dictionary04.database.DatabaseController;
import com.group04.dictionary04.interfaces.Dictionary;
import com.group04.dictionary04.model.default_Dictionary;
import com.group04.dictionary04.model.default_Language;

import java.util.ArrayList;
import java.util.List;

public class RatingViewActivity extends Activity {

    private LanguageIdentifier lang1;
    private default_Dictionary dict = null;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        DatabaseController dbController = new DatabaseController(this.getApplicationContext());
        dbController.saveTestDatabase();
        dict = dbController.getCurrentDatabase();

        setContentView(R.layout.ratingview);

        List<String> languages = new ArrayList<>();

        languages.add(0, dict.getLanguage(LanguageIdentifier.EN).getDisplayName());

        Spinner lang_spinner = (Spinner) findViewById(R.id.spinner_langt);

        ArrayAdapter<String> dataAdapter = new ArrayAdapter<String>(this,
                android.R.layout.simple_spinner_dropdown_item, languages);

        dataAdapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
        //filling both lang spinners with options
        lang_spinner.setAdapter(dataAdapter);
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
