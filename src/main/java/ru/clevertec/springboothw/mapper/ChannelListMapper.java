package ru.clevertec.springboothw.mapper;

import org.mapstruct.Mapper;
import ru.clevertec.springboothw.dto.channel.ChannelResponseOnlyNames;
import ru.clevertec.springboothw.dto.channel.ChannelResponse;
import ru.clevertec.springboothw.model.Channel;
import java.util.List;

@Mapper(componentModel = "spring", uses = ChannelMapper.class)
public interface ChannelListMapper {
    List<ChannelResponse> toResponse(List<Channel> channels);
    List<ChannelResponseOnlyNames> toResponseOnlyNames(List<Channel> channels);

}
