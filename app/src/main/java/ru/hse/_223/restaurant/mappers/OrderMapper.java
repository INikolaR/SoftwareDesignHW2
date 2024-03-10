package ru.hse._223.restaurant.mappers;

import org.mapstruct.Mapper;
import org.mapstruct.Mapping;
import org.mapstruct.MappingConstants;
import org.mapstruct.ReportingPolicy;

@Mapper(componentModel = MappingConstants.ComponentModel.SPRING, unmappedTargetPolicy = ReportingPolicy.IGNORE)
public interface OrderMapper {
    @Mapping(source = "remainingTime", target = "time")
    @Mapping(source = "orderStatus", target = "status")
    ru.hse._223.restaurant.api.dto.Order mapDataToDto(ru.hse._223.restaurant.data.Order order);
}
