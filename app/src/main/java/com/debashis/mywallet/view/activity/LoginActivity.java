package com.debashis.mywallet.view.activity;

import android.app.ProgressDialog;
import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import com.debashis.mywallet.model.User;
import com.debashis.mywallet.presenter.LoginPresenter;
import com.debashis.mywallet.presenter.LoginView;
import com.debashis.mywallet.R;

import org.androidannotations.annotations.AfterViews;
import org.androidannotations.annotations.Click;
import org.androidannotations.annotations.EActivity;
import org.androidannotations.annotations.ViewById;

import java.util.List;

/**
 * A login screen that offers login via email/password.
 */
@EActivity (R.layout.activity_login)
public class LoginActivity extends AppCompatActivity implements LoginView {

    // UI references.
    @ViewById(R.id.email)
    EditText mEmailView;
    @ViewById(R.id.password)
    EditText mPasswordView;
    @ViewById(R.id.sign_in_button)
    Button mSignInButton;
    @ViewById(R.id.sign_up_button)
    Button mSignUpButton;

    private ProgressDialog mDialog;
    private LoginPresenter mPresenter;

    @Click(R.id.sign_in_button)
    void SignIn(){
        String email = mEmailView.getText().toString();
        String password = mPasswordView.getText().toString();
        mPresenter.login(email, password);
    }

    @Click(R.id.sign_up_button)
    void SignUp(){
        Intent intent = new Intent(this, SignUpActivity_.class);
        startActivity(intent);
    }

    @AfterViews
    public void init() {
        mPresenter = new LoginPresenter(LoginActivity.this);
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
    public void showEmailIdError(boolean showError) {
        if(showError){
            mEmailView.setError(getString(R.string.error_field_required));
            mEmailView.requestFocus();
        }
        else
            mEmailView.setError(null);
    }

    @Override
    public void loginResponse(List<User> user) {
        if(user != null && user.size() > 0){
            Toast.makeText(this, R.string.toast_login_success, Toast.LENGTH_SHORT).show();
        }
        else
            Toast.makeText(this, R.string.toast_login_failed, Toast.LENGTH_SHORT).show();
    }

}

