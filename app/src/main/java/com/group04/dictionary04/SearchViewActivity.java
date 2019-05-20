package com.group04.dictionary04;

import android.app.Activity;
import android.app.AlertDialog;
import android.content.DialogInterface;
import android.database.Cursor;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.util.Log;
import android.view.MenuItem;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ListView;
import android.widget.RadioButton;
import android.widget.RatingBar;
import android.widget.Spinner;
import android.widget.Toast;

import com.group04.dictionary04.database.DatabaseController;
import com.group04.dictionary04.enums.DifficultyIdentifier;
import com.group04.dictionary04.enums.LanguageIdentifier;
import com.group04.dictionary04.model.default_Dictionary;
import com.group04.dictionary04.model.default_Vocabulary;

import java.util.ArrayList;
import java.util.List;

public class SearchViewActivity extends AppCompatActivity {
    private default_Dictionary dict = null;
    private LanguageIdentifier language1;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.searchview);
        setSupportActionBar((Toolbar)findViewById(R.id.myactionbar));
        getSupportActionBar().setDisplayHomeAsUpEnabled(true);
        getSupportActionBar().setDisplayShowHomeEnabled(true);

        final DatabaseController dbController = new DatabaseController(getApplicationContext());
        dict = dbController.getCurrentDatabase();


        String[] items = new String[] {"Tags", "Alphabet", "Language"};
        final Spinner spinner_sort = (Spinner) findViewById(R.id.spinner_sort);
        ArrayAdapter<String> adapter = new ArrayAdapter<String>(this,
                android.R.layout.simple_spinner_item, items);
        adapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
        spinner_sort.setAdapter(adapter);


        List<String> languages = new ArrayList<>();
        languages = dict.getLanguagesStrings();
        final Spinner spinner_lang = (Spinner) findViewById(R.id.spinner_lang);
        ArrayAdapter<String> lang_Adapter = new ArrayAdapter<String>(this,
                android.R.layout.simple_spinner_item, languages);
        lang_Adapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
        spinner_lang.setAdapter(lang_Adapter);


        Button sort_filter_button = (Button) findViewById(R.id.btn_filter);
        sort_filter_button.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                EditText search_word = (EditText) findViewById(R.id.txt_lang1_input);
                EditText tag = (EditText) findViewById(R.id.txt_tag);
                RadioButton ascending = (RadioButton) findViewById(R.id.btn_ascending);
                RadioButton descending = (RadioButton) findViewById(R.id.btn_descending);
                String sort_mode = (String) spinner_sort.getSelectedItem();
                ListView output_table = (ListView) findViewById(R.id.list_items);
                if (tag.getText().length() <= 0 && sort_mode == "Tags") {
                    Toast check = Toast.makeText(getApplicationContext(), "Enter a Tag!", Toast.LENGTH_LONG);
                    check.show();
                } else {
                    switch (spinner_lang.getSelectedItem().toString()) {
                        case "Spanish":
                            language1 = LanguageIdentifier.SP;
                            break;
                        case "German":
                            language1 = LanguageIdentifier.DE;
                            break;
                        case "English":
                            language1 = LanguageIdentifier.EN;
                            break;
                        case "French":
                            language1 = LanguageIdentifier.FR;
                            break;
                        case "Italy":
                            language1 = LanguageIdentifier.IT;
                            break;
                    }
                }
            }
        });
    }
    @Override
    public void onResume() {
        super.onResume();

        DatabaseController dbController = new DatabaseController(this.getApplicationContext());
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
    public boolean onOptionsItemSelected(MenuItem item) {
        int id = item.getItemId();

        if (id == android.R.id.home) {
            onBackPressed();  return true;
        }

        return super.onOptionsItemSelected(item);
    }
}




