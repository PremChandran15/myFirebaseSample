package com.schoolofskills.myfirebasesample;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.text.Editable;
import android.text.TextWatcher;
import android.view.View;
import android.widget.AdapterView;
import android.widget.EditText;
import android.widget.ListView;
import android.widget.TextView;

import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;

import java.util.ArrayList;

public class Location1Activity extends AppCompatActivity {

    private ListView mListView;
    private Location1ListAdapter mLocation1ListAdapter;
    private EditText mLocation1Search;
    private View viewLocation1;
    private ArrayList<String> locationNames = new ArrayList<>();
    private EditText et;


    public Location1Activity() {
    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_location1);

        mLocation1Search = (EditText) findViewById(R.id.searchLocation1);

        final DatabaseReference locationRef = FirebaseDatabase.getInstance().getReference().child("Locations").child("Location1");

//-------these are steps if I just want to populate the list view using FirebaseUI----------
        //mListView = new ListView(this);
        mListView = (ListView) findViewById(R.id.listViewLocation1);

        mLocation1ListAdapter = new Location1ListAdapter(this, Location1.class,R.layout.single_location_list,locationRef);

        mListView.setAdapter(mLocation1ListAdapter);

        //setContentView(mListView);

//        for(int i=0; i<mListView.getChildCount();i++){
//            viewLocation1 = mListView.getChildAt(i);
//            if(viewLocation1 instanceof TextView){
//                String fromID = viewLocation1.getResources().getResourceName(viewLocation1.getId());
//                if(fromID.contains("locationName")){
//                    String locationName = ((TextView) viewLocation1).getText().toString();
//                    locationNames.add(locationName);
//                }
//            }
//        }



        mLocation1Search.addTextChangedListener(new TextWatcher() {
            @Override
            public void beforeTextChanged(CharSequence s, int start, int count, int after) {

            }

            @Override
            public void onTextChanged(CharSequence s, int start, int before, int count) {
                if(s!=null || s.length()>0){
                    String locationName = s.toString();
                    mLocation1ListAdapter = new Location1ListAdapter(Location1Activity.this, Location1.class, R.layout.single_location_list,
                            locationRef.orderByChild("location").startAt(locationName).endAt(locationName + "~"));
                    mListView.setAdapter(mLocation1ListAdapter);

                    //setContentView(mListView);
                }
                else if(s.length()==0){
                    mLocation1ListAdapter = new Location1ListAdapter(Location1Activity.this, Location1.class,R.layout.single_location_list,locationRef);

                    mListView.setAdapter(mLocation1ListAdapter);

                    //setContentView(mListView);
                }
            }

            @Override
            public void afterTextChanged(Editable s) {
                if(s==null){
                    mLocation1ListAdapter = new Location1ListAdapter(Location1Activity.this, Location1.class,R.layout.single_location_list,locationRef);
                    mListView.setAdapter(mLocation1ListAdapter);
                }


            }
        });

        mListView.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
                Location1 selectedLocation = mLocation1ListAdapter.getItem(position);

                if(selectedLocation!=null){
                    Intent intent = new Intent(Location1Activity.this, ChecklistSelectorActivity.class);
                    /* Get the list ID using the adapter's get ref method to get the Firebase
                     * ref and then grab the key.
                     */
                    String listId = mLocation1ListAdapter.getRef(position).getKey();
                    intent.putExtra("location",listId);
                    /* Starts an active showing the details for the selected list */
                    startActivity(intent);
                }
            }
        });
    }

    @Override
    public void onDestroy() {
        super.onDestroy();
        //mLocation1ListAdapter.cleanup();
    }

    @Override
    protected void onRestart() {
        super.onRestart();
        //pass the saved instance state here
        //onCreate();
    }
}

