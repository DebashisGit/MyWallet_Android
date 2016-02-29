package com.debashis.mywallet.view.activity;

import android.app.Activity;
import android.app.ProgressDialog;
import android.content.Intent;
import android.os.Bundle;
import android.support.design.widget.FloatingActionButton;
import android.support.design.widget.Snackbar;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.Toast;

import com.debashis.mywallet.Constant;
import com.debashis.mywallet.R;
import com.debashis.mywallet.presenter.SettingView;
import com.debashis.mywallet.presenter.SettingViewPresenter;
import com.debashis.mywallet.storage.keychain.MyWalletKeyChain;

import org.androidannotations.annotations.AfterViews;
import org.androidannotations.annotations.Click;
import org.androidannotations.annotations.EActivity;
import org.androidannotations.annotations.ViewById;

@EActivity(R.layout.activity_setting)
public class SettingActivity extends Activity implements SettingView {

    @ViewById(R.id.crossImage)
    ImageView crossImage;

    @ViewById(R.id.bank_amt)
    EditText bankAmtText;

    @ViewById(R.id.credit_card_amt)
    EditText creditCardText;

    @ViewById(R.id.cash_amt)
    EditText cashText;

    @ViewById(R.id.save_button)
    Button saveButton;

    @ViewById(R.id.reset_button)
    Button resetButton;

    private ProgressDialog mDialog;
    private SettingViewPresenter mPresenter;

    @Click(R.id.save_button)
    public void saveButtonClick(View view){
        String bankAmtStr = bankAmtText.getText().toString();
        String cardAmtStr = creditCardText.getText().toString();
        String cashAmtStr = cashText.getText().toString();

        int bankAmount = (bankAmtStr == null || bankAmtStr.equals("")) ? 0 : Integer.parseInt(bankAmtStr);
        int cardAmount = (cardAmtStr == null || cardAmtStr.equals("")) ? 0 : Integer.parseInt(cardAmtStr);
        int cashAmount = (cashAmtStr == null || cashAmtStr.equals("")) ? 0 : Integer.parseInt(cashAmtStr);

        boolean isValid = mPresenter.validationCheck(bankAmount, cardAmount, cashAmount);

        if(isValid) {
            MyWalletKeyChain.saveExpenditureAmount(this, bankAmount, cardAmount, cashAmount);
            showSaveMessage(Constant.Message.MESSAGE_AMOUNT_SAVED_SUCCESSFULLY, Constant.ERROR_CODE_SUCCESS);
        }
        else
            showSaveMessage(Constant.Message.MESSAGE_AMOUNT_SAVE_FAILED, Constant.ERROR_CODE_FAIL);
    }

    @Click(R.id.reset_button)
    public void resetButtonClick(View view){

    }

    @AfterViews
    public void init(){

        mPresenter =  new SettingViewPresenter(this);

        int bankAmount = MyWalletKeyChain.getBankAmount(this);
        int cardAmount = MyWalletKeyChain.getCreditCardAmount(this);
        int cashAmount = MyWalletKeyChain.getCashAmount(this);

        bankAmtText.setText(Integer.toString(bankAmount));
        creditCardText.setText(Integer.toString(cardAmount));
        cashText.setText(Integer.toString(cashAmount));

        crossImage.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                closeActivity();
            }
        });
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

    public void showSaveMessage(String message, int errorCode) {
        Toast.makeText(this, message, Toast.LENGTH_SHORT).show();
        if(errorCode == Constant.ERROR_CODE_SUCCESS) {
            Intent intent = new Intent();
            intent.putExtra(Constant.WalletKeys.KEY_SETTING_UPDATION, true);
            setResult(Activity.RESULT_OK, intent);
            closeActivity();
        }
    }

    @Override
    public void showResetMessage(String message, int errorCode) {
        Toast.makeText(this, message, Toast.LENGTH_SHORT).show();
        if(errorCode == Constant.ERROR_CODE_SUCCESS)
            closeActivity();
    }

    @Override
    public void showBankAmountError() {
        bankAmtText.setError(getString(R.string.error_initial_amount_entered));
    }

    @Override
    public void showCardAmountError() {
        creditCardText.setError(getString(R.string.error_initial_amount_entered));
    }

    @Override
    public void showCashAmountError() {
        cashText.setError(getString(R.string.error_initial_amount_entered));
    }

    public void closeActivity(){
        this.finish();
    }

    @Override
    protected void onDestroy() {
        super.onDestroy();
        mPresenter.onDestroy();
    }
}
