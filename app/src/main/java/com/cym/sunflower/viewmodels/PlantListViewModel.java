package com.cym.sunflower.viewmodels;

import com.cym.sunflower.ui.PlantListFragment;
import com.cym.sunflower.data.Plant;
import com.cym.sunflower.data.PlantRepository;

import java.util.List;

import androidx.lifecycle.LiveData;
import androidx.lifecycle.MutableLiveData;
import androidx.lifecycle.Transformations;
import androidx.lifecycle.ViewModel;

import javax.inject.Inject;

/**
 * The ViewModel for {@link PlantListFragment}.
 */
public class PlantListViewModel extends ViewModel {
    private static int NO_GROW_ZONE = -1;

    PlantRepository plantRepository;
    private MutableLiveData<Integer> growZoneNumber = new MutableLiveData<>();
    public LiveData<List<Plant>> plants;

    @Inject
    public PlantListViewModel(PlantRepository plantRepository) {
        this.plantRepository = plantRepository;
        growZoneNumber.setValue(NO_GROW_ZONE);
        plants = Transformations.switchMap(growZoneNumber, it -> {
            if (it == NO_GROW_ZONE) {
                return plantRepository.getPlants();
            } else {
                return plantRepository.getPlantsWithGrowZoneNumber(it);
            }
        });
    }

    public void setGrowZoneNumber(int number) {
        growZoneNumber.setValue(number);
    }

    public void clearGrowZoneNumber() {
        growZoneNumber.setValue(NO_GROW_ZONE);
    }

    public boolean isFiltered() {
        return growZoneNumber.getValue() != NO_GROW_ZONE;
    }
}
