package com.charlest.openweathermap.weatherlist.ui;


import android.content.Context;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.LinearLayout;
import android.widget.ProgressBar;

import com.charlest.openweathermap.R;
import com.charlest.openweathermap.RefreshDataEvent;
import com.charlest.openweathermap.roomdb.entities.WeatherData;

import org.greenrobot.eventbus.EventBus;
import org.greenrobot.eventbus.Subscribe;
import org.greenrobot.eventbus.ThreadMode;

import java.util.List;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.appcompat.app.AlertDialog;
import androidx.fragment.app.Fragment;
import androidx.lifecycle.Observer;
import androidx.lifecycle.ViewModelProviders;
import androidx.recyclerview.widget.DividerItemDecoration;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

public class WeatherListFragment extends Fragment implements IOnWeatherDataClick {

    private WeatherListAdapter weatherListAdapter;

    private WeatherListViewModel weatherListViewModel;

    private ProgressBar pbLoading;

    private LinearLayout emptyView;

    private RecyclerView rvWeatherList;

    @Override
    public void onAttach(@NonNull Context context) {
        super.onAttach(context);
    }

    @Override
    public void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        weatherListAdapter = new WeatherListAdapter(this);

        weatherListViewModel = ViewModelProviders.of(this).get(WeatherListViewModel.class);
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        return inflater.inflate(R.layout.fragment_weather_list, container, false);
    }

    @Override
    public void onViewCreated(@NonNull View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);

        rvWeatherList = view.findViewById(R.id.rvWeatherList);
        pbLoading = view.findViewById(R.id.pbWeatherList);
        emptyView = view.findViewById(R.id.emptyView);

        rvWeatherList.setAdapter(weatherListAdapter);
        rvWeatherList.setLayoutManager(new LinearLayoutManager(requireActivity(), RecyclerView.VERTICAL, false));
        rvWeatherList.addItemDecoration(new DividerItemDecoration(requireContext(), RecyclerView.VERTICAL));

        weatherListViewModel.getWeatherList().observe(this, new Observer<List<WeatherData>>() {
            @Override
            public void onChanged(List<WeatherData> weatherData) {
                weatherListAdapter.refreshData(weatherData);
            }
        });

        weatherListViewModel.getWeatherListUIStateMutableLiveData().observe(this, new Observer<WeatherListUIState>() {
            @Override
            public void onChanged(WeatherListUIState weatherListUIState) {

                showLoadingView(false);
                showEmptyView(false);

                switch (weatherListUIState) {
                    case ERROR:
                        showError();
                        break;
                    case NO_RESULTS_FOUND:
                        showEmptyView(true);
                        break;
                    case LOADING_WEATHER_DATA:
                        showLoadingView(true);
                        break;
                    case FETCH_WEATHER_DATA_SUCCESS:
                        showEmptyView(false);
                        break;
                    default:
                        break;
                }
            }
        });


        weatherListViewModel.refreshData();

    }

    private void showError() {
        if(isAdded()) {
            new AlertDialog.Builder(requireActivity())
                    .setMessage(getString(R.string.error_fetching_weather_data))
                    .setPositiveButton(getString(android.R.string.ok), null)
                    .create()
                    .show();
        }
    }

    private void showLoadingView(boolean show) {
        if(isAdded()) {
            int visibility = show ? View.VISIBLE : View.INVISIBLE;
            pbLoading.setVisibility(visibility);
        }
    }

    private void showEmptyView(boolean show) {
        if(isAdded()) {
            int visibility = show ? View.VISIBLE : View.INVISIBLE;
            emptyView.setVisibility(visibility);
        }
    }

    @Subscribe(threadMode = ThreadMode.MAIN)
    public void onRefreshData(RefreshDataEvent event) {
        weatherListViewModel.refreshData();
    }


    @Override
    public void onWeatherDataClicked(WeatherData weatherData) {

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
        if(weatherListViewModel != null)
            weatherListViewModel.disposeNetworkCalls();
    }
}
