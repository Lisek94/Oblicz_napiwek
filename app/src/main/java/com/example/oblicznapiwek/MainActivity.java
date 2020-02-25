package com.example.oblicznapiwek;

import androidx.appcompat.app.AppCompatActivity;
import android.os.Bundle;
import android.text.Editable;
import android.text.TextWatcher;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.SeekBar;
import android.widget.TextView;
import java.text.NumberFormat;
import java.util.Random;

public class MainActivity extends AppCompatActivity {

    private EditText amountEditText;
    private TextView amountTextView;

    private TextView percentTextView;
    private SeekBar percentSeekBar;

    private TextView tipLabelTextView;
    private TextView tipTextView;

    private TextView totalLabelTextView;
    private TextView totalTextView;
    private Button randomTip;


    private static final NumberFormat currencyFormat = NumberFormat.getCurrencyInstance();
    private static final NumberFormat percentFormat = NumberFormat.getPercentInstance();

    private double billAmount = 0.0;
    private double tipPercent = 0.15;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        amountEditText = findViewById(R.id.amountEditText);
        amountTextView = findViewById(R.id.amountTextView);

        percentTextView = findViewById(R.id.percentTextView);
        percentSeekBar = findViewById(R.id.percentSeekBar);

        tipLabelTextView = findViewById(R.id.tipLabelTextView);
        tipTextView = findViewById(R.id.tipTextView);

        totalLabelTextView = findViewById(R.id.totalLabelTextView);
        totalTextView = findViewById(R.id.totalTextView);
        randomTip = findViewById(R.id.randomTip);

        amountEditText.addTextChangedListener(new TextWatcher() {
            @Override
            public void beforeTextChanged(CharSequence s, int start, int count, int after) {

            }

            @Override
            public void onTextChanged(CharSequence s, int start, int before, int count) {
                try {
                    billAmount = Double.parseDouble(s.toString()) / 100;
                    amountTextView.setText(currencyFormat.format(billAmount));
                } catch (NumberFormatException ex){
                    amountTextView.setText("");
                    billAmount = 0.0;
                }

                calculateTipAndTotalAmount();
            }

            @Override
            public void afterTextChanged(Editable s) {

            }
        });

        percentSeekBar.setOnSeekBarChangeListener(new SeekBar.OnSeekBarChangeListener() {
            @Override
            public void onProgressChanged(SeekBar seekBar, int progress, boolean fromUser) {
                tipPercent = progress / 100.0;
                calculateTipAndTotalAmount();
            }

            @Override
            public void onStartTrackingTouch(SeekBar seekBar) {

            }

            @Override
            public void onStopTrackingTouch(SeekBar seekBar) {

            }
        });

        randomTip.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Random random = new Random();
                int iRandom = random.nextInt(31);
                tipPercent =  iRandom /100.0;
                percentSeekBar.setProgress(iRandom);
                calculateTipAndTotalAmount();
            }
        });
    }

    private void calculateTipAndTotalAmount(){

        double tipAmount = billAmount*tipPercent;
        double totalAmount = billAmount+tipAmount;

        percentTextView.setText(percentFormat.format(tipPercent));
        tipTextView.setText(currencyFormat.format(tipAmount));
        totalTextView.setText(currencyFormat.format(totalAmount));
    }
}
