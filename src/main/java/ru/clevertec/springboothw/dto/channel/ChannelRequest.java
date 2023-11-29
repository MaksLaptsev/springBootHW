package ru.clevertec.springboothw.dto.channel;

import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.Size;

public record ChannelRequest(@NotBlank
                             @Size(max = 30)
                             String name,
                             @Size(max = 350)
                             String shortDescription,
                             @NotBlank
                             @Size(max = 30)
                             String language,
                             @NotBlank
                             @Size(max = 30)
                             String category) {
}
