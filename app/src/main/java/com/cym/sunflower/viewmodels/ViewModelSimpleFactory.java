package com.cym.sunflower.viewmodels;

import androidx.annotation.NonNull;
import androidx.lifecycle.ViewModel;
import androidx.lifecycle.ViewModelProvider;

import javax.inject.Inject;
import javax.inject.Singleton;

import dagger.Lazy;

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
