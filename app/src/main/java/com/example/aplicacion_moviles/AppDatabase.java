package com.example.aplicacion_moviles;

import androidx.room.Database;
import androidx.room.Room;
import androidx.room.RoomDatabase;
import androidx.room.migration.Migration;
import androidx.sqlite.db.SupportSQLiteDatabase;
import android.content.Context;

// IMPORTANTE: Se sube a version=2 porque se añadieron columnas nuevas a la tabla users.
// La migración agrega las columnas sin borrar datos existentes.
@Database(entities = {User.class}, version = 2, exportSchema = false)
public abstract class AppDatabase extends RoomDatabase {
    private static AppDatabase instance;

    public abstract UserDao userDao();

    // Migración de v1 a v2: agrega las nuevas columnas de la agenda
    static final Migration MIGRATION_1_2 = new Migration(1, 2) {
        @Override
        public void migrate(SupportSQLiteDatabase database) {
            database.execSQL("ALTER TABLE users ADD COLUMN nombre TEXT");
            database.execSQL("ALTER TABLE users ADD COLUMN telefono TEXT");
            database.execSQL("ALTER TABLE users ADD COLUMN direccion TEXT");
            database.execSQL("ALTER TABLE users ADD COLUMN fechaNacimiento TEXT");
            database.execSQL("ALTER TABLE users ADD COLUMN hobbies TEXT");
            database.execSQL("ALTER TABLE users ADD COLUMN comidaFavorita TEXT");
            database.execSQL("ALTER TABLE users ADD COLUMN deporte TEXT");
            database.execSQL("ALTER TABLE users ADD COLUMN musica TEXT");
            database.execSQL("ALTER TABLE users ADD COLUMN notas TEXT");
        }
    };

    public static synchronized AppDatabase getInstance(Context context) {
        if (instance == null) {
            instance = Room.databaseBuilder(
                            context.getApplicationContext(),
                            AppDatabase.class,
                            "app_database")
                    .addMigrations(MIGRATION_1_2)   // migración limpia, sin borrar datos
                    .build();
        }
        return instance;
    }
}