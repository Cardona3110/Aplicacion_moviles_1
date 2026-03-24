package com.example.aplicacion_moviles;
import android.os.Bundle;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;

import androidx.activity.EdgeToEdge;
import androidx.appcompat.app.AppCompatActivity;
import androidx.core.graphics.Insets;
import androidx.core.view.ViewCompat;
import androidx.core.view.WindowInsetsCompat;

public class Calculadora extends AppCompatActivity {

    EditText input_1, input_2;
    TextView txtResultado;
    Button btn_suma, btn_resta, btn_divi, btn_mult, btn_borrar;
    int num_1, num_2, result;

    @Override
    protected void onCreate(Bundle savedInstanceState) {


        super.onCreate(savedInstanceState);
        EdgeToEdge.enable(this);
        setContentView(R.layout.activity_calculadora);
        txtResultado = findViewById(R.id.Resultado);

        btn_suma = findViewById(R.id.btn_suma);
        btn_suma.setOnClickListener(view -> {
            input_1 = findViewById(R.id.Numero_1);
            String texto_1 = input_1.getText().toString();
            input_2 = findViewById(R.id.Numero_2);
            String texto_2 = input_2.getText().toString();
            if (!texto_1.isEmpty() || !texto_2.isEmpty()) {
                num_1 = Integer.parseInt(texto_1);
                num_2 = Integer.parseInt(texto_2);
                result = num_1 + num_2;
                txtResultado.setText(String.valueOf(result));
            }
        });

        btn_resta = findViewById(R.id.btn_resta);
        btn_resta.setOnClickListener(view -> {
            input_1 = findViewById(R.id.Numero_1);
            String texto_1 = input_1.getText().toString();
            input_2 = findViewById(R.id.Numero_2);
            String texto_2 = input_2.getText().toString();
            if (!texto_1.isEmpty() || !texto_2.isEmpty()) {
                num_1 = Integer.parseInt(texto_1);
                num_2 = Integer.parseInt(texto_2);
                result = num_1 - num_2;
                txtResultado.setText(String.valueOf(result));
            }
        });
        btn_mult = findViewById(R.id.btn_mutiplicacion);
        btn_mult.setOnClickListener(view -> {
            input_1 = findViewById(R.id.Numero_1);
            String texto_1 = input_1.getText().toString();
            input_2 = findViewById(R.id.Numero_2);
            String texto_2 = input_2.getText().toString();
            if (!texto_1.isEmpty() || !texto_2.isEmpty()) {
                num_1 = Integer.parseInt(texto_1);
                num_2 = Integer.parseInt(texto_2);
                result = num_1 * num_2;
                txtResultado.setText(String.valueOf(result));
            }
        });

        btn_divi = findViewById(R.id.btn_division);
        btn_divi.setOnClickListener(view -> {
            input_1 = findViewById(R.id.Numero_1);
            String texto_1 = input_1.getText().toString();
            input_2 = findViewById(R.id.Numero_2);
            String texto_2 = input_2.getText().toString();
            if(!texto_1.isEmpty() || !texto_2.isEmpty()){
                num_1 = Integer.parseInt(texto_1);
                num_2 = Integer.parseInt(texto_2);
                result = num_1/num_2;
                txtResultado.setText(String.valueOf(result));
            }
        });

        btn_borrar = findViewById(R.id.btn_borrar);
        btn_borrar.setOnClickListener(v -> {
            input_1.setText("");
            input_2.setText("");
            txtResultado.setText("");
        });

        ViewCompat.setOnApplyWindowInsetsListener(findViewById(R.id.main), (v, insets) -> {
            Insets systemBars = insets.getInsets(WindowInsetsCompat.Type.systemBars());
            v.setPadding(systemBars.left, systemBars.top, systemBars.right, systemBars.bottom);
            return insets;
        });
    }
}
