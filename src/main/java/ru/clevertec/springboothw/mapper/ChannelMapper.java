package ru.clevertec.springboothw.mapper;

import org.mapstruct.Mapper;
import ru.clevertec.springboothw.dto.channel.ChannelResponseFull;
import ru.clevertec.springboothw.dto.channel.ChannelResponseOnlyNames;
import ru.clevertec.springboothw.dto.channel.ChannelRequest;
import ru.clevertec.springboothw.dto.channel.ChannelResponse;
import ru.clevertec.springboothw.model.Channel;

@Mapper(componentModel = "spring")
public interface ChannelMapper {
    ChannelResponse toResponse(Channel channel);
    ChannelResponseFull toResponse(Channel channel,Long authorId);
    Channel fromRequest(ChannelRequest request);
    Channel fromRequest(ChannelRequest request, Long id);
    ChannelResponseOnlyNames toResponseOnlyNames(Channel channel);

}
