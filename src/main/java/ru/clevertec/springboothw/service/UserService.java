package ru.clevertec.springboothw.service;

import ru.clevertec.springboothw.dto.channel.ChannelResponseOnlyNames;
import ru.clevertec.springboothw.dto.user.UserRequest;
import ru.clevertec.springboothw.dto.user.UserResponse;

import java.util.List;

public interface UserService {
    UserResponse save(UserRequest request);
    UserResponse update(UserRequest request,Long id);
    UserResponse getById(Long id);
    List<ChannelResponseOnlyNames> getPersonSubscriptions(Long personId);
}
