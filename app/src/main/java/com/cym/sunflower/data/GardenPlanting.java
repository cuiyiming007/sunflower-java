package com.cym.sunflower.data;

import androidx.room.ColumnInfo;
import androidx.room.Entity;
import androidx.room.ForeignKey;
import androidx.room.Index;
import androidx.room.PrimaryKey;

import java.util.Calendar;

/**
 * {@link GardenPlanting} represents when a user adds a {@link Plant} to their garden, with useful metadata.
 * Properties such as {@link #lastWateringDate} are used for notifications (such as when to water the
 * plant).
 *
 * Declaring the column info allows for the renaming of variables without implementing a
 * database migration, as the column name would not change.
 */
@Entity(
        tableName = "garden_plantings",
        foreignKeys = {@ForeignKey(entity = Plant.class, parentColumns = {"id"}, childColumns = {"plant_id"})},
        indices = {@Index(value = {"plant_id"})}
)
public class GardenPlanting {
    @PrimaryKey(autoGenerate = true)
    @ColumnInfo(name = "id")
    public long gardenPlantingId = 0;
    @ColumnInfo(name = "plant_id")
    public String plantId;

    /**
     * Indicates when the {@link Plant} was planted. Used for showing notification when it's time
     * to harvest the plant.
     */
    @ColumnInfo(name = "plant_date")
    public Calendar plantDate = Calendar.getInstance();

    /**
     * Indicates when the {@link Plant} was last watered. Used for showing notification when it's
     * time to water the plant.
     */
    @ColumnInfo(name = "last_watering_date")
    public Calendar lastWateringDate = Calendar.getInstance();

    public GardenPlanting(String plantId) {
        this.plantId = plantId;
    }
}
