package com.group04.dictionary04;

import android.app.Activity;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.Button;
import android.widget.Toast;

public class InputViewActivity  extends Activity  {



    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.inputview);


        Button button = (Button) findViewById(R.id.button_input);

        button.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Toast displayToast = Toast.makeText(InputViewActivity.this,"top", Toast.LENGTH_LONG);
                displayToast.show();

            }
        });



    }







}
