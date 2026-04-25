package com.example.aplicacion_moviles;

import android.os.Bundle;
import android.widget.Button;
import android.widget.ImageButton;
import android.widget.TextView;
import android.view.View;
import androidx.appcompat.app.AppCompatActivity;

public class CalculatorActivity extends AppCompatActivity {
    private TextView tvResult, tvOperation;
    private String currentNumber = "";
    private String operation = "";
    private double firstNumber = 0;
    private double secondNumber = 0;
    private boolean isOperationSelected = false;
    private boolean isResultDisplayed = false;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_calculadora);

        // Botón atrás
        ImageButton btnBack = findViewById(R.id.btnBack);
        btnBack.setOnClickListener(v -> finish());

        tvResult    = findViewById(R.id.tvResult);
        tvOperation = findViewById(R.id.tvOperation);

        setupNumberButtons();
        setupOperationButtons();
        setupSpecialButtons();
    }

    private void setupNumberButtons() {
        int[] numberButtons = {
                R.id.btn0, R.id.btn1, R.id.btn2, R.id.btn3, R.id.btn4,
                R.id.btn5, R.id.btn6, R.id.btn7, R.id.btn8, R.id.btn9
        };

        View.OnClickListener listener = v -> {
            Button button = (Button) v;
            if (isResultDisplayed) {
                currentNumber = "";
                isResultDisplayed = false;
            }
            currentNumber += button.getText().toString();
            tvResult.setText(currentNumber);
        };

        for (int id : numberButtons) {
            findViewById(id).setOnClickListener(listener);
        }

        findViewById(R.id.btnDecimal).setOnClickListener(v -> {
            if (!currentNumber.contains(".")) {
                if (currentNumber.isEmpty()) currentNumber = "0";
                currentNumber += ".";
                tvResult.setText(currentNumber);
            }
        });
    }

    private void setupOperationButtons() {
        View.OnClickListener operationListener = v -> {
            Button button = (Button) v;
            if (!currentNumber.isEmpty()) {
                firstNumber = Double.parseDouble(currentNumber);
                operation = button.getText().toString();
                tvOperation.setText(currentNumber + " " + operation);
                currentNumber = "";
                isOperationSelected = true;
                isResultDisplayed = false;
            }
        };

        findViewById(R.id.btnAdd).setOnClickListener(operationListener);
        findViewById(R.id.btnSubtract).setOnClickListener(operationListener);
        findViewById(R.id.btnMultiply).setOnClickListener(operationListener);
        findViewById(R.id.btnDivide).setOnClickListener(operationListener);
    }

    private void setupSpecialButtons() {
        findViewById(R.id.btnEquals).setOnClickListener(v -> {
            if (isOperationSelected && !currentNumber.isEmpty()) {
                secondNumber = Double.parseDouble(currentNumber);
                double result = 0;

                switch (operation) {
                    case "+": result = firstNumber + secondNumber; break;
                    case "−": result = firstNumber - secondNumber; break;
                    case "×": result = firstNumber * secondNumber; break;
                    case "÷":
                        if (secondNumber != 0) {
                            result = firstNumber / secondNumber;
                        } else {
                            tvResult.setText("Error");
                            return;
                        }
                        break;
                }

                tvOperation.setText(firstNumber + " " + operation + " " + secondNumber + " =");
                tvResult.setText(formatResult(result));
                currentNumber = String.valueOf(result);
                isOperationSelected = false;
                isResultDisplayed = true;
            }
        });

        findViewById(R.id.btnClear).setOnClickListener(v -> {
            currentNumber = "";
            operation = "";
            firstNumber = 0;
            secondNumber = 0;
            isOperationSelected = false;
            isResultDisplayed = false;
            tvResult.setText("0");
            tvOperation.setText("");
        });

        findViewById(R.id.btnPercentage).setOnClickListener(v -> {
            if (!currentNumber.isEmpty()) {
                double number = Double.parseDouble(currentNumber);
                double result = number / 100;
                tvResult.setText(formatResult(result));
                currentNumber = String.valueOf(result);
                isResultDisplayed = true;
            }
        });

        findViewById(R.id.btnToggleSign).setOnClickListener(v -> {
            if (!currentNumber.isEmpty()) {
                double number = Double.parseDouble(currentNumber);
                number = -number;
                currentNumber = formatResult(number);
                tvResult.setText(currentNumber);
            }
        });
    }

    private String formatResult(double result) {
        if (result == (long) result) {
            return String.format("%d", (long) result);
        } else {
            return String.valueOf(result);
        }
    }
}