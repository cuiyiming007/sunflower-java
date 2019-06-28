package com.cym.sunflower.di;

import com.cym.sunflower.ui.GardenActivity;
import com.squareup.inject.assisted.dagger2.AssistedModule;

import dagger.Module;
import dagger.android.ContributesAndroidInjector;

@AssistedModule
@Module(includes = {AssistedInject_GardenActivityModule.class})
abstract class GardenActivityModule {
    @ContributesAndroidInjector(modules = {FragmentBuildersModule.class})
    abstract GardenActivity contributeGardenActivityInjector();
}
