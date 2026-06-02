package com.example.aplicacion_moviles;

import android.os.Bundle;
import android.widget.Button;
import android.widget.ImageButton;
import android.widget.TextView;
import android.view.View;
import androidx.appcompat.app.AlertDialog;
import androidx.appcompat.app.AppCompatActivity;
import java.util.ArrayList;
import java.util.List;

public class CalculatorActivity extends AppCompatActivity {
    private TextView tvResult, tvOperation;
    private String currentNumber = "";
    private String operation = "";
    private double firstNumber = 0;
    private double secondNumber = 0;
    private boolean isOperationSelected = false;
    private boolean isResultDisplayed = false;
    
    // Lista para guardar el historial de la sesión
    private List<String> historyList = new ArrayList<>();

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_calculadora);

        // Botón atrás
        ImageButton btnBack = findViewById(R.id.btnBack);
        btnBack.setOnClickListener(v -> finish());

        tvResult    = findViewById(R.id.tvResult);
        tvOperation = findViewById(R.id.tvOperation);

        Button btnHistory = findViewById(R.id.btnHistory);
        btnHistory.setOnClickListener(v -> showHistory());

        setupNumberButtons();
        setupOperationButtons();
        setupSpecialButtons();
    }

    private void showHistory() {
        if (historyList.isEmpty()) {
            ToastHelper.show(this, "El historial está vacío");
            return;
        }

        StringBuilder sb = new StringBuilder();
        for (String item : historyList) {
            sb.append(item).append("\n\n");
        }

        new AlertDialog.Builder(this)
                .setTitle("Historial de Operaciones")
                .setMessage(sb.toString())
                .setPositiveButton("Cerrar", null)
                .setNeutralButton("Limpiar", (dialog, which) -> historyList.clear())
                .show();
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

                String fullOperation = formatResult(firstNumber) + " " + operation + " " + formatResult(secondNumber) + " = " + formatResult(result);
                tvOperation.setText(fullOperation);
                tvResult.setText(formatResult(result));
                
                // Guardar en el historial
                historyList.add(fullOperation);

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

// Clase auxiliar simple para Toasts o podrías usar Toast directamente
class ToastHelper {
    static void show(android.content.Context context, String message) {
        android.widget.Toast.makeText(context, message, android.widget.Toast.LENGTH_SHORT).show();
    }
}