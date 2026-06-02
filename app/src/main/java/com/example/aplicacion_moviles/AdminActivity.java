package com.example.aplicacion_moviles;

import android.os.AsyncTask;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageButton;
import android.widget.TableLayout;
import android.widget.TableRow;
import android.widget.TextView;
import android.widget.Toast;
import androidx.appcompat.app.AppCompatActivity;
import java.util.List;

public class AdminActivity extends AppCompatActivity {
    private EditText etEmail, etPassword;
    private Button btnCreateUser;
    private ImageButton btnBack;
    private TableLayout tableUsers;
    private AppDatabase database;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_admin);

        database = AppDatabase.getInstance(this);

        btnBack = findViewById(R.id.btnBack);
        btnBack.setOnClickListener(v -> finish());

        etEmail = findViewById(R.id.etEmail);
        etPassword = findViewById(R.id.etPassword);
        btnCreateUser = findViewById(R.id.btnCreateUser);
        tableUsers = findViewById(R.id.tableUsers);

        btnCreateUser.setOnClickListener(v -> createUser());

        // Observar cambios en la base de datos para actualizar la tabla
        database.userDao().getAllUsers().observe(this, this::updateUserTable);
    }

    private void updateUserTable(List<User> users) {
        // Limpiar filas existentes excepto el encabezado (índice 0)
        int childCount = tableUsers.getChildCount();
        if (childCount > 1) {
            tableUsers.removeViews(1, childCount - 1);
        }

        for (User user : users) {
            TableRow row = new TableRow(this);
            row.setPadding(8, 8, 8, 8);

            TextView tvId = createTextView(String.valueOf(user.getId()));
            TextView tvEmail = createTextView(user.getEmail() != null ? user.getEmail() : "-");
            TextView tvPassword = createTextView(user.getPassword() != null ? user.getPassword() : "-");
            TextView tvNombre = createTextView(user.getNombre() != null ? user.getNombre() : "N/A");

            // Añadir márgenes para separar columnas
            TableRow.LayoutParams params = new TableRow.LayoutParams();
            params.setMargins(10, 0, 10, 0);
            
            tvEmail.setLayoutParams(params);
            tvPassword.setLayoutParams(params);
            tvNombre.setLayoutParams(params);

            row.addView(tvId);
            row.addView(tvEmail);
            row.addView(tvPassword);
            row.addView(tvNombre);

            tableUsers.addView(row);
        }
    }

    private TextView createTextView(String text) {
        TextView tv = new TextView(this);
        tv.setText(text);
        tv.setPadding(5, 5, 5, 5);
        tv.setTextColor(getResources().getColor(android.R.color.black));
        return tv;
    }

    private void createUser() {
        String email = etEmail.getText().toString().trim();
        String password = etPassword.getText().toString().trim();

        if (email.isEmpty() || password.isEmpty()) {
            Toast.makeText(this, "Complete todos los campos", Toast.LENGTH_SHORT).show();
            return;
        }

        User user = new User(email, password);

        AsyncTask.execute(() -> {
            database.userDao().insert(user);
            runOnUiThread(() -> {
                Toast.makeText(AdminActivity.this,
                        "✅ Usuario creado exitosamente", Toast.LENGTH_SHORT).show();
                etEmail.setText("");
                etPassword.setText("");
            });
        });
    }
}