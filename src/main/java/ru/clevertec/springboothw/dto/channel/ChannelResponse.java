package ru.clevertec.springboothw.dto.channel;

public record ChannelResponse(Long id,
                              String name,
                              String language,
                              String category,
                              Integer numberOfSubscribers,
                              String logoBase64) {

}
