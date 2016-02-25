package com.debashis.mywallet.presenter;

import com.debashis.mywallet.model.User;

import java.util.List;

/**
 * Created by sushil on 22/2/16.
 */
public interface LoginView {
    public void showProgressBar(boolean show);
    public void showEmailIdError(boolean showError);
    public void loginResponse(List<User> user);
}
