package com.cym.sunflower.viewmodels;

import com.cym.sunflower.data.GardenPlantingRepository;
import com.cym.sunflower.data.Plant;
import com.cym.sunflower.data.PlantRepository;

import androidx.annotation.NonNull;
import androidx.lifecycle.ViewModel;
import androidx.lifecycle.ViewModelProvider;

/**
 * Factory for creating a {@link PlantDetailViewModel} with a constructor that takes a {@link PlantRepository}
 * and an ID for the current {@link Plant}.
 */
public class PlantDetailViewModelFactory extends ViewModelProvider.NewInstanceFactory {
    private PlantRepository plantRepository;
    private GardenPlantingRepository gardenPlantingRepository;
    private String plantId;

    public PlantDetailViewModelFactory(PlantRepository plantRepository, GardenPlantingRepository gardenPlantingRepository, String plantId) {
        this.plantRepository = plantRepository;
        this.gardenPlantingRepository = gardenPlantingRepository;
        this.plantId = plantId;
    }

    @NonNull
    @Override
    public <T extends ViewModel> T create(@NonNull Class<T> modelClass) {
        return (T) new PlantDetailViewModel(plantRepository, gardenPlantingRepository, plantId);
    }
}
