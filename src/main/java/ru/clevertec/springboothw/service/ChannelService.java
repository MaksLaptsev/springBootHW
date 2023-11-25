package ru.clevertec.springboothw.service;

import org.springframework.data.domain.Pageable;
import ru.clevertec.springboothw.dto.channel.ChannelRequest;
import ru.clevertec.springboothw.dto.channel.ChannelResponse;
import ru.clevertec.springboothw.dto.channel.ChannelResponseFull;
import ru.clevertec.springboothw.dto.channel.ChannelResponseOnlyNames;
import ru.clevertec.springboothw.model.Channel;

import java.util.List;
import java.util.Map;
import java.util.Optional;

public interface ChannelService {
    List<ChannelResponse> findAllByNameContainingIgnoreCase(String name, Pageable pageable);
    List<ChannelResponse> findAllByLanguageContainingIgnoreCase(String language, Pageable pageable);
    List<ChannelResponse> findAllByCategoryContainingIgnoreCase(String category, Pageable pageable);
    ChannelResponseFull findById(Long id);
    ChannelResponseFull save(ChannelRequest request);
    ChannelResponseFull update(Long id,ChannelRequest request);
    ChannelResponseOnlyNames findAllByUserId(Long id);
}
