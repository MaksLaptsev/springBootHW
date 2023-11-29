package ru.clevertec.springboothw.dto.user;

public record UserResponse(Long id,
                           String nickName,
                           String name,
                           String email) {}
