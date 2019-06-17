package com.cym.sunflower.adapters;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.cym.sunflower.GardenFragmentDirections;
import com.cym.sunflower.R;
import com.cym.sunflower.data.PlantAndGardenPlantings;
import com.cym.sunflower.databinding.ListItemGardenPlantingBinding;
import com.cym.sunflower.viewmodels.PlantAndGardenPlantingsViewModel;

import androidx.annotation.NonNull;
import androidx.databinding.DataBindingUtil;
import androidx.navigation.NavDirections;
import androidx.navigation.Navigation;
import androidx.recyclerview.widget.ListAdapter;
import androidx.recyclerview.widget.RecyclerView;

public class GardenPlantingAdapter extends ListAdapter<PlantAndGardenPlantings, GardenPlantingAdapter.ViewHolder> {
    public GardenPlantingAdapter() {
        super(new GardenPlantDiffCallback());
    }

    @NonNull
    @Override
    public ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        return new ViewHolder(DataBindingUtil.inflate(LayoutInflater.from(parent.getContext()), R.layout.list_item_garden_planting, parent, false));
    }

    @Override
    public void onBindViewHolder(@NonNull ViewHolder holder, int position) {
        PlantAndGardenPlantings plantings = getItem(position);
        holder.itemView.setTag(plantings);
        holder.bind(v -> {
            NavDirections direction = GardenFragmentDirections.actionGardenFragmentToPlantDetailFragment(plantings.plant.plantId);
            Navigation.findNavController(v).navigate(direction);
        }, plantings);
    }


    class ViewHolder extends RecyclerView.ViewHolder {
        private ListItemGardenPlantingBinding binding;

        ViewHolder(ListItemGardenPlantingBinding binding) {
            super(binding.getRoot());
            this.binding = binding;
        }

        void bind(View.OnClickListener listener, PlantAndGardenPlantings plantings) {
            binding.setClickListener(listener);
            binding.setViewModel(new PlantAndGardenPlantingsViewModel(plantings));
            binding.executePendingBindings();
        }
    }
}
