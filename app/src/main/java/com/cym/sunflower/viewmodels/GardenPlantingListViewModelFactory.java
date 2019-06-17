package com.cym.sunflower.viewmodels;

import com.cym.sunflower.data.GardenPlantingRepository;

import androidx.annotation.NonNull;
import androidx.lifecycle.ViewModel;
import androidx.lifecycle.ViewModelProvider;

/**
 * Factory for creating a {@link GardenPlantingListViewModel} with a constructor that takes a
 * {@link GardenPlantingRepository}.
 */
public class GardenPlantingListViewModelFactory extends ViewModelProvider.NewInstanceFactory {
    private GardenPlantingRepository repository;

    public GardenPlantingListViewModelFactory(GardenPlantingRepository repository) {
        this.repository = repository;
    }

    @NonNull
    @Override
    public <T extends ViewModel> T create(@NonNull Class<T> modelClass) {
        return (T) new GardenPlantingListViewModel(repository);
    }
}
