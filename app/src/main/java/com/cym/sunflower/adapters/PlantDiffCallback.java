package com.cym.sunflower.adapters;

import com.cym.sunflower.data.Plant;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.DiffUtil;

public class PlantDiffCallback extends DiffUtil.ItemCallback<Plant> {

    @Override
    public boolean areItemsTheSame(@NonNull Plant oldItem, @NonNull Plant newItem) {
        return oldItem.plantId == newItem.plantId;
    }

    @Override
    public boolean areContentsTheSame(@NonNull Plant oldItem, @NonNull Plant newItem) {
        return oldItem == newItem;
    }
}
