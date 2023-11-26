package ru.clevertec.springboothw.dto.channel;

import com.fasterxml.jackson.annotation.JsonFormat;
import ru.clevertec.springboothw.dto.user.UserResponse;

import java.time.LocalDateTime;

public record ChannelResponseFull(Long id,
                                  @JsonFormat(shape = JsonFormat.Shape.STRING, pattern = "yyyy-MM-dd")
                                  LocalDateTime createdDate,
                                  String name,
                                  String language,
                                  String category,
                                  String shortDescription,
                                  UserResponse channelOwner,
                                  Integer numberOfSubscribers) {
}
