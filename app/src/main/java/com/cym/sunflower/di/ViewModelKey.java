package com.cym.sunflower.di;

import androidx.lifecycle.ViewModel;

import dagger.MapKey;

@MapKey
@interface ViewModelKey {
    Class<? extends ViewModel> value();
}
