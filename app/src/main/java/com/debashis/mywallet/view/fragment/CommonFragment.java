package com.debashis.mywallet.view.fragment;

import android.app.Activity;
import android.app.ProgressDialog;
import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.design.widget.FloatingActionButton;
import android.support.v4.app.Fragment;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import com.debashis.mywallet.Constant;
import com.debashis.mywallet.R;
import com.debashis.mywallet.adapter.ExpenditureRecyclerViewAdapter;
import com.debashis.mywallet.model.Expenditure;
import com.debashis.mywallet.presenter.ExpenditureView;
import com.debashis.mywallet.presenter.ExpenditureViewPresenter;
import com.debashis.mywallet.storage.keychain.MyWalletKeyChain;
import com.debashis.mywallet.view.activity.ExpenditureActivity_;
import com.debashis.mywallet.view.activity.ExpenditureEntryActivity_;

import java.util.List;

/**
 * Created by Debashis on 24/2/16.
 */
public class CommonFragment extends Fragment implements ExpenditureView {

    private View rootView;
    private TextView mAmountText;
    private RecyclerView mRecyclerView;
    private ProgressDialog mDialog;
    private RecyclerView.Adapter mAdapter;
    private FloatingActionButton mFloatingActionButton;
    private ExpenditureViewPresenter expenditureViewPresenter;
    private int expenditureType = 0;

    private int mInitialBankAmount;
    private int mInitialCardAmount;
    private int mInitialCashAmount;
    private int mInitialAmount;

    @Override
    public void onAttach(Context context) {
        super.onAttach(context);
        mInitialBankAmount = MyWalletKeyChain.getBankAmount(context);
        mInitialCardAmount = MyWalletKeyChain.getCreditCardAmount(context);
        mInitialCashAmount = MyWalletKeyChain.getCashAmount(context);
    }

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        expenditureViewPresenter = new ExpenditureViewPresenter(this);
    }

    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        rootView = inflater.inflate(R.layout.fragment_common_wallet, container, false);
        mAmountText = (TextView) rootView.findViewById(R.id.amount_text);
        mRecyclerView = (RecyclerView) rootView.findViewById(R.id.expenditure_recycler_view);
        mRecyclerView.setLayoutManager(new LinearLayoutManager(getActivity()));
        mFloatingActionButton = (FloatingActionButton) rootView.findViewById(R.id.fab);
        mFloatingActionButton.setOnClickListener(new OnFloatingActionButtonClickListener());
        return rootView;
    }

    @Override
    public void onActivityCreated(@Nullable Bundle savedInstanceState) {
        super.onActivityCreated(savedInstanceState);
        expenditureViewPresenter.fetchDataFromDatabase(expenditureType);

        Bundle bundle = getArguments();
        expenditureType = (int) bundle.get(Constant.WalletKeys.Key_Expenditure_Type);
        if(expenditureType == 1)
            mInitialAmount = mInitialBankAmount;
        else if(expenditureType == 2)
            mInitialAmount = mInitialCardAmount;
        else if(expenditureType == 3)
            mInitialAmount = mInitialCashAmount;

        setBalance();
    }

    @Override
    public void returnDataFromSQLite(List<Expenditure> expenditureList) {
        mAdapter = new ExpenditureRecyclerViewAdapter(expenditureList);
        mRecyclerView.setAdapter(mAdapter);
    }

    @Override
    public void setBalance() {
        int sumAmount = expenditureViewPresenter.getSumExpenditureAmount(expenditureType);
        int balance = mInitialAmount - sumAmount;

        StringBuilder builder = new StringBuilder();
        builder.append("Rs ");
        builder.append(balance);
        mAmountText.setText(builder.toString());
    }

    @Override
    public void showProgressBar(boolean show) {
        if(show){
            if(mDialog == null)
                mDialog = new ProgressDialog(getActivity());

            if(!mDialog.isShowing())
                mDialog.show();
        }
        else if(mDialog != null && mDialog.isShowing())
            mDialog.cancel();
    }

    @Override
    public void onActivityResult(int requestCode, int resultCode, Intent data) {
        if(requestCode == Constant.REQUEST_CODE && resultCode == Activity.RESULT_OK){
            boolean isCreated = data.getBooleanExtra(Constant.WalletKeys.KEY_EXPENDITURE_CREATION, false);
            boolean isUpdated = data.getBooleanExtra(Constant.WalletKeys.KEY_SETTING_UPDATION, false);
            if(isCreated){
                expenditureViewPresenter.fetchDataFromDatabase(expenditureType);
                mAdapter.notifyDataSetChanged();

                setBalance();
            }
            if(isUpdated)
                setBalance();
        }
    }

    class OnFloatingActionButtonClickListener implements View.OnClickListener {

        @Override
        public void onClick(View v) {
            Intent intent = new Intent(getActivity(), ExpenditureEntryActivity_.class);
            intent.putExtra(Constant.WalletKeys.Key_Expenditure_Type, expenditureType);
            intent.putExtra(Constant.WalletKeys.KEY_INITIAL_EXPENDITURE_AMOUNT, mInitialAmount);
            startActivityForResult(intent, Constant.REQUEST_CODE);
        }
    }
}
