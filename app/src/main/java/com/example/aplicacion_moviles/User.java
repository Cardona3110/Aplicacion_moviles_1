package com.example.aplicacion_moviles;

import androidx.room.Entity;
import androidx.room.PrimaryKey;

@Entity(tableName = "users")
public class User {
    @PrimaryKey(autoGenerate = true)
    private int id;
    private String email;
    private String password;

    // Campos de datos personales (agenda)
    private String nombre;
    private String telefono;
    private String direccion;
    private String fechaNacimiento;

    // Campos de preferencias (agenda)
    private String hobbies;
    private String comidaFavorita;
    private String deporte;
    private String musica;
    private String notas;

    // Campos legacy (se mantienen por compatibilidad)
    private String personalData;
    private String preferences;

    // Constructor básico (para crear usuario desde admin)
    public User(String email, String password) {
        this.email = email;
        this.password = password;
    }

    // ── Getters y Setters ──────────────────────────────────────────────────────

    public int getId() { return id; }
    public void setId(int id) { this.id = id; }

    public String getEmail() { return email; }
    public void setEmail(String email) { this.email = email; }

    public String getPassword() { return password; }
    public void setPassword(String password) { this.password = password; }

    // Datos personales
    public String getNombre() { return nombre; }
    public void setNombre(String nombre) { this.nombre = nombre; }

    public String getTelefono() { return telefono; }
    public void setTelefono(String telefono) { this.telefono = telefono; }

    public String getDireccion() { return direccion; }
    public void setDireccion(String direccion) { this.direccion = direccion; }

    public String getFechaNacimiento() { return fechaNacimiento; }
    public void setFechaNacimiento(String fechaNacimiento) { this.fechaNacimiento = fechaNacimiento; }

    // Preferencias
    public String getHobbies() { return hobbies; }
    public void setHobbies(String hobbies) { this.hobbies = hobbies; }

    public String getComidaFavorita() { return comidaFavorita; }
    public void setComidaFavorita(String comidaFavorita) { this.comidaFavorita = comidaFavorita; }

    public String getDeporte() { return deporte; }
    public void setDeporte(String deporte) { this.deporte = deporte; }

    public String getMusica() { return musica; }
    public void setMusica(String musica) { this.musica = musica; }

    public String getNotas() { return notas; }
    public void setNotas(String notas) { this.notas = notas; }

    // Legacy
    public String getPersonalData() { return personalData; }
    public void setPersonalData(String personalData) { this.personalData = personalData; }

    public String getPreferences() { return preferences; }
    public void setPreferences(String preferences) { this.preferences = preferences; }
}