package ru.clevertec.springboothw.service.impl;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import ru.clevertec.springboothw.dto.channel.ChannelRequest;
import ru.clevertec.springboothw.dto.channel.ChannelResponse;
import ru.clevertec.springboothw.dto.channel.ChannelResponseFull;
import ru.clevertec.springboothw.exception.ChannelNotFoundException;
import ru.clevertec.springboothw.exception.PersonNotFoundException;
import ru.clevertec.springboothw.mapper.ChannelListMapper;
import ru.clevertec.springboothw.mapper.ChannelMapper;
import ru.clevertec.springboothw.repository.ChannelRepository;
import ru.clevertec.springboothw.repository.UserRepository;
import ru.clevertec.springboothw.service.ChannelService;
import java.time.LocalDateTime;
import java.util.List;


@Service
@Transactional
public class ChannelServiceImpl implements ChannelService {
    private final UserRepository userRepository;
    private final ChannelRepository channelRepository;
    private final ChannelMapper channelMapper;
    private final ChannelListMapper channelListMapper;

    @Autowired
    public ChannelServiceImpl(UserRepository userRepository, ChannelRepository channelRepository,
                              ChannelMapper channelMapper, ChannelListMapper channelListMapper) {
        this.userRepository = userRepository;
        this.channelRepository = channelRepository;
        this.channelMapper = channelMapper;
        this.channelListMapper = channelListMapper;
    }

    @Override
    public List<ChannelResponse> findAllByNameContainingIgnoreCase(String name, Pageable pageable) {
        return channelListMapper
                .toResponse(channelRepository.findAllByNameContainingIgnoreCase(name,pageable));
    }

    @Override
    public List<ChannelResponse> findAllByLanguageContainingIgnoreCase(String language, Pageable pageable) {
        return channelListMapper
                .toResponse(channelRepository.findAllByLanguageContainingIgnoreCase(language,pageable));
    }

    @Override
    public List<ChannelResponse> findAllByCategoryContainingIgnoreCase(String category, Pageable pageable) {
        return channelListMapper
                .toResponse(channelRepository.findAllByCategoryContainingIgnoreCase(category,pageable));
    }

    @Override
    public ChannelResponseFull findById(Long id) {
        return channelRepository.findById(id)
                .map(channelMapper::toResponseFull)
                .orElseThrow(()->new ChannelNotFoundException("Channel with id: %s not found".formatted(id)));
    }

    @Override
    public ChannelResponseFull save(ChannelRequest channelRequest,Long authorId) {
        return channelMapper.toResponseFull(channelRepository.saveAndFlush(channelMapper
                        .fromRequest(channelRequest)
                        .setCreatedDate(LocalDateTime.now())
                        .setChannelOwner(userRepository.getUserById(authorId)
                                .orElseThrow(()-> new PersonNotFoundException("Person with id: %s not found"
                                        .formatted(authorId))))));
    }

    @Override
    public ChannelResponseFull update(ChannelRequest request, Long channelId) {
        return channelRepository.findById(channelId)
                .map(ch-> channelMapper.updateFromRequest(request,ch))
                .map(channelRepository::saveAndFlush)
                .map(channelMapper::toResponseFull)
                .orElseThrow(()->new ChannelNotFoundException("Channel with id: %s not found".formatted(channelId)));
    }

    @Override
    public ChannelResponse subscribeToChannel(Long channelId, Long personId) {
        return channelRepository.findById(channelId)
                .map(x-> {
                    x.getSubscribers().add(userRepository.getUserById(personId)
                            .orElseThrow(()-> new PersonNotFoundException("Person with id: %s not found"
                                    .formatted(personId))));
                    return x;
                })
                .map(channelMapper::toResponse)
                .orElseThrow(()->new ChannelNotFoundException("Channel with id: %s not found".formatted(channelId)));
    }

    @Override
    public ChannelResponse unSubscribeFromChannel(Long channelId, Long personId) {
        return channelRepository.findById(channelId)
                .map(x-> {
                    x.getSubscribers().remove(userRepository.getUserById(personId)
                            .orElseThrow(()-> new PersonNotFoundException("Person with id: %s not found"
                                    .formatted(personId))));
                    return x;
                })
                .map(channelMapper::toResponse)
                .orElseThrow(()->new ChannelNotFoundException("Channel with id: %s not found".formatted(channelId)));
    }
}
