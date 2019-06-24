package com.cym.sunflower.ui;


import android.content.Intent;
import android.os.Build;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.MenuItem;
import android.view.View;
import android.view.ViewGroup;

import androidx.annotation.NonNull;
import androidx.core.app.ShareCompat;
import androidx.databinding.DataBindingUtil;
import androidx.fragment.app.Fragment;
import androidx.lifecycle.ViewModelProviders;

import com.cym.sunflower.R;
import com.cym.sunflower.databinding.FragmentPlantDetailBinding;
import com.cym.sunflower.di.Injectable;
import com.cym.sunflower.viewmodels.PlantDetailViewModel;
import com.cym.sunflower.viewmodels.ViewModelProviderFactory;
import com.google.android.material.snackbar.Snackbar;

import javax.inject.Inject;

/**
 * A fragment representing a single Plant detail screen.
 */
public class PlantDetailFragment extends Fragment implements Injectable {

    private String shareText;

    @Inject
    public ViewModelProviderFactory factory;

    public PlantDetailFragment() {
        // Required empty public constructor
    }


    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        String plantId = PlantDetailFragmentArgs.fromBundle(getArguments()).getPlantId();

        PlantDetailViewModel plantDetailViewModel = ViewModelProviders.of(this, factory).get(PlantDetailViewModel.class);
        plantDetailViewModel.setPlantId(plantId);

        FragmentPlantDetailBinding binding = DataBindingUtil.inflate(inflater, R.layout.fragment_plant_detail, container, false);
        binding.setViewModel(plantDetailViewModel);
        binding.setLifecycleOwner(getViewLifecycleOwner());
        binding.fab.setOnClickListener(v -> {
            plantDetailViewModel.addPlantToGarden();
            Snackbar.make(v, R.string.added_plant_to_garden, Snackbar.LENGTH_LONG).show();
        });

        plantDetailViewModel.plant.observe(this, plant -> {
            if (plant == null) {
                shareText = "";
            } else {
                shareText = getString(R.string.share_text_plant, plant.name);
            }
        });

        setHasOptionsMenu(true);

        return binding.getRoot();
    }

    @Override
    public boolean onOptionsItemSelected(@NonNull MenuItem item) {
        switch (item.getItemId()) {
            case R.id.action_share:
                Intent shareIntent = ShareCompat.IntentBuilder.from(getActivity())
                        .setText(shareText)
                        .setType("text/plain")
                        .createChooserIntent();
                // https://android-developers.googleblog.com/2012/02/share-with-intents.html
                if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.LOLLIPOP) {
                    // If we're on Lollipop, we can open the intent as a document
                    shareIntent.addFlags(Intent.FLAG_ACTIVITY_NEW_DOCUMENT | Intent.FLAG_ACTIVITY_MULTIPLE_TASK);
                } else {
                    // Else, we will use the old CLEAR_WHEN_TASK_RESET flag
                    shareIntent.addFlags(Intent.FLAG_ACTIVITY_CLEAR_WHEN_TASK_RESET);
                }
                startActivity(shareIntent);
                return true;
            default:
                return super.onOptionsItemSelected(item);
        }
    }
}
