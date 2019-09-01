package com.svaps.bot.slackbot.domain.entity;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class SlackEvent {
  private String token;
  private String team_id;
  private String api_app_id;
  private String event_id;
  private AppMention event;
}
