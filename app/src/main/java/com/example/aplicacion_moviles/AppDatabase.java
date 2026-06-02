package com.example.aplicacion_moviles;

import androidx.room.Database;
import androidx.room.Room;
import androidx.room.RoomDatabase;
import androidx.room.migration.Migration;
import androidx.sqlite.db.SupportSQLiteDatabase;
import android.content.Context;

@Database(entities = {User.class}, version = 3, exportSchema = false)
public abstract class AppDatabase extends RoomDatabase {
    private static AppDatabase instance;

    public abstract UserDao userDao();

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

    static final Migration MIGRATION_2_3 = new Migration(2, 3) {
        @Override
        public void migrate(SupportSQLiteDatabase database) {
            database.execSQL("ALTER TABLE users ADD COLUMN genero TEXT");
            database.execSQL("ALTER TABLE users ADD COLUMN ocupacion TEXT");
            for (int i = 1; i <= 10; i++) {
                database.execSQL("ALTER TABLE users ADD COLUMN gusto" + i + " TEXT");
                database.execSQL("ALTER TABLE users ADD COLUMN pref" + i + " TEXT");
            }
        }
    };

    public static synchronized AppDatabase getInstance(Context context) {
        if (instance == null) {
            instance = Room.databaseBuilder(
                            context.getApplicationContext(),
                            AppDatabase.class,
                            "app_database")
                    .addMigrations(MIGRATION_1_2, MIGRATION_2_3)
                    .fallbackToDestructiveMigration() // Opcional: si la migración falla, recrea la base de datos
                    .build();
        }
        return instance;
    }
}