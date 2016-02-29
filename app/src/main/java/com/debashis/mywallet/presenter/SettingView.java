package com.debashis.mywallet.presenter;

/**
 * Created by Debashis on 29/2/16.
 */
public interface SettingView {
    public void showProgressBar(boolean show);
    public void showResetMessage(String message, int errorCode);
    public void showBankAmountError();
    public void showCardAmountError();
    public void showCashAmountError();
}
