package com.karthik.weather.service;

import com.karthik.weather.model.WeatherInfo;

public interface AppServiceImpl {

	WeatherInfo getLocationWeatherInfo(String locationName);

}
