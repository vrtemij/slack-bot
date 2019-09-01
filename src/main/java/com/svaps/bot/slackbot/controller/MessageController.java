package com.svaps.bot.slackbot.controller;

import com.svaps.bot.slackbot.domain.entity.SlackEvent;
import com.svaps.bot.slackbot.domain.entity.WeatherInfo;
import com.svaps.bot.slackbot.service.WeatherService;
import net.aksingh.owmjapis.api.APIException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import javax.xml.bind.JAXBException;

@RestController
@RequestMapping("api/weather")
public class MessageController {

  @Autowired
  private WeatherService weatherService;

  @PostMapping
  public WeatherInfo sendWeather(@RequestBody SlackEvent slackEvent) throws JAXBException, APIException {
    return weatherService.getWeatherInfo(slackEvent.getEvent().getText(), slackEvent.getEvent().getChannel());
  }

}
