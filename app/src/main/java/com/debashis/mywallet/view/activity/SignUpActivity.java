package com.debashis.mywallet.view.activity;

import android.app.ProgressDialog;
import android.content.Intent;
import android.database.sqlite.SQLiteDatabase;
import android.support.v7.app.AppCompatActivity;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import com.debashis.mywallet.Constant;
import com.debashis.mywallet.storage.keychain.MyWalletKeyChain;
import com.debashis.mywallet.storage.sqlite.DatabaseHelper;
import com.debashis.mywallet.presenter.SignUpPresenter;
import com.debashis.mywallet.presenter.SignUpView;
import com.debashis.mywallet.R;
import com.debashis.mywallet.storage.sqlite.DatabaseManager;

import org.androidannotations.annotations.AfterViews;
import org.androidannotations.annotations.Click;
import org.androidannotations.annotations.EActivity;
import org.androidannotations.annotations.ViewById;

/**
 * Created by Debashis on 23/2/16.
 */
@EActivity (R.layout.activity_signup)
public class SignUpActivity extends AppCompatActivity implements SignUpView {

    @ViewById(R.id.signup_text)
    TextView signUpText;

    @ViewById(R.id.firstname)
    EditText firstName;

    @ViewById(R.id.lastname)
    EditText lastName;

    @ViewById(R.id.signup_email)
    EditText email;

    @ViewById(R.id.signup_password)
    EditText password;

    @ViewById(R.id.user_sign_up_button)
    Button signUpButton;

    private ProgressDialog mDialog;
    private SignUpPresenter signUpPresenter;
    private DatabaseHelper dbHelper;

    @AfterViews
    public void init(){
        signUpPresenter = new SignUpPresenter(this);
        dbHelper = new DatabaseHelper(this);
    }

    @Click(R.id.user_sign_up_button)
    void SignUp(){
        String firstNameValue = firstName.getText().toString();
        String lastNameValue = lastName.getText().toString();
        String emailValue = email.getText().toString();
        String passwordValue = password.getText().toString();

        signUpPresenter.doSignUp(firstNameValue, lastNameValue, emailValue, passwordValue);
    }

    @Override
    public void showProgressBar(boolean show) {
        if(show){
            if(mDialog == null)
                mDialog = new ProgressDialog(this);
            if(!mDialog.isShowing())
                mDialog.show();
        }
        else{
            if(mDialog.isShowing())
                mDialog.cancel();
        }
    }

    @Override
    public void showFirstNameError(boolean showError) {
        if(showError){
            firstName.setError(getString(R.string.error_field_required));
            firstName.requestFocus();
        }
        else
            firstName.setError(null);
    }

    @Override
    public void showLastNameError(boolean showError) {
        if(showError){
            lastName.setError(getString(R.string.error_field_required));
            lastName.requestFocus();
        }
        else
            lastName.setError(null);
    }

    @Override
    public void showEmailIdError(boolean showError) {
        if(showError){
            email.setError(getString(R.string.error_field_required));
            email.requestFocus();
        }
        else
            email.setError(null);
    }

    @Override
    public void showPasswordError(boolean showError) {
        if(showError){
            password.setError(getString(R.string.error_field_required));
            password.requestFocus();
        }
        else
            password.setError(null);
    }

    @Override
    public void signUpResponse(long rowId) {
        if(rowId == -1){
            Toast.makeText(this, R.string.toast_signup_failed, Toast.LENGTH_SHORT).show();
        }else{
            Toast.makeText(this, R.string.toast_signup_success, Toast.LENGTH_SHORT).show();
            MyWalletKeyChain.saveHasLoggedIn(this, true);
            Intent intent = new Intent(this, ExpenditureActivity_.class);
            startActivity(intent);
            finish();
        }
    }
}
