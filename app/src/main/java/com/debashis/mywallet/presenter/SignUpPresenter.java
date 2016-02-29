package com.debashis.mywallet.presenter;

import android.database.sqlite.SQLiteDatabase;
import android.text.TextUtils;

import com.debashis.mywallet.model.User;
import com.debashis.mywallet.storage.keychain.MyWalletKeyChain;
import com.debashis.mywallet.storage.sqlite.DatabaseManager;

/**
 * Created by Debashis on 23/2/16.
 */
public class SignUpPresenter {

    private SignUpView mSignUpView;

    public SignUpPresenter(SignUpView signUpView){
        this.mSignUpView = signUpView;
    }

    public void doSignUp(String firstName, String lastName, String email, String password){
        boolean cancel = false;
        if(TextUtils.isEmpty(firstName)){
            cancel = true;
            mSignUpView.showFirstNameError(true);
        }
        else if(!cancel && TextUtils.isEmpty(lastName)){
            cancel = true;
            mSignUpView.showLastNameError(true);
        }
        else if(!cancel && TextUtils.isEmpty(email)){
            cancel = true;
            mSignUpView.showEmailIdError(true);
        }
        else if(!cancel && TextUtils.isEmpty(password)){
            cancel = true;
            mSignUpView.showPasswordError(true);
        }

        if(!cancel){
            User user = new User(firstName, lastName, email, password);
            SQLiteDatabase db = DatabaseManager.getInstance().openDatabase();
            long rowId = -1;
            try {
                rowId = DatabaseManager.getDatabaseHelper().insertUserData(db, user);
            }
            catch(Exception ex){
                ex.printStackTrace();
            }
            DatabaseManager.getInstance().closeDatabase();
            mSignUpView.signUpResponse(rowId);
        }
    }
}
