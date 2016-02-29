package com.debashis.mywallet.presenter;

import android.database.sqlite.SQLiteDatabase;
import android.util.Log;

import com.debashis.mywallet.storage.keychain.MyWalletKeyChain;
import com.debashis.mywallet.storage.sqlite.DatabaseManager;

import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.Locale;

/**
 * Created by Debashis on 29/2/16.
 */
public class ExpenditureEntryPresenter {

    private ExpenditureEntryView  mExpenditureEntryView;
    private static final String LOG_TAG = ExpenditureEntryPresenter.class.getSimpleName();

    public ExpenditureEntryPresenter(ExpenditureEntryView entryView){
        this.mExpenditureEntryView = entryView;
    }

    public void createExpenditure(String name, String amount, int initialAmount, int type){
        if(name == null || name.equals("")) {
            mExpenditureEntryView.showExpenditureNameError(true);
            return;
        }
        if(amount == null || amount.equals("")) {
            mExpenditureEntryView.showExpenditureAmountError(true);
            return;
        }

        SimpleDateFormat dateFormat = new SimpleDateFormat("dd-MM-yyyy HH:mm:ss", Locale.getDefault());
        Date date = new Date();
        String dateStr = dateFormat.format(date);

        String[] params = new String[]{name, amount, dateStr, String.valueOf(type)};

        SQLiteDatabase db = DatabaseManager.getInstance().openDatabase();
        int sumAmount = DatabaseManager.getDatabaseHelper().getExpenditureSumAmountByType(db, new String[]{String.valueOf(type)});

        if(sumAmount + Integer.parseInt(amount) > initialAmount) {
            mExpenditureEntryView.showEnteredAmountError(true);
            return;
        }
        else
            mExpenditureEntryView.showEnteredAmountError(false);

        long rowId = DatabaseManager.getDatabaseHelper().insertExpenditureData(db, params);
        DatabaseManager.getInstance().closeDatabase();

        if(rowId > 0) {
            Log.d(LOG_TAG, "New expenditure added successfully");
            mExpenditureEntryView.isExpenditureCreated(true);
        }
        else {
            Log.d(LOG_TAG, "Adding expenditure failed");
            mExpenditureEntryView.isExpenditureCreated(false);
        }
    }

    public void onDestroy(){
        mExpenditureEntryView = null;
    }
}
