package com.cym.sunflower.viewmodels;

import android.os.Build;
import android.util.Log;

import com.cym.sunflower.data.GardenPlanting;
import com.cym.sunflower.data.GardenPlantingRepository;
import com.cym.sunflower.data.PlantAndGardenPlantings;

import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

import androidx.lifecycle.LiveData;
import androidx.lifecycle.Transformations;
import androidx.lifecycle.ViewModel;

public class GardenPlantingListViewModel extends ViewModel {

    public LiveData<List<GardenPlanting>> gardenPlantings;
    public LiveData<List<PlantAndGardenPlantings>> plantAndGardenPlantings;

    public GardenPlantingListViewModel(GardenPlantingRepository gardenPlantingRepository) {
        Log.d("GardenPlantingList", "i am here");
        gardenPlantings = gardenPlantingRepository.getGardenPlantings();

        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.N) {
            plantAndGardenPlantings =
                    Transformations.map(gardenPlantingRepository.getPlantAndGardenPlantings(),
                            plantings -> plantings.stream()
                                    .filter(planting -> !planting.gardenPlantings.isEmpty())
                                    .collect(Collectors.toList()));
        } else {
            plantAndGardenPlantings =
                    Transformations.map(gardenPlantingRepository.getPlantAndGardenPlantings(),
                            plantings -> {
                                List<PlantAndGardenPlantings> list = new ArrayList<>();
                                for (PlantAndGardenPlantings plant : plantings) {
                                    if (!plant.gardenPlantings.isEmpty()) {
                                        list.add(plant);
                                    }
                                }
                                return list;
                            });
        }
    }
}
