package com.debashis.mywallet.storage.sqlite;

import android.provider.BaseColumns;

/**
 * Created by sushil on 23/2/16.
 */
public final class DatabaseContract {

    public static final  int    DATABASE_VERSION   = 1;
    public static final  String DATABASE_NAME      = "wallet_database.db";
    private static final String TEXT_TYPE          = " TEXT";
    private static final String DATE_TYPE          = " DATETIME";
    private static final String INTEGER_TYPE          = " INTEGER";
    private static final String COMMA_SEP          = ", ";

    private static final int BANK_ACCOUNT_EXPENDITURE_TYPE = 1;
    private static final int CREDIT_CARD_EXPENDITURE_TYPE = 2;
    private static final int CASH_EXPENDITURE_TYPE = 3;

    public DatabaseContract(){};

    public static abstract class User implements BaseColumns {
        public static final String TABLE_NAME = "User";
        public static final String COLUMN_NAME_USER_FIRST_NAME = "firstname";
        public static final String COLUMN_NAME_USER_LAST_NAME = "lastname";
        public static final String COLUMN_NAME_USER_EMAIL = "email";
        public static final String COLUMN_NAME_USER_PASSWORD = "password";

        public static final String CREATE_USER_TABLE = "CREATE TABLE "
                + TABLE_NAME + "( "
                + COLUMN_NAME_USER_FIRST_NAME + TEXT_TYPE + COMMA_SEP
                + COLUMN_NAME_USER_LAST_NAME + TEXT_TYPE + COMMA_SEP
                + COLUMN_NAME_USER_EMAIL + TEXT_TYPE +  " PRIMARY KEY" + COMMA_SEP
                + COLUMN_NAME_USER_PASSWORD + TEXT_TYPE + " );";

        public static final String DELETE_USER_TABLE = "DROP TABLE IF EXISTS"
                + TABLE_NAME;

        public static final String FIND_USER = "Select * from "
                + TABLE_NAME
                + " Where "
                + COLUMN_NAME_USER_EMAIL + " = ?"
                + " And "
                + COLUMN_NAME_USER_PASSWORD + " = ?";
    }

    public static abstract class Expenditure implements BaseColumns {
        public static final String TABLE_NAME = "Expenditure";
        public static final String COLUMN_NAME_EXPENDITURE_NAME = "expenditure_name";
        public static final String COLUMN_NAME_EXPENDITURE_AMOUNT = "expenditure_amount";
        public static final String COLUMN_NAME_EXPENDITURE_DATE = "expenditure_date";
        public static final String COLUMN_NAME_EXPENDITURE_TYPE = "expenditure_type";

        public static final String CREATE_EXPENDITURE_TABLE = "CREATE TABLE "
                + TABLE_NAME + "( "
                + COLUMN_NAME_EXPENDITURE_NAME + TEXT_TYPE + COMMA_SEP
                + COLUMN_NAME_EXPENDITURE_AMOUNT + INTEGER_TYPE + COMMA_SEP
                + COLUMN_NAME_EXPENDITURE_DATE + DATE_TYPE + COMMA_SEP
                + COLUMN_NAME_EXPENDITURE_TYPE + INTEGER_TYPE + " );";

        public static final String DELETE_USER_TABLE = "DROP TABLE IF EXISTS"
                + TABLE_NAME;

        public static final String TOTAL_EXPENDITURE_AMOUNT_BANK = "Select sum("
                + COLUMN_NAME_EXPENDITURE_AMOUNT + ") from "
                + TABLE_NAME
                + " Where "
                + COLUMN_NAME_EXPENDITURE_TYPE + " = 1";

        public static final String TOTAL_EXPENDITURE_AMOUNT_CREDIT_CARD = "Select sum("
                + COLUMN_NAME_EXPENDITURE_AMOUNT + ") from "
                + TABLE_NAME
                + " Where "
                + COLUMN_NAME_EXPENDITURE_TYPE + " = 2";

        public static final String TOTAL_EXPENDITURE_AMOUNT_CASH = "Select sum("
                + COLUMN_NAME_EXPENDITURE_AMOUNT + ") from "
                + TABLE_NAME
                + " Where "
                + COLUMN_NAME_EXPENDITURE_TYPE + " = 3";
    }
}
