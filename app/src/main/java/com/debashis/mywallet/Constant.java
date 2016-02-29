package com.debashis.mywallet;

/**
 * Created by Debashis on 25/2/16.
 */
public class Constant {

    public static final String BANK_ACCOUNT_TAB = "Bank Account";
    public static final String CREDIT_CARD_TAB = "Credit card";
    public static final String CASH_TAB = "Cash";

    public static final int TAB_COUNT = 3;
    public static final int REQUEST_CODE = 1;
    public static final int ERROR_CODE_SUCCESS = 1;
    public static final int ERROR_CODE_FAIL = 0;

    public static class WalletKeys {
        public static final String Key_Expenditure_Type = "Key_Expenditure_Type";
        public static final String KEY_EXPENDITURE_CREATION = "Key_Expenditure_Creation";
        public static final String KEY_SETTING_UPDATION = "Key_Setting_Updation";
        public static final String KEY_INITIAL_EXPENDITURE_AMOUNT = "Key_Initial_Expenditure_Amount";
        public static final String KEY_SHOW_SETTING_ACTIVITY = "Key_Show_Setting_Activity";
    }

    public static class Message {
        public static final String MESSAGE_AMOUNT_SAVED_SUCCESSFULLY = "Amount saved successfully";
        public static final String MESSAGE_AMOUNT_SAVE_FAILED = "Save failed.";
    }
}