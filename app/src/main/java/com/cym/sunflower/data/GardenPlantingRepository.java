package com.cym.sunflower.data;

import java.util.List;

import androidx.lifecycle.LiveData;

public class GardenPlantingRepository {
    private GardenPlantingDao gardenPlantingDao;

    private volatile static GardenPlantingRepository instance;

    private GardenPlantingRepository(GardenPlantingDao gardenPlantingDao) {
        this.gardenPlantingDao = gardenPlantingDao;
    }

    public void createGardenPlanting(String plantId) {
        GardenPlanting gardenPlanting = new GardenPlanting(plantId);
        new Thread(new Runnable() {
            @Override
            public void run() {
                gardenPlantingDao.insertGardenPlanting(gardenPlanting);
            }
        }).start();

    }

    public void removeGardenPlanting(GardenPlanting gardenPlanting) {
        new Thread(new Runnable() {
            @Override
            public void run() {
                gardenPlantingDao.deleteGardenPlanting(gardenPlanting);
            }
        }).start();
    }

    public LiveData<GardenPlanting> getGardenPlantingForPlant(String plantId) {
        return gardenPlantingDao.getGardenPlantingForPlant(plantId);
    }

    public LiveData<List<GardenPlanting>> getGardenPlantings() {
        return gardenPlantingDao.getGardenPlantings();
    }

    public LiveData<List<PlantAndGardenPlantings>> getPlantAndGardenPlantings() {
        return gardenPlantingDao.getPlantAndGardenPlantings();
    }

    public static GardenPlantingRepository getInstance(GardenPlantingDao gardenPlantingDao) {
        if (instance == null) {
            synchronized (GardenPlantingRepository.class) {
                if (instance == null) {
                    instance = new GardenPlantingRepository(gardenPlantingDao);
                }
            }
        }
        return instance;
    }
}
