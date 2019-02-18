package com.charlest.openweathermap.weatherdetail;


import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.charlest.openweathermap.R;

import androidx.fragment.app.Fragment;

public class WeatherDetailFragment extends Fragment {


    public WeatherDetailFragment() {
        // Required empty public constructor
    }


    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        return inflater.inflate(R.layout.fragment_weather_detail, container, false);
    }

}
