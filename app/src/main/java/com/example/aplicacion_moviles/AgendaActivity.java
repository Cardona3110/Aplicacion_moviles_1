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

    // Datos personales
    private EditText etNombre, etTelefono, etEmail, etDireccion, etFechaNacimiento;
    // Preferencias
    private EditText etHobbies, etComidaFavorita, etDeporte, etMusica, etNotas;

    private Button btnSave, btnLoad;
    private ImageButton btnBack;
    private AppDatabase database;
    private int userId;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_agenda);

        database = AppDatabase.getInstance(this);
        userId = getIntent().getIntExtra("user_id", -1);

        // Botón atrás
        btnBack = findViewById(R.id.btnBack);
        btnBack.setOnClickListener(v -> finish());

        // Datos personales
        etNombre          = findViewById(R.id.etNombre);
        etTelefono        = findViewById(R.id.etTelefono);
        etEmail           = findViewById(R.id.etEmail);
        etDireccion       = findViewById(R.id.etDireccion);
        etFechaNacimiento = findViewById(R.id.etFechaNacimiento);

        // Preferencias
        etHobbies        = findViewById(R.id.etHobbies);
        etComidaFavorita = findViewById(R.id.etComidaFavorita);
        etDeporte        = findViewById(R.id.etDeporte);
        etMusica         = findViewById(R.id.etMusica);
        etNotas          = findViewById(R.id.etNotas);

        btnSave = findViewById(R.id.btnSave);
        btnLoad = findViewById(R.id.btnLoad);

        btnSave.setOnClickListener(v -> saveData());
        btnLoad.setOnClickListener(v -> loadData());

        // Carga automática al abrir la pantalla
        loadData();
    }

    private void saveData() {
        // Captura todos los campos
        String nombre          = etNombre.getText().toString().trim();
        String telefono        = etTelefono.getText().toString().trim();
        String emailVal        = etEmail.getText().toString().trim();
        String direccion       = etDireccion.getText().toString().trim();
        String fechaNacimiento = etFechaNacimiento.getText().toString().trim();
        String hobbies         = etHobbies.getText().toString().trim();
        String comidaFavorita  = etComidaFavorita.getText().toString().trim();
        String deporte         = etDeporte.getText().toString().trim();
        String musica          = etMusica.getText().toString().trim();
        String notas           = etNotas.getText().toString().trim();

        AsyncTask.execute(() -> {
            User user = database.userDao().getUserById(userId);
            if (user != null) {
                user.setNombre(nombre);
                user.setTelefono(telefono);
                user.setDireccion(direccion);
                user.setFechaNacimiento(fechaNacimiento);
                user.setHobbies(hobbies);
                user.setComidaFavorita(comidaFavorita);
                user.setDeporte(deporte);
                user.setMusica(musica);
                user.setNotas(notas);
                database.userDao().update(user);

                runOnUiThread(() ->
                        Toast.makeText(AgendaActivity.this,
                                "✅ Datos guardados correctamente", Toast.LENGTH_SHORT).show()
                );
            }
        });
    }

    private void loadData() {
        AsyncTask.execute(() -> {
            User user = database.userDao().getUserById(userId);
            runOnUiThread(() -> {
                if (user != null) {
                    setField(etNombre,          user.getNombre());
                    setField(etTelefono,        user.getTelefono());
                    setField(etDireccion,       user.getDireccion());
                    setField(etFechaNacimiento, user.getFechaNacimiento());
                    setField(etHobbies,         user.getHobbies());
                    setField(etComidaFavorita,  user.getComidaFavorita());
                    setField(etDeporte,         user.getDeporte());
                    setField(etMusica,          user.getMusica());
                    setField(etNotas,           user.getNotas());
                }
            });
        });
    }

    /** Evita mostrar "null" en los campos vacíos */
    private void setField(EditText field, String value) {
        field.setText(value != null ? value : "");
    }
}