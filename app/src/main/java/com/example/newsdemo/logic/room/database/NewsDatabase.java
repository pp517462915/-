package com.example.newsdemo.logic.room.database;

import androidx.annotation.NonNull;
import androidx.room.Database;
import androidx.room.Room;
import androidx.room.RoomDatabase;
import androidx.room.migration.Migration;
import androidx.sqlite.db.SupportSQLiteDatabase;

import com.example.newsdemo.NewsDemoApplication;
import com.example.newsdemo.logic.room.dao.NewsChannalDao;
import com.example.newsdemo.logic.room.dao.NewsDao;
import com.example.newsdemo.logic.room.entity.News;
import com.example.newsdemo.logic.room.entity.NewsChannal;

@Database(entities = {News.class, NewsChannal.class}, version = 3)
public abstract class NewsDatabase extends RoomDatabase {

    private static NewsDatabase db;

    public abstract NewsDao newsDao();
    public abstract NewsChannalDao newsChannalDao();

    // 版本1升级到版本2,News表中的isCollet, isDelete属性
    private static Migration MIGRATION_2_3 = new Migration(2,3){
        @Override
        public void migrate(@NonNull SupportSQLiteDatabase database) {
            database.execSQL("ALTER TABLE news ADD COLUMN is_delete int NOT NULL DEFAULT 0");
            database.execSQL("ALTER TABLE news ADD COLUMN is_collect int NOT NULL DEFAULT 0");
        }
    };

    public static NewsDatabase getDatabase() {
        if (db == null) {
            db = Room.databaseBuilder(NewsDemoApplication.getContext(), NewsDatabase.class, "new_db")
                    .allowMainThreadQueries()
                    .fallbackToDestructiveMigration()
                    .addMigrations(MIGRATION_2_3)
                    .build();
        }
        return db;
    }
}
