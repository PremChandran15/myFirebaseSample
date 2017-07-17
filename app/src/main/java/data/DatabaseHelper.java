package data;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.DatabaseErrorHandler;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteException;
import android.database.sqlite.SQLiteOpenHelper;
import android.util.Log;

import com.schoolofskills.myfirebasesample.InspectionOne;
import com.schoolofskills.myfirebasesample.InspectionTable;

import java.util.ArrayList;

/**
 * Created by premkumar on 13/06/2016.
 */
public class DatabaseHelper extends SQLiteOpenHelper {

    private String retrievedValue ;
    private String retrievedRemark ;

    public DatabaseHelper(Context context) {
        super(context, Constants.DATABASE_NAME, null, Constants.DATABASE_VERSION);
    }

    @Override
    public void onCreate(SQLiteDatabase db) {

        //I can also use small letters here, but i'm just differentiating between SQL and normal commands here

        String CREATE_INSPECTION_TABLE = "CREATE TABLE " + Constants.TABLE_NAME  + "(" +
                Constants.KEY_ID + " INTEGER PRIMARY KEY AUTOINCREMENT, " + Constants.CONTENT_NAME + " TEXT , " + Constants.VALUE_NAME
                + " TEXT , " + Constants.REMARK_NAME + " TEXT);";

        db.execSQL(CREATE_INSPECTION_TABLE);

    }

    @Override
    public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {

        db.execSQL("DROP TABLE IF EXISTS " + Constants.TABLE_NAME);

        Log.v("ON_UPGRADE", "DROPPING THE TABLE AND CREATING A NEW ONE!");

        //create a new one
        onCreate(db);

    }

    public void clearTable(){
        SQLiteDatabase db = this.getWritableDatabase();

        db.execSQL("DELETE FROM " + Constants.TABLE_NAME);
        db.execSQL("VACUUM");
    }

    public void addInspection(String name, String value, String remark) {

        SQLiteDatabase db = this.getWritableDatabase();

        db.execSQL("INSERT INTO " + Constants.TABLE_NAME + " (" + Constants.CONTENT_NAME + ", " + Constants.VALUE_NAME + ", " + Constants.REMARK_NAME + ") VALUES ('" + name + "', '" + value + "', '" + remark + "')");
//        ContentValues values = new ContentValues();
//        values.put(Constants.CONTENT_NAME, name);
//        values.put(Constants.VALUE_NAME, value);
//        values.put(Constants.REMARK_NAME, remark);
//
//
//        db.insert(Constants.TABLE_NAME, null, values);
//
//        Log.i("Status table: ", "Awesome man");
//
        db.close();


    }

//    public void addInspection (InspectionTable inspectionTable){
//        SQLiteDatabase db = this.getWritableDatabase();
//
//        ContentValues values = new ContentValues();
//        values.put(Constants.CONTENT_NAME, inspectionTable.getContentName());
//        values.put(Constants.VALUE_NAME, inspectionTable.getValueName());
//        values.put(Constants.REMARK_NAME, inspectionTable.getRemarkName());
//
//        db.insert(Constants.TABLE_NAME,null, values);
//
//        db.close();
//    }

    public String getValue(String value){

        String selectQuery = "SELECT * FROM " + Constants.TABLE_NAME + " WHERE " + Constants.CONTENT_NAME + " =?";

        SQLiteDatabase db = this.getReadableDatabase();
        Cursor cursor = db.rawQuery(selectQuery, new String[]{value});

        //if still doesnt work, then something to do with this, change the value to the value passed

        int valueIndex = cursor.getColumnIndex(Constants.VALUE_NAME);

//        //loop through cursor
        if (cursor.moveToFirst()){

            do {
                retrievedValue = cursor.getString(valueIndex);
                Log.i("Value: ", retrievedValue);

            } while (cursor.moveToNext());
        }


        cursor.close();
//        db.close();

        return retrievedValue;
    }

    public String getRemark(String remark){
        String selectQuery = "SELECT * FROM " + Constants.TABLE_NAME + " WHERE " + Constants.CONTENT_NAME + " =?";

        SQLiteDatabase db = this.getReadableDatabase();
        Cursor cursor = db.rawQuery(selectQuery, new String[]{remark});

        int remarkIndex = cursor.getColumnIndex(Constants.REMARK_NAME);

        if (cursor.moveToFirst()){


            do {
                retrievedValue = cursor.getString(remarkIndex);
                Log.i("Remark: ", retrievedValue);


            } while (cursor.moveToNext());
        }

        cursor.close();

        return retrievedValue;

    }

    //public ArrayList<InspectionTable> getInspections() throws SQLiteException{
    public void getInspections() throws SQLiteException{

        String selectQuery = "SELECT * FROM " + Constants.TABLE_NAME;

        try {
            SQLiteDatabase db = this.getReadableDatabase();
            Cursor cursor = db.rawQuery(selectQuery, null);

            //Cursor cursor = db.query(Constants.TABLE_NAME, new String[]{Constants.KEY_ID, Constants.CONTENT_NAME, Constants.VALUE_NAME, Constants.REMARK_NAME}, null, null, null, null, Constants.KEY_ID + " DESC");

            //loop through cursor
            if (cursor.moveToFirst()) {

                do {

//                    InspectionTable inspectionTable = new InspectionTable();
//                    inspectionTable.setContentName(cursor.getString(cursor.getColumnIndex(Constants.CONTENT_NAME)));
//                    inspectionTable.setValueName(cursor.getString(cursor.getColumnIndex(Constants.VALUE_NAME)));
//                    inspectionTable.setRemarkName(cursor.getString(cursor.getColumnIndex(Constants.REMARK_NAME)));
//                    inspectionTable.setItemID(cursor.getInt(cursor.getColumnIndex(Constants.KEY_ID)));

                    Log.i("Value Name: ", cursor.getString(cursor.getColumnIndex(Constants.CONTENT_NAME)));
                    Log.i("Value1: ", cursor.getString(cursor.getColumnIndex(Constants.VALUE_NAME)));
                    //Log.i("Value2: ", cursor.getString(cursor.getColumnIndex(Constants.VALUE_NAME2)));
                    Log.i("Remarks: ", cursor.getString(cursor.getColumnIndex(Constants.REMARK_NAME)));

    //                java.text.DateFormat dateFormat = java.text.DateFormat.getDateInstance();
    //                String dataData = dateFormat.format(new Date(cursor.getLong(cursor.getColumnIndex(Constants.DATE_NAME))).getTime());

                    //wish.setRecordDate(dataData);

                    //inspectionStorage.add(inspectionTable);

                } while (cursor.moveToNext());
            }

            cursor.close();
            db.close();
        } catch (SQLiteException e) {
            e.printStackTrace();
        }

    }
}
