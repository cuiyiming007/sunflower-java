package com.cym.sunflower.works;

import android.app.Application;
import android.content.Context;
import android.util.Log;

import androidx.annotation.NonNull;
import androidx.work.Worker;
import androidx.work.WorkerParameters;

import com.cym.sunflower.data.Plant;
import com.cym.sunflower.data.PlantDao;
import com.cym.sunflower.di.DaggerSunFlowerComponent;
import com.cym.sunflower.utilities.Constants;
import com.google.gson.Gson;
import com.google.gson.reflect.TypeToken;
import com.google.gson.stream.JsonReader;

import java.io.IOException;
import java.io.InputStreamReader;
import java.util.List;

import javax.inject.Inject;

public class SeedDatabaseWork extends Worker {

    private String TAG = SeedDatabaseWork.class.getSimpleName();
    @Inject
    public PlantDao plantDao;
    @Inject
    public Gson gson;

    public SeedDatabaseWork(@NonNull Context appContext, @NonNull WorkerParameters workerParams) {
        super(appContext, workerParams);
        DaggerSunFlowerComponent.builder().application((Application) getApplicationContext()).build().inject(this);

    }

    @NonNull
    @Override
    public Result doWork() {
        try {
            JsonReader jsonReader = new JsonReader(new InputStreamReader(getApplicationContext().getAssets().open(Constants.PLANT_DATA_FILENAME)));
            List<Plant> plantList = gson.fromJson(jsonReader, new TypeToken<List<Plant>>(){}.getType());

            plantDao.insertAll(plantList);

            return Result.success();
        } catch (IOException e) {
            Log.e(TAG, "Error seeding database", e);
            e.printStackTrace();
            return Result.failure();
        }
    }
}
