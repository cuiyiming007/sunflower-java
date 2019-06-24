Android Sunflower Java
=========================
A gardening app illustrating Android development best practices with Android Jetpack in **`JAVA`**.

The google official sample is in KOTLIN.

Releases:

Version | Description
 ------ | -------
[v1.2.0](https://github.com/cuiyiming007/sunflowr-by-java/releases/tag/v1.2.0)  | another simple and better way to inject `ViewModel` 
[v1.1.0](https://github.com/cuiyiming007/sunflowr-by-java/releases/tag/v1.1.0)  | using dagger2 as di, add LongClickListener to delete plant
[v1.0.0](https://github.com/cuiyiming007/sunflowr-by-java/releases/tag/v1.0.0)  | android architecture in Java

## A brief description between v1.1.0 and v1.2.0

In [v1.1.0](https://github.com/cuiyiming007/sunflowr-by-java/releases/tag/v1.1.0), I reference the Google Official Sample [GithubBrowserSample](https://github.com/googlesamples/android-architecture-components/tree/master/GithubBrowserSample). Re-write the di codes from Kotlin to Java.  
To inject ViewModels, we’ve to create a singleton [factory](https://github.com/cuiyiming007/sunflowr-by-java/blob/6bd49fe8c585e80c62366a6d513945aa9d89712f/app/src/main/java/com/cym/sunflower/viewmodels/ViewModelProviderFactory.java) that was supplied with a map of `ViewModel`-based classes and their respective `Provider`s. It required us to create a custom [`ViewModelKey` annotation](https://github.com/cuiyiming007/sunflowr-by-java/blob/6bd49fe8c585e80c62366a6d513945aa9d89712f/app/src/main/java/com/cym/sunflower/di/ViewModelKey.java) and use Dagger to generate the map in a [module](https://github.com/cuiyiming007/sunflowr-by-java/blob/6bd49fe8c585e80c62366a6d513945aa9d89712f/app/src/main/java/com/cym/sunflower/di/ViewModelModule.java) using `IntoMap` bindings.  
There is the code snippet,  
ViewModelFactory:
```java
@Singleton
public class ViewModelProviderFactory extends ViewModelProvider.NewInstanceFactory {

    Map<Class<? extends ViewModel>, Provider<ViewModel>> creators;

    @Inject
    public ViewModelProviderFactory(Map<Class<? extends ViewModel>, Provider<ViewModel>> creators) {
        this.creators = creators;
    }

    @NonNull
    @Override
    public <T extends ViewModel> T create(@NonNull Class<T> modelClass) {
        Provider<ViewModel> creator = creators.get(modelClass);
        if (creator == null) {
            for (Map.Entry<Class<? extends ViewModel>, Provider<ViewModel>> entry : creators.entrySet()) {
                if (modelClass.isAssignableFrom(entry.getKey())) {
                    creator = entry.getValue();
                    break;
                }
            }
        }

        if (creator == null) {
            throw new IllegalArgumentException("unknown model class " + modelClass);
        }
        return (T) creator.get();
    }
}
```

ViewModelKey:
```java
@MapKey
@interface ViewModelKey {
    Class<? extends ViewModel> value();
}
```

ViewModelModule:
```java
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
```

In [v1.2.0](https://github.com/cuiyiming007/sunflowr-by-java/releases/tag/v1.2.0), we only need to write a generic [`ViewModel factory` class](https://github.com/cuiyiming007/sunflowr-by-java/blob/99d7c5c561b25a350c36b679615bc078132a2f43/app/src/main/java/com/cym/sunflower/viewmodels/ViewModelSimpleFactory.java) of which instances are created for each Activity or [Fragment](https://github.com/cuiyiming007/sunflowr-by-java/blob/99d7c5c561b25a350c36b679615bc078132a2f43/app/src/main/java/com/cym/sunflower/ui/GardenFragment.java) instance.   
There is the code snippet,  
ViewModelFactory:
```java
@Singleton
public class ViewModelSimpleFactory<VM extends ViewModel> extends ViewModelProvider.NewInstanceFactory {

    private Lazy<VM> viewModel;
    @Inject
    public ViewModelSimpleFactory(Lazy<VM> viewModel) {
        this.viewModel = viewModel;
    }

    @NonNull
    @Override
    public <T extends ViewModel> T create(@NonNull Class<T> modelClass) {
        return (T) viewModel.get();
    }
}
```

Activity or Fragment:
```java
public class GardenFragment extends Fragment implements Injectable {
    @Inject
    public ViewModelSimpleFactory<GardenPlantingListViewModel> factory;

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        //...
        GardenPlantingListViewModel viewModel = ViewModelProviders.of(this, factory).get(GardenPlantingListViewModel.class);
        //...
    }
}
```

That's all!


------------------------

[![CircleCI](https://circleci.com/gh/googlesamples/android-sunflower/tree/master.svg?style=shield)](https://circleci.com/gh/googlesamples/android-sunflower/tree/master)

A gardening app illustrating Android development best practices with Android Jetpack.

Android Sunflower is currently released as an alpha and is under heavy development. To view the
latest changes, please visit the
[Releases page](https://github.com/googlesamples/android-sunflower/releases).
Note that some changes (such as database schema modifications) are not backwards
compatible during this alpha period and may cause the app to crash. In this
case, please uninstall and re-install the app.

Introduction
------------

Android Jetpack is a set of components, tools and guidance to make great Android apps. They bring
together the existing Support Library and Architecture Components and arranges them into four
categories:

![Android Jetpack](https://github.com/googlesamples/android-sunflower/raw/master/screenshots/jetpack_donut.png "Android Jetpack Components")

Android Sunflower demonstrates utilizing these components to create a simple gardening app.
Read the
[Introducing Android Sunflower](https://medium.com/androiddevelopers/introducing-android-sunflower-e421b43fe0c2)
article for a walkthrough of the app.

Getting Started
---------------
This project uses the Gradle build system. To build this project, use the
`gradlew build` command or use "Import Project" in Android Studio.

There are two Gradle tasks for testing the project:
* `connectedAndroidTest` - for running Espresso on a connected device
* `test` - for running unit tests

For more resources on learning Android development, visit the
[Developer Guides](https://developer.android.com/guide/) at
[developer.android.com](https://developer.android.com).

Screenshots
-----------

![List of plants](https://github.com/googlesamples/android-sunflower/raw/master/screenshots/phone_plant_list.png "A list of plants")
![Plant details](https://github.com/googlesamples/android-sunflower/raw/master/screenshots/phone_plant_detail.png "Details for a specific plant")
![My Garden](https://github.com/googlesamples/android-sunflower/raw/master/screenshots/phone_my_garden.png "Plants that have been added to your garden")

Libraries Used
--------------
* [Foundation][0] - Components for core system capabilities, Kotlin extensions and support for
  multidex and automated testing.
  * [AppCompat][1] - Degrade gracefully on older versions of Android.
  * [Android KTX][2] - Write more concise, idiomatic Kotlin code.
  * [Test][4] - An Android testing framework for unit and runtime UI tests.
* [Architecture][10] - A collection of libraries that help you design robust, testable, and
  maintainable apps. Start with classes for managing your UI component lifecycle and handling data
  persistence.
  * [Data Binding][11] - Declaratively bind observable data to UI elements.
  * [Lifecycles][12] - Create a UI that automatically responds to lifecycle events.
  * [LiveData][13] - Build data objects that notify views when the underlying database changes.
  * [Navigation][14] - Handle everything needed for in-app navigation.
  * [Room][16] - Access your app's SQLite database with in-app objects and compile-time checks.
  * [ViewModel][17] - Store UI-related data that isn't destroyed on app rotations. Easily schedule
     asynchronous tasks for optimal execution.
  * [WorkManager][18] - Manage your Android background jobs.
* [UI][30] - Details on why and how to use UI Components in your apps - together or separate
  * [Animations & Transitions][31] - Move widgets and transition between screens.
  * [Fragment][34] - A basic unit of composable UI.
  * [Layout][35] - Lay out widgets using different algorithms.
* Third party
  * [Glide][90] for image loading
  * [Kotlin Coroutines][91] for managing background threads with simplified code and reducing needs for callbacks

[0]: https://developer.android.com/jetpack/components
[1]: https://developer.android.com/topic/libraries/support-library/packages#v7-appcompat
[2]: https://developer.android.com/kotlin/ktx
[4]: https://developer.android.com/training/testing/
[10]: https://developer.android.com/jetpack/arch/
[11]: https://developer.android.com/topic/libraries/data-binding/
[12]: https://developer.android.com/topic/libraries/architecture/lifecycle
[13]: https://developer.android.com/topic/libraries/architecture/livedata
[14]: https://developer.android.com/topic/libraries/architecture/navigation/
[16]: https://developer.android.com/topic/libraries/architecture/room
[17]: https://developer.android.com/topic/libraries/architecture/viewmodel
[18]: https://developer.android.com/topic/libraries/architecture/workmanager
[30]: https://developer.android.com/guide/topics/ui
[31]: https://developer.android.com/training/animation/
[34]: https://developer.android.com/guide/components/fragments
[35]: https://developer.android.com/guide/topics/ui/declaring-layout
[90]: https://bumptech.github.io/glide/
[91]: https://kotlinlang.org/docs/reference/coroutines-overview.html

Upcoming features
-----------------
Updates will include incorporating additional Jetpack components and updating existing components
as the component libraries evolve.

Interested in seeing a particular feature of the Android Framework or Jetpack implemented in this
app? Please open a new [issue](https://github.com/googlesamples/android-sunflower/issues).

Android Studio IDE setup
------------------------
For development, the latest version of Android Studio is required. The latest version can be
downloaded from [here](https://developer.android.com/studio/).

Sunflower uses [ktlint](https://ktlint.github.io/) to enforce Kotlin coding styles.
Here's how to configure it for use with Android Studio (instructions adapted
from the ktlint [README](https://github.com/shyiko/ktlint/blob/master/README.md)):

- Close Android Studio if it's open

- Download ktlint using these [installation instructions](https://github.com/shyiko/ktlint/blob/master/README.md#installation)

- Inside the project root directory run:

  `./ktlint --apply-to-idea-project --android`

- Start Android Studio

Additional resources
--------------------
Check out these Wiki pages to learn more about Android Sunflower:

- [Notable Community Contributions](https://github.com/googlesamples/android-sunflower/wiki/Notable-Community-Contributions)

- [Publications](https://github.com/googlesamples/android-sunflower/wiki/Sunflower-Publications)

Non-Goals
---------
The focus of this project is on Android Jetpack and the Android framework.
Thus, there are no immediate plans to implement features outside of this scope.

A note on dependency injection - while many projects (such as
[Plaid](https://github.com/nickbutcher/plaid)) use
[Dagger 2](https://github.com/google/dagger) for DI, there are no plans to
incorporate DI into Sunflower.  This allows developers unfamiliar with dependency
injection to better understand Sunflower's code without having to learn DI.

Support
-------

- Stack Overflow:
  - http://stackoverflow.com/questions/tagged/android
  - http://stackoverflow.com/questions/tagged/android-jetpack

If you've found an error in this sample, please file an issue:
https://github.com/googlesamples/android-sunflower/issues

Patches are encouraged, and may be submitted by forking this project and submitting a pull request
through GitHub.

Third Party Content
-------------------
Select text used for describing the plants (in `plants.json`) are used from Wikipedia via CC BY-SA 3.0 US (license in `ASSETS_LICENSE`).

License
-------

Copyright 2019 CuiYiming

Licensed to the Apache Software Foundation (ASF) under one or more contributor
license agreements.  See the NOTICE file distributed with this work for
additional information regarding copyright ownership.  The ASF licenses this
file to you under the Apache License, Version 2.0 (the "License"); you may not
use this file except in compliance with the License.  You may obtain a copy of
the License at

  http://www.apache.org/licenses/LICENSE-2.0

Unless required by applicable law or agreed to in writing, software
distributed under the License is distributed on an "AS IS" BASIS, WITHOUT
WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.  See the
License for the specific language governing permissions and limitations under
the License.