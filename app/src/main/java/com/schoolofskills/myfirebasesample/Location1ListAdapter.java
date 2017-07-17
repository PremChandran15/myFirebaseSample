package com.schoolofskills.myfirebasesample;

import android.app.Activity;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Filter;
import android.widget.Filterable;
import android.widget.TextView;

//import com.firebase.client.Query;
//import com.firebase.ui.FirebaseListAdapter;

import com.firebase.ui.database.FirebaseListAdapter;
import com.google.firebase.database.Query;

import java.util.ArrayList;

/**
 * Created by premkumar on 23/04/2016.
 */
public class Location1ListAdapter extends FirebaseListAdapter<Location1>{

    public Location1ListAdapter(Activity activity, Class<Location1> modelClass, int modelLayout, Query ref) {
        super(activity, modelClass, modelLayout, ref);
        this.mActivity = activity;
    }

    @Override
    protected void populateView(View v, Location1 model, int position) {

        //TextView locationNumber = (TextView) v.findViewById(R.id.locationNumber);
        TextView locationName = (TextView) v.findViewById(R.id.locationName);
        TextView locationID = (TextView) v.findViewById(R.id.locationID);

        //locationNumber.setText(model.getLocationNumber());
        locationName.setText(model.getLocation());
        locationID.setText(Integer.toString((int) model.getStationNumber()));
    }
}
