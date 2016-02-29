package com.debashis.mywallet.presenter;

/**
 * Created by Debashis on 29/2/16.
 */
public interface ExpenditureEntryView {
    public void showProgressBar(boolean show);
    public void isExpenditureCreated(boolean created);
    public void showExpenditureNameError(boolean show);
    public void showExpenditureAmountError(boolean show);
    public void showEnteredAmountError(boolean show);
}
