package com.cym.sunflower;


import android.os.Bundle;

import androidx.fragment.app.Fragment;
import androidx.lifecycle.ViewModelProviders;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.cym.sunflower.adapters.GardenPlantingAdapter;
import com.cym.sunflower.databinding.FragmentGardenBinding;
import com.cym.sunflower.utilities.InjectorUtils;
import com.cym.sunflower.viewmodels.GardenPlantingListViewModel;
import com.cym.sunflower.viewmodels.GardenPlantingListViewModelFactory;


public class GardenFragment extends Fragment {

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        FragmentGardenBinding binding = FragmentGardenBinding.inflate(inflater, container, false);
        GardenPlantingAdapter adapter = new GardenPlantingAdapter();
        binding.gardenList.setAdapter(adapter);
        subscribeUi(adapter, binding);
        return binding.getRoot();
    }

    private void subscribeUi(GardenPlantingAdapter adapter, FragmentGardenBinding binding) {
        GardenPlantingListViewModelFactory factory = InjectorUtils.provideGardenPlantingListViewModelFactory(requireContext());
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
