package com.example.aplicacion_moviles;

import android.os.AsyncTask;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageButton;
import android.widget.Toast;
import androidx.appcompat.app.AppCompatActivity;

public class AgendaActivity extends AppCompatActivity {

    private EditText etNombre, etTelefono, etDireccion, etFechaNacimiento, etGenero, etOcupacion;
    private EditText[] etGustos = new EditText[10];
    private EditText[] etPrefs = new EditText[10];

    private Button btnSave;
    private ImageButton btnBack;
    private AppDatabase database;
    private int userId;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_agenda);

        database = AppDatabase.getInstance(this);
        userId = getIntent().getIntExtra("user_id", -1);

        btnBack = findViewById(R.id.btnBack);
        btnBack.setOnClickListener(v -> finish());

        // Datos personales
        etNombre = findViewById(R.id.etNombre);
        etTelefono = findViewById(R.id.etTelefono);
        etDireccion = findViewById(R.id.etDireccion);
        etFechaNacimiento = findViewById(R.id.etFechaNacimiento);
        etGenero = findViewById(R.id.etGenero);
        etOcupacion = findViewById(R.id.etOcupacion);

        // Gustos
        for (int i = 0; i < 10; i++) {
            int resId = getResources().getIdentifier("etGusto" + (i + 1), "id", getPackageName());
            etGustos[i] = findViewById(resId);
        }

        // Preferencias
        for (int i = 0; i < 10; i++) {
            int resId = getResources().getIdentifier("etPref" + (i + 1), "id", getPackageName());
            etPrefs[i] = findViewById(resId);
        }

        btnSave = findViewById(R.id.btnSave);
        btnSave.setOnClickListener(v -> saveData());

        loadData();
    }

    private void saveData() {
        AsyncTask.execute(() -> {
            User user = database.userDao().getUserById(userId);
            if (user != null) {
                user.setNombre(etNombre.getText().toString());
                user.setTelefono(etTelefono.getText().toString());
                user.setDireccion(etDireccion.getText().toString());
                user.setFechaNacimiento(etFechaNacimiento.getText().toString());
                user.setGenero(etGenero.getText().toString());
                user.setOcupacion(etOcupacion.getText().toString());

                user.setGusto1(etGustos[0].getText().toString());
                user.setGusto2(etGustos[1].getText().toString());
                user.setGusto3(etGustos[2].getText().toString());
                user.setGusto4(etGustos[3].getText().toString());
                user.setGusto5(etGustos[4].getText().toString());
                user.setGusto6(etGustos[5].getText().toString());
                user.setGusto7(etGustos[6].getText().toString());
                user.setGusto8(etGustos[7].getText().toString());
                user.setGusto9(etGustos[8].getText().toString());
                user.setGusto10(etGustos[9].getText().toString());

                user.setPref1(etPrefs[0].getText().toString());
                user.setPref2(etPrefs[1].getText().toString());
                user.setPref3(etPrefs[2].getText().toString());
                user.setPref4(etPrefs[3].getText().toString());
                user.setPref5(etPrefs[4].getText().toString());
                user.setPref6(etPrefs[5].getText().toString());
                user.setPref7(etPrefs[6].getText().toString());
                user.setPref8(etPrefs[7].getText().toString());
                user.setPref9(etPrefs[8].getText().toString());
                user.setPref10(etPrefs[9].getText().toString());

                database.userDao().update(user);
                runOnUiThread(() -> Toast.makeText(this, "✅ Guardado", Toast.LENGTH_SHORT).show());
            }
        });
    }

    private void loadData() {
        AsyncTask.execute(() -> {
            User user = database.userDao().getUserById(userId);
            if (user != null) {
                runOnUiThread(() -> {
                    etNombre.setText(user.getNombre());
                    etTelefono.setText(user.getTelefono());
                    etDireccion.setText(user.getDireccion());
                    etFechaNacimiento.setText(user.getFechaNacimiento());
                    etGenero.setText(user.getGenero());
                    etOcupacion.setText(user.getOcupacion());

                    etGustos[0].setText(user.getGusto1());
                    etGustos[1].setText(user.getGusto2());
                    etGustos[2].setText(user.getGusto3());
                    etGustos[3].setText(user.getGusto4());
                    etGustos[4].setText(user.getGusto5());
                    etGustos[5].setText(user.getGusto6());
                    etGustos[6].setText(user.getGusto7());
                    etGustos[7].setText(user.getGusto8());
                    etGustos[8].setText(user.getGusto9());
                    etGustos[9].setText(user.getGusto10());

                    etPrefs[0].setText(user.getPref1());
                    etPrefs[1].setText(user.getPref2());
                    etPrefs[2].setText(user.getPref3());
                    etPrefs[3].setText(user.getPref4());
                    etPrefs[4].setText(user.getPref5());
                    etPrefs[5].setText(user.getPref6());
                    etPrefs[6].setText(user.getPref7());
                    etPrefs[7].setText(user.getPref8());
                    etPrefs[8].setText(user.getPref9());
                    etPrefs[10-1].setText(user.getPref10());
                });
            }
        });
    }
}