package com.charlest.openweathermap.weatherdetail.ui;

import android.text.Layout;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.charlest.openweathermap.R;
import com.charlest.openweathermap.databinding.WeatherDetailItemBinding;
import com.charlest.openweathermap.weatherdetail.data.WeatherDetail;

import java.util.ArrayList;
import java.util.List;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

public class WeatherDetailRecyclerViewAdapter extends RecyclerView.Adapter<WeatherDetailRecyclerViewAdapter.WeatherDetailViewHolder> {

    private List<WeatherDetail> weatherDetails = new ArrayList<>();

    public void refreshDetail(List<WeatherDetail> weatherDetails) {
        this.weatherDetails = weatherDetails;
        notifyDataSetChanged();
    }

    @NonNull
    @Override
    public WeatherDetailViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        return new WeatherDetailViewHolder(WeatherDetailItemBinding.bind(LayoutInflater.from(parent.getContext()).inflate(R.layout.weather_detail_item, parent, false)));
    }

    @Override
    public void onBindViewHolder(@NonNull WeatherDetailViewHolder holder, int position) {
        holder.bind(weatherDetails.get(position));
    }

    @Override
    public int getItemCount() {
        return weatherDetails == null ? 0 : weatherDetails.size();
    }

    class WeatherDetailViewHolder extends RecyclerView.ViewHolder {

        private WeatherDetailItemBinding binding;

        public WeatherDetailViewHolder(WeatherDetailItemBinding binding) {
            super(binding.getRoot());
            this.binding = binding;
        }

        void bind(WeatherDetail weatherDetail) {
            binding.setWeatherdetail(weatherDetail);
            binding.executePendingBindings();
        }
    }
}
