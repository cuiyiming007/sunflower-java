package com.cym.sunflower.di;

import com.cym.sunflower.ui.GardenActivity;

import dagger.Module;
import dagger.android.ContributesAndroidInjector;

@Module
abstract class GardenActivityModule {
    @ContributesAndroidInjector(modules = {FragmentBuikdersModule.class})
    abstract GardenActivity contributeGardenActivityInjector();
}
