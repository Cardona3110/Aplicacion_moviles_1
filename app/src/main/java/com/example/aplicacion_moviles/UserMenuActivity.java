package com.example.aplicacion_moviles;


import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import androidx.appcompat.app.AppCompatActivity;

public class UserMenuActivity extends AppCompatActivity {
    private Button btnAgenda, btnCalculator;
    private int userId;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_user_menu);

        userId = getIntent().getIntExtra("user_id", -1);

        btnAgenda = findViewById(R.id.btnAgenda);
        btnCalculator = findViewById(R.id.btnCalculator);

        btnAgenda.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(UserMenuActivity.this, AgendaActivity.class);
                intent.putExtra("user_id", userId);
                startActivity(intent);
            }
        });

        btnCalculator.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(UserMenuActivity.this, CalculatorActivity.class);
                startActivity(intent);
            }
        });
    }
}