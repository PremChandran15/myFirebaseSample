package com.schoolofskills.myfirebasesample;

import android.content.Context;
import android.content.Intent;
import android.database.sqlite.SQLiteException;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.graphics.Color;
import android.graphics.drawable.Drawable;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Base64;
import android.util.Log;
import android.view.Gravity;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.Spinner;
import android.widget.TableLayout;
import android.widget.TableRow;

import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ServerValue;

import java.util.HashMap;

import data.DatabaseHelper;

//import com.firebase.client.DataSnapshot;
//import com.firebase.client.Firebase;
//import com.firebase.client.FirebaseError;
//import com.firebase.client.ValueEventListener;
//import com.firebase.ui.FirebaseListAdapter;

public class ImageCheckerActivity extends AppCompatActivity {

    private Button mCheckId, mAddEdittextButton, mUpdateDatabase, mDeleteButton, mDeleteButton2;
    private ImageView mImageCheck;
    private String mListID, mInspectionID, textValue1, textValue2, textValue3, tag;
    private TableLayout mTableLayout;
    private LayoutInflater inflater;
    private View firstView, secondView, thirdView;
    private int rowNumber = 1;

    private HashMap<String, Object> defectsActivity = new HashMap<String, Object>();
    private HashMap<String, Object> defectsRow = new HashMap<String, Object>();

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_image_checker);

        //inflater = (LayoutInflater) getSystemService(Context.LAYOUT_INFLATER_SERVICE);
        //firstView = inflater.inflate(R.layout.attrib_row, null);

        //mImageCheck = (ImageView) findViewById(R.id.checkImageView);
        mCheckId = (Button) findViewById(R.id.checkIdButton);
        mAddEdittextButton = (Button) findViewById(R.id.addButtonDefects);
        mTableLayout = (TableLayout) ImageCheckerActivity.this.findViewById(R.id.defectsTable);
        mUpdateDatabase = (Button) findViewById(R.id.UpdateDatabaseButton);
        mDeleteButton = (Button) findViewById(R.id.deleteRowButton);

        inflater = (LayoutInflater) getSystemService(Context.LAYOUT_INFLATER_SERVICE);
        firstView = inflater.inflate(R.layout.activity_file_upload, null); // inflate firstview.xml layout file
        secondView = inflater.inflate(R.layout.activity_inspection1, null);
        thirdView = inflater.inflate(R.layout.attrib_row,null);

        Intent intent = getIntent();
        Bundle bundle = intent.getExtras();
        mListID = bundle.getString("Location");
        mInspectionID = bundle.getString("InspectionID");

        final DatabaseReference checklistRef = FirebaseDatabase.getInstance().getReference().child("Checklist").child(mListID).child(mInspectionID);

        final DatabaseReference pushChecklist = checklistRef.push();


        mAddEdittextButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                rowNumber++;

//                while (rowNumber < 10) {

                final TableRow tableRow = (TableRow) LayoutInflater.from(ImageCheckerActivity.this).inflate(R.layout.attrib_row, null);
                tableRow.setTag("Defect " + rowNumber);
                //tableRow.setTag(R.string.tap);
                for (int i = 0; i < tableRow.getChildCount(); i++) {
                    View view = tableRow.getChildAt(i);
                    if (view instanceof EditText) {
                        String hint = ((EditText) view).getHint().toString();
                        if (hint.contains("Defects")) {
                            view.setId(R.id.defects_value_1);

                        } else if (hint.contains("Severity")) {
                            view.setId(R.id.severity_value_1);
                        } else if (hint.contains("action")) {
                            view.setId(R.id.action_value_1);
                        }
                    }
                }
                mTableLayout.addView(tableRow);

            }
            //}
        });

//        mDeleteButton.setOnClickListener(new View.OnClickListener() {
//            @Override
//            public void onClick(View v) {
//                TableLayout tableLayout = (TableLayout) v.getParent().getParent();
//                TableRow tableRow = (TableRow) v.getParent();
//                tableLayout.removeView(tableRow);
//            }
//        });
//
//        mDeleteButton2 = (Button) thirdView.findViewById(R.id.deleteRowButton2);
//
//        mDeleteButton2.setOnClickListener(new View.OnClickListener() {
//            @Override
//            public void onClick(View v) {
//                mTableLayout.removeView((TableRow)v.getParent());
//            }
//        });


        mCheckId.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
//                TableLayout layout = (TableLayout) findViewById(R.id.defectsTable);
//                EditText etValue1, etValue2, etValue3;
//                View view;

