package com.cym.sunflower.viewmodels;

import com.cym.sunflower.data.PlantRepository;

import androidx.annotation.NonNull;
import androidx.lifecycle.ViewModel;
import androidx.lifecycle.ViewModelProvider;

/**
 * Factory for creating a {@link PlantListViewModel} with a constructor that takes a {@link PlantRepository}.
 */
public class PlantListViewModelFactory extends ViewModelProvider.NewInstanceFactory {
    PlantRepository repository;

    public PlantListViewModelFactory(PlantRepository repository) {
        this.repository = repository;
    }

    @NonNull
    @Override
    public <T extends ViewModel> T create(@NonNull Class<T> modelClass) {
        return (T) new PlantListViewModel(repository);
    }
}
