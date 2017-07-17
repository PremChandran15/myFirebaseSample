package com.schoolofskills.myfirebasesample;

import android.content.Intent;
import android.database.sqlite.SQLiteException;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.MotionEvent;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ScrollView;
import android.widget.Spinner;
import android.widget.TableLayout;
import android.widget.TableRow;

import com.schoolofskills.myfirebasesample.photomultiple.FileUploadActivity;

import java.util.ArrayList;

import data.DatabaseHelper;

public class Inspection1Activity extends AppCompatActivity {

    private EditText mResistanceValue, mCurrentValue, mCapacitanceValue, mVoltageValue, mResistanceRemark, mVoltageRemark, mFlowmeterRemark, mPipeRemark, mTapRemark;
    private Spinner mFlowmeterValue, mTapValue, mPipeValue,mItem65, mItem66,mItem67,mItem68,mItem69,mItem70,mItem71;
    private String mListID, mInspectionID;
    private Button mUpdateButton, mCheckDB;
    private String tag, text, textValue;
    private TableRow mVoltageLayout, mFlowmeterLayout;
    private MultiSpinner mMultiSpinner;
    private ScrollView mScrollView;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_inspection1);

        mResistanceValue = (EditText) findViewById(R.id.resistanceValue);
        mCapacitanceValue = (EditText) findViewById(R.id.capacitanceValue);
        mVoltageValue = (EditText) findViewById(R.id.voltageValue);
        mCurrentValue = (EditText) findViewById(R.id.currentValue);
        mResistanceRemark = (EditText) findViewById(R.id.resistanceRemark);
        mVoltageRemark = (EditText) findViewById(R.id.voltageRemark);
        mFlowmeterRemark = (EditText) findViewById(R.id.flowmeterRemark);
        mPipeRemark = (EditText) findViewById(R.id.pipeRemark);
        mTapRemark = (EditText) findViewById(R.id.tapRemark);
        mFlowmeterValue = (Spinner) findViewById(R.id.flowmeterValue);
        mPipeValue = (Spinner) findViewById(R.id.pipeValue);
        mTapValue = (Spinner) findViewById(R.id.tapValue);
        mItem65 = (Spinner) findViewById(R.id.item65Value);
        mItem66 = (Spinner) findViewById(R.id.item66Value);
        mItem67 = (Spinner) findViewById(R.id.item67Value);
        mItem68 = (Spinner) findViewById(R.id.item68Value);
        mItem69 = (Spinner) findViewById(R.id.item69Value);
        mItem70 = (Spinner) findViewById(R.id.item70Value);
        mItem71 = (Spinner) findViewById(R.id.item71Value);
        mUpdateButton = (Button) findViewById(R.id.updateButton);
        mCheckDB = (Button) findViewById(R.id.dbTestButton);
        mVoltageLayout = (TableRow) findViewById(R.id.voltageLayout);
        mFlowmeterLayout = (TableRow) findViewById(R.id.flowmeterLayout);
        mMultiSpinner = (MultiSpinner) findViewById(R.id.testValue);

        //getSupportActionBar().setDisplayShowHomeEnabled(true);
        getSupportActionBar().setIcon(R.drawable.ic_launcher_rlde);

        mScrollView = (ScrollView) findViewById(R.id.Inspection1Activity);
        mScrollView.setDescendantFocusability(ViewGroup.FOCUS_BEFORE_DESCENDANTS);
        mScrollView.setFocusable(true);
        mScrollView.setFocusableInTouchMode(true);
        mScrollView.setOnTouchListener(new View.OnTouchListener() {
            @Override
            public boolean onTouch(View v, MotionEvent event) {
                v.requestFocusFromTouch();
                return false;
            }
        });

        String[] flowmeterContent = new String[]{"Working", "Not Working"};
        ArrayAdapter<String> adapterFlowmeter = new ArrayAdapter<String>(this, android.R.layout.simple_spinner_dropdown_item, flowmeterContent);
        mFlowmeterValue.setAdapter(adapterFlowmeter);

        String[] pipeContent = new String[]{"Pipe Closed", "Pipe Open"};
        ArrayAdapter<String> adapterPipe = new ArrayAdapter<String>(this, android.R.layout.simple_spinner_dropdown_item, pipeContent);
        mPipeValue.setAdapter(adapterPipe);

        String[] tapContent = new String[]{"Tap Open", "Tap Closed"};
        ArrayAdapter<String> adapterTap = new ArrayAdapter<String>(this, android.R.layout.simple_spinner_dropdown_item, tapContent);
        mTapValue.setAdapter(adapterTap);
        mItem65.setAdapter(adapterTap);
        mItem66.setAdapter(adapterTap);
        mItem67.setAdapter(adapterTap);
        mItem68.setAdapter(adapterTap);
        mItem69.setAdapter(adapterTap);
        mItem70.setAdapter(adapterTap);
        mItem71.setAdapter(adapterTap);

        ArrayList<String> testContent = new ArrayList<>();

        testContent.add("Test1");
        testContent.add("test2");
        testContent.add("test3");
        testContent.add("test4");
        testContent.add("test5");

        mMultiSpinner.setItems(testContent, "Test item", new MultiSpinner.MultiSpinnerListener() {
            @Override
            public void onItemsSelected(boolean[] selected) {

            }
        });



        Intent intent = getIntent();
        Bundle extras = intent.getExtras();
        mListID = extras.getString("location");
        mInspectionID = extras.getString("inspection");

        setTitle(mListID);

        if(mInspectionID.equals("Inspection1")) {

            //final DatabaseReference checklistRef = FirebaseDatabase.getInstance().getReference().child("Checklist").child(mListID).child(mInspectionID);

            mUpdateButton.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {

                    //I'm getting all the data here from the EditText..Here I am getting it one by one.. what if I wanna just iterate through the editText
//                double capacitanceValue = Double.valueOf(mCapacitanceValue.getText().toString());
//                double currentValue = Double.valueOf(mCurrentValue.getText().toString());
//                double voltageValue = Double.valueOf(mVoltageValue.getText().toString());
//                double resistanceValue = Double.valueOf(mResistanceValue.getText().toString());
//                String resistanceRemark = mResistanceRemark.getText().toString();
//                String voltageRemark = mVoltageRemark.getText().toString();
//                String flowmeterValue = mFlowmeterValue.getSelectedItem().toString();
//                String flowmeterRemark = mFlowmeterRemark.getText().toString();
//                String tapValue = mTapValue.getSelectedItem().toString();
//                String tapRemark = mTapRemark.getText().toString();
//                String pipeValue = mPipeValue.getSelectedItem().toString();
//                String pipeRemark = mPipeRemark.getText().toString();

                    String selectedItem = mMultiSpinner.getSelectedItem().toString();
                    Log.i("Selected Items: ", selectedItem);

                    //here I am trying to iterate through EditText.. My idea is to get the ID and assign it to the SQL Table.. easy bro..
                    TableLayout layout = (TableLayout) findViewById(R.id.Inspection1ActivityTable);
                    EditText et, etValue;
                    View view;
                    Spinner spinner;
                    DatabaseHelper db = new DatabaseHelper(getApplicationContext());
                    db.clearTable();

                    //to iterate with tag
                    for (int i = 0; i < layout.getChildCount(); i++) {
                        view = layout.getChildAt(i);
                        if (view instanceof TableRow) {
                            tag = view.getTag().toString();
                            Log.i("Tag ID: ", tag);
                            for (int j = 0; j < ((TableRow) view).getChildCount(); j++) {
                                if (((TableRow) view).getChildAt(j) instanceof EditText) {
                                    //Just added this part.. the problem is I am getting the whole resource ID.. I just want the ID.. for that you must Tag your layout with android:tag..god damn it!!
                                    String hint = ((EditText) ((TableRow) view).getChildAt(j)).getHint().toString();
                                    //tag = ((TableRow) view).getChildAt(j).getResources().getResourceName(((TableRow) view).getChildAt(j).getId());
                                    //String tag = String.valueOf(((TableRow) view).getChildAt(j).getId());
                                    Log.i("From Hint: ", hint);
                                    if (hint.contains("Observation")) {
                                        et = (EditText) ((TableRow) view).getChildAt(j);
                                        text = et.getText().toString();
                                        Log.i("From Remark: ", text);
                                    } else {
                                        etValue = (EditText) ((TableRow) view).getChildAt(j);
                                        textValue = etValue.getText().toString();
                                        Log.i("From Value: ", textValue);
                                    }
                                } else if (((TableRow) view).getChildAt(j) instanceof Spinner) {
                                    //tag = ((TableRow) view).getChildAt(j).getResources().getResourceName(((TableRow) view).getChildAt(j).getId());
                                    spinner = (Spinner) ((TableRow) view).getChildAt(j);
                                    textValue = spinner.getSelectedItem().toString();
                                    Log.i("From Spinner: ", textValue);
                                }
                            }
                            try {
                                db.addInspection(tag, textValue, text);
                            } catch (SQLiteException e) {
                                Log.e("Write error: ", e.getMessage());
                            }
                        }
                    }


                    //if you wanna explicitly set the childAt attribute to get the Value, only if you know the flow will not change for sure!
//                for(int i=0; i<layout.getChildCount(); i++) {
//                    if (layout.getChildAt(i) instanceof TableRow) {
//                        TableRow row = (TableRow) layout.getChildAt(i);
//                        if(row.getChildAt(1) instanceof EditText) {
//                            et = (EditText) row.getChildAt(1);
//                            String text = et.getText().toString();
//                            Log.i("From Iteration: ", text);
//                        }
//                    }
//                }


//                try {
//                    DatabaseHelper databaseHelper = new DatabaseHelper(getApplicationContext());
//
//                    //delete data in table
//                    databaseHelper.clearTable();
//
//                    databaseHelper.addInspection("Capacitance", String.valueOf(capacitanceValue), null);
//                    databaseHelper.addInspection("Current", String.valueOf(currentValue), null);
//                    databaseHelper.addInspection("Voltage", String.valueOf(voltageValue),voltageRemark);
//                    databaseHelper.addInspection("Resistance", String.valueOf(resistanceValue),resistanceRemark);
//                    databaseHelper.addInspection("Flowmeter", flowmeterValue,flowmeterRemark);
//                    databaseHelper.addInspection("Tap", tapValue,tapRemark);
//                    databaseHelper.addInspection("Pipe", pipeValue, pipeRemark);
//
//                    //must first delete the data in the table, the iterate through the layout, and get each ID and populate in table
//
//                    InspectionTable inspectionTable = new InspectionTable();
//
//                    databaseHelper.close();
//                } catch (Exception e) {
//                    e.printStackTrace();
//                    Log.d("Writing error", e.getMessage());
//                }


//                Intent intent = new Intent(Inspection1Activity.this, FileUploadActivity.class);
//                Bundle extras = new Bundle();
//                extras.putString("InspectionID", mInspectionID);
//                extras.putString("Location",mListID);
//                intent.putExtras(extras);
//                startActivity(intent);
                }
            });
        }

        else if(mInspectionID.equals("Inspection2")){
            mVoltageLayout.setVisibility(View.GONE);
            mFlowmeterLayout.setVisibility((View.GONE));

            mUpdateButton.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {

                    //I'm getting all the data here from the EditText..Here I am getting it one by one.. what if I wanna just iterate through the editText

                    //here I am trying to iterate through EditText.. My idea is to get the ID and assign it to the SQL Table.. easy bro..
                    TableLayout layout = (TableLayout) findViewById(R.id.Inspection1ActivityTable);
                    EditText et, etValue;
                    View view;
                    Spinner spinner;
                    DatabaseHelper db = new DatabaseHelper(getApplicationContext());
                    db.clearTable();

                    //either create new tags, read only from the tags, or read from everything, create separate delete methods in DB Helper,
                    //and delete those from these two


                    //to iterate with tag
                    for (int i = 0; i < layout.getChildCount(); i++) {
                        view = layout.getChildAt(i);
                        if (view instanceof TableRow) {
                            tag = view.getTag().toString();
                            if (tag.contains("Test")) {
                                Log.i("Tag ID: ", tag);
                                for (int j = 0; j < ((TableRow) view).getChildCount(); j++) {
                                    if (((TableRow) view).getChildAt(j) instanceof EditText) {
                                        //Just added this part.. the problem is I am getting the whole resource ID.. I just want the ID.. for that you must Tag your layout with android:tag..god damn it!!
                                        String hint = ((EditText) ((TableRow) view).getChildAt(j)).getHint().toString();
                                        String textID = ((TableRow) view).getChildAt(j).getResources().getResourceName(((TableRow) view).getChildAt(j).getId());
                                        //String tag = String.valueOf(((TableRow) view).getChildAt(j).getId());
                                        Log.i("From Hint: ", hint);
                                        Log.i("from textID: ",textID);

                                        if (hint.contains("Observation")) {
                                            et = (EditText) ((TableRow) view).getChildAt(j);
                                            text = et.getText().toString();
                                            Log.i("From Remark: ", text);
                                        } else {
                                            etValue = (EditText) ((TableRow) view).getChildAt(j);
                                            textValue = etValue.getText().toString();
                                            Log.i("From Value: ", textValue);
                                        }
                                    } else if (((TableRow) view).getChildAt(j) instanceof Spinner) {
                                        //tag = ((TableRow) view).getChildAt(j).getResources().getResourceName(((TableRow) view).getChildAt(j).getId());
                                        spinner = (Spinner) ((TableRow) view).getChildAt(j);
                                        textValue = spinner.getSelectedItem().toString();
                                        Log.i("From Spinner: ", textValue);
                                    }
                                }
                                try {
                                    db.addInspection(tag, textValue, text);
                                } catch (SQLiteException e) {
                                    Log.e("Write error: ", e.getMessage());
                                }
                            }
                        }
                    }
                }
            });
        }

        //this is just to check if the DB is written or not
        mCheckDB.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                DatabaseHelper db = new DatabaseHelper(getApplicationContext());
                db.getInspections();

                //Intent intent = new Intent(Inspection1Activity.this, ImageCheckerActivity.class);
                Intent intent = new Intent(Inspection1Activity.this, FileUploadActivity.class);
                Bundle extras = new Bundle();
                extras.putString("InspectionID", mInspectionID);
                extras.putString("Location",mListID);
                intent.putExtras(extras);
                startActivity(intent);
            }
        });



    }

    @Override
    protected void onSaveInstanceState(Bundle outState) {
        super.onSaveInstanceState(outState);

        outState.putString("Resistance Remark", mResistanceRemark.getText().toString());
        outState.putDouble("Resistance Value", Double.valueOf(mResistanceValue.getText().toString()));
        outState.putDouble("Capacitance Value", Double.valueOf(mCapacitanceValue.getText().toString()));
        outState.putString("Flowmeter Value", mFlowmeterValue.getSelectedItem().toString());

    }

    @Override
    protected void onRestoreInstanceState(Bundle savedInstanceState) {
        super.onRestoreInstanceState(savedInstanceState);

        savedInstanceState.getString("Resistance Remark");
        savedInstanceState.getString("Flowmeter Value");
        savedInstanceState.getDouble("Capacitance Value");
        savedInstanceState.getDouble("Resistance Value");
    }
}
