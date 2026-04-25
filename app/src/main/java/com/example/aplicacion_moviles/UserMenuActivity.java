package com.example.aplicacion_moviles;

import android.content.Intent;
import android.os.Bundle;
import android.widget.Button;
import android.widget.ImageButton;
import androidx.appcompat.app.AppCompatActivity;

public class UserMenuActivity extends AppCompatActivity {
    private Button btnAgenda, btnCalculator;
    private ImageButton btnBack;
    private int userId;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_user_menu);

        userId = getIntent().getIntExtra("user_id", -1);

        btnBack = findViewById(R.id.btnBack);
        btnBack.setOnClickListener(v -> finish());

        btnAgenda = findViewById(R.id.btnAgenda);
        btnCalculator = findViewById(R.id.btnCalculator);

        btnAgenda.setOnClickListener(v -> {
            Intent intent = new Intent(UserMenuActivity.this, AgendaActivity.class);
            intent.putExtra("user_id", userId);
            startActivity(intent);
        });

        btnCalculator.setOnClickListener(v -> {
            Intent intent = new Intent(UserMenuActivity.this, CalculatorActivity.class);
            startActivity(intent);
        });
    }
}