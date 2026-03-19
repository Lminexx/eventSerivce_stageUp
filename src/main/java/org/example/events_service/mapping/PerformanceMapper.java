package org.example.events_service.mapping;

import org.example.events_service.DTO.PerformanceDTO;
import org.example.events_service.entity.Performance;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;

@Mapper(componentModel = "spring")
public interface PerformanceMapper {

    @Mapping(target = "id", source = "performance.id")
    @Mapping(target = "eventId", source = "performance.event.id")
    // Может быть null, MapStruct обработает это безопасно (NullPointerException не будет)
    @Mapping(target = "applicationId", source = "performance.application.id")
    @Mapping(target = "artistUserId", source = "performance.artistUserId")
    @Mapping(target = "performanceDate", source = "performance.performanceDate")
    @Mapping(target = "startTime", source = "performance.startTime")
    @Mapping(target = "endTime", source = "performance.endTime")
    @Mapping(target = "actualFeePaid", source = "performance.actualFeePaid")
    @Mapping(target = "status", source = "performance.status")
    @Mapping(target = "feedbackRequested", source = "performance.feedbackRequested")
    PerformanceDTO toPerformanceDto(Performance performance);

    @Mapping(target = "id", source = "dto.id")
    @Mapping(target = "event.id", source = "dto.eventId")
    @Mapping(target = "application.id", source = "dto.applicationId")
    @Mapping(target = "artistUserId", source = "dto.artistUserId")
    @Mapping(target = "performanceDate", source = "dto.performanceDate")
    @Mapping(target = "startTime", source = "dto.startTime")
    @Mapping(target = "endTime", source = "dto.endTime")
    @Mapping(target = "actualFeePaid", source = "dto.actualFeePaid")
    @Mapping(target = "status", source = "dto.status")
    @Mapping(target = "feedbackRequested", source = "dto.feedbackRequested")
    Performance toPerformance(PerformanceDTO dto);
}
