package com.group04.dictionary04;

import android.app.Activity;
import android.app.AlertDialog;
import android.content.DialogInterface;
import android.os.Bundle;
import android.provider.MediaStore;
import android.util.Log;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.ListView;
import android.widget.RadioButton;
import android.widget.RadioGroup;
import android.widget.RatingBar;
import android.widget.Spinner;
import android.widget.Toast;

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
import java.util.Collections;
import java.util.Comparator;
import java.util.List;

public class RatingViewActivity extends Activity {

    private LanguageIdentifier language1;
    private default_Dictionary dict = null;
    private DifficultyIdentifier difficult = null;
    private RadioButton btnAscending;
    private RadioButton btnDescending;
    private RatingBar ratingBar;
    private Button btnFilter;
    private ListView items;
    private List<String> vocabs = new ArrayList<String>();




    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        DatabaseController dbController = new DatabaseController(this.getApplicationContext());
        dbController.saveTestDatabase();
        dict = dbController.getCurrentDatabase();

        setContentView(R.layout.ratingview);

        ratingBar = (RatingBar) findViewById(R.id.ratingBar_rating);
        btnFilter = (Button) findViewById(R.id.btn_filter);
        btnAscending = (RadioButton) findViewById(R.id.btn_ascending);
        btnDescending = (RadioButton) findViewById(R.id.btn_descending);
        items = (ListView) findViewById(R.id.list_items);


        //SPINNER
        List<String> languages = new ArrayList<>();

        languages = dict.getLanguagesStrings();
        final Spinner lang_spinner = (Spinner) findViewById(R.id.spinner_langt);

        ArrayAdapter<String> dataAdapter = new ArrayAdapter<String>(this,
                android.R.layout.simple_spinner_dropdown_item, languages);

        dataAdapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);

        lang_spinner.setAdapter(dataAdapter);


        //TEST LIST INPUT
        vocabs.add("Vocab1");
        vocabs.add("Vocab2");
        vocabs.add("Vocab3");

        ArrayAdapter<String> rateAdapter = new ArrayAdapter<>(RatingViewActivity.this,
                android.R.layout.simple_list_item_1, vocabs);

        items.setAdapter(rateAdapter);


        btnFilter.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                //CHECK RADIO BUTTONS
                if(!btnAscending.isChecked() && !btnDescending.isChecked()) {
                    Toast.makeText(RatingViewActivity.this, "Please choose a sort sequence",
                            Toast.LENGTH_SHORT).show();
                }
                //CHECK RATING BAR
                else if((int)ratingBar.getRating() == 0){
                    Toast.makeText(RatingViewActivity.this, "Please choose a difficulty",
                            Toast.LENGTH_SHORT).show();
                }
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


                    loadCurrentLanguageList(dict.getLanguageByIndex(lang_spinner.getSelectedItem().toString()), difficult);

                }
            }
        });


        items.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
                AlertDialog.Builder dialog = new AlertDialog.Builder(RatingViewActivity.this);
                dialog.setMessage("Do you want to change the difficulty?").setCancelable(false)
                        .setPositiveButton("Yes", new DialogInterface.OnClickListener() {
                            @Override
                            public void onClick(DialogInterface dialog, int which) {
                                String changed = "Changed";
                                Toast.makeText(RatingViewActivity.this, changed,
                                        Toast.LENGTH_SHORT).show();
                            }
                        })
                        .setNegativeButton("No", new DialogInterface.OnClickListener() {
                            @Override
                            public void onClick(DialogInterface dialog, int which) {
                                dialog.cancel();
                            }
                        });

                AlertDialog dia_alert = dialog.create();
                dia_alert.setTitle("Difficulty");
                dia_alert.show();
            }
        });
    }



    private void loadCurrentLanguageList(default_Language language, DifficultyIdentifier difficulty) {


        List<default_Entry> entries = new ArrayList<>();
        List<default_Vocabulary> vocabularie = language.getVocabularies();

        for(default_Vocabulary vocIt : vocabularie)
        {
            Log.d("log", "looking for vocabulary " + vocIt.getId() + " " + vocIt.getValue() + " Difficulty: " +
                    difficulty);
            for(default_Entry entryIt : dict.getEntries())
            {
                Log.d("log", "check matching in Entry " +
                        entryIt.getId1().getId() + " " + entryIt.getId1().getValue() + " " +
                        entryIt.getId2().getId() + " " + entryIt.getId2().getValue() + " Rating:" + entryIt.getRating());
                if((vocIt.getId().equals(entryIt.getId1().getId()) || vocIt.getId().equals(entryIt.getId2().getId())))
                {
                    Log.d("log", "MATCHING FOUND AND ADD TO LIST " + vocIt.getId());
                    entries.add(entryIt);
                }

//                 currently not working because no difficulty set by the entries
//                if((vocIt.getId().equals(entryIt.getId1().getId()) || vocIt.getId().equals(entryIt.getId2().getId())) &&
//                        difficulty.equals(entryIt.getRating()))
//                {
//                    Log.d("log", "MATCHING FOUND AND ADD TO LIST " + vocIt.getId());
//                    entries.add(entryIt);
//                }
            }
        }
        // TODO: Sorting

        Log.d("log", "now there are " + entries.size() + " in the new atries list");

        ArrayAdapter<default_Vocabulary> dataAdapter = new ArrayAdapter<default_Vocabulary>(this, android.R.layout.simple_list_item_1, language.getVocabularies());
        items.setAdapter(dataAdapter);

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
