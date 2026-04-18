package com.example.aplicacion_moviles;

import android.os.AsyncTask;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;
import androidx.appcompat.app.AppCompatActivity;

public class AgendaActivity extends AppCompatActivity {
    private EditText etPersonalData, etPreferences;
    private Button btnSave, btnLoad;
    private AppDatabase database;
    private int userId;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_agenda);

        database = AppDatabase.getInstance(this);
        userId = getIntent().getIntExtra("user_id", -1);

        etPersonalData = findViewById(R.id.etPersonalData);
        etPreferences = findViewById(R.id.etPreferences);
        btnSave = findViewById(R.id.btnSave);
        btnLoad = findViewById(R.id.btnLoad);

        btnSave.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                saveData();
            }
        });

        btnLoad.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                loadData();
            }
        });
    }

    private void saveData() {
        String personalData = etPersonalData.getText().toString();
        String preferences = etPreferences.getText().toString();

        AsyncTask.execute(new Runnable() {
            @Override
            public void run() {
                User user = database.userDao().getUserById(userId);
                user.setPersonalData(personalData);
                user.setPreferences(preferences);
                database.userDao().update(user);

                runOnUiThread(new Runnable() {
                    @Override
                    public void run() {
                        Toast.makeText(AgendaActivity.this, "Datos guardados", Toast.LENGTH_SHORT).show();
                    }
                });
            }
        });
    }

    private void loadData() {
        AsyncTask.execute(new Runnable() {
            @Override
            public void run() {
                User user = database.userDao().getUserById(userId);
                runOnUiThread(new Runnable() {
                    @Override
                    public void run() {
                        etPersonalData.setText(user.getPersonalData());
                        etPreferences.setText(user.getPreferences());
                    }
                });
            }
        });
    }
}