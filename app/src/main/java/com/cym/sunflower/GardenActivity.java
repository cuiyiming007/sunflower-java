package com.cym.sunflower;

import android.Manifest;
import android.content.pm.PackageManager;
import android.os.Bundle;
import android.os.Environment;

import com.cym.sunflower.databinding.ActivityGardenBinding;

import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;

import androidx.appcompat.app.AppCompatActivity;
import androidx.core.app.ActivityCompat;
import androidx.core.content.ContextCompat;
import androidx.core.view.GravityCompat;
import androidx.databinding.DataBindingUtil;
import androidx.drawerlayout.widget.DrawerLayout;
import androidx.navigation.NavController;
import androidx.navigation.Navigation;
import androidx.navigation.ui.AppBarConfiguration;
import androidx.navigation.ui.NavigationUI;

public class GardenActivity extends AppCompatActivity {

    private DrawerLayout drawerLayout;
    private AppBarConfiguration appBarConfiguration;
    private NavController navController;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        ActivityGardenBinding binding = DataBindingUtil.setContentView(this, R.layout.activity_garden);
        drawerLayout = binding.drawerLayout;

        navController = Navigation.findNavController(this, R.id.garden_nav_fragment);
        appBarConfiguration = new AppBarConfiguration.Builder(navController.getGraph()).setDrawerLayout(drawerLayout).build();

        // Set up ActionBar
        setSupportActionBar(binding.toolbar);
        NavigationUI.setupActionBarWithNavController(this, navController, appBarConfiguration);

        // Set up navigation menu
        NavigationUI.setupWithNavController(binding.navigationView, navController);
    }

    @Override
    public boolean onSupportNavigateUp() {
//        return navController.navigateUp() || super.onSupportNavigateUp();
        return NavigationUI.navigateUp(navController, drawerLayout) || super.onSupportNavigateUp();
    }

    @Override
    public void onBackPressed() {
        if (drawerLayout.isDrawerOpen(GravityCompat.START)) {
            drawerLayout.closeDrawer(GravityCompat.START);
        } else {
            super.onBackPressed();
        }
    }
}
