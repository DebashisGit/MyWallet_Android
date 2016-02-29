package com.debashis.mywallet.model;

/**
 * Created by Debashis on 25/2/16.
 */
public class Expenditure {
    private String expenditureName;
    private int expenditureAmount;
    private String expenditureDate;
    private int expenditureType;

    public Expenditure(String expenditureName, int expenditureAmount, String expenditureDate, int expenditureType){
        this.expenditureName = expenditureName;
        this.expenditureAmount = expenditureAmount;
        this.expenditureDate = expenditureDate;
        this.expenditureType = expenditureType;
    }

    public String getExpenditureName() {
        return expenditureName;
    }

    public int getExpenditureAmount() {
        return expenditureAmount;
    }

    public String getExpenditureDate() {
        return expenditureDate;
    }

    public int getExpenditureType() {
        return expenditureType;
    }
}
