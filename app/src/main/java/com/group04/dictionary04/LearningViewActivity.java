package com.group04.dictionary04;

import android.app.Activity;
import android.app.AlertDialog;
import android.content.DialogInterface;
import android.os.Bundle;
import android.text.Editable;
import android.text.TextWatcher;
import android.util.Log;
import android.view.View;
import android.widget.*;
import com.group04.dictionary04.database.DatabaseController;
import com.group04.dictionary04.model.default_Dictionary;
import com.group04.dictionary04.model.default_Language;
import com.group04.dictionary04.model.default_Vocabulary;

public class LearningViewActivity extends Activity {
    private default_Dictionary dict = null;
    private ListView vocList = null;
    private EditText search = null;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.learningview);
        DatabaseController dbController = new DatabaseController(this.getApplicationContext());
        //dbController.saveTestDatabase();
        dict = dbController.getCurrentDatabase();

        search = (EditText) findViewById(R.id.search);
        vocList = (ListView) findViewById(R.id.vocList);
        final Spinner lang_spinner = (Spinner) findViewById(R.id.languageSelection);
        ArrayAdapter<String> dataAdapter = new ArrayAdapter<String>(this, android.R.layout.simple_spinner_item, dict.getLanguagesStrings());
        dataAdapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
        lang_spinner.setAdapter(dataAdapter);

        lang_spinner.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
            @Override
            public void onItemSelected(AdapterView<?> parent, View view, int position, long id) {
                default_Language language = dict.getLanguageByIndex(parent.getSelectedItem().toString());
                Toast.makeText(parent.getContext(), "" + language.getDisplayName(), Toast.LENGTH_SHORT).show();
                loadCurrentLanguageList(language);
            }

            @Override
            public void onNothingSelected(AdapterView<?> parent) {
                // sometimes you need nothing here
            }
        });

        vocList.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
                default_Vocabulary voc = (default_Vocabulary) parent.getItemAtPosition(position);
                Toast.makeText(parent.getContext(), "" + voc.getValue(), Toast.LENGTH_SHORT).show();
                showTranslations(voc);
            }
        });

        search.addTextChangedListener(new TextWatcher() {
            @Override
            public void beforeTextChanged(CharSequence s, int start, int count, int after) {

            }

            @Override
            public void onTextChanged(CharSequence s, int start,
                                      int before, int count) {

                loadCurrentLanguageList(dict.getLanguageByIndex(lang_spinner.getSelectedItem().toString()));
            }

            @Override
            public void afterTextChanged(Editable s) {

            }
        });
    }

    private void showTranslations(default_Vocabulary voc) {
        new AlertDialog.Builder(this)
            .setTitle(voc.getValue())
            .setMessage(dict.getTranslationString(voc))
            .setPositiveButton(android.R.string.ok, new DialogInterface.OnClickListener() {
                public void onClick(DialogInterface dialog, int which) { }
            })
            .setIcon(android.R.drawable.ic_dialog_alert)
            .show();
    }

    private void loadCurrentLanguageList(default_Language language) {
        String searchText = search.getText().toString();
        ArrayAdapter<default_Vocabulary> dataAdapter = new ArrayAdapter<default_Vocabulary>(this, android.R.layout.simple_spinner_item, language.getVocabulariesQuery(searchText));
        dataAdapter.setDropDownViewResource(android.R.layout.simple_selectable_list_item);
        vocList.setAdapter(dataAdapter);
    }

    @Override
    public void onResume() {
        super.onResume();

        DatabaseController dbController = new DatabaseController(this.getApplicationContext());
        //dbController.saveTestDatabase();
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