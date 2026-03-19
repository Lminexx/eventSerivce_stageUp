package org.example.events_service.mapping;

import org.example.events_service.DTO.ApplicationDTO;
import org.example.events_service.entity.Application;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;

@Mapper(componentModel = "spring")
public interface ApplicationMapper {

    @Mapping(target = "id", source = "application.id")
    @Mapping(target = "eventId", source = "application.event.id")
    @Mapping(target = "artistUserId", source = "application.artistUserId")
    @Mapping(target = "applicationDate", source = "application.applicationDate")
    @Mapping(target = "status", source = "application.status")
    @Mapping(target = "coverLetter", source = "application.coverLetter")
    ApplicationDTO toApplicationDto(Application application);

    @Mapping(target = "id", source = "dto.id")
    @Mapping(target = "event.id", source = "dto.eventId")
    @Mapping(target = "artistUserId", source = "dto.artistUserId")
    @Mapping(target = "applicationDate", source = "dto.applicationDate")
    @Mapping(target = "status", source = "dto.status")
    @Mapping(target = "coverLetter", source = "dto.coverLetter")
    Application toApplication(ApplicationDTO dto);
}