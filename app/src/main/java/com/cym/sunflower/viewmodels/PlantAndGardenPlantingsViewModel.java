package com.cym.sunflower.viewmodels;

import com.cym.sunflower.data.GardenPlanting;
import com.cym.sunflower.data.Plant;
import com.cym.sunflower.data.PlantAndGardenPlantings;

import java.text.SimpleDateFormat;
import java.util.Locale;

import androidx.databinding.ObservableField;
import androidx.databinding.ObservableInt;
import androidx.lifecycle.ViewModel;

public class PlantAndGardenPlantingsViewModel extends ViewModel {
    public final ObservableField<String> waterDateString;
    public final ObservableInt wateringInterval;
    public final ObservableField<String> imageUrl;
    public final ObservableField<String> plantName;
    public final ObservableField<String> plantDateString;

    public PlantAndGardenPlantingsViewModel(PlantAndGardenPlantings plantings) {
        Plant plant = plantings.plant;
        GardenPlanting gardenPlanting = plantings.gardenPlantings.get(0);

        waterDateString = new ObservableField<>(new SimpleDateFormat("MMM d, yyyy", Locale.CHINESE).format(gardenPlanting.lastWateringDate.getTime()));
        wateringInterval = new ObservableInt(plant.wateringInterval);
        imageUrl = new ObservableField<>(plant.imageUrl);
        plantName = new ObservableField<>(plant.name);
        plantDateString = new ObservableField<>(new SimpleDateFormat("MMM d, yyyy", Locale.CHINESE).format(gardenPlanting.plantDate.getTime()));
    }
}
