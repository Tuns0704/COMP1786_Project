package com.example.comp1786_project;

import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.content.Intent;
import android.database.Cursor;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.ImageView;
import android.widget.TextView;

import com.google.android.material.floatingactionbutton.FloatingActionButton;

import java.util.ArrayList;

public class ExpensesMainActivity extends AppCompatActivity implements RecycleViewInterface {
    RecyclerView recyclerView;
    FloatingActionButton add_btn;
    ImageView empty_imageView;
    TextView no_data;
    DatabaseHelper db;
    ExpensesAdapter expensesAdapter;
    int tripId;

    private ArrayList<String>  expense_id,
        expense_type,
        expense_amount,
        expense_time;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_expenses_main);

        //Get Id from View
        recyclerView = findViewById(R.id.recycleViewExpense);
        add_btn = findViewById(R.id.addExpenses_btn);
        no_data = findViewById(R.id.no_data_expense);
        empty_imageView = findViewById(R.id.empty_imageViewExpense);

        //Add-btn
        add_btn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(ExpensesMainActivity.this, AddExpenseActivity.class);
                tripId = Integer.parseInt(getIntent().getStringExtra("id"));
                intent.putExtra("id",tripId);
                startActivity(intent);
            }
        });

        db = new DatabaseHelper(ExpensesMainActivity.this);
        expense_id = new ArrayList<>();
        expense_type = new ArrayList<>();
        expense_amount = new ArrayList<>();
        expense_time = new ArrayList<>();

        storeDataToArray();

        expensesAdapter = new ExpensesAdapter(ExpensesMainActivity.this, ExpensesMainActivity.this,
                expense_id,
                expense_type,
                expense_amount,
                expense_time,this);

        recyclerView.setAdapter(expensesAdapter);
        recyclerView.setLayoutManager(new LinearLayoutManager(ExpensesMainActivity.this));
    }

    @Override
    public void OnItemClick(int position) {

    }


    @Override
    protected void onActivityResult(int requestCode, int resultCode, @Nullable Intent data) {
        super.onActivityResult(requestCode, resultCode, data);
        if(requestCode == 1){
            recreate();
        }
    }

    void storeDataToArray(){
        int id = Integer.parseInt(getIntent().getStringExtra("id"));
        Cursor cursor = db.readDataExpensesById(id);
        if(cursor.getCount()==0){
            empty_imageView.setVisibility(View.VISIBLE);
            no_data.setVisibility(View.VISIBLE);
        }else{
            cursor.moveToFirst();
            while (!cursor.isAfterLast()){
                expense_id.add(cursor.getString(0));
                expense_type.add(cursor.getString(1));
                expense_amount.add(cursor.getString(2));
                expense_time.add(cursor.getString(3));
                cursor.moveToNext();
            }
        }
        empty_imageView.setVisibility(View.GONE);
        no_data.setVisibility(View.GONE);
    }
}