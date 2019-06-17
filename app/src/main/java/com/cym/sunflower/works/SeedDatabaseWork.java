package com.cym.sunflower.works;

import android.content.Context;
import android.util.Log;

import com.cym.sunflower.data.AppDatabase;
import com.cym.sunflower.data.Plant;
import com.cym.sunflower.utilities.Constants;
import com.google.gson.Gson;
import com.google.gson.reflect.TypeToken;
import com.google.gson.stream.JsonReader;

import java.io.IOException;
import java.io.InputStreamReader;
import java.util.List;

import androidx.annotation.NonNull;
import androidx.room.RoomDatabase;
import androidx.work.Worker;
import androidx.work.WorkerParameters;

public class SeedDatabaseWork extends Worker {

    private String TAG = SeedDatabaseWork.class.getSimpleName();

    public SeedDatabaseWork(@NonNull Context appContext, @NonNull WorkerParameters workerParams) {
        super(appContext, workerParams);
    }

    @NonNull
    @Override
    public Result doWork() {
        try {
            JsonReader jsonReader = new JsonReader(new InputStreamReader(getApplicationContext().getAssets().open(Constants.PLANT_DATA_FILENAME)));
            List<Plant> plantList =new Gson().fromJson(jsonReader, new TypeToken<List<Plant>>(){}.getType());

            AppDatabase database = AppDatabase.getInstance(getApplicationContext());
            database.plantDao().insertAll(plantList);

            return Result.success();
        } catch (IOException e) {
            Log.e(TAG, "Error seeding database", e);
            e.printStackTrace();
            return Result.failure();
        }
    }
}
