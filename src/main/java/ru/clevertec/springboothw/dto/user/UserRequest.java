package ru.clevertec.springboothw.dto.user;

import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.Size;

public record UserRequest(@Size(max = 30) String name, @NotBlank @Size(max = 30) String nickName,
                          @Size(max = 30) @Email String email) {
}
