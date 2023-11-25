package ru.clevertec.springboothw.mapper;

import org.mapstruct.Mapper;
import ru.clevertec.springboothw.dto.user.UserRequest;
import ru.clevertec.springboothw.dto.user.UserResponse;
import ru.clevertec.springboothw.dto.user.UserRequestRecord;
import ru.clevertec.springboothw.model.Person;

@Mapper(componentModel = "spring")
public interface UserMapper {
    Person fromRequest(UserRequest userRequestRecord);
    Person fromRequest(UserRequest userRequestRecord,long id);
    UserResponse toResponse(Person person);

}
