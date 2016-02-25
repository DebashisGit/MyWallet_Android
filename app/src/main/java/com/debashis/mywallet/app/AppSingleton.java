package com.debashis.mywallet.app;

import android.content.Context;
import android.content.SharedPreferences;

import com.debashis.mywallet.storage.sqlite.DatabaseHelper;
import com.debashis.mywallet.storage.sqlite.DatabaseManager;

/**
 * Created by sushil on 23/2/16.
 */
public class AppSingleton {

    private static AppSingleton appSingleton;
    public SharedPreferences sharedPreference;

    private AppSingleton(){};

    private AppSingleton(Context context){
        DatabaseManager.initializeManager(new DatabaseHelper(context));//Initialize Database manager
    };

    public synchronized static AppSingleton getInstance(Context context){
        if(appSingleton == null){
            appSingleton = new AppSingleton(context);
        }

        return appSingleton;
    }
}
