package com.debashis.mywallet.view.activity;

import android.app.Activity;
import android.app.ProgressDialog;
import android.content.Intent;
import android.os.Bundle;
import android.util.DisplayMetrics;
import android.view.View;
import android.view.ViewGroup;
import android.view.WindowManager;
import android.widget.Button;
import android.widget.EditText;

import com.debashis.mywallet.Constant;
import com.debashis.mywallet.R;
import com.debashis.mywallet.presenter.ExpenditureEntryPresenter;
import com.debashis.mywallet.presenter.ExpenditureEntryView;

import org.androidannotations.annotations.AfterViews;
import org.androidannotations.annotations.Click;
import org.androidannotations.annotations.EActivity;
import org.androidannotations.annotations.ViewById;

/**
 * Created by Debashis on 29/2/16.
 */
@EActivity(R.layout.entry_expenditure)
public class ExpenditureEntryActivity extends Activity implements ExpenditureEntryView {

    @ViewById(R.id.expenditure_name)
    EditText expenditureName;

    @ViewById(R.id.expenditure_amount)
    EditText expenditureAmount;

    @ViewById(R.id.save_expenditure)
    Button saveExpenditure;

    private ProgressDialog mDialog;
    private ExpenditureEntryPresenter mPresenter;
    private int mExpenditureType;
    private int mInitialAmount;

    @AfterViews
    public void init(){

        setWindowAttributes();

        mPresenter = new ExpenditureEntryPresenter(this);
        mExpenditureType = getIntent().getIntExtra(Constant.WalletKeys.Key_Expenditure_Type, 0);
        mInitialAmount = getIntent().getIntExtra(Constant.WalletKeys.KEY_INITIAL_EXPENDITURE_AMOUNT, 0);
    }

    @Click(R.id.save_expenditure)
    public void saveButtonClick(View view){
        String name = expenditureName.getText().toString();
        String amount = expenditureAmount.getText().toString();
        mPresenter.createExpenditure(name, amount, mInitialAmount, mExpenditureType);
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

    public void closeActivity() {
        this.finish();
    }

    @Override
    public void showExpenditureNameError(boolean show) {
        if(show){
            expenditureName.setError(getString(R.string.error_field_required));
        }
        else
            expenditureName.setError(null);
    }

    @Override
    public void showExpenditureAmountError(boolean show) {
        if(show){
            expenditureAmount.setError(getString(R.string.error_field_required));
        }
        else
            expenditureAmount.setError(null);
    }

    @Override
    public void showEnteredAmountError(boolean show) {
        if(show){
            expenditureAmount.setError(getString(R.string.error_amount_entered));
        }
        else
            expenditureAmount.setError(null);
    }

    @Override
    public void isExpenditureCreated(boolean created){
        Intent intent = new Intent();
        intent.putExtra(Constant.WalletKeys.KEY_EXPENDITURE_CREATION, created);
        setResult(Activity.RESULT_OK, intent);
        closeActivity();
    }

    @Override
    protected void onDestroy() {
        super.onDestroy();
        mPresenter.onDestroy();
    }

    private void setWindowAttributes(){
        DisplayMetrics metrics = getResources().getDisplayMetrics();
        final int screen_width = metrics.widthPixels;
        final int screen_height = metrics.heightPixels;
        final int new_window_width = screen_width * 85 / 100;
        final int new_window_height = screen_height * 85 / 100;
        WindowManager.LayoutParams layout = getWindow().getAttributes();
        layout.width = Math.max(layout.width, new_window_width);
        layout.height = Math.max(layout.height, new_window_height);
        getWindow().setAttributes(layout);
    }
}
