package com.cym.sunflower.adapters;

import com.cym.sunflower.data.PlantAndGardenPlantings;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.DiffUtil;

public class GardenPlantDiffCallback extends DiffUtil.ItemCallback<PlantAndGardenPlantings> {

    @Override
    public boolean areItemsTheSame(@NonNull PlantAndGardenPlantings oldItem, @NonNull PlantAndGardenPlantings newItem) {
        return oldItem.plant.plantId == newItem.plant.plantId;
    }

    @Override
    public boolean areContentsTheSame(@NonNull PlantAndGardenPlantings oldItem, @NonNull PlantAndGardenPlantings newItem) {
        return oldItem.plant == newItem.plant;
    }
}
