package org.itss.backtoschool.course.mapper;

import org.itss.backtoschool.course.dto.UserDTO;
import org.itss.backtoschool.course.entities.User;
import org.mapstruct.Mapper;

@Mapper(componentModel = "spring")
public interface UserMapper {
    UserDTO toDTO(User user);
    User toEntity(UserDTO dto);
}
