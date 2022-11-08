package com.evv.calculator.ui;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.view.View;
import android.widget.TextView;

import com.evv.calculator.R;
import com.evv.calculator.model.Calculator;
import com.evv.calculator.model.Operator;
import com.evv.calculator.presenter.CalculatorPresenter;

import java.util.HashMap;
import java.util.Map;

public class MainActivity extends AppCompatActivity implements CalculatorView {

    private TextView calculatorDisplay;
    private CalculatorPresenter presenter;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        if (savedInstanceState!=null) {
            presenter = savedInstanceState.getParcelable("presenter");
        } else {
            presenter = new CalculatorPresenter(this, new Calculator());
        }
        guiSetup ();
    }

    private void guiSetup() {
        calculatorDisplay = findViewById(R.id.calculator_display);
        ButtonListenerSetUp();
    }

    private void ButtonListenerSetUp() {
        //numbers buttons setup
        Map <Integer, Integer> digits = new HashMap<>();
        digits.put(R.id.key_1, 1);
        digits.put(R.id.key_2, 2);
        digits.put(R.id.key_3, 3);
        digits.put(R.id.key_4, 4);
        digits.put(R.id.key_5, 5);
        digits.put(R.id.key_6, 6);
        digits.put(R.id.key_7, 7);
        digits.put(R.id.key_8, 8);
        digits.put(R.id.key_9, 9);
        digits.put(R.id.key_0, 0);

        View.OnClickListener digitClickListener = new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                presenter.onDigitPressed (digits.get(view.getId()));
            }
        };
        findViewById(R.id.key_1).setOnClickListener(digitClickListener);
        findViewById(R.id.key_2).setOnClickListener(digitClickListener);
        findViewById(R.id.key_3).setOnClickListener(digitClickListener);
        findViewById(R.id.key_4).setOnClickListener(digitClickListener);
        findViewById(R.id.key_5).setOnClickListener(digitClickListener);
        findViewById(R.id.key_6).setOnClickListener(digitClickListener);
        findViewById(R.id.key_7).setOnClickListener(digitClickListener);
        findViewById(R.id.key_8).setOnClickListener(digitClickListener);
        findViewById(R.id.key_9).setOnClickListener(digitClickListener);
        findViewById(R.id.key_0).setOnClickListener(digitClickListener);

        //operators buttons setup
        Map <Integer, Operator> operators = new HashMap<>();
        operators.put(R.id.key_plus, Operator.ADDITION);
        operators.put(R.id.key_minus, Operator.SUBTRACTION);
        operators.put(R.id.key_multiplication, Operator.MULTIPLICATION);
        operators.put(R.id.key_divide, Operator.DIVISION);
        View.OnClickListener operatorOnClickListener =  new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                presenter.onOperatorPressed(operators.get(view.getId()));
            }
        };
        findViewById(R.id.key_plus).setOnClickListener(operatorOnClickListener);
        findViewById(R.id.key_minus).setOnClickListener(operatorOnClickListener);
        findViewById(R.id.key_multiplication).setOnClickListener(operatorOnClickListener);
        findViewById(R.id.key_divide).setOnClickListener(operatorOnClickListener);
        findViewById(R.id.key_percent).setOnClickListener(operatorOnClickListener);

        //dot button setup
        findViewById(R.id.key_comma).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                presenter.onDotPressed();
            }
        });

        //command buttons setup

        findViewById (R.id.key_ac).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                presenter.allClean ();
            }
        });

        findViewById(R.id.key_del).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                presenter.deleteButtonClicked();
            }
        });
    }

    @Override
    public void showResult(String result) {
        calculatorDisplay.setText(result);

    }


    @Override
    protected void onSaveInstanceState(@NonNull Bundle outState) {
        super.onSaveInstanceState(outState);
        outState.putParcelable("presenter", presenter);
    }
}