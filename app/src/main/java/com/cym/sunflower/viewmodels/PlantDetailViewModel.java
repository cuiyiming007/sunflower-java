package com.cym.sunflower.viewmodels;

import android.os.Build;

import com.cym.sunflower.ui.PlantDetailFragment;
import com.cym.sunflower.data.GardenPlanting;
import com.cym.sunflower.data.GardenPlantingRepository;
import com.cym.sunflower.data.Plant;
import com.cym.sunflower.data.PlantRepository;
import com.squareup.inject.assisted.Assisted;
import com.squareup.inject.assisted.AssistedInject;

import java.util.Objects;

import androidx.lifecycle.LiveData;
import androidx.lifecycle.Transformations;
import androidx.lifecycle.ViewModel;

/**
 * The ViewModel used in {@link PlantDetailFragment}.
 */
public class PlantDetailViewModel extends ViewModel {
    PlantRepository plantRepository;
    private GardenPlantingRepository gardenPlantingRepository;
    private String plantId;

    public LiveData<Boolean> isPlanted;
    public LiveData<Plant> plant;

    @AssistedInject
    public PlantDetailViewModel(PlantRepository plantRepository, GardenPlantingRepository gardenPlantingRepository,@Assisted String plantId) {
        this.plantRepository = plantRepository;
        this.gardenPlantingRepository = gardenPlantingRepository;
        this.plantId = plantId;

        LiveData<GardenPlanting> gardenPlantingForPlant = gardenPlantingRepository.getGardenPlantingForPlant(plantId);
        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.N) {
            isPlanted = Transformations.map(gardenPlantingForPlant, Objects::nonNull);
        } else {
            isPlanted = Transformations.map(gardenPlantingForPlant, plantingPlant -> plantingPlant != null);
        }

        plant = plantRepository.getPlant(plantId);
    }

    @AssistedInject.Factory
    public interface Factory {
        PlantDetailViewModel create(String plantId);
    }

    public void addPlantToGarden() {
        gardenPlantingRepository.createGardenPlanting(plantId);
    }

}
