package com.group04.dictionary04;

import android.app.Activity;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.util.Log;
import android.view.View;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Spinner;
import android.widget.Toast;

import com.group04.dictionary.enums.LanguageIdentifier;
import com.group04.dictionary04.database.DatabaseController;
import com.group04.dictionary04.interfaces.Dictionary;
import com.group04.dictionary04.model.default_Dictionary;

import org.w3c.dom.Text;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

import com.group04.dictionary04.database.DatabaseController;
import com.group04.dictionary04.model.default_Dictionary;

public class InputViewActivity extends Activity {

    private default_Dictionary dict = null;
    private LanguageIdentifier language1;
    private LanguageIdentifier language2;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        final DatabaseController dbController = new DatabaseController(getApplicationContext());
        dbController.saveTestDatabase();
        dict = dbController.getCurrentDatabase();

        setContentView(R.layout.inputview);
        List<String> languages = new ArrayList<>();

        languages.add(0, "German");
        languages.add(1, "English");
        languages.add(2, "Spanish");

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
                    String toast_success1 = "Successful";
                    Toast toast_success = Toast.makeText(getApplicationContext(),toast_success1, Toast.LENGTH_LONG);
                    toast_success.show();

                    switch (spinner1.getSelectedItemPosition())
                    {
                        case 0:
                            language1 = LanguageIdentifier.valueOf("DE");
                        case 1:
                            language1 = LanguageIdentifier.valueOf("EN");
                        case 2:
                            language1 = LanguageIdentifier.valueOf("SP");
                    }
                    switch (spinner2.getSelectedItemPosition())
                    {
                        case 0:
                            language2 = LanguageIdentifier.valueOf("DE");
                        case 1:
                            language2 = LanguageIdentifier.valueOf("EN");
                        case 2:
                            language2 = LanguageIdentifier.valueOf("SP");
                    }

                    dict.addTranslation(field1.toString(), field2.toString(), language1, language2);

//                    Log.d("log", "Currently there are " + dict.getEntries().size() + " entries in this dict");

                }

            }
        });
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
