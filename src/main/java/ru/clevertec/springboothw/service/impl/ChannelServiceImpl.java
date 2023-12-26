package ru.clevertec.springboothw.service.impl;

import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.domain.Specification;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.multipart.MultipartFile;
import ru.clevertec.springboothw.dto.channel.ChannelRequest;
import ru.clevertec.springboothw.dto.channel.ChannelResponse;
import ru.clevertec.springboothw.dto.channel.ChannelResponseFull;
import ru.clevertec.springboothw.exception.ChannelNotFoundException;
import ru.clevertec.springboothw.exception.PersonNotFoundException;
import ru.clevertec.springboothw.mapper.ChannelListMapper;
import ru.clevertec.springboothw.mapper.ChannelMapper;
import ru.clevertec.springboothw.model.Channel;
import ru.clevertec.springboothw.repository.ChannelRepository;
import ru.clevertec.springboothw.repository.UserRepository;
import ru.clevertec.springboothw.service.ChannelService;

import java.time.LocalDateTime;
import java.util.HashSet;
import java.util.List;

import static ru.clevertec.springboothw.repository.specification.ChannelSpecification.*;

@Service
@Transactional
@RequiredArgsConstructor
public class ChannelServiceImpl implements ChannelService {
    private final UserRepository userRepository;
    private final ChannelRepository channelRepository;
    private final ChannelMapper channelMapper;
    private final ChannelListMapper channelListMapper;
    private final FileService fileService;

    @Override
    public List<ChannelResponse> findAllByFilters(String name, String language, String category, Pageable pageable) {
        return channelListMapper
                .toResponse(channelRepository
                        .findAll(Specification.where(hasName(name)).and(hasLanguage(language)).and(hasCategory(category))
                                , pageable).getContent());
    }

    @Override
    public ChannelResponseFull findById(Long id) {
        return channelRepository.findById(id)
                .map(channelMapper::toResponseFull)
                .orElseThrow(() -> new ChannelNotFoundException("Channel with id: %s not found".formatted(id)));
    }

    @Override
    public ChannelResponseFull save(ChannelRequest channelRequest, MultipartFile file) {
        Channel channel = channelRepository
                .save(channelMapper
                        .fromRequest(channelRequest)
                        .setCreatedDate(LocalDateTime.now())
                        .setSubscribers(new HashSet<>())
                        .setLogo(fileService.uploadFileAndDecodeToBase64(file))
                        .setChannelOwner(userRepository
                                .getUserById(channelRequest.authorId())
                                .orElseThrow(() -> new PersonNotFoundException("Person with id: %s not found".formatted(channelRequest.authorId())))));

        return channelMapper
                .toResponseFull(channel);


    }

    @Override
    public ChannelResponseFull update(ChannelRequest request, Long channelId, MultipartFile file) {
        return channelRepository.findById(channelId)
                .map(ch -> channelMapper.updateFromRequest(request, ch))
                .map(ch -> ch.setLogo(fileService.uploadFileAndDecodeToBase64(file)))
                .map(channelRepository::save)
                .map(channelMapper::toResponseFull)
                .orElseThrow(() -> new ChannelNotFoundException("Channel with id: %s not found".formatted(channelId)));
    }

    @Override
    public ChannelResponse subscribeToChannel(Long channelId, Long personId) {
        return channelRepository.findById(channelId)
                .map(x -> {
                    x.getSubscribers().add(userRepository.getUserById(personId)
                            .orElseThrow(() -> new PersonNotFoundException("Person with id: %s not found"
                                    .formatted(personId))));
                    return x;
                })
                .map(channelRepository::save)
                .map(channelMapper::toResponse)
                .orElseThrow(() -> new ChannelNotFoundException("Channel with id: %s not found".formatted(channelId)));
    }

    @Override
    public ChannelResponse unSubscribeFromChannel(Long channelId, Long personId) {
        return channelRepository.findById(channelId)
                .map(x -> {
                    x.getSubscribers().remove(userRepository.getUserById(personId)
                            .orElseThrow(() -> new PersonNotFoundException("Person with id: %s not found"
                                    .formatted(personId))));
                    return x;
                })
                .map(channelRepository::save)
                .map(channelMapper::toResponse)
                .orElseThrow(() -> new ChannelNotFoundException("Channel with id: %s not found".formatted(channelId)));
    }
}
