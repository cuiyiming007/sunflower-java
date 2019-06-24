package com.cym.sunflower.adapters;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AlertDialog;
import androidx.databinding.DataBindingUtil;
import androidx.navigation.NavDirections;
import androidx.navigation.Navigation;
import androidx.recyclerview.widget.ListAdapter;
import androidx.recyclerview.widget.RecyclerView;

import com.cym.sunflower.R;
import com.cym.sunflower.data.GardenPlantingRepository;
import com.cym.sunflower.data.PlantAndGardenPlantings;
import com.cym.sunflower.databinding.ListItemGardenPlantingBinding;
import com.cym.sunflower.ui.GardenFragmentDirections;
import com.cym.sunflower.viewmodels.PlantAndGardenPlantingsViewModel;

import javax.inject.Inject;

public class GardenPlantingAdapter extends ListAdapter<PlantAndGardenPlantings, GardenPlantingAdapter.ViewHolder> {

    public GardenPlantingRepository gardenPlantingRepository;

    @Inject
    public GardenPlantingAdapter(GardenPlantingRepository gardenPlantingRepository) {
        super(new GardenPlantDiffCallback());
        this.gardenPlantingRepository = gardenPlantingRepository;
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
        }, l -> {
            AlertDialog.Builder dialogBuilder = new AlertDialog.Builder(l.getContext());
            dialogBuilder.setTitle("删除这株植物？")
                    .setPositiveButton("确定", (dialog, which) -> {
                gardenPlantingRepository.removeGardenPlanting(plantings.gardenPlantings.get(0));
            }).setNegativeButton("取消", ((dialog, which) -> {
                dialog.cancel();
            })).show();
            return true;
        }, plantings);
    }


    class ViewHolder extends RecyclerView.ViewHolder {
        private ListItemGardenPlantingBinding binding;

        ViewHolder(ListItemGardenPlantingBinding binding) {
            super(binding.getRoot());
            this.binding = binding;
        }

        void bind(View.OnClickListener listener, View.OnLongClickListener longClickListener, PlantAndGardenPlantings plantings) {
            binding.setClickListener(listener);
            binding.setLongClickListener(longClickListener);
            binding.setViewModel(new PlantAndGardenPlantingsViewModel(plantings));
            binding.executePendingBindings();
        }
    }
}
