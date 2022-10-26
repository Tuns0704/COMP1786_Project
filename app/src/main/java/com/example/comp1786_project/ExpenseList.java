package com.example.comp1786_project;

public class ExpenseList {
    private String expense_id;
    private String trip_id;
    private String expense_type;
    private String expense_amount;
    private String expense_time;

    public String getExpense_id() {
        return expense_id;
    }
    public void setExpense_id(String expense_id) {
        this.expense_id = expense_id;
    }

    public String getTrip_id(){
        return trip_id;
    }
    public void setTrip_id(String trip_id){
        this.trip_id = trip_id;
    }

    public String getExpense_type() {
        return expense_type;
    }
    public void setExpense_type(String expense_type) {
        this.expense_type = expense_type;
    }

    public String getExpense_amount() {
        return expense_amount;
    }
    public void setExpense_amount(String expense_amount) {
        this.expense_amount = expense_amount;
    }

    public String getExpense_time() {
        return expense_time;
    }
    public void setExpense_time(String expense_time) {
        this.expense_time = expense_time;
    }

    public ExpenseList(String expense_id, String trip_id, String expense_type, String expense_amount, String expense_time){
        this.expense_id = expense_id;
        this.trip_id = trip_id;
        this.expense_type = expense_type;
        this.expense_amount = expense_amount;
        this.expense_time = expense_time;
    }
}
