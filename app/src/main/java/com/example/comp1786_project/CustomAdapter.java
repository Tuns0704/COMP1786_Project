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

public class CustomAdapter extends RecyclerView.Adapter<CustomAdapter.AppViewHolder> {
    Activity activity;
    private final Context context;
    private final RecycleViewInterface recycleViewInterface;
    ArrayList trip_id,
            trip_name,
            trip_destination,
            trip_date,
            trip_requireAssessment,
            trip_description;

    CustomAdapter(Activity activity ,Context context,
                  ArrayList trip_id,
                  ArrayList trip_name,
                  ArrayList trip_destination,
                  ArrayList trip_date,
                  ArrayList trip_requireAssessment,
                  ArrayList trip_description,
                  RecycleViewInterface recycleViewInterface) {
        this.activity = activity;
        this.context = context;
        this.trip_id = trip_id;
        this.trip_name = trip_name;
        this.trip_destination = trip_destination;
        this.trip_date = trip_date;
        this.trip_requireAssessment = trip_requireAssessment;
        this.trip_description = trip_description;
        this.recycleViewInterface = recycleViewInterface;
    }

    @NonNull
    @Override
    public AppViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        LayoutInflater inflater = LayoutInflater.from(context);
        View view = inflater.inflate(R.layout.card_row, parent, false);
        return new AppViewHolder(view, recycleViewInterface);
    }

    @Override
    public void onBindViewHolder(@NonNull final AppViewHolder holder, final int position) {
        holder.trip_id_txt.setText(String.valueOf(trip_id.get(position)));
        holder.trip_name_txt.setText(String.valueOf(trip_name.get(position)));
        holder.trip_destination_txt.setText(String.valueOf(trip_destination.get(position)));
        holder.trip_date_txt.setText(String.valueOf(trip_date.get(position)));
        holder.trip_requireAssessment_txt.setText(String.valueOf(trip_requireAssessment.get(position)));
        holder.trip_description_txt.setText(String.valueOf(trip_description.get(position)));
    }

    @Override
    public int getItemCount() {
        return trip_id.size();
    }

    public static class AppViewHolder extends RecyclerView.ViewHolder {

        TextView trip_id_txt,
                trip_name_txt,
                trip_destination_txt,
                trip_date_txt,
                trip_requireAssessment_txt,
                trip_description_txt;

        public AppViewHolder(@NonNull View itemView, RecycleViewInterface recycleViewInterface) {
            super(itemView);
            trip_id_txt = itemView.findViewById(R.id.trip_id_txt);
            trip_name_txt = itemView.findViewById(R.id.trip_name_txt);
            trip_destination_txt = itemView.findViewById(R.id.trip_destination_txt);
            trip_date_txt = itemView.findViewById(R.id.trip_date_txt);
            trip_requireAssessment_txt = itemView.findViewById(R.id.trip_require_assessment_txt);
            trip_description_txt = itemView.findViewById(R.id.trip_description_txt);
            itemView.setOnClickListener(view -> {
                if (recycleViewInterface != null) {
                    int pos = getAdapterPosition();

                    if (pos != RecyclerView.NO_POSITION) {
                        recycleViewInterface.OnItemClick(pos);
                    }
                }
            });
        }
    }
}