package ru.clevertec.springboothw.mapper;

import org.mapstruct.*;
import org.springframework.beans.factory.annotation.Autowired;
import ru.clevertec.springboothw.dto.channel.ChannelResponseFull;
import ru.clevertec.springboothw.dto.channel.ChannelResponseOnlyNames;
import ru.clevertec.springboothw.dto.channel.ChannelRequest;
import ru.clevertec.springboothw.dto.channel.ChannelResponse;
import ru.clevertec.springboothw.model.Channel;
import ru.clevertec.springboothw.service.impl.FileService;

@Mapper(componentModel = "spring",unmappedTargetPolicy = ReportingPolicy.IGNORE)
public abstract class ChannelMapper {

    protected FileService fileservice;

    @Mapping(target = "numberOfSubscribers",expression = "java(channel.getSubscribers().size())")
    @Mapping(target = "logoBase64",expression = "java(fileservice.getFileFromStorageBase64(channel.getFileName()))")
    public abstract ChannelResponse toResponse(Channel channel);


    @Mapping(target = "numberOfSubscribers",expression = "java(channel.getSubscribers().size())")
    @Mapping(target = "logoBase64",expression = "java(fileservice.getFileFromStorageBase64(channel.getFileName()))")
    public abstract ChannelResponseFull toResponseFull(Channel channel);
    public abstract Channel fromRequest(ChannelRequest request);
    abstract ChannelResponseOnlyNames toResponseOnlyNames(Channel channel);
    @Mapping(target = "id",source = "channel.id")
    @Mapping(target = "createdDate",source = "channel.createdDate")
    @Mapping(target = "channelOwner",source = "channel.channelOwner")
    @Mapping(target = "subscribers",source = "channel.subscribers")
    @Mapping(target = "name",source = "request.name")
    @Mapping(target = "shortDescription",source = "request.shortDescription")
    @Mapping(target = "language",source = "request.language")
    @Mapping(target = "category",source = "request.category")
    public abstract Channel updateFromRequest(ChannelRequest request, Channel channel);

    @Autowired
    public void setBean(FileService fileservice){
        this.fileservice = fileservice;
        }
}
