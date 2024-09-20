package com.karthik.weather.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.servlet.ModelAndView;

import com.karthik.weather.model.WeatherInfo;
import com.karthik.weather.service.AppServiceImpl;

@Controller
public class AppController {
	
	@Autowired
	private AppServiceImpl appServiceImpl;
	
	@GetMapping("/")
	public ModelAndView getHomePage() {
		System.out.println("Inside getHomePage Method");
		System.out.println("--------------------------------");
		ModelAndView mv = new ModelAndView();
		mv.setViewName("index.html");
		System.out.println("--------------------------------");
		return mv;
	}
	
	@GetMapping("/searchLocation")
	public ModelAndView getLocationInfo(
			@RequestParam("locationName") String locationName
			) {
		ModelAndView mv = new ModelAndView();
		System.out.println("Inside getLocationInfo Method");
		System.out.println("--------------------------------");
		System.out.println("locationName: "+locationName);
		
		if(locationName.isBlank() || locationName.isEmpty() || locationName == null) {
			System.out.println("locationName is empty");
			mv.addObject("errorlocationsearchbar", "Location name is required!");
			mv.setViewName("index.html");
			return mv;
		}
		WeatherInfo weatherInfo = appServiceImpl.getLocationWeatherInfo(locationName);
		
		if(weatherInfo == null) {
			mv.addObject("errorlocationsearchbar", "Provide a valid location!");
		}else {
			mv.addObject("weatherInfo", weatherInfo);
			mv.addObject("locationName", weatherInfo.getLocation().getName().trim());
		}
		mv.setViewName("index.html");
		System.out.println("--------------------------------");
		return mv;
	}
	
}
