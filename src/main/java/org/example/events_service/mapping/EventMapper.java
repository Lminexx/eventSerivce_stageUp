package org.example.events_service.mapping;

import org.example.events_service.DTO.EventDTO;
import org.example.events_service.entity.Event;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;

@Mapper(componentModel = "spring")
public interface EventMapper {

    @Mapping(target = "id", source = "event.id")
    @Mapping(target = "organizerUserId", source = "event.organizerUserId")
    @Mapping(target = "title", source = "event.title")
    @Mapping(target = "description", source = "event.description")
    @Mapping(target = "eventDate", source = "event.eventDate")
    @Mapping(target = "startTime", source = "event.startTime")
    @Mapping(target = "endTime", source = "event.endTime")
    @Mapping(target = "locationName", source = "event.locationName")
    @Mapping(target = "address", source = "event.address")
    @Mapping(target = "city", source = "event.city")
    @Mapping(target = "requiredGenre", source = "event.requiredGenre")
    @Mapping(target = "durationMinutes", source = "event.durationMinutes")
    @Mapping(target = "technicalRequirements", source = "event.technicalRequirements")
    @Mapping(target = "rewardType", source = "event.rewardType")
    @Mapping(target = "fixedFeeAmount", source = "event.fixedFeeAmount")
    @Mapping(target = "status", source = "event.status")
    @Mapping(target = "creationDate", source = "event.creationDate")
    EventDTO toEventDto(Event event);

    @Mapping(target = "id", source = "dto.id")
    @Mapping(target = "organizerUserId", source = "dto.organizerUserId")
    @Mapping(target = "title", source = "dto.title")
    @Mapping(target = "description", source = "dto.description")
    @Mapping(target = "eventDate", source = "dto.eventDate")
    @Mapping(target = "startTime", source = "dto.startTime")
    @Mapping(target = "endTime", source = "dto.endTime")
    @Mapping(target = "locationName", source = "dto.locationName")
    @Mapping(target = "address", source = "dto.address")
    @Mapping(target = "city", source = "dto.city")
    @Mapping(target = "requiredGenre", source = "dto.requiredGenre")
    @Mapping(target = "durationMinutes", source = "dto.durationMinutes")
    @Mapping(target = "technicalRequirements", source = "dto.technicalRequirements")
    @Mapping(target = "rewardType", source = "dto.rewardType")
    @Mapping(target = "fixedFeeAmount", source = "dto.fixedFeeAmount")
    @Mapping(target = "status", source = "dto.status")
    @Mapping(target = "creationDate", source = "dto.creationDate")
    Event toEvent(EventDTO dto);
}