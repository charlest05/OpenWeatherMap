package com.charlest.openweathermap;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AlertDialog;
import androidx.appcompat.app.AppCompatActivity;
import androidx.core.app.ActivityCompat;
import androidx.core.content.ContextCompat;
import androidx.lifecycle.Observer;
import androidx.lifecycle.ViewModelProviders;

import android.Manifest;
import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.TextView;
import android.widget.Toast;

import com.charlest.openweathermap.events.RefreshDataEvent;
import com.charlest.openweathermap.localtiontracker.LocationTrackerService;
import com.charlest.openweathermap.localtiontracker.data.CurrentLocation;

import org.greenrobot.eventbus.EventBus;

import static android.content.pm.PackageManager.PERMISSION_GRANTED;

public class MainActivity extends AppCompatActivity {

    private static final int LOCATION_PERMISSION_REQUEST_CODE = 0x001;

    private LocationUpdatesViewModel locationUpdatesViewModel;

    private TextView tvLocation;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        locationUpdatesViewModel = ViewModelProviders.of(this).get(LocationUpdatesViewModel.class);
        tvLocation = findViewById(R.id.tvCurrentLocation);

        checkAndRequestPermission();
    }

    private void startLocationUIUpdates() {
        startService(new Intent(this, LocationTrackerService.class));

        locationUpdatesViewModel.getCurrentLocation().observe(this, new Observer<CurrentLocation>() {
            @Override
            public void onChanged(CurrentLocation currentLocation) {
                if(currentLocation != null) {
                    tvLocation.setText(currentLocation.getFormattedCoordinates());
                } else {
                    tvLocation.setText(getString(R.string.location_appear_here));
                }
            }
        });
    }


    private void checkAndRequestPermission() {
        if (hasLocationPermission(this)) {
            startLocationUIUpdates();
        } else {
            ActivityCompat.requestPermissions(this,
                    new String[]{Manifest.permission.ACCESS_FINE_LOCATION}, LOCATION_PERMISSION_REQUEST_CODE);
        }
    }

    private static boolean hasLocationPermission(Context context) {
        int permissionToAccessLocation = ContextCompat.checkSelfPermission(context,
                android.Manifest.permission.ACCESS_FINE_LOCATION);
        return permissionToAccessLocation == PERMISSION_GRANTED;
    }

    @Override
    public void onRequestPermissionsResult(int requestCode, @NonNull String[] permissions, @NonNull int[] grantResults) {
        super.onRequestPermissionsResult(requestCode, permissions, grantResults);

        if (requestCode == LOCATION_PERMISSION_REQUEST_CODE) {
            if (grantResults.length > 0 && grantResults[0] == PERMISSION_GRANTED) {
                startLocationUIUpdates();
            } else {
                AlertDialog.Builder builder = new AlertDialog.Builder(this);
                builder.setMessage(R.string.permission_not_granted);
                builder.setPositiveButton(android.R.string.ok, null);
                AlertDialog dialog = builder.create();
                dialog.show();
            }
        }
    }
}
