package com.cym.sunflower.data;

import android.content.Context;

import com.cym.sunflower.utilities.Constants;
import com.cym.sunflower.works.SeedDatabaseWork;

import androidx.annotation.NonNull;
import androidx.room.Database;
import androidx.room.Room;
import androidx.room.RoomDatabase;
import androidx.room.TypeConverters;
import androidx.sqlite.db.SupportSQLiteDatabase;
import androidx.work.OneTimeWorkRequest;
import androidx.work.WorkManager;
import androidx.work.WorkRequest;

/**
 * The Room database for this app
 */
@Database(entities = {Plant.class, GardenPlanting.class}, version = 1, exportSchema = false)
@TypeConverters({Converters.class})
public abstract class AppDatabase extends RoomDatabase {
    public abstract PlantDao plantDao();

    public abstract GardenPlantingDao gardenPlantingDao();

    private static volatile AppDatabase INSTANCE;

    //Singleton instantiation
    public static AppDatabase getInstance(final Context context) {
        if (INSTANCE == null) {
            synchronized (AppDatabase.class) {
                if (INSTANCE == null) {
                    INSTANCE = Room.databaseBuilder(context.getApplicationContext(), AppDatabase.class, Constants.DATABASE_NAME)
                            .addCallback(new Callback() {
                                @Override
                                public void onCreate(@NonNull SupportSQLiteDatabase db) {
                                    super.onCreate(db);
                                    WorkRequest request = OneTimeWorkRequest.from(SeedDatabaseWork.class);
                                    WorkManager.getInstance().enqueue(request);
                                }
                            })
                            .build();
                }
            }
        }
        return INSTANCE;
    }
}
