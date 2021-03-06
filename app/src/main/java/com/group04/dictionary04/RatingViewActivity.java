package com.group04.dictionary04;

import android.app.Activity;
import android.app.AlertDialog;
import android.content.DialogInterface;
import android.os.Bundle;
import android.provider.MediaStore;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.text.Editable;
import android.text.TextWatcher;
import android.view.MenuItem;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.*;

import com.group04.dictionary04.enums.DifficultyIdentifier;
import com.group04.dictionary04.enums.LanguageIdentifier;
import com.group04.dictionary04.database.DatabaseController;
import com.group04.dictionary04.interfaces.Dictionary;
import com.group04.dictionary04.model.default_Dictionary;
import com.group04.dictionary04.model.default_Entry;
import com.group04.dictionary04.model.default_Filter;
import com.group04.dictionary04.model.default_Language;
import com.group04.dictionary04.model.default_Vocabulary;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collections;
import java.util.Comparator;
import java.util.List;

public class RatingViewActivity extends AppCompatActivity {

    private LanguageIdentifier language1;
    private default_Dictionary dict = null;
    private DifficultyIdentifier difficult = null;
    private RadioButton btnAscending;
    private RadioButton btnDescending;
    private RatingBar ratingBar;
    private RatingBar ratingBarNew;
    private Button btnFilter;
    private ListView items;
    private List<String> vocabs = new ArrayList<String>();

    private RatingBar ratingPopup;
    private Button btnPopup;
    private Button btnClear;
    private View ratingPopupView;
    private EditText search = null;
    private Spinner lang_spinner = null;
    private Spinner tag_spinner = null;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        DatabaseController dbController = new DatabaseController(this.getApplicationContext());
        //dbController.saveTestDatabase();
        dict = dbController.getCurrentDatabase();

        setContentView(R.layout.ratingview);
        setSupportActionBar((Toolbar)findViewById(R.id.myactionbar));
        getSupportActionBar().setDisplayHomeAsUpEnabled(true);
        getSupportActionBar().setDisplayShowHomeEnabled(true);

        ratingBar = (RatingBar) findViewById(R.id.ratingBar_rating);
        btnFilter = (Button) findViewById(R.id.btn_filter);
        btnAscending = (RadioButton) findViewById(R.id.btn_ascending);
        btnDescending = (RadioButton) findViewById(R.id.btn_descending);
        items = (ListView) findViewById(R.id.list_items);
        btnClear = (Button) findViewById(R.id.btn_clear);

        search = (EditText) findViewById(R.id.search);
        //SPINNER
        List<String> languages = new ArrayList<>();

        languages = dict.getLanguagesStrings();
        lang_spinner = (Spinner) findViewById(R.id.spinner_langt);

        ArrayAdapter<String> dataAdapter = new ArrayAdapter<String>(this,
                android.R.layout.simple_spinner_dropdown_item, languages);

        dataAdapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);

        lang_spinner.setAdapter(dataAdapter);

        //TEST LIST INPUT
        //vocabs.add("");


        ArrayAdapter<String> rateAdapter = new ArrayAdapter<>(RatingViewActivity.this,
                android.R.layout.simple_list_item_1, vocabs);

        items.setAdapter(rateAdapter);

        search.addTextChangedListener(new TextWatcher() {
            @Override
            public void beforeTextChanged(CharSequence s, int start, int count, int after) {

            }

            @Override
            public void onTextChanged(CharSequence s, int start,
                                      int before, int count) {

                Log.d("asdf", "Bin hier");
                queryData();
            }

            @Override
            public void afterTextChanged(Editable s) {

            }
        });

        btnFilter.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                queryData();
            }
        });

        btnClear.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                ratingBar.setRating(0);
            }
        });


        items.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(final AdapterView<?> parent, View view, final int position, long id) {


                AlertDialog.Builder dialog = new AlertDialog.Builder(RatingViewActivity.this);

                initPopupViewControls();

                dialog.setView(ratingPopupView);
                Button change_difficulty_button = (Button)ratingPopupView.findViewById(R.id.btn_change_difficulty);
                Button delete_entry_button = (Button)ratingPopupView.findViewById(R.id.btn_delete_entry);

                final AlertDialog dia_alert = dialog.create();
                dia_alert.setTitle("Change difficulty or delete entry");
                dia_alert.show();

                change_difficulty_button.setOnClickListener(new View.OnClickListener() {
                    @Override
                    public void onClick(View v) { {
                        ratingBarNew = (RatingBar) ratingPopupView.findViewById(R.id.ratingBar_popup);
                        default_Entry entry_to_change = (default_Entry)parent.getAdapter().getItem(position);
                        int new_rating = (int)ratingBarNew.getRating();
                        entry_to_change.setRating(Integer.toString(new_rating));
                        Toast.makeText(RatingViewActivity.this, "CHANGED " + entry_to_change.getId1().getValue() +" TO " + new_rating + " STARS",
                                Toast.LENGTH_SHORT).show();

                        // get Difficulty from ratingBar and set it to default entry
                        dia_alert.cancel();

                    }

                    }

                });

                delete_entry_button.setOnClickListener(new View.OnClickListener() {
                    @Override
                    public void onClick(View v) { {
                        default_Entry entry_to_delete = (default_Entry)parent.getAdapter().getItem(position);
                        dict.deleteEntry(entry_to_delete);
                        //entry_to_change.setRating(Integer.toString(new_rating));
                        Toast.makeText(RatingViewActivity.this, "Entry " + entry_to_delete.getId1().getValue() +" deleted",
                                Toast.LENGTH_SHORT).show();

                        // get Difficulty from ratingBar and set it to default entry
                        dia_alert.cancel();

                        //refresh ArrayAdapter after deleting entry
                        queryData();
                    }

                    }

                });

            }
        });
    }

    private void queryData() {
        //CHECK RADIO BUTTONS
        if(!btnAscending.isChecked() && !btnDescending.isChecked()) {
            Toast.makeText(RatingViewActivity.this, "Please choose a sort sequence",
                    Toast.LENGTH_SHORT).show();
        }
        //CHECK RATING BAR
        /*else if((int)ratingBar.getRating() == 0){
            Toast.makeText(RatingViewActivity.this, "Please choose a difficulty",
                    Toast.LENGTH_SHORT).show();
        }*/
        else {

            //GET DIFFICULTY
            if((int) ratingBar.getRating() == 1)
                difficult = DifficultyIdentifier.BEGINNER;
            else if((int) ratingBar.getRating() == 2)
                difficult = DifficultyIdentifier.INTERMEDIATE;
            else if((int) ratingBar.getRating() == 3)
                difficult = DifficultyIdentifier.ADVANCED;
            else
                difficult = DifficultyIdentifier.NATIVE;

            loadCurrentLanguageList(dict.getLanguageByName(lang_spinner.getSelectedItem().toString()), difficult);

            //ratingBar.setRating(0);
        }
    }

    private void initPopupViewControls()
    {
        // Get layout inflater object.
        LayoutInflater layoutInflater = LayoutInflater.from(RatingViewActivity.this);

        // Inflate the popup dialog from a layout xml file.
        ratingPopupView = layoutInflater.inflate(R.layout.ratingpopup, null);
    }

    //Sort List ascending or descending
    private void sortList(List<default_Entry> list){
        if(btnAscending.isChecked()){
            Collections.sort(list, new Comparator<default_Entry>() {
                @Override
                public int compare(default_Entry o1, default_Entry o2) {
                    return o1.getId1().getValue().compareTo(o2.getId1().getValue());
                }
            });
        }
        else{
            Collections.sort(list, new Comparator<default_Entry>() {
                @Override
                public int compare(default_Entry o1, default_Entry o2) {
                    return o2.getId1().getValue().compareTo(o1.getId1().getValue());
                }
            });
        }
    }


    private void loadCurrentLanguageList(default_Language language, DifficultyIdentifier difficulty) {
        String searchText = search.getText().toString();
        float ratingBarValue = ratingBar.getRating();
        final List<default_Entry> entries = language.getVocabulariesQueryByTagRating(ratingBarValue, difficulty, searchText, dict);
        //List<default_Vocabulary> vocabularie = language.getVocabularies();
        //int difficulty_value = difficulty.getValue();

        /*List<default_Vocabulary> vocabularie = language.getVocabularies();
        int difficulty_value = difficulty.getValue();

        for(default_Vocabulary vocIt : vocabularie)
        {
            Log.d("log", "looking for vocabulary " + vocIt.getId() + " " + vocIt.getValue() + " Difficulty: " +
                    difficulty_value);
            for(default_Entry entryIt : dict.getEntries())
            {
                Log.d("log", "check matching in Entry " +
                        entryIt.getId1().getId() + " " + entryIt.getId1().getValue() + " " +
                        entryIt.getId2().getId() + " " + entryIt.getId2().getValue() + " Rating:" + entryIt.getRating());


                if(entryIt.getRating() == null) // no rating at the current record - so add it into the list
                {
                    if((vocIt.getId().equals(entryIt.getId1().getId()) || vocIt.getId().equals(entryIt.getId2().getId())))
                    {
                        Log.d("log", "MATCHING FOUND BUT NO RATING GIVVEN AND ADD TO LIST " + vocIt.getId());
                        entries.add(entryIt);
                    }
                }
                else // there is a rating
                {
                    if((vocIt.getId().equals(entryIt.getId1().getId()) || vocIt.getId().equals(entryIt.getId2().getId())) &&
                            difficulty_value == Integer.valueOf(entryIt.getRating()))
                    {
                        Log.d("log", "MATCHING FOUND AND ADD TO LIST " + vocIt.getId());
                        entries.add(entryIt);
                    }
                }
            }
        }*/

        Log.d("log", "now there are " + entries.size() + " in the new atries list");

        // Show Entries in List
        ArrayAdapter<default_Entry> dataAdapter = new ArrayAdapter<default_Entry>(this, android.R.layout.simple_list_item_2, android.R.id.text1, entries) {
            @Override
            public View getView(int position, View convertView, ViewGroup parent) {
                View view = super.getView(position, convertView, parent);
                TextView text1 = (TextView) view.findViewById(android.R.id.text1);
                TextView text2 = (TextView) view.findViewById(android.R.id.text2);

                if(entries.get(position).getTag() != null)
                    text1.setText(entries.get(position).getId1().getValue() + " - (" + entries.get(position).getTag() + ")");
                else
                    text1.setText(entries.get(position).getId1().getValue());

                text2.setText(entries.get(position).getId2().getValue());
                return view;
            }
        };

        sortList(entries);

        items.setAdapter(dataAdapter);
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
