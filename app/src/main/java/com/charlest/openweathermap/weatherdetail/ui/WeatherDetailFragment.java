package com.charlest.openweathermap.weatherdetail.ui;


import android.graphics.drawable.Drawable;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.ProgressBar;
import android.widget.TextView;

import com.bumptech.glide.Glide;
import com.bumptech.glide.request.RequestOptions;
import com.charlest.openweathermap.R;
import com.charlest.openweathermap.events.RefreshDataEvent;
import com.charlest.openweathermap.roomdb.entities.WeatherData;
import com.charlest.openweathermap.weatherdetail.data.WeatherDetail;

import org.greenrobot.eventbus.EventBus;
import org.greenrobot.eventbus.Subscribe;
import org.greenrobot.eventbus.ThreadMode;

import java.util.ArrayList;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.appcompat.app.AlertDialog;
import androidx.core.content.ContextCompat;
import androidx.fragment.app.Fragment;
import androidx.lifecycle.Observer;
import androidx.lifecycle.ViewModelProviders;
import androidx.recyclerview.widget.DividerItemDecoration;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

public class WeatherDetailFragment extends Fragment {

    private WeatherDetailRecyclerViewAdapter adapter;

    private WeatherDetailViewModel weatherDetailViewModel;

    private RecyclerView rvWeatherDetail;

    private ProgressBar pbWeatherDetail;

    private TextView tvTitle, tvTemperature, tvActualWeather;

    private RequestOptions requestOptions;

    private ImageView ivWeatherIcon;

    @Override
    public void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        adapter = new WeatherDetailRecyclerViewAdapter();
        weatherDetailViewModel = ViewModelProviders.of(this).get(WeatherDetailViewModel.class);

        Drawable placeHolder = ContextCompat.getDrawable(requireContext(), R.drawable.ic_cloud);
        requestOptions = new RequestOptions()
                .centerCrop()
                .placeholder(placeHolder)
                .error(placeHolder);
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        return inflater.inflate(R.layout.fragment_weather_detail, container, false);
    }

    @Override
    public void onViewCreated(@NonNull View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);

        int id = WeatherDetailFragmentArgs.fromBundle(getArguments()).getWeatherdataId();
        weatherDetailViewModel.setCityID(id);

        rvWeatherDetail = view.findViewById(R.id.rvWeatherDetail);
        pbWeatherDetail = view.findViewById(R.id.pbWeatherDetail);
        tvTitle = view.findViewById(R.id.tvTitle);
        tvTemperature = view.findViewById(R.id.tvTemperature);
        tvActualWeather = view.findViewById(R.id.tvActualWeather);
        ivWeatherIcon = view.findViewById(R.id.ivWeatherIcon);

        rvWeatherDetail.setAdapter(adapter);
        rvWeatherDetail.setLayoutManager(new LinearLayoutManager(requireContext(), RecyclerView.VERTICAL, false));
        rvWeatherDetail.addItemDecoration(new DividerItemDecoration(requireContext(), RecyclerView.VERTICAL));

        weatherDetailViewModel.getWeatherData().observe(this, new Observer<WeatherData>() {
            @Override
            public void onChanged(WeatherData weatherData) {
                if(weatherData != null) {
                    updateDetails(weatherData);
                }
            }
        });


        weatherDetailViewModel.getWeatherListUIStateMutableLiveData().observe(this, new Observer<WeatherDetailUIState>() {
            @Override
            public void onChanged(WeatherDetailUIState weatherDetailUIState) {

                showLoadingView(false);

                switch (weatherDetailUIState) {
                    case ERROR:
                        showError();
                        break;
                    case NO_RESULTS_FOUND:
                        break;
                    case LOADING_WEATHER_DATA:
                        showLoadingView(true);
                        break;
                    case FETCH_WEATHER_DATA_SUCCESS:
                        break;
                    default:
                        break;
                }
            }
        });

    }

    private ArrayList<WeatherDetail> generateWeatherDetail(WeatherData weatherData) {
        ArrayList<WeatherDetail> weatherDetails = new ArrayList<>();
        weatherDetails.add(new WeatherDetail(getString(R.string.wind), weatherData.getFormattedWindData()));
        weatherDetails.add(new WeatherDetail(getString(R.string.cloudiness), weatherData.getWeatherdescription()));
        weatherDetails.add(new WeatherDetail(getString(R.string.pressure), weatherData.getPressure()));
        weatherDetails.add(new WeatherDetail(getString(R.string.humidity), weatherData.getHumidity()));
        weatherDetails.add(new WeatherDetail(getString(R.string.sunrise), weatherData.getSunrise()));
        weatherDetails.add(new WeatherDetail(getString(R.string.sunset), weatherData.getSunset()));
        weatherDetails.add(new WeatherDetail(getString(R.string.geo_coords), weatherData.getFormattedCoordinates()));

        return weatherDetails;
    }

    private void updateDetails(WeatherData weatherData) {
        tvTitle.setText(weatherData.getName());
        tvActualWeather.setText(weatherData.getActualWeather());
        tvTemperature.setText(weatherData.getTemperature());
        adapter.refreshDetail(generateWeatherDetail(weatherData));

        Glide.with(requireContext())
                .applyDefaultRequestOptions(requestOptions)
                .load( weatherData.getWeatherIconUrl())
                .into(ivWeatherIcon);
    }


    private void showError() {
        if(isAdded()) {
            new AlertDialog.Builder(requireContext())
                    .setMessage(getString(R.string.error_fetching_weather_data))
                    .setPositiveButton(getString(android.R.string.ok), null)
                    .create()
                    .show();
        }
    }

    private void showLoadingView(boolean show) {
        if(isAdded()) {
            int visibility = show ? View.VISIBLE : View.INVISIBLE;
            pbWeatherDetail.setVisibility(visibility);
        }
    }

    @Subscribe(threadMode = ThreadMode.MAIN)
    public void onRefreshData(RefreshDataEvent event) {
        weatherDetailViewModel.refreshData();
    }


    @Override
    public void onStart() {
        super.onStart();
        EventBus.getDefault().register(this);
    }

    @Override
    public void onStop() {
        EventBus.getDefault().unregister(this);
        super.onStop();
    }

    @Override
    public void onDestroy() {
        super.onDestroy();
        if(weatherDetailViewModel != null)
            weatherDetailViewModel.disposeNetworkCalls();
    }

}
