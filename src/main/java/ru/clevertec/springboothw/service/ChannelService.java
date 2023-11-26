package ru.clevertec.springboothw.service;

import org.springframework.data.domain.Pageable;
import ru.clevertec.springboothw.dto.channel.ChannelRequest;
import ru.clevertec.springboothw.dto.channel.ChannelResponse;
import ru.clevertec.springboothw.dto.channel.ChannelResponseFull;
import java.util.List;

public interface ChannelService {
    List<ChannelResponse> findAllByNameContainingIgnoreCase(String name, Pageable pageable);
    List<ChannelResponse> findAllByLanguageContainingIgnoreCase(String language, Pageable pageable);
    List<ChannelResponse> findAllByCategoryContainingIgnoreCase(String category, Pageable pageable);
    ChannelResponseFull findById(Long id);
    ChannelResponseFull save(ChannelRequest request,Long authorId);
    ChannelResponseFull update(ChannelRequest request,Long channelId);
    ChannelResponse subscribeToChannel(Long channelId,Long personId);
    ChannelResponse unSubscribeFromChannel(Long channelId,Long personId);
}
