package com.group04.dictionary04;

import android.app.Activity;
import android.app.AlertDialog;
import android.content.DialogInterface;
import android.media.Rating;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.util.Log;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.MenuItem;
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
import com.group04.dictionary04.model.CustomAdapter;
import com.group04.dictionary04.model.default_Dictionary;

import org.w3c.dom.Text;

import java.util.ArrayList;
import java.util.List;

import com.group04.dictionary04.database.DatabaseController;
import com.group04.dictionary04.model.default_Dictionary;
import com.group04.dictionary04.model.default_Language;
import com.group04.dictionary04.model.default_Vocabulary;

public class InputViewActivity extends AppCompatActivity {

    private default_Dictionary dict = null;
    private LanguageIdentifier language1;
    private LanguageIdentifier language2;
    private DifficultyIdentifier difficult;

    String[] langs={"Spanish","German","English","French","Italian"};
    int images[] = {R.drawable.spain, R.drawable.germany, R.drawable.united_states, R.drawable.france, R.drawable.italy };

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.inputview);
        setSupportActionBar((Toolbar)findViewById(R.id.myactionbar));
        getSupportActionBar().setDisplayHomeAsUpEnabled(true);
        getSupportActionBar().setDisplayShowHomeEnabled(true);

        final DatabaseController dbController = new DatabaseController(getApplicationContext());
        //dbController.saveTestDatabase();
        dict = dbController.getCurrentDatabase();


        List<String> languages = new ArrayList<>();

        //Adding all usable languages to List
        languages = dict.getLanguagesStrings();


        Spinner lang_spinner = (Spinner) findViewById(R.id.spinner1_input);
        Spinner lang_spinner2 = (Spinner) findViewById(R.id.spinner2_input);

        ArrayAdapter<String> dataAdapter = new ArrayAdapter<String>(this,
                android.R.layout.simple_spinner_item, languages);

        CustomAdapter customAdapter=new CustomAdapter(this,
                images, langs);


        dataAdapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
        //customAdapter.setDropDownViewResource(android.R.layout.spinnercustom);


        //filling both lang spinners with options
        lang_spinner.setAdapter(dataAdapter);
        lang_spinner2.setAdapter(dataAdapter);
        //lang_spinner.setAdapter(customAdapter);
        //lang_spinner2.setAdapter(customAdapter);

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
                    Log.e(spinner1.getSelectedItem().toString(), "success");

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
                        case "Italian":
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
                        case "Italian":
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

    public void returnMenu(View view) {
        finish();
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

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        int id = item.getItemId();

        if (id == android.R.id.home) {
            onBackPressed();  return true;
        }

        return super.onOptionsItemSelected(item);
    }

}
