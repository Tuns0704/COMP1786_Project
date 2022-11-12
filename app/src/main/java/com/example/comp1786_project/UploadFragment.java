package com.example.comp1786_project;

import android.os.Bundle;
import androidx.fragment.app.Fragment;
import androidx.recyclerview.widget.RecyclerView;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.Toast;

import java.util.ArrayList;
import java.util.List;

import retrofit.Call;
import retrofit.Callback;
import retrofit.GsonConverterFactory;
import retrofit.Response;
import retrofit.Retrofit;

public class UploadFragment extends Fragment implements RecycleViewInterface {
    Button submit;
    RecyclerView recyclerView;
    DatabaseHelper db;

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
    }
    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        View view = inflater.inflate(R.layout.fragment_upload, container, false);
        submit = view.findViewById(R.id.submit);


        db = new DatabaseHelper(getActivity());

        Retrofit retrofit = new Retrofit.Builder()
                .baseUrl("http://localhost:61421/comp1424cw")
                .addConverterFactory(GsonConverterFactory.create())
                .build();

        return view;
    }


    @Override
    public void OnItemClick(int position) {

    }
}