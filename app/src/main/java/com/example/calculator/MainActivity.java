package com.example.calculator;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;

import java.util.Objects;

public class MainActivity extends AppCompatActivity {

    TextView resultField;
    EditText numberField;
    TextView operationField;

    Double operand = null;
    String lustOperation = "=";

    static final String SAVEOPERATION = "SAVEOPERATION";
    static final String SAVEOPERAND = "SAVEOPERAND";
    static final String SAVERESULTFIELD = "SAVERESULTFIELD";


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        initGui();
    }

    private void initGui() {
        resultField = findViewById(R.id.resultField);
        resultField.setText("");
        numberField = findViewById(R.id.numberField);
        operationField = findViewById(R.id.operationField);
    }

    @Override
    protected void onSaveInstanceState(@NonNull Bundle outState) {
        super.onSaveInstanceState(outState);
        if (operand != null) {
            outState.putDouble(SAVEOPERAND, operand);
        }
        outState.putString(SAVEOPERATION, lustOperation);
        outState.putString(SAVERESULTFIELD, resultField.getText().toString());
    }

    @Override
    protected void onRestoreInstanceState(@NonNull Bundle savedInstanceState) {
        super.onRestoreInstanceState(savedInstanceState);
        operand = savedInstanceState.getDouble(SAVEOPERAND);
        lustOperation = savedInstanceState.getString(SAVEOPERATION);
        resultField.setText(savedInstanceState.getString(SAVERESULTFIELD));
    }

//    public void onNumberClick(View view) {
//        Button button = (Button) view;
//        numberField.append(button.getText());
//        if (lustOperation.equals("=") && operand != null) {
//            operand = null;
//        }
//    }

//    public void onOperationClick(View view) {
//        String operator = ((Button) view).getText().toString();
//        String number = numberField.getText().toString();
//        if (number.length() > 0) {
//            number.replace(',', '.');
//            try {
//                performOperation(Double.valueOf(number), operator);
//            } catch (NumberFormatException ex) {
//                numberField.setText("");
//            }
//        }
//        lustOperation = operator;
//        operationField.setText(lustOperation);
//    }

    private void performOperation(Double number, String operator) {
        if (operand == null) {
            operand = number;
        } else {
            if (lustOperation.equals("=")) {
                lustOperation = operator;
            }
            switch (lustOperation) {
                case "=":
                    operand = number;
                    break;
                case "/":
                    if (number == 0) {
                        operand = 0.0;
                    } else {
                        operand /= number;
                    }
                    break;
                case "*":
                    operand *= number;
                    break;
                case "+":
                    operand += number;
                    break;
                case "-":
                    operand -= number;
                    break;
            }
        }
        resultField.setText(operand.toString().replace('.', ','));
        numberField.setText("");
    }
}