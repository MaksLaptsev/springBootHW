package ru.clevertec.springboothw.service;

import ru.clevertec.springboothw.dto.user.UserResponse;

public interface UserService<T> {
    UserResponse save(T t);
    UserResponse update(T t,Long id);
    UserResponse getById(Long id);
}
