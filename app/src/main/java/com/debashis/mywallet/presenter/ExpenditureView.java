package com.debashis.mywallet.presenter;

import com.debashis.mywallet.model.Expenditure;

import java.util.List;

/**
 * Created by sushil on 25/2/16.
 */
public interface ExpenditureView {
    void returnDataFromSQLite(List<Expenditure> expenditureList);
    void setBalance(String balance);
    public void showProgressBar(boolean show);
}