//                for(int i=0; i<layout.getChildCount(); i++){
//                    view = layout.getChildAt(i);
//                    if(view instanceof TableRow){
//                        String tagTable = view.getTag().toString();
//                        Log.i("From Table Tag: ", tagTable);
//                        for(int j=0;j<((TableRow) view).getChildCount();j++){
//                            if(((TableRow) view).getChildAt(j) instanceof EditText){
//                                String editTextID = ((TableRow) view).getChildAt(j).getResources().getResourceName(((TableRow) view).getChildAt(j).getId());
//                                Log.i("Edit Text ID: ", editTextID);
//                            }
//                        }
//                    }
//                }
                HashMap<String, Object> defectsMap = new HashMap<String, Object>();
                //HashMap<String, Object> defectsRow = new HashMap<String, Object>();
                //HashMap<String, Object> defectsActivity = new HashMap<String, Object>();

                for (int i = 0; i < mTableLayout.getChildCount(); i++) {
                    View defectsView = mTableLayout.getChildAt(i);
                    if (defectsView instanceof TableRow) {
                        String tagRow = defectsView.getTag().toString();
                        if(!tagRow.contains("Row Text")&& !tagRow.contains("Row Add Button")) {

                            for (int j = 0; j < ((TableRow) defectsView).getChildCount(); j++) {
                                if (((TableRow) defectsView).getChildAt(j) instanceof EditText) {
                                    String hint = ((EditText) ((TableRow) defectsView).getChildAt(j)).getHint().toString();
                                    if (hint.contains("Defects")) {
                                        //make a pojo and write...
                                        textValue1 = ((EditText) ((TableRow) defectsView).getChildAt(j)).getText().toString();
                                    } else if (hint.contains("Severity")) {
                                        textValue2 = ((EditText) ((TableRow) defectsView).getChildAt(j)).getText().toString();
                                    } else if (hint.contains("action")) {
                                        textValue3 = ((EditText) ((TableRow) defectsView).getChildAt(j)).getText().toString();
                                    }
                                }
                            }
//                            defectsMap.put(tagRow +" Defects", textValue1);
//                            defectsMap.put(tagRow +" Severity", textValue2);
//                            defectsMap.put(tagRow +" Action", textValue3);
                            DefectsDetected defectsDetected = new DefectsDetected(textValue1,textValue2,textValue3);
                            defectsRow.put(tagRow, defectsDetected);
                        }

                    }
//                    defectsRow.put(tagRow, defectsMap);
                }

                defectsActivity.put("Defects Detected", defectsRow);
            }
        });

        if (mInspectionID.equals("Inspection1")) {
            mUpdateDatabase.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    TableLayout layout = (TableLayout) secondView.findViewById(R.id.Inspection1ActivityTable);
                    View view;
                    DatabaseHelper db = new DatabaseHelper(getApplicationContext());
                    HashMap<String, String> map = new HashMap<>();


                    for (int i = 0; i < layout.getChildCount(); i++) {
                        view = layout.getChildAt(i);
                        if (view instanceof TableRow) {
                            tag = view.getTag().toString();
                            Log.i("Tag ID: ", tag);
                            try {
                                map.put(tag + "Value", db.getValue(tag));
                                map.put(tag + "Remark", db.getRemark(tag));
                            } catch (SQLiteException e) {
                                Log.e("Write error: ", e.getMessage());
                            }
                        }
                    }

                    ValueAndRemark resistanceMap = new ValueAndRemark(Double.valueOf(map.get("resistanceTestValue")), map.get("resistanceTestRemark"));
                    ValueAndRemark voltageMap = new ValueAndRemark(Double.valueOf(map.get("voltageValue")), map.get("voltageRemark"));
                    Flowmeter flowmeterMap = new Flowmeter(map.get("flowmeterValue"), map.get("flowmeterRemark"));
                    Tap tapMap = new Tap(map.get("tapTestValue"), map.get("tapTestRemark"));
                    Pipe pipeMap = new Pipe(map.get("pipeTestValue"), map.get("pipeTestRemark"));
                    double capacitanceValue = Double.valueOf(map.get("capacitanceTestValue"));
                    double currentValue = Double.valueOf(map.get("currentTestValue"));

                    HashMap<String, Object> createdAt = new HashMap<String, Object>();
                    createdAt.put("Created At", ServerValue.TIMESTAMP);

                    InspectionOne newInspectionOne = new InspectionOne(capacitanceValue, currentValue, createdAt, flowmeterMap, tapMap, pipeMap, resistanceMap, voltageMap, defectsRow);

                    //checklistRef.setValue(newInspectionOne);
                    pushChecklist.setValue(newInspectionOne);
                }
            });

        }
        else if (mInspectionID.equals("Inspection2")){
            mUpdateDatabase.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    TableLayout layout = (TableLayout) secondView.findViewById(R.id.Inspection1ActivityTable);
                    View view;
                    DatabaseHelper db = new DatabaseHelper(getApplicationContext());
                    HashMap<String, String> map = new HashMap<>();


                    for (int i = 0; i < layout.getChildCount(); i++) {
                        view = layout.getChildAt(i);
                        if (view instanceof TableRow) {
                            tag = view.getTag().toString();
                            Log.i("Tag ID: ", tag);
                            if (tag.contains("Test")) {
                                try {
                                    map.put(tag + "Value", db.getValue(tag));
                                    map.put(tag + "Remark", db.getRemark(tag));
                                } catch (SQLiteException e) {
                                    Log.e("Write error: ", e.getMessage());
                                }
                            }
                        }
                    }

                    ValueAndRemark resistanceMap = new ValueAndRemark(Double.valueOf(map.get("resistanceTestValue")), map.get("resistanceTestRemark"));
                    //ValueAndRemark voltageMap = new ValueAndRemark(Double.valueOf(map.get("voltageValue")), map.get("voltageRemark"));
                    //Flowmeter flowmeterMap = new Flowmeter(map.get("flowmeterValue"), map.get("flowmeterRemark"));
                    Tap tapMap = new Tap(map.get("tapTestValue"), map.get("tapTestRemark"));
                    Pipe pipeMap = new Pipe(map.get("pipeTestValue"), map.get("pipeTestRemark"));
                    double capacitanceValue = Double.valueOf(map.get("capacitanceTestValue"));
                    double currentValue = Double.valueOf(map.get("currentTestValue"));

                    HashMap<String, Object> createdAt = new HashMap<String, Object>();
                    createdAt.put("Created At", ServerValue.TIMESTAMP);

                    InspectionOne newInspectionTwo = new InspectionOne(capacitanceValue, currentValue, createdAt, tapMap, pipeMap, resistanceMap,defectsRow);

                    //checklistRef.setValue(newInspectionTwo);
                    pushChecklist.setValue(newInspectionTwo);


                }
            });
        }
    }
}
