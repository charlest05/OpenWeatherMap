package com.charlest.openweathermap;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;

import com.charlest.openweathermap.R;
import com.charlest.openweathermap.roomdb.AppDatabase;

public class MainActivity extends AppCompatActivity {

    //TODO Support for tablet and phone
    //TODO Multi Language application
    //TODO UnitTest/UITest/Integration test
    //TODO add current location
    //TODO add one dangerous permission
    //TODO add Fabric.io or firebase analytics

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
    }
}
