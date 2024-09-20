package com.karthik.weather.service;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;
import org.springframework.web.reactive.function.client.WebClient;

import com.karthik.weather.model.WeatherInfo;

@Service
public class AppService implements AppServiceImpl{
	
	@Value("${app.weather.api-key}")
	private String weatherApiKey;

	@Override
	public WeatherInfo getLocationWeatherInfo(String locationName) {
		// TODO Auto-generated method stub
		
		String url = "https://api.weatherapi.com/v1/current.json?key="+weatherApiKey+"&q="+locationName.trim()+"&aqi=no";
		

		System.out.println("url: "+url);
		
		WebClient.Builder builder = WebClient.builder();
		WeatherInfo weatherInfo = new WeatherInfo();
		try {

			weatherInfo = builder.build()
					.get()
					.uri(url)
					.retrieve()
					.bodyToMono(WeatherInfo.class)
					.block();
		} catch (Exception e) {
			// TODO: handle exception
		}
		
		System.out.println("weatherInfo: "+weatherInfo);
		
		try {
			if(weatherInfo.getLocation() == null) {
				weatherInfo = null;
			}else {
				System.out.println("----------------------------------------");
				System.out.println(weatherInfo.toString());
				System.out.println("Country: "+weatherInfo.getLocation().getCountry());
				System.out.println("Location Name: "+weatherInfo.getLocation().getName());
				System.out.println("Condition: "+weatherInfo.getCurrent().getCondition().getText());
				System.out.println("Temperature Celsius: "+weatherInfo.getCurrent().getTemp_c());
				System.out.println("----------------------------------------");
			}
		} catch (Exception e) {
			// TODO: handle exception
		}
		return weatherInfo;
	}

}
