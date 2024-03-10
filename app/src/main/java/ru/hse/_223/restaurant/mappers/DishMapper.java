package ru.hse._223.restaurant.mappers;

import org.mapstruct.*;

@Mapper(componentModel = MappingConstants.ComponentModel.SPRING, unmappedTargetPolicy = ReportingPolicy.IGNORE)
public interface DishMapper {

    @Mapping(source = "name", target = "name")
    @Mapping(source = "amount", target = "amount")
    @Mapping(source = "price", target = "price")
    @Mapping(source = "cookingTime", target = "cookingTime")
    ru.hse._223.restaurant.api.dto.Dish mapDataToDto(ru.hse._223.restaurant.data.Dish dish);
    @InheritInverseConfiguration
    ru.hse._223.restaurant.data.Dish mapDtoToData(ru.hse._223.restaurant.api.dto.Dish dish);

}
