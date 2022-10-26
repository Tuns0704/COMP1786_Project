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
    ArrayList<TripList> tripList = new ArrayList<>();

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

        storeDataToArrays();

        customAdapter = new CustomAdapter(getActivity(), getContext(),
                tripList,
                this);
        recyclerView.setAdapter(customAdapter);
        recyclerView.setLayoutManager(new LinearLayoutManager(getActivity()));
        return view;
    }

    void storeDataToArrays(){
        Cursor cursor = db.readAllDataTrip();
        if(cursor.getCount() == 0){
            empty_imageView.setVisibility(View.VISIBLE);
            no_data.setVisibility(View.VISIBLE);
        }else {
            cursor.moveToFirst();
            while (!cursor.isAfterLast()){
                tripList.add(new TripList(cursor.getString(0),
                        cursor.getString(1),
                        cursor.getString(2),
                        cursor.getString(3),
                        cursor.getString(4),
                        cursor.getString(5)));
                cursor.moveToNext();
            }
            empty_imageView.setVisibility(View.GONE);
            no_data.setVisibility(View.GONE);
        }
    }

    @Override
    public void OnItemClick(int position) {
        Intent intent = new Intent(getActivity(),DetailActivity.class);
        TripList tList = tripList.get(position);
        intent.putExtra("id", String.valueOf(tList.getTrip_id()));
        intent.putExtra("name", String.valueOf(tList.getTrip_name()));
        intent.putExtra("destination", String.valueOf(tList.getTrip_destination()));
        intent.putExtra("date", String.valueOf(tList.getTrip_date()));
        intent.putExtra("requireAssessment", String.valueOf(tList.getTrip_requireAssessment()));
        intent.putExtra("description", String.valueOf(tList.getTrip_description()));
        startActivityForResult(intent,1);
    }
}