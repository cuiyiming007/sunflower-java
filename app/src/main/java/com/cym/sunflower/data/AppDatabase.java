package com.cym.sunflower.data;

import androidx.room.Database;
import androidx.room.RoomDatabase;
import androidx.room.TypeConverters;

/**
 * The Room database for this app
 */
@Database(entities = {Plant.class, GardenPlanting.class}, version = 1, exportSchema = false)
@TypeConverters({Converters.class})
public abstract class AppDatabase extends RoomDatabase {
    public abstract PlantDao plantDao();

    public abstract GardenPlantingDao gardenPlantingDao();
}
