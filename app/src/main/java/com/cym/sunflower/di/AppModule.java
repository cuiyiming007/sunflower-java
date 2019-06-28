package com.cym.sunflower.di;

import android.app.Application;

import androidx.annotation.NonNull;
import androidx.room.Room;
import androidx.room.RoomDatabase;
import androidx.sqlite.db.SupportSQLiteDatabase;
import androidx.work.OneTimeWorkRequest;
import androidx.work.WorkManager;
import androidx.work.WorkRequest;

import com.cym.sunflower.data.AppDatabase;
import com.cym.sunflower.data.GardenPlantingDao;
import com.cym.sunflower.data.PlantDao;
import com.cym.sunflower.utilities.Constants;
import com.cym.sunflower.works.SeedDatabaseWork;
import com.google.gson.Gson;

import javax.inject.Singleton;

import dagger.Module;
import dagger.Provides;

@Module
public class AppModule {
    @Singleton
    @Provides
    static AppDatabase provideAppDatabase(Application application) {
        return Room.databaseBuilder(application, AppDatabase.class, Constants.DATABASE_NAME)
                .addCallback(new RoomDatabase.Callback() {
                    @Override
                    public void onCreate(@NonNull SupportSQLiteDatabase db) {
                        super.onCreate(db);
                        WorkRequest request = OneTimeWorkRequest.from(SeedDatabaseWork.class);
                        WorkManager.getInstance().enqueue(request);
                    }
                })
                .build();
    }

    @Singleton
    @Provides
    static GardenPlantingDao provideGardenPlantingDao(AppDatabase db) {
        return db.gardenPlantingDao();
    }

    @Singleton
    @Provides
    static PlantDao providePlantDao(AppDatabase db) {
        return db.plantDao();
    }


    @Singleton
    @Provides
    static Gson provideGson() {
        return new Gson();
    }
}
