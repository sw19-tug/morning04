package com.group04.dictionary04;

import android.app.Activity;
import android.app.AlertDialog;
import android.content.DialogInterface;
import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.util.SparseBooleanArray;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ListView;
import android.widget.RatingBar;
import android.widget.Spinner;
import android.widget.TextView;
import android.widget.Toast;

import com.google.gson.Gson;
import com.group04.dictionary04.database.DatabaseController;
import com.group04.dictionary04.enums.DifficultyIdentifier;
import com.group04.dictionary04.enums.LanguageIdentifier;
import com.group04.dictionary04.interfaces.Exam;
import com.group04.dictionary04.model.default_Dictionary;
import com.group04.dictionary04.model.default_Entry;
import com.group04.dictionary04.model.default_Exam;
import com.group04.dictionary04.model.default_Language;
import com.group04.dictionary04.model.default_Vocabulary;

import java.util.ArrayList;
import java.util.List;

import static android.content.ContentValues.TAG;

public class AdvancedTestViewActivity extends Activity implements View.OnClickListener {

    private default_Dictionary dict;
    private Spinner lang1;
    private Spinner lang2;
    private EditText tag;
    private RatingBar rating;
    private Button btnFilter;
    private Button btnAdd;
    private Button btnStart;
    private ListView list;

    private List<default_Entry> exam_entries = new ArrayList<>();


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.advancedtestview);

        DatabaseController dbController = new DatabaseController(this.getApplicationContext());
        dict = dbController.getCurrentDatabase();


        lang1 = findViewById(R.id.spinner_from);
        lang2 = findViewById(R.id.spinner_to);
        tag = findViewById(R.id.et_tag);
        rating = findViewById(R.id.ratingBar_rate);
        btnFilter = findViewById(R.id.btn_filter2);
        btnAdd = findViewById(R.id.btn_add);
        btnStart = findViewById(R.id.btn_start);
        list = findViewById(R.id.lv_vocabs);

        setDropdown();

        btnFilter.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

            int difficulty = (int)rating.getRating();

            loadLanguageList(dict.getLanguageByIndex(lang1.getSelectedItem().toString()),
            dict.getLanguageByIndex(lang2.getSelectedItem().toString()).getLangName(),
            tag.getText().toString(), difficulty);

            tag.getText().clear();
            rating.setRating(0);
            }
        });

        btnStart.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

               Gson gson = new Gson();

               Intent myIntent = new Intent(AdvancedTestViewActivity.this, TestViewActivity.class);
               Exam exam = new default_Exam();
               exam.setVocsToTest(exam_entries);
               myIntent.putExtra("data", gson.toJson(exam));
               startActivity(myIntent);
            }
        });

        btnAdd.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                SparseBooleanArray checkedVocabs = list.getCheckedItemPositions();
                if (checkedVocabs != null) {

                    for(int count = 0; count < checkedVocabs.size(); count++) {

                        if (checkedVocabs.valueAt(count)) {

                            default_Entry entry = (default_Entry)list.getAdapter()
                              .getItem(checkedVocabs.keyAt(count));
                            exam_entries.add(entry);

                            String length = String.valueOf(exam_entries.size()) + " Elements in exam";

                           Toast toast = Toast.makeText(getApplicationContext(), length, Toast.LENGTH_LONG);
                           toast.show();

                           Log.d("log", "added entry " + entry.getId1().getValue() + " " + entry.getId2().getValue());

                        }
                    }
                }
            }
        });
    }

    public void setDropdown(){

        List<String> languages;
        languages = dict.getLanguagesStrings();

        ArrayAdapter<String> dataAdapter = new ArrayAdapter<String>(this,
                android.R.layout.simple_spinner_dropdown_item, languages);

        dataAdapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);

        lang1.setAdapter(dataAdapter);
        lang2.setAdapter(dataAdapter);
    }

    private void loadLanguageList(default_Language lang1, LanguageIdentifier lang2, String tag,
      int difficulty_value) {

        final List<default_Entry> entries = new ArrayList<>();
        List<default_Vocabulary> vocab = lang1.getVocabularies();

        for(default_Vocabulary vocIt : vocab)
        {
            for(default_Entry entryIt : dict.getEntries())
            {
                //tag and rating empty - show everything
                if(tag.isEmpty() && difficulty_value == 0){

                    if((vocIt.getId().equals(entryIt.getId1().getId()) &&
                      (entryIt.getId2().getLanguage() == lang2)))
                    {
                        entries.add(entryIt);
                    }
                }
                //only rating empty
                else if(!tag.isEmpty() && difficulty_value == 0)
                {
                    if(entryIt.getTag() != null){

                        if((vocIt.getId().equals(entryIt.getId1().getId()) &&
                          (entryIt.getId2().getLanguage() == lang2)) && entryIt.getTag().equals(tag))
                        {
                            entries.add(entryIt);
                        }
                    }
                }
                //only tag empty
                else if(tag.isEmpty())
                {
                    if(entryIt.getRating() != null){

                        if((vocIt.getId().equals(entryIt.getId1().getId()) &&
                          (entryIt.getId2().getLanguage() == lang2)) && difficulty_value ==
                          Integer.valueOf(entryIt.getRating()))
                        {
                            entries.add(entryIt);
                        }
                    }
                }
                //nothing empty
                else{

                    if(entryIt.getTag() != null && entryIt.getRating() != null){

                        if((vocIt.getId().equals(entryIt.getId1().getId()) &&
                          (entryIt.getId2().getLanguage() == lang2)) && difficulty_value ==
                          Integer.valueOf(entryIt.getRating()) && entryIt.getTag().equals(tag))
                        {
                            entries.add(entryIt);
                        }
                    }
                }
            }
        }

        final ArrayAdapter<default_Entry> arrayAdapter = new ArrayAdapter<>
                (this, android.R.layout.simple_list_item_multiple_choice, entries);

        list.setAdapter(arrayAdapter);
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
