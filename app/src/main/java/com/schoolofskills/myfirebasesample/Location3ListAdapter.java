package com.schoolofskills.myfirebasesample;

import android.app.Activity;
import android.view.View;
import android.widget.TextView;

import com.firebase.ui.database.FirebaseListAdapter;
import com.google.firebase.database.Query;

/**
 * Created by premkumar on 05/06/2016.
 */
public class Location3ListAdapter extends FirebaseListAdapter<Location3> {

    public Location3ListAdapter(Activity activity, Class<Location3> modelClass, int modelLayout, Query ref) {
        super(activity, modelClass, modelLayout, ref);
        this.mActivity = activity;
    }

    @Override
    protected void populateView(View v, Location3 model, int position) {

        //TextView locationNumber = (TextView) v.findViewById(R.id.locationNumber);
        TextView locationName = (TextView) v.findViewById(R.id.locationName);
        TextView locationID = (TextView) v.findViewById(R.id.locationID);

        //locationNumber.setText(model.getLocationNumber());
        locationName.setText(model.getLocation());
        locationID.setText(Integer.toString((int) model.getStationNumber()));
    }
}
