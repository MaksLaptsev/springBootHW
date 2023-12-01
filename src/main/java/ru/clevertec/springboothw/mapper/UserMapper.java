package ru.clevertec.springboothw.mapper;

import org.mapstruct.Mapper;
import org.mapstruct.ReportingPolicy;
import ru.clevertec.springboothw.dto.user.UserRequest;
import ru.clevertec.springboothw.dto.user.UserResponse;
import ru.clevertec.springboothw.model.Person;

@Mapper(componentModel = "spring",unmappedTargetPolicy = ReportingPolicy.IGNORE)
public interface UserMapper {
    Person fromRequest(UserRequest userRequestRecord);
    Person fromRequest(UserRequest userRequestRecord,long id);
    UserResponse toResponse(Person person);

}
