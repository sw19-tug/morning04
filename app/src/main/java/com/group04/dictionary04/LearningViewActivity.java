package com.group04.dictionary04;

import android.app.Activity;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.*;
import com.group04.dictionary04.database.DatabaseController;
import com.group04.dictionary04.model.default_Dictionary;
import com.group04.dictionary04.model.default_Language;

public class LearningViewActivity extends Activity {
    private default_Dictionary dict = null;
    private ListView vocList = null;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.learningview);

        DatabaseController dbController = new DatabaseController(this.getApplicationContext());
        dbController.saveTestDatabase();
        dict = dbController.getCurrentDatabase();

        Log.d("asdfasdf", dict.getLanguagesStrings().size() + "sizuasdfasdfasd");

        vocList = (ListView) findViewById(R.id.vocList);
        Spinner lang_spinner = (Spinner) findViewById(R.id.languageSelection);
        ArrayAdapter<String> dataAdapter = new ArrayAdapter<String>(this, android.R.layout.simple_spinner_item, dict.getLanguagesStrings());
        dataAdapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
        lang_spinner.setAdapter(dataAdapter);

        lang_spinner.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
            @Override
            public void onItemSelected(AdapterView<?> parent, View view, int position, long id) {
                default_Language language = dict.getLanguageByIndex(parent.getSelectedItem().toString());
                Toast.makeText(parent.getContext(), "Spinner item: " + language.getDisplayName(), Toast.LENGTH_SHORT).show();
                loadCurrentLanguageList(language);
            }

            @Override
            public void onNothingSelected(AdapterView<?> parent) {
                // sometimes you need nothing here
            }
        });
    }

    private void loadCurrentLanguageList(default_Language language) {
        ArrayAdapter<String> dataAdapter = new ArrayAdapter<String>(this, android.R.layout.simple_spinner_item, language.getVocabularyStrings());
        dataAdapter.setDropDownViewResource(android.R.layout.simple_selectable_list_item);
        vocList.setAdapter(dataAdapter);
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