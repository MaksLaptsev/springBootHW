package ru.clevertec.springboothw.service.impl;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import ru.clevertec.springboothw.dto.user.UserRequest;
import ru.clevertec.springboothw.dto.user.UserResponse;
import ru.clevertec.springboothw.dto.user.UserRequestRecord;
import ru.clevertec.springboothw.mapper.UserMapper;
import ru.clevertec.springboothw.repository.UserRepository;
import ru.clevertec.springboothw.service.UserService;

@Service
@Transactional
public class UserServiceImpl implements UserService<UserRequest> {

    private UserRepository userRepository;
    private UserMapper userMapper;

    @Autowired
    public UserServiceImpl(UserRepository userRepository, UserMapper userMapper) {
        this.userRepository = userRepository;
        this.userMapper = userMapper;
    }

    @Override
    public UserResponse save(UserRequest user) {
        return userMapper.toResponse(userRepository.saveAndFlush(userMapper.fromRequest(user)));
    }



    @Override
    public UserResponse update(UserRequest user,Long id) {
        return userRepository.getUserById(id)
                .map(u-> userMapper.fromRequest(user,id))
                .map(u-> userRepository.saveAndFlush(u))
                .map(userMapper::toResponse)
                .orElseThrow(()-> new RuntimeException());
    }

    @Override
    public UserResponse getById(Long id) {
        return userMapper.toResponse(userRepository.getUserById(id).orElseThrow(()->new RuntimeException()));
    }
}
