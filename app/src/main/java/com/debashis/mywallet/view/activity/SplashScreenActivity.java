package com.debashis.mywallet.view.activity;

import android.annotation.SuppressLint;
import android.app.Activity;
import android.app.ProgressDialog;
import android.content.Intent;
import android.support.v7.app.ActionBar;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.os.Handler;
import android.view.MotionEvent;
import android.view.View;

import com.debashis.mywallet.R;
import com.debashis.mywallet.app.AppSingleton;
import com.debashis.mywallet.storage.keychain.MyWalletKeyChain;

import org.androidannotations.annotations.AfterViews;
import org.androidannotations.annotations.EActivity;

/**
 * An example full-screen activity that shows and hides the system UI (i.e.
 * status bar and navigation/system bar) with user interaction.
 */
@EActivity(R.layout.activity_splash_screen)
public class SplashScreenActivity extends Activity {

    private boolean mHasLogged = false;
    private ProgressDialog mDialog;

    @AfterViews
    public void init(){
        showProgressBar(true);
        mHasLogged = MyWalletKeyChain.hasUserLoggedIn(this);
        AppSingleton.getInstance(this);//Initialize the Singleton class which in turn will create the database.
        showProgressBar(false);

        if(mHasLogged){
            launchMainActivity();
            finish();
        }
        else {
            launchLoginActivity();
            finish();
        }
    }

    private void launchLoginActivity() {
        Intent intent = new Intent(this, LoginActivity_.class);
        startActivity(intent);
    }

    private void launchMainActivity() {

    }

    private void showProgressBar(boolean show){
        if(show){
            if(mDialog == null)
                mDialog = new ProgressDialog(this);

            mDialog.show();
        }
        else if (mDialog != null && mDialog.isShowing()){
            mDialog.cancel();
        }

    }
}
