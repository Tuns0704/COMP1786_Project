package com.example.comp1786_project;

import android.content.DialogInterface;
import android.content.Intent;
import android.database.Cursor;
import android.os.Bundle;

import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import com.google.android.material.floatingactionbutton.FloatingActionButton;

import java.util.ArrayList;

public class HomeFragment extends Fragment implements RecycleViewInterface {
    RecyclerView recyclerView;
    FloatingActionButton add_btn;
    ImageView empty_imageView;
    TextView no_data;
    DatabaseHelper db;
    CustomAdapter customAdapter;

    private ArrayList<String>  trip_id,
            trip_name,
            trip_destination,
            trip_date,
            trip_requireAssessment,
            trip_description;

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        View view = inflater.inflate(R.layout.fragment_home, container, false);

        //Get Id from View
        recyclerView = view.findViewById(R.id.recycleView);
        add_btn = view.findViewById(R.id.addTrip_btn);
        empty_imageView = view.findViewById(R.id.empty_imageView);
        no_data = view.findViewById(R.id.no_data_txt);

        //Add-btn
        add_btn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(getActivity() ,AddActivity.class);
                startActivity(intent);
            }
        });

        db = new DatabaseHelper(getActivity());
        trip_id = new ArrayList<>();
        trip_name = new ArrayList<>();
        trip_destination = new ArrayList<>();
        trip_date = new ArrayList<>();
        trip_requireAssessment = new ArrayList<>();
        trip_description = new ArrayList<>();

        storeDataToArrays();

        customAdapter = new CustomAdapter(getActivity(), getContext(),
                trip_id,
                trip_name,
                trip_destination,
                trip_date,
                trip_requireAssessment,
                trip_description, this);
        recyclerView.setAdapter(customAdapter);
        recyclerView.setLayoutManager(new LinearLayoutManager(getActivity()));
        return view;
    }

    void storeDataToArrays(){
        Cursor cursor = db.readAllDataTrip();
        if(cursor.getCount() == 0){
            empty_imageView.setVisibility(View.VISIBLE);
            no_data.setVisibility(View.VISIBLE);
        }else{
            cursor.moveToFirst();
            while (!cursor.isAfterLast()){
                trip_id.add(cursor.getString(0));
                trip_name.add(cursor.getString(1));
                trip_destination.add(cursor.getString(2));
                trip_date.add(cursor.getString(3));
                trip_requireAssessment.add(cursor.getString(4));
                trip_description.add(cursor.getString(5));
                cursor.moveToNext();
            }
            empty_imageView.setVisibility(View.GONE);
            no_data.setVisibility(View.GONE);
        }
    }

    @Override
    public void OnItemClick(int position) {
        Intent intent = new Intent(getActivity(),DetailActivity.class);
        intent.putExtra("id", String.valueOf(trip_id.get(position)));
        intent.putExtra("name", String.valueOf(trip_name.get(position)));
        intent.putExtra("destination", String.valueOf(trip_destination.get(position)));
        intent.putExtra("date", String.valueOf(trip_date.get(position)));
        intent.putExtra("requireAssessment", String.valueOf(trip_requireAssessment.get(position)));
        intent.putExtra("description", String.valueOf(trip_description.get(position)));
        startActivityForResult(intent,1);
    }
}