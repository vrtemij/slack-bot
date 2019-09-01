package com.svaps.bot.slackbot.service;

import com.svaps.bot.slackbot.domain.entity.WeatherInfo;
import com.svaps.bot.slackbot.domain.request.RequestMessage;
import lombok.extern.slf4j.Slf4j;
import net.aksingh.owmjapis.api.APIException;
import net.aksingh.owmjapis.core.OWM;
import net.aksingh.owmjapis.model.CurrentWeather;
import org.springframework.http.HttpEntity;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpMethod;
import org.springframework.http.MediaType;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;

import java.util.Collections;

@Service
@Slf4j
public class WeatherServiceImpl implements WeatherService {
    private static final String CITY_NOT_FOUND_MESSAGE = "Sorry, we dont have information about city %s";
    private static final String BOT_USER_TOKEN = "xoxb-601709616855-733490461891-taLyo1a6AhqzoGq08JLsLo0J";
    private static final String POST_MESSAGE_URL = "https://slack.com/api/chat.postMessage";
    private static final String API_KEY = "c10d262e3cd3541ce8012683e2b16ae8";
    private static final double DIFF = 273.15;

    @Override
    public WeatherInfo getWeatherInfo(String message, String channel) {

        String city = getCityFromString(message);
        OWM owm = new OWM(API_KEY);
        CurrentWeather currentWeather;
        WeatherInfo weatherInfo = null;
        try {
            currentWeather = owm.currentWeatherByCityName(city);
            weatherInfo = WeatherInfo.builder()
                .degree(Math.round(currentWeather.getMainData().getTemp() - DIFF))
                .information(currentWeather.getWeatherList().get(0).getMoreInfo())
                .build();
        } catch (APIException e) {
            log.error(e.getMessage());
        }

        RequestMessage requestMessage =  RequestMessage.builder()
            .text(weatherInfo != null
                ? weatherInfo.toString()
                : String.format(CITY_NOT_FOUND_MESSAGE, city))
            .channel(channel)
            .build();
        sendMessage(requestMessage);
        return weatherInfo;
    }

    private String getCityFromString(String message) {
        int startIndex = message.indexOf(" in ");
        int endIndex = message.indexOf("?", startIndex);
        if (endIndex == -1) {
            endIndex = message.length();
        }
        return message.substring(startIndex + 4, endIndex);
    }


    private void sendMessage(RequestMessage requestMessage) {
        try {
            RestTemplate restTemplate = new RestTemplate();
            HttpHeaders headers = new HttpHeaders();
            headers.set("Authorization", "Bearer "+ BOT_USER_TOKEN);
            headers.setAccept((Collections.singletonList(MediaType.APPLICATION_JSON)));
            HttpEntity<RequestMessage> entity = new HttpEntity<>(requestMessage, headers);
            restTemplate.exchange(POST_MESSAGE_URL, HttpMethod.POST, entity, String.class);
        } catch (Exception e) {
            log.error(e.getMessage());
        }

    }

}
