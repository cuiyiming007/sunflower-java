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
import com.cym.sunflower.data.GardenPlantingRepository;
import com.cym.sunflower.data.PlantDao;
import com.cym.sunflower.data.PlantRepository;
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
    AppDatabase provideAppDatabase(Application application) {
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
    GardenPlantingDao provideGardenPlantingDao(AppDatabase db) {
        return db.gardenPlantingDao();
    }

    @Singleton
    @Provides
    PlantDao providePlantDao(AppDatabase db) {
        return db.plantDao();
    }

    @Singleton
    @Provides
    GardenPlantingRepository provideGardenPlantingRepository(GardenPlantingDao gardenPlantingDao) {
        return new GardenPlantingRepository(gardenPlantingDao);
    }

    @Singleton
    @Provides
    PlantRepository providePlantRepository(PlantDao plantDao) {
        return new PlantRepository(plantDao);
    }

    @Singleton
    @Provides
    Gson provideGson() {
        return new Gson();
    }
}
