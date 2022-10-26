package com.example.comp1786_project;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;
import android.widget.Toast;

import androidx.annotation.Nullable;

public class DatabaseHelper extends SQLiteOpenHelper {
    private final Context context;
    public static final String DATABASE_NAME = "TripsExpense.db";
    public static final int DATABASE_VERSION = 1;

    private static final String TRIP_TABLE = "Trips";
    private static final String ID_COLUMN = "Id";
    private static final String NAME_COLUMN = "Name";
    private static final String DESTINATION_COLUMN = "Trip_Destination";
    private static final String DATE_OF_THE_TRIP_COLUMN = "Trip_Date";
    private static final String REQUIRE_ASSESSMENT_COLUMN = "Require_Assessment";
    private static final String DESCRIPTION_COLUMN = "Description";

    private static final String EXPENSE_TABLE = "Expenses";
    private static final String TRIP_ID_COLUMN = "TripId";
    private static final String TYPE_COLUMN = "Type";
    private static final String AMOUNT_COLUMN = "Amount";
    private static final String TIME_OF_EXPENSE_COLUMN = "Time_of_expense";


    public DatabaseHelper(@Nullable Context context) {
        super(context, DATABASE_NAME, null, DATABASE_VERSION);
        this.context = context;
    }

    @Override
    public void onCreate(SQLiteDatabase db) {
        String queryCreateTripTable = "CREATE TABLE " + TRIP_TABLE +
                        " (" + ID_COLUMN + " INTEGER PRIMARY KEY AUTOINCREMENT," +
                        NAME_COLUMN + " TEXT, " +
                        DESTINATION_COLUMN + " TEXT," +
                        DATE_OF_THE_TRIP_COLUMN + " TEXT," +
                        REQUIRE_ASSESSMENT_COLUMN + " TEXT," +
                        DESCRIPTION_COLUMN + " TEXT);";
        String queryCreateExpenseTable = "CREATE TABLE " + EXPENSE_TABLE +
                        " (" + ID_COLUMN + " INTEGER PRIMARY KEY AUTOINCREMENT," +
                        TYPE_COLUMN + " TEXT," +
                        AMOUNT_COLUMN + " TEXT, " +
                        TIME_OF_EXPENSE_COLUMN + " TEXT,"+
                        TRIP_ID_COLUMN + " INTEGER, FOREIGN KEY " + "(" + TRIP_ID_COLUMN + ") REFERENCES "+ TRIP_TABLE +  "(" +
                        ID_COLUMN + ") ON UPDATE CASCADE ON DELETE CASCADE);";
        db.execSQL(queryCreateTripTable);
        db.execSQL(queryCreateExpenseTable);
    }

    @Override
    public void onUpgrade(SQLiteDatabase db, int i, int i1) {
        db.execSQL("DROP TABLE IF EXISTS " + TRIP_TABLE);
        db.execSQL("DROP TABLE IF EXISTS " + EXPENSE_TABLE);
        onCreate(db);
    }

    void addTrip(String name, String tripDestination, String tripDate, String requireAssessment, String description){
        SQLiteDatabase db = this.getWritableDatabase();
        ContentValues cv = new ContentValues();
        cv.put(NAME_COLUMN, name);
        cv.put(DESTINATION_COLUMN, tripDestination);
        cv.put(DATE_OF_THE_TRIP_COLUMN, tripDate);
        cv.put(REQUIRE_ASSESSMENT_COLUMN, requireAssessment);
        cv.put(DESCRIPTION_COLUMN, description);

        long result = db.insert(TRIP_TABLE, null, cv);
        if(result == -1){
            Toast.makeText(context, "Fail to Add", Toast.LENGTH_SHORT).show();
        }else{
            Toast.makeText(context, "Add Successfully!", Toast.LENGTH_SHORT).show();
        }
    }

    void addExpenses(int trip_id, String type, String amount, String timeExpense){
        SQLiteDatabase db = this.getWritableDatabase();
        ContentValues cv = new ContentValues();
        cv.put(TRIP_ID_COLUMN, trip_id);
        cv.put(TYPE_COLUMN, type);
        cv.put(AMOUNT_COLUMN, amount);
        cv.put(TIME_OF_EXPENSE_COLUMN, timeExpense);

        long result = db.insert(EXPENSE_TABLE, null, cv);
        if(result == -1){
            Toast.makeText(context, "Fail to Add", Toast.LENGTH_SHORT).show();
        }else{
            Toast.makeText(context, "Add Successfully!", Toast.LENGTH_SHORT).show();
        }
    }

    Cursor readAllDataTrip(){
        String query = "SELECT * FROM " + TRIP_TABLE;
        SQLiteDatabase db = this.getReadableDatabase();

        Cursor cursor = null;
        if(db != null){
            cursor = db.rawQuery(query, null);
        }
        return cursor;
    }

    Cursor readDataExpensesById(int id){
        String query = "SELECT * FROM " + EXPENSE_TABLE +" WHERE TripId=" +id;
        SQLiteDatabase db = this.getReadableDatabase();

        Cursor cursor = null;
        if(db != null){
            cursor = db.rawQuery(query, null);
        }
        return cursor;
    }

    void updateTripData(String row_id, String name, String tripDestination, String tripDate, String requireAssessment, String description){
        SQLiteDatabase db = this.getWritableDatabase();
        ContentValues cv = new ContentValues();
        cv.put(NAME_COLUMN, name);
        cv.put(DESTINATION_COLUMN, tripDestination);
        cv.put(DATE_OF_THE_TRIP_COLUMN, tripDate);
        cv.put(REQUIRE_ASSESSMENT_COLUMN, requireAssessment);
        cv.put(DESCRIPTION_COLUMN, description);

        long result = db.update(TRIP_TABLE, cv, "Id=?", new String[]{row_id});
        if(result == -1){
            Toast.makeText(context, "Fail to update", Toast.LENGTH_SHORT).show();
        }else{
            Toast.makeText(context, "Update Successfully!", Toast.LENGTH_SHORT).show();
        }
    }

    void deleteOneRowTrip(String row_id){
        SQLiteDatabase db = this.getWritableDatabase();
        long result = db.delete(TRIP_TABLE, "id=?", new String[]{row_id});
        if(result == -1){
            Toast.makeText(context, "Fail to delete", Toast.LENGTH_SHORT).show();
        }else{
            Toast.makeText(context, "Delete Successfully!", Toast.LENGTH_SHORT).show();
        }
    }

    void deleteAllDataTrip(){
        SQLiteDatabase db = this.getWritableDatabase();
        db.execSQL("DELETE FROM " + TRIP_TABLE);
    }
}
