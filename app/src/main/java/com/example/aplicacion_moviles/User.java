package com.example.aplicacion_moviles;

import androidx.room.Entity;
import androidx.room.PrimaryKey;

@Entity(tableName = "users")
public class User {
    @PrimaryKey(autoGenerate = true)
    private int id;
    private String email;
    private String password;

    // Campos de datos personales
    private String nombre;
    private String telefono;
    private String direccion;
    private String fechaNacimiento;
    private String genero;
    private String ocupacion;

    // Gustos (10 items)
    private String gusto1, gusto2, gusto3, gusto4, gusto5, gusto6, gusto7, gusto8, gusto9, gusto10;

    // Preferencias (10 items)
    private String pref1, pref2, pref3, pref4, pref5, pref6, pref7, pref8, pref9, pref10;

    // Campos legacy
    private String personalData;
    private String preferences;
    private String hobbies;
    private String comidaFavorita;
    private String deporte;
    private String musica;
    private String notas;

    public User(String email, String password) {
        this.email = email;
        this.password = password;
    }

    // Getters y Setters
    public int getId() { return id; }
    public void setId(int id) { this.id = id; }

    public String getEmail() { return email; }
    public void setEmail(String email) { this.email = email; }

    public String getPassword() { return password; }
    public void setPassword(String password) { this.password = password; }

    public String getNombre() { return nombre; }
    public void setNombre(String nombre) { this.nombre = nombre; }

    public String getTelefono() { return telefono; }
    public void setTelefono(String telefono) { this.telefono = telefono; }

    public String getDireccion() { return direccion; }
    public void setDireccion(String direccion) { this.direccion = direccion; }

    public String getFechaNacimiento() { return fechaNacimiento; }
    public void setFechaNacimiento(String fechaNacimiento) { this.fechaNacimiento = fechaNacimiento; }

    public String getGenero() { return genero; }
    public void setGenero(String genero) { this.genero = genero; }

    public String getOcupacion() { return ocupacion; }
    public void setOcupacion(String ocupacion) { this.ocupacion = ocupacion; }

    // Gustos
    public String getGusto1() { return gusto1; }
    public void setGusto1(String gusto1) { this.gusto1 = gusto1; }
    public String getGusto2() { return gusto2; }
    public void setGusto2(String gusto2) { this.gusto2 = gusto2; }
    public String getGusto3() { return gusto3; }
    public void setGusto3(String gusto3) { this.gusto3 = gusto3; }
    public String getGusto4() { return gusto4; }
    public void setGusto4(String gusto4) { this.gusto4 = gusto4; }
    public String getGusto5() { return gusto5; }
    public void setGusto5(String gusto5) { this.gusto5 = gusto5; }
    public String getGusto6() { return gusto6; }
    public void setGusto6(String gusto6) { this.gusto6 = gusto6; }
    public String getGusto7() { return gusto7; }
    public void setGusto7(String gusto7) { this.gusto7 = gusto7; }
    public String getGusto8() { return gusto8; }
    public void setGusto8(String gusto8) { this.gusto8 = gusto8; }
    public String getGusto9() { return gusto9; }
    public void setGusto9(String gusto9) { this.gusto9 = gusto9; }
    public String getGusto10() { return gusto10; }
    public void setGusto10(String gusto10) { this.gusto10 = gusto10; }

    // Preferencias
    public String getPref1() { return pref1; }
    public void setPref1(String pref1) { this.pref1 = pref1; }
    public String getPref2() { return pref2; }
    public void setPref2(String pref2) { this.pref2 = pref2; }
    public String getPref3() { return pref3; }
    public void setPref3(String pref3) { this.pref3 = pref3; }
    public String getPref4() { return pref4; }
    public void setPref4(String pref4) { this.pref4 = pref4; }
    public String getPref5() { return pref5; }
    public void setPref5(String pref5) { this.pref5 = pref5; }
    public String getPref6() { return pref6; }
    public void setPref6(String pref6) { this.pref6 = pref6; }
    public String getPref7() { return pref7; }
    public void setPref7(String pref7) { this.pref7 = pref7; }
    public String getPref8() { return pref8; }
    public void setPref8(String pref8) { this.pref8 = pref8; }
    public String getPref9() { return pref9; }
    public void setPref9(String pref9) { this.pref9 = pref9; }
    public String getPref10() { return pref10; }
    public void setPref10(String pref10) { this.pref10 = pref10; }

    // Legacy (mantener por compatibilidad con código existente si es necesario)
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
    public String getPersonalData() { return personalData; }
    public void setPersonalData(String personalData) { this.personalData = personalData; }
    public String getPreferences() { return preferences; }
    public void setPreferences(String preferences) { this.preferences = preferences; }
}