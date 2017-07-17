package com.schoolofskills.myfirebasesample;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ListView;

import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;

public class Location2Activity extends AppCompatActivity {

    private ListView mListView;
    private Location2ListAdapter mLocation2ListAdapter;

    public Location2Activity() {
    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        DatabaseReference locationRef = FirebaseDatabase.getInstance().getReference().child("Locations").child("Location2");

//-------these are steps if I just want to populate the list view using FirebaseUI----------
        mListView = new ListView(this);

        mLocation2ListAdapter = new Location2ListAdapter(this, Location2.class,R.layout.single_location_list,locationRef);

        mListView.setAdapter(mLocation2ListAdapter);

        setContentView(mListView);

        mListView.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
                Location2 selectedLocation = mLocation2ListAdapter.getItem(position);

                if(selectedLocation!=null){
                    Intent intent = new Intent(Location2Activity.this, ChecklistSelectorActivity.class);
                    /* Get the list ID using the adapter's get ref method to get the Firebase
                     * ref and then grab the key.
                     */
                    String listId = mLocation2ListAdapter.getRef(position).getKey();
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

}
