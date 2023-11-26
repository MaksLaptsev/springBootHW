package ru.clevertec.springboothw.service.impl;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import ru.clevertec.springboothw.dto.channel.ChannelResponseOnlyNames;
import ru.clevertec.springboothw.dto.user.UserRequest;
import ru.clevertec.springboothw.dto.user.UserResponse;
import ru.clevertec.springboothw.exception.PersonNotFoundException;
import ru.clevertec.springboothw.mapper.ChannelListMapper;
import ru.clevertec.springboothw.mapper.UserMapper;
import ru.clevertec.springboothw.repository.UserRepository;
import ru.clevertec.springboothw.service.UserService;
import java.util.List;

@Service
@Transactional
public class UserServiceImpl implements UserService {

    private final UserRepository userRepository;
    private final UserMapper userMapper;

    private final ChannelListMapper channelListMapper;

    @Autowired
    public UserServiceImpl(UserRepository userRepository, UserMapper userMapper,ChannelListMapper channelListMapper) {
        this.userRepository = userRepository;
        this.userMapper = userMapper;
        this.channelListMapper = channelListMapper;
    }

    @Override
    public UserResponse save(UserRequest user) {
        return userMapper.toResponse(userRepository.saveAndFlush(userMapper.fromRequest(user)));
    }



    @Override
    public UserResponse update(UserRequest user,Long id) {
        return userRepository.getUserById(id)
                .map(u-> userMapper.fromRequest(user,id))
                .map(userRepository::saveAndFlush)
                .map(userMapper::toResponse)
                .orElseThrow(()-> new PersonNotFoundException("Person with id: %s not found".formatted(id)));
    }

    @Override
    public UserResponse getById(Long id) {
        return userMapper.toResponse(userRepository.getUserById(id)
                .orElseThrow(()-> new PersonNotFoundException("Person with id: %s not found".formatted(id))));
    }

    @Override
    public List<ChannelResponseOnlyNames> getPersonSubscriptions(Long personId) {
        return channelListMapper.toResponseOnlyNames(userRepository.getUserById(personId)
                .orElseThrow(()-> new PersonNotFoundException("Person with id: %s not found".formatted(personId)))
                .getChannelsSubscribed());
    }
}
