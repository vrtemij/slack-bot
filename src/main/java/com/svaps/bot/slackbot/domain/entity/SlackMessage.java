package com.svaps.bot.slackbot.domain.entity;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class SlackMessage {
  private String command;
  private String text;
  private String response_url;
  private String trigger_id;
}
