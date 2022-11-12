package com.example.comp1786_project;

import android.annotation.SuppressLint;
import android.app.Activity;
import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Filter;
import android.widget.Filterable;
import android.widget.TextView;
import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import java.util.ArrayList;
import java.util.Collection;
import java.util.List;
import java.util.Locale;

public class CustomAdapter extends RecyclerView.Adapter<CustomAdapter.AppViewHolder> {
    Activity activity;
    private final Context context;
    private final RecycleViewInterface recycleViewInterface;
    List<TripList> tripList;

    @SuppressLint("NotifyDataSetChanged")
    public void setFilteredList(List<TripList> filteredList){
        this.tripList = filteredList;
        notifyDataSetChanged();
    }

    CustomAdapter(Activity activity ,Context context,
                    ArrayList<TripList> tripList,
                  RecycleViewInterface recycleViewInterface) {
        this.activity = activity;
        this.context = context;
        this.tripList = tripList;
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
        TripList tList = tripList.get(position);
        holder.trip_id_txt.setText(String.valueOf(tList.getTrip_id()));
        holder.trip_name_txt.setText(String.valueOf(tList.getTrip_name()));
        holder.trip_destination_txt.setText(String.valueOf(tList.getTrip_destination()));
        holder.trip_date_txt.setText(String.valueOf(tList.getTrip_date()));
        holder.trip_requireAssessment_txt.setText(String.valueOf(tList.getTrip_requireAssessment()));
        holder.trip_description_txt.setText(String.valueOf(tList.getTrip_description()));
    }

    @Override
    public int getItemCount() {
        return tripList.size();
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