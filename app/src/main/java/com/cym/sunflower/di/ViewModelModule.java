package com.cym.sunflower.di;

import androidx.lifecycle.ViewModel;
import androidx.lifecycle.ViewModelProvider;

import com.cym.sunflower.viewmodels.GardenPlantingListViewModel;
import com.cym.sunflower.viewmodels.PlantDetailViewModel;
import com.cym.sunflower.viewmodels.PlantListViewModel;
import com.cym.sunflower.viewmodels.ViewModelProviderFactory;

import dagger.Binds;
import dagger.Module;
import dagger.multibindings.IntoMap;

@Module
abstract class ViewModelModule {
    @Binds
    @IntoMap
    @ViewModelKey(GardenPlantingListViewModel.class)
    abstract ViewModel bindGardenPlantingListViewModel(GardenPlantingListViewModel gardenPlantingListViewModel);

    @Binds
    @IntoMap
    @ViewModelKey(PlantDetailViewModel.class)
    abstract ViewModel bindPlantDetailViewModel(PlantDetailViewModel plantDetailViewModel);

    @Binds
    @IntoMap
    @ViewModelKey(PlantListViewModel.class)
    abstract ViewModel bindPlantListViewModel(PlantListViewModel plantListViewModel);

    @Binds
    abstract ViewModelProvider.Factory bindViewModelFactory(ViewModelProviderFactory factory);
}
