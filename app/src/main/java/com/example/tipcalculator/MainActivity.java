package com.example.tipcalculator;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.text.Editable;
import android.text.TextWatcher;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.SeekBar;
import android.widget.TextView;

import java.util.Locale;

public class MainActivity extends AppCompatActivity {

    int tipPercentage;
    final int INITIAL_TIP_PERCENTAGE = 15;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        tipPercentage = INITIAL_TIP_PERCENTAGE;

        EditText edtMeal = findViewById(R.id.edtMeal);
        edtMeal.addTextChangedListener(new TextWatcher() {
            @Override
            public void beforeTextChanged(CharSequence s, int start, int count, int after) {

            }

            @Override
            public void onTextChanged(CharSequence s, int start, int before, int count) {
                displayTotal();
            }

            @Override
            public void afterTextChanged(Editable s) {

            }
        });

        SeekBar slider = findViewById(R.id.seekBar);
        slider.setOnSeekBarChangeListener(new SeekBar.OnSeekBarChangeListener() {
            @Override
            public void onProgressChanged(SeekBar seekBar, int progress, boolean fromUser) {
                tipPercentage = progress;
                displayTipPercentage();
                displayTotal();
            }

            @Override
            public void onStartTrackingTouch(SeekBar seekBar) {

            }

            @Override
            public void onStopTrackingTouch(SeekBar seekBar) {

            }
        });

        Button btnReset = findViewById(R.id.btnReset);

        btnReset.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                EditText edtMeal = findViewById(R.id.edtMeal);
                edtMeal.setText(null);
                edtMeal.dispatchDisplayHint(View.VISIBLE);

                tipPercentage = INITIAL_TIP_PERCENTAGE;
                SeekBar slider = findViewById(R.id.seekBar);
                slider.setProgress(tipPercentage);
                displayTipPercentage();

                TextView txtResult = findViewById(R.id.txtResult);
                txtResult.setText("TOTAL");
            }
        });
    }

    public void displayTipPercentage(){
        TextView display = findViewById(R.id.txtPercentage);
        display.setText(tipPercentage + "%");
    }

    public void displayTotal(){
        double mealCostNumber = 0;
        EditText edtMeal = findViewById(R.id.edtMeal);
        String mealCostText = edtMeal.getText().toString();

        if(!mealCostText.isEmpty()){
            mealCostNumber = Double.parseDouble(mealCostText);
        }

        double totalCost = mealCostNumber * (tipPercentage / 100.0 + 1);

        TextView txtResult = findViewById(R.id.txtResult);
        String messageTotal = String.format(Locale.getDefault(),"$ %.2f", totalCost);
        txtResult.setText(messageTotal);
    }
}