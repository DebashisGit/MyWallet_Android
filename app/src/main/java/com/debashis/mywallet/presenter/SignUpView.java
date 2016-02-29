package com.debashis.mywallet.presenter;

/**
 * Created by Debashis on 23/2/16.
 */
public interface SignUpView {
    public void showProgressBar(boolean show);
    public void showFirstNameError(boolean showError);
    public void showLastNameError(boolean showError);
    public void showEmailIdError(boolean showError);
    public void showPasswordError(boolean showError);
    public void signUpResponse(long rowId);
}
