package com.example.myapplication;

import android.content.Intent;
import android.os.Bundle;
import android.text.Editable;
import android.text.TextWatcher;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.RadioButton;
import android.widget.RadioGroup;
import android.widget.TextView;

import androidx.appcompat.app.AppCompatActivity;

public class MainActivity extends AppCompatActivity {

    private RadioButton maleRadioButton, femaleRadioButton, adultRadioButton, childRadioButton, studentRadioButton;
    private EditText quantityEditText;
    private TextView genderTextView, ticketTextView, totalTextView;
    private Button showSelectionButton;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        maleRadioButton = findViewById(R.id.maleRadioButton);
        femaleRadioButton = findViewById(R.id.femaleRadioButton);
        adultRadioButton = findViewById(R.id.adultRadioButton);
        childRadioButton = findViewById(R.id.childRadioButton);
        studentRadioButton = findViewById(R.id.studentRadioButton);
        quantityEditText = findViewById(R.id.quantityEditText);
        genderTextView = findViewById(R.id.genderTextView);
        ticketTextView = findViewById(R.id.ticketTextView);
        totalTextView = findViewById(R.id.totalTextView);
        showSelectionButton = findViewById(R.id.showSelectionButton);

        // 監聽性別 RadioButton 的變化
        RadioGroup genderRadioGroup = findViewById(R.id.genderRadioGroup);
        genderRadioGroup.setOnCheckedChangeListener(new RadioGroup.OnCheckedChangeListener() {
            @Override
            public void onCheckedChanged(RadioGroup group, int checkedId) {
                updateResult();
            }
        });

        // 監聽票種 RadioButton 的變化
        RadioGroup ticketRadioGroup = findViewById(R.id.ticketRadioGroup);
        ticketRadioGroup.setOnCheckedChangeListener(new RadioGroup.OnCheckedChangeListener() {
            @Override
            public void onCheckedChanged(RadioGroup group, int checkedId) {
                updateResult();
            }
        });

        // 監聽購票張數 EditText 的變化
        quantityEditText.addTextChangedListener(new TextWatcher() {
            @Override
            public void beforeTextChanged(CharSequence s, int start, int count, int after) {}

            @Override
            public void onTextChanged(CharSequence s, int start, int before, int count) {
                updateResult();
            }

            @Override
            public void afterTextChanged(Editable s) {}
        });
        showSelectionButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                // Get selected gender
                String gender = maleRadioButton.isChecked() ? "Male" : "Female";

                // Get selected ticket
                String ticket = "";
                if (adultRadioButton.isChecked()) {
                    ticket = "Adult (500)";
                } else if (childRadioButton.isChecked()) {
                    ticket = "Child (250)";
                } else if (studentRadioButton.isChecked()) {
                    ticket = "Student (400)";
                }

                // Get quantity
                int quantity = Integer.parseInt(quantityEditText.getText().toString());

                // Calculate total price
                double total = calculateTotalPrice(ticket, quantity);

                // Create intent to start SecondActivity
                Intent intent = new Intent(MainActivity.this, SecondActivity.class);
                // Pass data to SecondActivity
                intent.putExtra("gender", gender);
                intent.putExtra("ticket", ticket);
                intent.putExtra("total", total);
                intent.putExtra("quantity", quantity); // Pass quantity to SecondActivity
                // Start SecondActivity
                startActivity(intent);
            }
        });

    }

    private void updateResult() {
        // Get selected gender
        String gender = maleRadioButton.isChecked() ? "Male" : "Female";

        // Get selected ticket
        String ticket = "";
        if (adultRadioButton.isChecked()) {
            ticket = "Adult (500)";
        } else if (childRadioButton.isChecked()) {
            ticket = "Child (250)";
        } else if (studentRadioButton.isChecked()) {
            ticket = "Student (400)";
        }

        // Get quantity
        int quantity = 0;
        try {
            quantity = Integer.parseInt(quantityEditText.getText().toString());
        } catch (NumberFormatException e) {
            e.printStackTrace();
        }

        // Calculate total price
        double total = calculateTotalPrice(ticket, quantity);

        // Update text views with result
        genderTextView.setText("性別: " + gender);
        ticketTextView.setText("票種: " + ticket);
        totalTextView.setText("金額: " + total);
    }

    private double calculateTotalPrice(String ticket, int quantity) {
        double price = 0;
        if (ticket.equals("Adult (500)")) {
            price = 500;
        } else if (ticket.equals("Child (250)")) {
            price = 250;
        } else if (ticket.equals("Student (400)")) {
            price = 400;
        }
        return price * quantity;
    }
}
