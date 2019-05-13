package com.group04.dictionary04;

import android.app.Activity;
import android.app.AlertDialog;
import android.content.DialogInterface;
import android.os.Bundle;
import android.provider.MediaStore;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.view.MenuItem;
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
import com.group04.dictionary04.model.default_Filter;
import com.group04.dictionary04.model.default_Language;

import java.util.ArrayList;
import java.util.List;

public class RatingViewActivity extends AppCompatActivity {

    private LanguageIdentifier language1;
    private default_Dictionary dict = null;
    private DifficultyIdentifier difficult = null;
    private RadioButton btnAscending;
    private RadioButton btnDescending;
    private RatingBar ratingBar;
    private Button btnFilter;
    private ListView items;



    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        DatabaseController dbController = new DatabaseController(this.getApplicationContext());
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


        //SPINNER
        List<String> languages = new ArrayList<>();

        languages = dict.getLanguagesStrings();
        final Spinner lang_spinner = (Spinner) findViewById(R.id.spinner_langt);

        ArrayAdapter<String> dataAdapter = new ArrayAdapter<String>(this,
                android.R.layout.simple_spinner_dropdown_item, languages);

        dataAdapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);

        lang_spinner.setAdapter(dataAdapter);

        //TEST LIST INPUT
        List<String> vocabs = new ArrayList<String>();
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

                   //GET LANGUAGE
                   if(lang_spinner.getSelectedItem().equals("Spanish"))
                       language1 = LanguageIdentifier.SP;
                   else if(lang_spinner.getSelectedItem().equals("German"))
                        language1 = LanguageIdentifier.DE;
                   else if(lang_spinner.getSelectedItem().equals("English"))
                        language1 = LanguageIdentifier.EN;
                   else if(lang_spinner.getSelectedItem().equals("French"))
                        language1 = LanguageIdentifier.FR;
                   else if(lang_spinner.getSelectedItem().equals("Italy"))
                        language1 = LanguageIdentifier.IT;


                    Toast.makeText(RatingViewActivity.this, difficult.toString() + " "
                            + (int) ratingBar.getRating() + " " + language1.toString(),
                            Toast.LENGTH_SHORT).show();


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
