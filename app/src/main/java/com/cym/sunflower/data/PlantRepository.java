package com.cym.sunflower.data;

import java.util.List;

import androidx.lifecycle.LiveData;

/**
 * Repository module for handling data operations.
 */
public class PlantRepository {
    private PlantDao plantDao;
    private static volatile PlantRepository instance;

    private PlantRepository(PlantDao plantDao) {
        this.plantDao = plantDao;
    }

    public LiveData<List<Plant>> getPlants() {
        return plantDao.getPlants();
    }

    public LiveData<Plant> getPlant(String plantId) {
        return plantDao.getPlant(plantId);
    }

    public LiveData<List<Plant>> getPlantsWithGrowZoneNumber(int growZoneNumber) {
        return plantDao.getPlantsWithGrowZoneNumber(growZoneNumber);
    }

    public static PlantRepository getInstance(PlantDao plantDao) {
        if (instance == null) {
            synchronized (PlantRepository.class) {
                if (instance == null) {
                    instance = new PlantRepository(plantDao);
                }
            }
        }
        return instance;
    }
}
