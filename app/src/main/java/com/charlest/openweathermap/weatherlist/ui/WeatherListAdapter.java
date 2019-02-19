package com.charlest.openweathermap.weatherlist.ui;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.charlest.openweathermap.R;
import com.charlest.openweathermap.databinding.WeatherListItemBinding;
import com.charlest.openweathermap.roomdb.entities.WeatherData;

import java.util.ArrayList;
import java.util.List;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

public class WeatherListAdapter extends RecyclerView.Adapter<WeatherListAdapter.WeatherListViewHolder> {

    private List<WeatherData> weatherDataList = new ArrayList<>();

    private IOnWeatherDataClick mOnWeatherDataClick;

    public WeatherListAdapter(IOnWeatherDataClick iOnWeatherDataClick) {
        mOnWeatherDataClick = iOnWeatherDataClick;
    }

    public void refreshData(List<WeatherData> weatherData) {
        this.weatherDataList = weatherData;
        notifyDataSetChanged();
    }

    @NonNull
    @Override
    public WeatherListViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        return new WeatherListViewHolder(WeatherListItemBinding.bind(LayoutInflater.from(parent.getContext()).inflate(R.layout.weather_list_item, parent, false)));
    }

    @Override
    public void onBindViewHolder(@NonNull WeatherListViewHolder holder, int position) {
        holder.bind(weatherDataList.get(position), mOnWeatherDataClick);
    }

    @Override
    public int getItemCount() {
        return weatherDataList == null ? 0 : weatherDataList.size();
    }

    class WeatherListViewHolder extends RecyclerView.ViewHolder {

        WeatherListItemBinding binding;

        public WeatherListViewHolder(WeatherListItemBinding binding) {
            super(binding.getRoot());

            this.binding = binding;
        }

        void bind(WeatherData weatherData, IOnWeatherDataClick iOnWeatherDataClick) {
            binding.setWeatherData(weatherData);
            binding.setClickCallback(iOnWeatherDataClick);
            binding.executePendingBindings();
        }
    }
}
