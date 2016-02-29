package com.debashis.mywallet.presenter;

import com.debashis.mywallet.model.Expenditure;

import java.util.List;

/**
 * Created by Debashis on 25/2/16.
 */
public interface ExpenditureView {
    void returnDataFromSQLite(List<Expenditure> expenditureList);
    void setBalance();
    void showProgressBar(boolean show);
}
