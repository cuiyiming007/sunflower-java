package com.cym.sunflower.adapters;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.cym.sunflower.ui.PlantListFragment;
import com.cym.sunflower.data.Plant;
import com.cym.sunflower.databinding.ListItemPlantBinding;
import com.cym.sunflower.ui.PlantListFragmentDirections;

import androidx.annotation.NonNull;
import androidx.navigation.Navigation;
import androidx.recyclerview.widget.ListAdapter;
import androidx.recyclerview.widget.RecyclerView;

import javax.inject.Inject;

/**
 * Adapter for the {@link RecyclerView} in {@link PlantListFragment}
 */
public class PlantAdapter extends ListAdapter<Plant, PlantAdapter.ViewHolder> {

    @Inject
    public PlantAdapter() {
        super(new PlantDiffCallback());
    }

    @NonNull
    @Override
    public ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        return new ViewHolder(ListItemPlantBinding.inflate(LayoutInflater.from(parent.getContext()), parent, false));
    }

    @Override
    public void onBindViewHolder(@NonNull ViewHolder holder, int position) {
        Plant plant = getItem(position);
        holder.bind(v -> {
            PlantListFragmentDirections.ActionPlantListFragmentToPlantDetailFragment direction =
                    PlantListFragmentDirections.actionPlantListFragmentToPlantDetailFragment(plant.plantId);
            Navigation.findNavController(v).navigate(direction);
        }, plant);
    }

    class ViewHolder extends RecyclerView.ViewHolder {
        private ListItemPlantBinding binding;

        ViewHolder(ListItemPlantBinding binding) {
            super(binding.getRoot());
            this.binding = binding;
        }

        void bind(View.OnClickListener listener, Plant item) {
            binding.setClickListener(listener);
            binding.setPlant(item);
            binding.executePendingBindings();
        }
    }
}
