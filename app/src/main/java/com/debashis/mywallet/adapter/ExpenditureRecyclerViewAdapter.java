package com.debashis.mywallet.adapter;

import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import com.debashis.mywallet.R;
import com.debashis.mywallet.model.Expenditure;

import java.util.List;

/**
 * Created by sushil on 25/2/16.
 */
public class ExpenditureRecyclerViewAdapter extends RecyclerView.Adapter<ExpenditureRecyclerViewAdapter.ViewHolder> {

    private List<Expenditure> expenditureList;

    public ExpenditureRecyclerViewAdapter(List<Expenditure> expenditureList){
        this.expenditureList = expenditureList;
    }

    @Override
    public ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {

        View view = LayoutInflater.from(parent.getContext())
                .inflate(R.layout.recycler_view_item, parent, false);
        ViewHolder viewHolder = new ViewHolder(view);
        return viewHolder;
    }

    @Override
    public void onBindViewHolder(ViewHolder holder, int position) {
        Expenditure expenditure = expenditureList.get(position);
        holder.nameText.setText(expenditure.getExpenditureName());
        holder.dateText.setText(expenditure.getExpenditureDate());
        holder.amountText.setText(expenditure.getExpenditureAmount());
    }

    @Override
    public int getItemCount() {
        return expenditureList.size();
    }

    public static class ViewHolder extends RecyclerView.ViewHolder{

        TextView nameText;
        TextView amountText;
        TextView dateText;

        public ViewHolder(View itemView) {
            super(itemView);
            nameText = (TextView) itemView.findViewById(R.id.name_text);
            amountText = (TextView) itemView.findViewById(R.id.amount_text);
            dateText = (TextView) itemView.findViewById(R.id.date_text);
        }
    }
}
