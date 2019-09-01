package com.svaps.bot.slackbot.domain.entity;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@Builder
@AllArgsConstructor
@NoArgsConstructor
public class WeatherInfo {
  private String information;
  private double degree;

  @Override
  public String toString() {
    return String.format("It's currentrly %s, temperature is %s degrees", information, degree);
  }
}
