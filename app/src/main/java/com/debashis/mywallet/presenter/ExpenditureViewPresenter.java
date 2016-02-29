package com.debashis.mywallet.presenter;

import android.database.sqlite.SQLiteDatabase;

import com.debashis.mywallet.model.Expenditure;
import com.debashis.mywallet.storage.sqlite.DatabaseManager;

import java.util.List;

/**
 * Created by Debashis on 25/2/16.
 */
public class ExpenditureViewPresenter {

    private ExpenditureView mExpenditureView;

    public ExpenditureViewPresenter(ExpenditureView expenditureView){
        this.mExpenditureView = expenditureView;
    }

    public void fetchDataFromDatabase(int type){
        List<Expenditure> expenditureList = null;
        String[] params = new String[]{Integer.toString(type)};
        SQLiteDatabase db = DatabaseManager.getInstance().openDatabase();

        try {
            expenditureList= DatabaseManager.getDatabaseHelper().getExpenditureList(db, params);
        }
        catch(Exception ex){
            ex.printStackTrace();
        }

        DatabaseManager.getInstance().closeDatabase();
        mExpenditureView.returnDataFromSQLite(expenditureList);
    }

    public int getSumExpenditureAmount(int type){
        String[] params = new String[]{Integer.toString(type)};
        SQLiteDatabase db = DatabaseManager.getInstance().openDatabase();
        int sumAmount = DatabaseManager.getDatabaseHelper().getExpenditureSumAmountByType(db, params);

        return sumAmount;
    }
}
