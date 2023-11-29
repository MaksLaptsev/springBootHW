package ru.clevertec.springboothw.mapper;

import org.mapstruct.Mapper;
import org.mapstruct.Mapping;
import ru.clevertec.springboothw.dto.channel.ChannelResponseFull;
import ru.clevertec.springboothw.dto.channel.ChannelResponseOnlyNames;
import ru.clevertec.springboothw.dto.channel.ChannelRequest;
import ru.clevertec.springboothw.dto.channel.ChannelResponse;
import ru.clevertec.springboothw.model.Channel;
import ru.clevertec.springboothw.service.impl.FileService;

@Mapper(componentModel = "spring")
public interface ChannelMapper {
    @Mapping(target = "numberOfSubscribers",expression = "java(channel.getSubscribers().size())")
    ChannelResponse toResponse(Channel channel);
    @Mapping(target = "numberOfSubscribers",expression = "java(channel.getSubscribers().size())")
    @Mapping(target = "logoBase64",expression = "java(fileService.getFileFromStorageBase64(channel.getFileName()))")
    ChannelResponseFull toResponseFull(Channel channel,FileService fileService);
    Channel fromRequest(ChannelRequest request);
    ChannelResponseOnlyNames toResponseOnlyNames(Channel channel);
    @Mapping(target = "id",source = "channel.id")
    @Mapping(target = "createdDate",source = "channel.createdDate")
    @Mapping(target = "channelOwner",source = "channel.channelOwner")
    @Mapping(target = "subscribers",source = "channel.subscribers")
    @Mapping(target = "name",source = "request.name")
    @Mapping(target = "shortDescription",source = "request.shortDescription")
    @Mapping(target = "language",source = "request.language")
    @Mapping(target = "category",source = "request.category")
    Channel updateFromRequest(ChannelRequest request,Channel channel);

}
