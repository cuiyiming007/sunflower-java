package com.cym.sunflower.data;

import java.util.List;

import androidx.lifecycle.LiveData;
import androidx.room.Dao;
import androidx.room.Delete;
import androidx.room.Insert;
import androidx.room.Query;
import androidx.room.Transaction;

/**
 * The Data Access Object for the {@link GardenPlanting} class.
 */
@Dao
public interface GardenPlantingDao {
    @Query("SELECT * FROM garden_plantings")
    LiveData<List<GardenPlanting>> getGardenPlantings();

    @Query("SELECT * FROM garden_plantings WHERE id = :gardenPlantingId")
    LiveData<GardenPlanting> getGardenPlanting(long gardenPlantingId);

    @Query("SELECT * FROM garden_plantings WHERE plant_id = :plantId")
    LiveData<GardenPlanting> getGardenPlantingForPlant(String plantId);

    /**
     * This query will tell Room to query both the {@link Plant} and {@link GardenPlanting} tables and handle
     * the object mapping.
     */
    @Transaction
    @Query("SELECT * FROM plants")
    LiveData<List<PlantAndGardenPlantings>> getPlantAndGardenPlantings();

    @Insert
    long insertGardenPlanting(GardenPlanting gardenPlanting);

    @Delete
    void deleteGardenPlanting(GardenPlanting gardenPlanting);
}
