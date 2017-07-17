package com.schoolofskills.myfirebasesample;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.AdapterView;
import android.widget.Button;

public class LocationSelectorActivity extends AppCompatActivity {

    private Button mLocation1, mLocation2, mLocation3, mLocation4;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_location_selector);

        mLocation1 = (Button) findViewById(R.id.buttonLocation1);
        mLocation2 = (Button) findViewById(R.id.buttonLocation2);
        mLocation3 = (Button) findViewById(R.id.buttonLocation3);
        mLocation4 = (Button) findViewById(R.id.buttonLocation4);

        mLocation1.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(LocationSelectorActivity.this, Location1Activity.class);
                startActivity(intent);
            }
        });

        mLocation2.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(LocationSelectorActivity.this, Location2Activity.class);
                startActivity(intent);
            }
        });

        mLocation3.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(LocationSelectorActivity.this, Location3Activity.class);
                startActivity(intent);
            }
        });

        mLocation4.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(LocationSelectorActivity.this, Location4Activity.class);
                startActivity(intent);
            }
        });


    }


}
