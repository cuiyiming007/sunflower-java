package com.cym.sunflower.ui;


import android.content.Context;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.MenuItem;
import android.view.View;
import android.view.ViewGroup;

import androidx.annotation.NonNull;
import androidx.fragment.app.Fragment;
import androidx.lifecycle.ViewModelProviders;

import com.cym.sunflower.R;
import com.cym.sunflower.adapters.PlantAdapter;
import com.cym.sunflower.databinding.FragmentPlantListBinding;
import com.cym.sunflower.di.Injectable;
import com.cym.sunflower.viewmodels.PlantListViewModel;
import com.cym.sunflower.viewmodels.ViewModelProviderFactory;

import javax.inject.Inject;

public class PlantListFragment extends Fragment implements Injectable {

    private PlantListViewModel viewModel;

    @Inject
    public ViewModelProviderFactory factory;
    @Inject
    public PlantAdapter adapter;

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        FragmentPlantListBinding binding = FragmentPlantListBinding.inflate(inflater, container, false);
        Context context = getContext();
        if (context == null) {
            return binding.getRoot();
        }
        viewModel = ViewModelProviders.of(this, factory).get(PlantListViewModel.class);

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
