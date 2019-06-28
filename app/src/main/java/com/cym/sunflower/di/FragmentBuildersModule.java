package com.cym.sunflower.di;

import com.cym.sunflower.ui.GardenFragment;
import com.cym.sunflower.ui.PlantDetailFragment;
import com.cym.sunflower.ui.PlantListFragment;

import dagger.Module;
import dagger.android.ContributesAndroidInjector;

@Module
abstract class FragmentBuildersModule {
    @ContributesAndroidInjector
    abstract GardenFragment contributeGardenFragmentInjector();

    @ContributesAndroidInjector
    abstract PlantDetailFragment contributePlantDetailFragmentInjector();

    @ContributesAndroidInjector
    abstract PlantListFragment contributePlantListFragmentInjector();
}
