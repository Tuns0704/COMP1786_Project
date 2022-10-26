package com.example.comp1786_project;
import android.content.Intent;
import android.database.Cursor;
import android.os.Bundle;
import androidx.fragment.app.Fragment;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import androidx.appcompat.widget.SearchView;
import android.widget.TextView;
import android.widget.Toast;
import java.util.ArrayList;
import java.util.List;

public class SearchFragment extends Fragment implements RecycleViewInterface {
    RecyclerView recyclerView;
    ImageView empty_imageView;
    TextView no_data;
    DatabaseHelper db;
    CustomAdapter customAdapter;
    SearchView searchView;
    ArrayList<TripList> tripList = new ArrayList<>();

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_search, container, false);
        db = new DatabaseHelper(getActivity());

        //Get Id from View
        recyclerView = view.findViewById(R.id.searchRecyclerView);
        empty_imageView = view.findViewById(R.id.empty_imageSearchView);
        no_data = view.findViewById(R.id.no_data_search);
        searchView = view.findViewById(R.id.searchView);

        //searchView
        searchView.clearFocus();
        searchView.setOnQueryTextListener(new SearchView.OnQueryTextListener() {
            @Override
            public boolean onQueryTextSubmit(String s) {
                return false;
            }

            @Override
            public boolean onQueryTextChange(String s) {
                filterList(s);
                return true;
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

    private void filterList(String text){
        List<TripList> filteredList = new ArrayList<>();
        for (TripList tList : tripList){
            if(tList.getTrip_name().toLowerCase().contains(text.toLowerCase())){
                filteredList.add(tList);
            }
        }
        if (filteredList.isEmpty()){
            Toast.makeText(getContext(), "No data found", Toast.LENGTH_SHORT).show();
        }else {
            customAdapter.setFilteredList(filteredList);
        }
    }
}