package com.cym.sunflower.utilities;

import android.content.Context;

import com.cym.sunflower.data.AppDatabase;
import com.cym.sunflower.data.GardenPlantingRepository;
import com.cym.sunflower.data.PlantRepository;
import com.cym.sunflower.viewmodels.GardenPlantingListViewModelFactory;
import com.cym.sunflower.viewmodels.PlantDetailViewModelFactory;
import com.cym.sunflower.viewmodels.PlantListViewModelFactory;

/**
 * Static methods used to inject classes needed for various Activities and Fragments.
 */
public class InjectorUtils {

    private static PlantRepository getPlantRepository(Context context) {
        return PlantRepository.getInstance(AppDatabase.getInstance(context.getApplicationContext()).plantDao());
    }

    private static GardenPlantingRepository getGardenPlantingRepository(Context context) {
        return GardenPlantingRepository.getInstance(AppDatabase.getInstance(context.getApplicationContext()).gardenPlantingDao());
    }

    public static GardenPlantingListViewModelFactory provideGardenPlantingListViewModelFactory(Context context) {
        return new GardenPlantingListViewModelFactory(getGardenPlantingRepository(context));
    }

    public static PlantListViewModelFactory providePlantListViewModelFactory(Context context) {
        return new PlantListViewModelFactory(getPlantRepository(context));
    }

    public static PlantDetailViewModelFactory providePlantDetailViewModelFactory(Context context, String plantId) {
        return new PlantDetailViewModelFactory(getPlantRepository(context), getGardenPlantingRepository(context), plantId);
    }
}