package com.example.comp1786_project;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.AutoCompleteTextView;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

public class AddExpenseActivity extends AppCompatActivity {
    Button expenseTime_btn, addExpense_btn;
    AutoCompleteTextView autoCompleteTextView;
    EditText expense_amount;
    private final DatePicker _datePicker = new DatePicker(this);
    String[] itemTypes = {"Foods","Travel","Transport","Drinks"};

    int tripId;
    String expenseType, expenseAmount, expenseTime;
    ArrayAdapter<String> adapterItems;



    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_add_expense);

        //Date picker class custom
        expenseTime_btn = findViewById(R.id.expense_time_btn);
        _datePicker.initDatePicker(expenseTime_btn);
        _datePicker.button.setText(_datePicker.getTodayDate());

        //FindViewById
        autoCompleteTextView = findViewById(R.id.auto_complete_txt);
        expense_amount = findViewById(R.id.expense_amount_input);
        addExpense_btn = findViewById(R.id.addExpenses);

        //String id to int
        Bundle extras = getIntent().getExtras();
        tripId = extras.getInt("id");

        //Dropdown Input
        adapterItems = new ArrayAdapter<String>(AddExpenseActivity.this, R.layout.list_of_type,itemTypes);
        autoCompleteTextView.setAdapter(adapterItems);

        autoCompleteTextView.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> adapterView, View view, int position, long id) {
                expenseType = adapterView.getItemAtPosition(position).toString().trim();
                Toast.makeText(getApplicationContext(), "Type: "+ expenseType,Toast.LENGTH_SHORT).show();
            }
        });

        //Add Expense
        addExpense_btn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                DatabaseHelper db = new DatabaseHelper(AddExpenseActivity.this);
                if (expense_amount.getText().toString().trim().length() == 0){
                    expense_amount.setError("Amount is required!");
                }else{
                    //get value
                    expenseAmount = expense_amount.getText().toString().trim();
                    expenseTime = expenseTime_btn.getText().toString().trim();
                    //Add Expenses
                    db.addExpenses( tripId,
                            expenseType,
                            expenseAmount,
                            expenseTime
                    );
                    String id = String.valueOf(getIntent().getIntExtra("id",tripId));
                    Intent intent = new Intent(AddExpenseActivity.this,ExpensesMainActivity.class);
                    intent.putExtra("id", id);
                    startActivity(intent);
                }
            }
        });
    }
    //    //Open DatePicker Dialog
    public void openDatePicker(View view) {
        _datePicker.openDatePicker(view);
    }
}