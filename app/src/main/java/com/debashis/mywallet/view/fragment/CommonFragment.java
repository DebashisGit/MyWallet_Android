package com.debashis.mywallet.view.fragment;

import android.app.ProgressDialog;
import android.content.Context;
import android.os.Bundle;
import android.support.annotation.Nullable;
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
    private RecyclerView.LayoutManager layoutManager = new LinearLayoutManager(getActivity());
    private int expenditureType = 0;

    @Override
    public void onAttach(Context context) {
        super.onAttach(context);
    }

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        Bundle bundle = getArguments();
        expenditureType = (int) bundle.get(Constant.WalletKeys.Key_Expenditure_Type);
    }

    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        rootView = inflater.inflate(R.layout.fragment_common_wallet, container, false);
        mAmountText = (TextView) rootView.findViewById(R.id.amount_text);
        mRecyclerView = (RecyclerView) rootView.findViewById(R.id.expenditure_recycler_view);
        return rootView;
    }

    @Override
    public void onActivityCreated(@Nullable Bundle savedInstanceState) {
        super.onActivityCreated(savedInstanceState);
    }

    @Override
    public void returnDataFromSQLite(List<Expenditure> expenditureList) {
        mAdapter = new ExpenditureRecyclerViewAdapter(expenditureList);
        mRecyclerView.setAdapter(mAdapter);
    }

    @Override
    public void setBalance(String balance) {
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
}
