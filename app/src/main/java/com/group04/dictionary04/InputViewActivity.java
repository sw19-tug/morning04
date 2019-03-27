package com.group04.dictionary04;

import android.app.Activity;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Spinner;
import android.widget.Toast;

import org.w3c.dom.Text;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

public class InputViewActivity  extends Activity  {



    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
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
                }

            }
        });



    }



}
