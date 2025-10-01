package org.itss.backtoschool.course.mapper;

import org.itss.backtoschool.course.dto.UserDTO;
import org.itss.backtoschool.course.entities.User;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;

import java.util.List;

@Mapper(componentModel = "spring")
public interface UserMapper {


    @Mapping(source = "id", target = "id")
    @Mapping(source = "userName", target = "userName")
    @Mapping(source = "password", target = "password")
    @Mapping(source = "email", target = "email")
    @Mapping(source = "phone", target = "phone")
    @Mapping(source = "homeAdress", target = "homeAdress")
    UserDTO toDTO(User user);




    List<UserDTO> toDTOList(List<User> users);


    List<User> toEntityList(List<UserDTO> dtos);
}
