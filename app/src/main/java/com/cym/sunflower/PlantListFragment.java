package com.cym.sunflower;


import android.content.Context;
import android.os.Bundle;

import androidx.annotation.NonNull;
import androidx.fragment.app.Fragment;
import androidx.lifecycle.ViewModelProviders;

import android.view.LayoutInflater;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.MenuItem;
import android.view.View;
import android.view.ViewGroup;

import com.cym.sunflower.adapters.PlantAdapter;
import com.cym.sunflower.databinding.FragmentPlantListBinding;
import com.cym.sunflower.utilities.InjectorUtils;
import com.cym.sunflower.viewmodels.PlantListViewModel;
import com.cym.sunflower.viewmodels.PlantListViewModelFactory;

public class PlantListFragment extends Fragment {

    private PlantListViewModel viewModel;


    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        FragmentPlantListBinding binding = FragmentPlantListBinding.inflate(inflater, container, false);
        Context context = getContext();
        if (context == null) {
            return binding.getRoot();
        }
        PlantListViewModelFactory factory = InjectorUtils.providePlantListViewModelFactory(context);
        viewModel = ViewModelProviders.of(this, factory).get(PlantListViewModel.class);

        PlantAdapter adapter = new PlantAdapter();
        binding.plantList.setAdapter(adapter);
        subscribeUi(adapter);

        setHasOptionsMenu(true);
        return binding.getRoot();
    }

    @Override
    public void onCreateOptionsMenu(@NonNull Menu menu, @NonNull MenuInflater inflater) {
        inflater.inflate(R.menu.menu_plant_list, menu);
    }

    @Override
    public boolean onOptionsItemSelected(@NonNull MenuItem item) {
        switch (item.getItemId()) {
            case R.id.filter_zone:
                updateData();
                return true;
            default:
                return super.onOptionsItemSelected(item);
        }
    }

    private void subscribeUi(PlantAdapter adapter) {
        viewModel.plants.observe(getViewLifecycleOwner(), plants -> {
            if (plants != null) {
                adapter.submitList(plants);
            }
        });
    }

    private void updateData() {
        if (viewModel.isFiltered()) {
            viewModel.clearGrowZoneNumber();
        } else {
            viewModel.setGrowZoneNumber(9);
        }
    }
}
