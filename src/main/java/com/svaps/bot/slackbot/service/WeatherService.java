package com.svaps.bot.slackbot.service;

import com.svaps.bot.slackbot.domain.entity.WeatherInfo;
import net.aksingh.owmjapis.api.APIException;

import javax.xml.bind.JAXBException;

public interface WeatherService {
  WeatherInfo getWeatherInfo(String city, String channel) throws JAXBException, APIException;
}
