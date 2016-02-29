package com.debashis.mywallet.storage.sqlite;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;
import android.util.Log;

import com.debashis.mywallet.model.Expenditure;
import com.debashis.mywallet.model.User;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by Debashis on 23/2/16.
 */
public class DatabaseHelper extends SQLiteOpenHelper {

    private static final String LOG_TAG = DatabaseHelper.class.getSimpleName();

    public DatabaseHelper(Context context){
        super(context, DatabaseContract.DATABASE_NAME, null, DatabaseContract.DATABASE_VERSION);
    }

    @Override
    public void onCreate(SQLiteDatabase db) {
        db.execSQL(DatabaseContract.User.CREATE_USER_TABLE);
        db.execSQL(DatabaseContract.Expenditure.CREATE_EXPENDITURE_TABLE);
        Log.i(LOG_TAG, "onCreate(): Tables got created");
    }

    @Override
    public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {
        db.execSQL(DatabaseContract.User.DELETE_USER_TABLE);
        onCreate(db);
    }

    public long insertUserData(SQLiteDatabase db, User user){

        String firstName = user.getFirstName();
        String lastName = user.getLastName();
        String email = user.getEmail();
        String password = user.getPassword();
        ContentValues values = new ContentValues();
        values.put(DatabaseContract.User.COLUMN_NAME_USER_FIRST_NAME, firstName);
        values.put(DatabaseContract.User.COLUMN_NAME_USER_LAST_NAME, lastName);
        values.put(DatabaseContract.User.COLUMN_NAME_USER_EMAIL, email);
        values.put(DatabaseContract.User.COLUMN_NAME_USER_PASSWORD, password);

        long rowId = db.insert(DatabaseContract.User.TABLE_NAME, null, values);

        return rowId;
    }

    public List<User> getUserList(SQLiteDatabase db, String[] params){
        Cursor cursor = db.rawQuery(DatabaseContract.User.FIND_USER, params);
        List<User> userList = new ArrayList<>();
        if(cursor != null && cursor.moveToFirst()){

            do{
                String firstName = cursor.getString(cursor.getColumnIndex(DatabaseContract.User.COLUMN_NAME_USER_FIRST_NAME));
                String lastName = cursor.getString(cursor.getColumnIndex(DatabaseContract.User.COLUMN_NAME_USER_LAST_NAME));
                String email = cursor.getString(cursor.getColumnIndex(DatabaseContract.User.COLUMN_NAME_USER_EMAIL));
                String password = cursor.getString(cursor.getColumnIndex(DatabaseContract.User.COLUMN_NAME_USER_PASSWORD));

                User user = new User(firstName, lastName, email, password);
                userList.add(user);
            }while(cursor.moveToNext());
            cursor.close();
        }

        return userList;
    }

    public List<Expenditure> getExpenditureList(SQLiteDatabase db, String[] params){
        Cursor cursor = db.rawQuery(DatabaseContract.Expenditure.EXPENDITURE_LIST_QUERY, params);
        List<Expenditure> expenditureList = new ArrayList<>();
        if(cursor != null && cursor.moveToFirst()){

            do{
                String expenditureName = cursor.getString(cursor.getColumnIndex(DatabaseContract.Expenditure.COLUMN_NAME_EXPENDITURE_NAME));
                int expenditureAmount = cursor.getInt(cursor.getColumnIndex(DatabaseContract.Expenditure.COLUMN_NAME_EXPENDITURE_AMOUNT));
                String expenditureDate = cursor.getString(cursor.getColumnIndex(DatabaseContract.Expenditure.COLUMN_NAME_EXPENDITURE_DATE));
                int expenditureType = cursor.getInt(cursor.getColumnIndex(DatabaseContract.Expenditure.COLUMN_NAME_EXPENDITURE_TYPE));

                Expenditure expenditure = new Expenditure(expenditureName, expenditureAmount, expenditureDate, expenditureType);
                expenditureList.add(expenditure);
            }while(cursor.moveToNext());
            cursor.close();
        }

        return expenditureList;
    }

    public long insertExpenditureData(SQLiteDatabase db, String[] params){

        ContentValues values = new ContentValues();
        values.put(DatabaseContract.Expenditure.COLUMN_NAME_EXPENDITURE_NAME, params[0]);
        values.put(DatabaseContract.Expenditure.COLUMN_NAME_EXPENDITURE_AMOUNT, params[1]);
        values.put(DatabaseContract.Expenditure.COLUMN_NAME_EXPENDITURE_DATE, params[2]);
        values.put(DatabaseContract.Expenditure.COLUMN_NAME_EXPENDITURE_TYPE, params[3]);

        long rowId = db.insert(DatabaseContract.Expenditure.TABLE_NAME, null, values);
        if(rowId > 0)
            Log.d(LOG_TAG, "Expenditure got created successfully");

        return rowId;
    }

    public int getExpenditureSumAmountByType(SQLiteDatabase db, String[] type){
        Cursor cursor = db.rawQuery(DatabaseContract.Expenditure.EXPENDITURE_SUM_AMOUNT, type);
        int sum = 0;
        if(cursor != null && cursor.getCount() > 0 && cursor.moveToFirst()){
            do{
                sum = cursor.getInt(0);
            }while(cursor.moveToNext());
            cursor.close();
        }

        return sum;
    }
}
