package com.cym.sunflower.di;

import android.app.Application;

import com.cym.sunflower.SunFlowerApp;
import com.cym.sunflower.works.SeedDatabaseWork;

import javax.inject.Singleton;

import dagger.BindsInstance;
import dagger.Component;
import dagger.android.AndroidInjectionModule;

@Singleton
@Component(modules = {AndroidInjectionModule.class, GardenActivityModule.class, AppModule.class})
public interface SunFlowerComponent {
    @Component.Builder
    interface Builder {
        @BindsInstance
        Builder application(Application application);

        SunFlowerComponent build();
    }

    void inject(SunFlowerApp sunFlowerApp);

    void inject(SeedDatabaseWork worker);
}
