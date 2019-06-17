package com.cym.sunflower.data;

import java.util.ArrayList;
import java.util.List;

import androidx.room.Embedded;
import androidx.room.Relation;

/**
 * This class captures the relationship between a {@link Plant} and a user's {@link GardenPlanting}, which is
 * used by Room to fetch the related entities.
 */
public class PlantAndGardenPlantings {
    @Embedded
    public Plant plant;

    @Relation(parentColumn = "id", entityColumn = "plant_id")
    public List<GardenPlanting> gardenPlantings = new ArrayList<>();
}
