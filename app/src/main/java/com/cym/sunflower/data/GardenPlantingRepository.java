package com.cym.sunflower.data;

import java.util.List;

import androidx.lifecycle.LiveData;

import javax.inject.Inject;
import javax.inject.Singleton;

@Singleton
public class GardenPlantingRepository {
    private GardenPlantingDao gardenPlantingDao;

    @Inject
    public GardenPlantingRepository(GardenPlantingDao gardenPlantingDao) {
        this.gardenPlantingDao = gardenPlantingDao;
    }

    public void createGardenPlanting(String plantId) {
        GardenPlanting gardenPlanting = new GardenPlanting(plantId);
        new Thread(() -> gardenPlantingDao.insertGardenPlanting(gardenPlanting)).start();

    }

    public void removeGardenPlanting(GardenPlanting gardenPlanting) {
        new Thread(() -> gardenPlantingDao.deleteGardenPlanting(gardenPlanting)).start();
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
}
