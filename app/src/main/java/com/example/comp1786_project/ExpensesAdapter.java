package com.example.comp1786_project;

import android.app.Activity;
import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import java.util.ArrayList;

public class ExpensesAdapter extends RecyclerView.Adapter<ExpensesAdapter.ExpensesViewHolder> {
    Activity activity;
    private final Context context;
    private final RecycleViewInterface recycleViewInterface;
    ArrayList expense_id,
            expense_type,
            expense_amount,
            expense_time;

    ExpensesAdapter(Activity activity, Context context,
                    ArrayList expense_id,
                    ArrayList expense_type,
                    ArrayList expense_amount,
                    ArrayList expense_time,
                    RecycleViewInterface recycleViewInterface) {
        this.activity = activity;
        this.context = context;
        this.expense_id = expense_id;
        this.expense_type = expense_type;
        this.expense_amount = expense_amount;
        this.expense_time = expense_time;
        this.recycleViewInterface = recycleViewInterface;
    }

    @NonNull
    @Override
    public ExpensesViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        LayoutInflater inflater = LayoutInflater.from(context);
        View view = inflater.inflate(R.layout.expense_row, parent, false);
        return new ExpensesViewHolder(view, recycleViewInterface);
    }

    @Override
    public void onBindViewHolder(@NonNull ExpensesViewHolder holder, int position) {
        holder.expense_type_txt.setText(String.valueOf(expense_type.get(position)));
        holder.expense_amount_txt.setText(String.valueOf(expense_amount.get(position)));
        holder.expense_time_txt.setText(String.valueOf(expense_time.get(position)));
    }


    @Override
    public int getItemCount() {
        return expense_id.size();
    }

    public static class ExpensesViewHolder extends RecyclerView.ViewHolder{
        TextView expense_type_txt,
                expense_amount_txt,
                expense_time_txt;
        public ExpensesViewHolder(@NonNull View itemView, RecycleViewInterface recycleViewInterface) {
            super(itemView);
            expense_type_txt = itemView.findViewById(R.id.expense_type_txt);
            expense_amount_txt = itemView.findViewById(R.id.expense_amount_txt);
            expense_time_txt = itemView.findViewById(R.id.expense_time_txt);
            itemView.setOnClickListener(view -> {
                if(recycleViewInterface != null){
                    int pos = getAdapterPosition();
                    if(pos != RecyclerView.NO_POSITION){
                        recycleViewInterface.OnItemClick(pos);
                    }
                }
            });
        }
    }
}
