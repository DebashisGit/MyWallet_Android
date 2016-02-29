package com.debashis.mywallet.presenter;

import android.database.sqlite.SQLiteDatabase;

import com.debashis.mywallet.storage.sqlite.DatabaseManager;

/**
 * Created by Debashis on 1/3/16.
 */
public class SettingViewPresenter {

    private SettingView mSettingView;

    public SettingViewPresenter(SettingView settingView){
        this.mSettingView = settingView;
    }

    public boolean validationCheck(int bankAmount, int cardAmount, int cashAmount){

        int bankExpenditureAmount = getSumExpenditureAmount(1);
        int cardExpenditureAmount = getSumExpenditureAmount(2);
        int cashExpenditureAmount = getSumExpenditureAmount(3);

        if(bankAmount < bankExpenditureAmount){
            mSettingView.showBankAmountError();
            return false;
        }
        if(cardAmount < cardExpenditureAmount){
            mSettingView.showCardAmountError();
            return false;
        }
        if(cashAmount < cashExpenditureAmount){
            mSettingView.showCashAmountError();
            return false;
        }

        return true;
    }

    public int getSumExpenditureAmount(int type){
        String[] params = new String[]{Integer.toString(type)};
        SQLiteDatabase db = DatabaseManager.getInstance().openDatabase();
        int sumAmount = DatabaseManager.getDatabaseHelper().getExpenditureSumAmountByType(db, params);

        return sumAmount;
    }

    public void onDestroy(){
        mSettingView = null;
    }
}
