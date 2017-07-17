package com.schoolofskills.myfirebasesample;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;


public class ChecklistSelectorActivity extends AppCompatActivity {

    private Button mElectrical1, mElectrical2, mHse;
    private TextView mSelectChecklistText;
    private String mListID;
    private Location1 mLocation1;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_checklist_selector);

        mElectrical1 = (Button) findViewById(R.id.buttonInspection1);
        mElectrical2 = (Button) findViewById(R.id.buttonInspection2);
        mHse = (Button) findViewById(R.id.buttonHseInspection);
        mSelectChecklistText = (TextView) findViewById(R.id.textViewLocation);

        Intent intent = getIntent();
        mListID = intent.getStringExtra("location");

//        Toolbar toolbar = (Toolbar) findViewById(R.id.);
//        /* Common toolbar setup */
//        setSupportActionBar(toolbar);
//        /* Add back button to the action bar */
//        if (getSupportActionBar() != null) {
//            getSupportActionBar().setDisplayHomeAsUpEnabled(true);
//        }
        mSelectChecklistText.setText(mListID);

        setTitle(mListID);

        //using the different database for testing purpose
//        DatabaseReference selectChecklistRef = FirebaseDatabase.getInstance().getReference().child(mListID).child("Checklist");
//
//        selectChecklistRef.addChildEventListener(new ChildEventListener() {
//            @Override
//            public void onChildAdded(DataSnapshot dataSnapshot, String s) {
//                String checklist = dataSnapshot.getKey();
//                mElectrical1.setText(checklist);
//            }
//
//            @Override
//            public void onChildChanged(DataSnapshot dataSnapshot, String s) {
//
//            }
//
//            @Override
//            public void onChildRemoved(DataSnapshot dataSnapshot) {
//
//            }
//
//            @Override
//            public void onChildMoved(DataSnapshot dataSnapshot, String s) {
//
//            }
//
//            @Override
//            public void onCancelled(DatabaseError databaseError) {
//
//            }
//        });

        mElectrical1.setText("Inspection1");
        mElectrical2.setText("Inspection2");
        mHse.setText("HSE_Inspection");

        mElectrical1.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(ChecklistSelectorActivity.this, Inspection1Activity.class);
                Bundle extras = new Bundle();
                extras.putString("location", mListID);
                extras.putString("inspection", String.valueOf(mElectrical1.getText()));

                intent.putExtras(extras);
                startActivity(intent);
            }
        });

        mElectrical2.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(ChecklistSelectorActivity.this, Inspection1Activity.class);
                Bundle extras = new Bundle();
                extras.putString("location", mListID);
                extras.putString("inspection", String.valueOf(mElectrical2.getText()));

                intent.putExtras(extras);
                startActivity(intent);
            }
        });




   }
}
