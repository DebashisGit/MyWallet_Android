package com.debashis.mywallet.storage.keychain;

import android.content.Context;
import android.content.SharedPreferences;

/**
 * Created by Debashis on 23/2/16.
 */
public class MyWalletKeyChain {

    private static final String WalletKeyChainName = "WalletKeyChainName";
    private static final String key_user_logged_in = "key_user_logged_in";
    private static final String key_bank_amount = "key_bank_amount";
    private static final String key_credit_card_amount = "key_credit_card_amount";
    private static final String key_cash_amount = "key_cash_amount";

    private static SharedPreferences walletSharedPreferences(Context ctx){
        return ctx.getSharedPreferences(WalletKeyChainName, Context.MODE_PRIVATE);
    }

    private static void saveString(Context context, String key, String value){
        SharedPreferences.Editor editor = walletSharedPreferences(context).edit();
        editor.putString(key, value);
        editor.commit();
    }

    private static String getString(Context context, String key){
        return walletSharedPreferences(context).getString(key, null);
    }

    private static void saveInteger(Context context, String key, int value){
        SharedPreferences.Editor editor = walletSharedPreferences(context).edit();
        editor.putInt(key, value);
        editor.commit();
    }

    private static int getInteger(Context context, String key){
        return walletSharedPreferences(context).getInt(key, 0);
    }

    private static void saveBoolean(Context context, String key, boolean value){
        SharedPreferences.Editor editor = walletSharedPreferences(context).edit();
        editor.putBoolean(key, value);
        editor.commit();
    }

    private static boolean getBoolean(Context context, String key){
        return walletSharedPreferences(context).getBoolean(key, false);
    }

    public static void saveHasLoggedIn(Context context, boolean value){
        saveBoolean(context, key_user_logged_in, value);
    }

    public static boolean hasUserLoggedIn(Context context){
        return getBoolean(context, key_user_logged_in);
    }

    public static void saveBankAmount(Context context, int value){
        saveInteger(context, key_bank_amount, value);
    }

    public static int getBankAmount(Context context){
        return getInteger(context, key_bank_amount);
    }

    public static void saveCreditCardAmount(Context context, int value){
        saveInteger(context, key_credit_card_amount, value);
    }

    public static int getCreditCardAmount(Context context){
        return getInteger(context, key_credit_card_amount);
    }

    public static void saveCashAmount(Context context, int value){
        saveInteger(context, key_cash_amount, value);
    }

    public static int getCashAmount(Context context){
        return getInteger(context, key_cash_amount);
    }

    public static void saveExpenditureAmount(Context context, int bankAmount, int cardAmount, int cashAmount){
        saveBankAmount(context, bankAmount);
        saveCreditCardAmount(context, cardAmount);
        saveCashAmount(context, cashAmount);
    }
}
