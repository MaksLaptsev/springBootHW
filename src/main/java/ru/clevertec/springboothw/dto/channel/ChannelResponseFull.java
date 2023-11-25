package ru.clevertec.springboothw.dto.channel;

import com.fasterxml.jackson.annotation.JsonFormat;

import java.time.LocalDateTime;

public record ChannelResponseFull(Long id,
                                  @JsonFormat(shape = JsonFormat.Shape.STRING, pattern = "yyyy-MM-dd'T'HH:mm:ss")
                                  LocalDateTime createdDate,
                                  String name,
                                  String language,
                                  String category,
                                  String shortDescription,
                                  Long authorId) {
}
