package com.example.quiz;

import android.os.Bundle;
import android.view.View;
import android.widget.CheckBox;
import android.widget.CompoundButton;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.activity.EdgeToEdge;
import androidx.appcompat.app.AppCompatActivity;
import androidx.core.graphics.Insets;
import androidx.core.view.ViewCompat;
import androidx.core.view.WindowInsetsCompat;

public class MainActivity extends AppCompatActivity implements CompoundButton.OnCheckedChangeListener{
    private CheckBox burger, fries, drink, soup;
    private ImageView imgBurger, imgFries, imgDrink, imgSoup;
    private  int[]chkIDs ={R.id.chk1, R.id.chk2, R.id.chk3, R.id.chk4};
    private TextView output;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        EdgeToEdge.enable(this);
        setContentView(R.layout.activity_main);
        imgBurger=(ImageView) findViewById(R.id.output1);
        imgFries=(ImageView) findViewById(R.id.output2);
        imgDrink=(ImageView) findViewById(R.id.output3);
        imgSoup=(ImageView) findViewById(R.id.output4);
        burger=(CheckBox) findViewById(R.id.chk1);
        fries=(CheckBox) findViewById(R.id.chk2);
        drink=(CheckBox) findViewById(R.id.chk3);
        soup=(CheckBox) findViewById(R.id.chk4);

        for (int id : chkIDs){
            CheckBox chk = (CheckBox) findViewById(id);
            chk.setOnCheckedChangeListener(this);
        }
    }


    @Override
    public void onCheckedChanged(CompoundButton buttonView, boolean isChecked) {

        int id = buttonView.getId();

        if (id == R.id.chk1){
            imgBurger.setVisibility(isChecked? View.VISIBLE :View.GONE);
        }
        else if (id == R.id.chk2){
            imgFries.setVisibility(isChecked? View.VISIBLE : View.GONE);
        }
        else if (id == R.id.chk3){
            imgDrink.setVisibility(isChecked? View.VISIBLE : View.GONE);
        }
        else if (id == R.id.chk4){
            imgSoup.setVisibility(isChecked? View.VISIBLE : View.GONE);

        }

    }
}