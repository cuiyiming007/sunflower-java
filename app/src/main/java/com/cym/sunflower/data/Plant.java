package com.cym.sunflower.data;

import java.util.Calendar;

import androidx.annotation.NonNull;
import androidx.room.ColumnInfo;
import androidx.room.Entity;
import androidx.room.PrimaryKey;

@Entity(tableName = "plants")
public class Plant {
    @NonNull
    @PrimaryKey @ColumnInfo(name = "id")
    public String plantId;

    public String name;

    public String description;
    @ColumnInfo(name = "grow_zone_number")
    public int growZoneNumber;
    @ColumnInfo(name = "watering_interval")
    public int wateringInterval = 7;
    @ColumnInfo(name = "image_url")
    public String imageUrl = "";

    /**
     * Determines if the plant should be watered.  Returns true if {@param since}'s date > date of last
     * watering + watering Interval; false otherwise.
     */
    public boolean shouldBeWatered(Calendar since, Calendar lastWateringDate) {
        lastWateringDate.add(Calendar.DAY_OF_YEAR, wateringInterval);
        return since.compareTo(lastWateringDate) > 0;
    }

    @NonNull
    @Override
    public String toString() {
        return name;
    }
}
