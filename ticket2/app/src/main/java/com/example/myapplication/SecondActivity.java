package com.example.myapplication;

import android.content.Intent;
import android.os.Bundle;
import android.widget.TextView;

import androidx.appcompat.app.AppCompatActivity;

public class SecondActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_second);

        // Get the result data from the intent
        Intent intent = getIntent();
        String gender = intent.getStringExtra("gender");
        String ticket = intent.getStringExtra("ticket");
        double total = intent.getDoubleExtra("total", 0.0);
        int quantity = intent.getIntExtra("quantity", 0); // New line to get quantity

        // Display the result in TextViews
        TextView genderTextView = findViewById(R.id.genderTextView);
        genderTextView.setText("Selected Gender: " + gender);

        TextView ticketTextView = findViewById(R.id.ticketTextView);
        ticketTextView.setText("Selected Ticket: " + ticket);

        TextView quantityTextView = findViewById(R.id.quantityTextView); // New TextView for quantity
        quantityTextView.setText("Quantity: " + quantity); // Display quantity

        TextView totalTextView = findViewById(R.id.totalTextView);
        totalTextView.setText("Total Price: " + total);
    }
}
