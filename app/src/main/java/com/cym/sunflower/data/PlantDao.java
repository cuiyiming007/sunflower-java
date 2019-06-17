package com.cym.sunflower.data;

import java.util.List;

import androidx.lifecycle.LiveData;
import androidx.room.Dao;
import androidx.room.Insert;
import androidx.room.OnConflictStrategy;
import androidx.room.Query;

/**
 * The Data Access Object for the Plant class.
 */
@Dao
public interface PlantDao {
    @Query("SELECT * FROM plants ORDER BY name")
    LiveData<List<Plant>> getPlants();

    @Query("SELECT * FROM plants WHERE grow_zone_number = :growZoneNumber ORDER BY name")
    LiveData<List<Plant>> getPlantsWithGrowZoneNumber(int growZoneNumber);

    @Query("SELECT * FROM plants WHERE id = :plantId")
    LiveData<Plant> getPlant(String plantId);

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    void insertAll(List<Plant> plants);
}
