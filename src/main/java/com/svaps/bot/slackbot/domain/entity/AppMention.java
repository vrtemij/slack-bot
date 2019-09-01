package com.svaps.bot.slackbot.domain.entity;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class AppMention {
  private String type;
  private String user;
  private String text;
  private String ts;
  private String channel;
  private String event_ts;
}
