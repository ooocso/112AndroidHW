package com.example.myapplication;
import android.os.Bundle;
import android.text.Editable;
import android.text.TextWatcher;
import android.widget.EditText;
import android.widget.RadioButton;
import android.widget.RadioGroup;
import android.widget.TextView;

import androidx.appcompat.app.AppCompatActivity;

public class MainActivity extends AppCompatActivity {

    private RadioButton maleRadioButton, femaleRadioButton, adultRadioButton, childRadioButton, studentRadioButton;
    private EditText quantityEditText;
    private TextView genderTextView, ticketTextView, totalTextView;

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
    }

    private void updateResult() {
        String gender = "";
        if (maleRadioButton.isChecked()) {
            gender = "Male";
        } else if (femaleRadioButton.isChecked()) {
            gender = "Female";
        }

        String ticket = "";
        if (adultRadioButton.isChecked()) {
            ticket = "Adult (500)";
        } else if (childRadioButton.isChecked()) {
            ticket = "Child (250)";
        } else if (studentRadioButton.isChecked()) {
            ticket = "Student (400)";
        }

        int quantity = 0;
        try {
            quantity = Integer.parseInt(quantityEditText.getText().toString());
        } catch (NumberFormatException e) {
            e.printStackTrace();
        }

        double total = calculateTotalPrice(ticket, quantity);

        genderTextView.setText("Selected Gender: " + gender);
        ticketTextView.setText("Selected Ticket: " + ticket);
        totalTextView.setText("Total Price: " + total);
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

