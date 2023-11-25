package ru.clevertec.springboothw.service.impl;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import ru.clevertec.springboothw.dto.channel.ChannelRequest;
import ru.clevertec.springboothw.dto.channel.ChannelResponse;
import ru.clevertec.springboothw.dto.channel.ChannelResponseFull;
import ru.clevertec.springboothw.dto.channel.ChannelResponseOnlyNames;
import ru.clevertec.springboothw.mapper.ChannelListMapper;
import ru.clevertec.springboothw.mapper.ChannelMapper;
import ru.clevertec.springboothw.model.Channel;
import ru.clevertec.springboothw.repository.ChannelRepository;
import ru.clevertec.springboothw.repository.UserRepository;
import ru.clevertec.springboothw.service.ChannelService;

import java.time.LocalDateTime;
import java.util.List;
import java.util.Map;

@Service
@Transactional
public class ChannelServiceImpl implements ChannelService {
    private UserRepository userRepository;
    private ChannelRepository channelRepository;
    private ChannelMapper channelMapper;
    private ChannelListMapper channelListMapper;

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
        return channelListMapper.toResponse(channelRepository.findAllByNameContainingIgnoreCase(name,pageable));
    }

    @Override
    public List<ChannelResponse> findAllByLanguageContainingIgnoreCase(String language, Pageable pageable) {
        return channelListMapper.toResponse(channelRepository.findAllByNameContainingIgnoreCase(language,pageable));
    }

    @Override
    public List<ChannelResponse> findAllByCategoryContainingIgnoreCase(String category, Pageable pageable) {
        return channelListMapper.toResponse(channelRepository.findAllByNameContainingIgnoreCase(category,pageable));
    }

    @Override
    public ChannelResponseFull findById(Long id) {
        return channelMapper.toResponse(channelRepository.findById(id).orElseThrow(()->new RuntimeException()), 1L);
    }

    @Override
    public ChannelResponseFull save(ChannelRequest channelRequest) {
        return channelMapper.toResponse(channelRepository.saveAndFlush(channelMapper.fromRequest(channelRequest).setCreatedDate(LocalDateTime.now())),1L);
    }

    @Override
    public ChannelResponseFull update(Long id, ChannelRequest channelRequest) {
        return null;
    }

    @Override
    public ChannelResponseOnlyNames findAllByUserId(Long id) {
        return null;
    }


}
