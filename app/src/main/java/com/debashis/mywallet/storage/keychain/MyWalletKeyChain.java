package com.debashis.mywallet.storage.keychain;

import android.content.Context;
import android.content.SharedPreferences;

/**
 * Created by Debashis on 23/2/16.
 */
public class MyWalletKeyChain {

    private static final String WalletKeyChainName = "WalletKeyChainName";
    private static final String key_user_logged_in = "key_user_logged_in";

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
}
