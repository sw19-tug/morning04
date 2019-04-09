package com.group04.dictionary04;

import android.app.Activity;
import android.app.AlertDialog;
import android.content.DialogInterface;
import android.media.Rating;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.EditText;
import android.widget.RatingBar;
import android.widget.Spinner;
import android.widget.Toast;

import com.group04.dictionary04.enums.LanguageIdentifier;
import com.group04.dictionary04.enums.DifficultyIdentifier;
import com.group04.dictionary04.enums.LanguageIdentifier;
import com.group04.dictionary04.database.DatabaseController;
import com.group04.dictionary04.interfaces.Dictionary;
import com.group04.dictionary04.model.default_Dictionary;

import org.w3c.dom.Text;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

import com.group04.dictionary04.database.DatabaseController;
import com.group04.dictionary04.model.default_Dictionary;
import com.group04.dictionary04.model.default_Language;
import com.group04.dictionary04.model.default_Vocabulary;

public class InputViewActivity extends Activity {

    private default_Dictionary dict = null;
    private LanguageIdentifier language1;
    private LanguageIdentifier language2;
    private DifficultyIdentifier difficult;



    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        final DatabaseController dbController = new DatabaseController(getApplicationContext());
        //dbController.saveTestDatabase();
        dict = dbController.getCurrentDatabase();

        setContentView(R.layout.inputview);
        List<String> languages = new ArrayList<>();

        //Adding all usable languages to List
        languages = dict.getLanguagesStrings();


        Spinner lang_spinner = (Spinner) findViewById(R.id.spinner1_input);
        Spinner lang_spinner2 = (Spinner) findViewById(R.id.spinner2_input);

        ArrayAdapter<String> dataAdapter = new ArrayAdapter<String>(this,
                android.R.layout.simple_spinner_item, languages);

        dataAdapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);

        //filling both lang spinners with options
        lang_spinner.setAdapter(dataAdapter);
        lang_spinner2.setAdapter(dataAdapter);
        Button button = (Button) findViewById(R.id.button_input);

        button.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                EditText field1 = (EditText) findViewById(R.id.txt_lang1_input);
                EditText field2 = (EditText) findViewById(R.id.txt_lang2_input);
                Spinner spinner1 = (Spinner) findViewById(R.id.spinner1_input);
                Spinner spinner2 = (Spinner) findViewById(R.id.spinner2_input);

                RatingBar difficulty = (RatingBar) findViewById(R.id.ratingBar_difficulty);
                EditText tag = (EditText) findViewById(R.id.txt_tag_input);


                if(spinner1.getSelectedItemPosition() == spinner2.getSelectedItemPosition())
                {
                    Toast lang_error = Toast.makeText(getApplicationContext(),"Need to select diff lang", Toast.LENGTH_LONG);
                    lang_error.show();

                }
                else if(field1.getText().length() <= 0 || field2.getText().length() <= 0)
                {
                    Toast check = Toast.makeText(getApplicationContext(), "one field is empty", Toast.LENGTH_LONG);
                    check.show();
                }
                else
                {

                    switch(spinner1.getSelectedItem().toString())
                    {
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

                    switch(spinner2.getSelectedItem().toString())
                    {
                        case "Spanish":
                            language2 = LanguageIdentifier.SP;
                            break;
                        case "German":
                            language2 = LanguageIdentifier.DE;
                            break;
                        case "English":
                            language2 = LanguageIdentifier.EN;
                            break;
                        case "French":
                            language2 = LanguageIdentifier.FR;
                            break;
                        case "Italy":
                            language2 = LanguageIdentifier.IT;
                            break;
                    }


                    switch((int)difficulty.getRating())
                    {
                        case 1:
                            difficult = DifficultyIdentifier.BEGINNER;
                            break;
                        case 2:
                            difficult = DifficultyIdentifier.INTERMEDIATE;
                            break;
                        case 3:
                            difficult = DifficultyIdentifier.ADVANCED;
                            break;
                        case 4:
                            difficult = DifficultyIdentifier.NATIVE;
                            break;
                    }



                    if(difficulty.getRating() != 0 && !tag.getText().toString().isEmpty())
                    {
                        dict.addTranslationWithDiffAndTag(field1.getText().toString(), field2.getText().toString(), language1, language2, difficult, tag.getText().toString());

                        String toast_success1 = "added successfully";
                        Toast toast_success = Toast.makeText(getApplicationContext(),toast_success1, Toast.LENGTH_LONG);
                        toast_success.show();
                        field1.setText("");
                        field2.setText("");
                        tag.setText("");
                        difficulty.setRating(0);

                    }
                    else
                    {
                        AlertDialog.Builder alertdial = new AlertDialog.Builder(InputViewActivity.this);
                        alertdial.setMessage("Do you want to continue without setting a difficult and adding a tag?").setCancelable(false)
                                .setPositiveButton("Yes", new DialogInterface.OnClickListener() {
                                    @Override
                                    public void onClick(DialogInterface dialog, int which) {

                                        EditText field1 = (EditText) findViewById(R.id.txt_lang1_input);
                                        EditText field2 = (EditText) findViewById(R.id.txt_lang2_input);
                                        EditText tag = (EditText) findViewById(R.id.txt_tag_input);

                                        dict.addTranslation(field1.getText().toString(), field2.getText().toString(), language1, language2);

                                        String toast_success1 = "added successfully w/o tag and diff";
                                        Toast toast_success = Toast.makeText(getApplicationContext(),toast_success1, Toast.LENGTH_LONG);
                                        toast_success.show();
                                        field1.setText("");
                                        field2.setText("");


                                    }
                                })
                                .setNegativeButton("No", new DialogInterface.OnClickListener() {
                                    @Override
                                    public void onClick(DialogInterface dialog, int which) {
                                        dialog.cancel();
                                    }
                                });

                        AlertDialog alert = alertdial.create();
                        alert.setTitle("Continue without adding all info's?");
                        alert.show();

                    }

                    Log.d("log", "Currently there are " + dict.getEntries().size() + " entries in this dict");

                }

            }
        });


    }


    @Override
    public void onResume() {
        super.onResume();

        DatabaseController dbController = new DatabaseController(this.getApplicationContext());
        //dbController.saveTestDatabase();
        dict = dbController.getCurrentDatabase();
    }

    @Override
    public void onPause() {
        super.onPause();

        DatabaseController dbController = new DatabaseController(this.getApplicationContext());
        dbController.saveCurrentDatabase(dict);
        System.out.println(dict.getEntries().size());
    }

    @Override
    public void onStop() {
        super.onStop();

        DatabaseController dbController = new DatabaseController(this.getApplicationContext());
        dbController.saveCurrentDatabase(dict);
    }



}
