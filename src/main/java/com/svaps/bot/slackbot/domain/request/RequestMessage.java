package com.svaps.bot.slackbot.domain.request;

import lombok.Builder;
import lombok.Data;

@Data
@Builder
public class RequestMessage {
  private String text;
  private String channel;
}
