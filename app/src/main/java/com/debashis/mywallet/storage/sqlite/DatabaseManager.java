package com.debashis.mywallet.storage.sqlite;

import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;

/**
 * Created by Debashis on 23/2/16.
 */
public class DatabaseManager {
    private static DatabaseManager mDatabaseManager;
    private static SQLiteOpenHelper mDbHelper;
    private SQLiteDatabase mDatabase;

    private int mOpenCounter;
    private static final String LOG_TAG = DatabaseManager.class.getSimpleName();

    private DatabaseManager(){};

    public static synchronized void initializeManager(SQLiteOpenHelper openHelper){
        if(mDatabaseManager == null){
            mDatabaseManager = new DatabaseManager();
            mDbHelper = openHelper;
        }
    }

    public static synchronized DatabaseManager getInstance(){
        if(mDatabaseManager == null){
            throw new IllegalStateException(LOG_TAG +
                    " is not initialized, call initializeInstance(..) method first.");
        }
        return mDatabaseManager;
    }

    public static synchronized DatabaseHelper getDatabaseHelper(){
        return (DatabaseHelper) mDbHelper;
    }

    public synchronized SQLiteDatabase openDatabase(){
        mOpenCounter++;
        if(mOpenCounter == 1){
            //Open database
            mDatabase = mDbHelper.getWritableDatabase();
        }

        return mDatabase;
    }

    public synchronized void closeDatabase(){
        mOpenCounter--;
        if(mOpenCounter == 0){
            //Close database
            mDatabase.close();
        }
    }
}
