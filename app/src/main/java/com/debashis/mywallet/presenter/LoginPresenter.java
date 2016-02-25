package com.debashis.mywallet.presenter;

import android.database.sqlite.SQLiteDatabase;
import android.text.TextUtils;
import android.util.Log;

import com.debashis.mywallet.model.User;
import com.debashis.mywallet.storage.sqlite.DatabaseManager;

import java.util.List;

/**
 * Created by Debashis on 23/2/16.
 */
public class LoginPresenter {

    private static final String LOG_TAG = LoginPresenter.class.getSimpleName();

    LoginView loginView;

    public LoginPresenter(LoginView loginView){
        this.loginView = loginView;
    }

    public void login(String email, String password){
        boolean cancel = false;
        if(TextUtils.isEmpty(email)){
            cancel = true;
            loginView.showEmailIdError(true);
        }

        if(!cancel){
            SQLiteDatabase db = DatabaseManager.getInstance().openDatabase();
            String[] userParams = new String[]{email, password};
            List<User> userList = null;
            try {
                userList= DatabaseManager.getDatabaseHelper().getUserList(db, userParams);
                if(userList.size() > 0){
                    Log.i(LOG_TAG, "User exists in the database");

                }
            }
            catch(Exception ex){
                ex.printStackTrace();
            }
            DatabaseManager.getInstance().closeDatabase();
            loginView.loginResponse(userList);
        }
    }

}
