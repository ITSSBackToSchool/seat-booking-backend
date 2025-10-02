package org.itss.backtoschool.deskops.mapper;

import org.itss.backtoschool.deskops.dto.WeatherDTO;
import org.itss.backtoschool.deskops.entities.WeatherData;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;

@Mapper(componentModel = "spring")
public interface WeatherMapper {

    @Mapping(source = "location.building.name", target = "buildingName")
    @Mapping(source = "location.city", target = "city")
    WeatherDTO toDTO(WeatherData weatherData);
}