package com.cym.sunflower.ui;


import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import androidx.fragment.app.Fragment;
import androidx.lifecycle.ViewModelProviders;

import com.cym.sunflower.adapters.GardenPlantingAdapter;
import com.cym.sunflower.databinding.FragmentGardenBinding;
import com.cym.sunflower.di.Injectable;
import com.cym.sunflower.viewmodels.GardenPlantingListViewModel;
import com.cym.sunflower.viewmodels.ViewModelSimpleFactory;

import javax.inject.Inject;


public class GardenFragment extends Fragment implements Injectable {

    @Inject
    GardenPlantingAdapter adapter;
    @Inject
    public ViewModelSimpleFactory<GardenPlantingListViewModel> factory;

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        FragmentGardenBinding binding = FragmentGardenBinding.inflate(inflater, container, false);
        binding.gardenList.setAdapter(adapter);
        subscribeUi(adapter, binding);
        return binding.getRoot();
    }

    private void subscribeUi(GardenPlantingAdapter adapter, FragmentGardenBinding binding) {
        GardenPlantingListViewModel viewModel = ViewModelProviders.of(this, factory).get(GardenPlantingListViewModel.class);

        viewModel.gardenPlantings.observe(getViewLifecycleOwner(),
                plantings -> binding.setHasPlantings(plantings != null && !plantings.isEmpty()));

        viewModel.plantAndGardenPlantings.observe(getViewLifecycleOwner(), result -> {
            if (result != null && !result.isEmpty()) {
                adapter.submitList(result);
            }
        });
    }
}
