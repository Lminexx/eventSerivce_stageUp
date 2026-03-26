package org.example.events_service.service;

import lombok.extern.slf4j.Slf4j;
import org.example.events_service.DTO.EventDTO;
import org.example.events_service.entity.Event;
import org.example.events_service.exception.AccessDeniedException;
import org.example.events_service.exception.EventNotFoundException;
import org.example.events_service.mapping.EventMapper;
import org.example.events_service.repository.ApplicationRepository;
import org.example.events_service.repository.EventRepository;
import org.modelmapper.internal.bytebuddy.implementation.bytecode.Throw;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.math.BigDecimal;
import java.time.LocalDate;
import java.time.LocalTime;
import java.time.OffsetDateTime;
import java.util.UUID;
@Slf4j
@Service
public class EventService {

    private final EventRepository eventRepository;
    private final EventMapper eventMapper;
    private final ApplicationRepository applicationRepository;

    public EventService(EventRepository eventRepository, EventMapper eventMapper, ApplicationRepository applicationRepository) {
        this.eventRepository = eventRepository;
        this.eventMapper = eventMapper;
        this.applicationRepository = applicationRepository;
    }


    public EventDTO createEvent(UUID organizerUserId,
                                String title,
                                String description,
                                LocalDate eventDate,
                                LocalTime startTime,
                                LocalTime endTime,
                                String locationName,
                                String address,
                                String city,
                                String requiredGenre,
                                Integer durationMinutes,
                                String technicalRequirements,
                                String rewardType,
                                BigDecimal fixedFeeAmount,
                                OffsetDateTime creationDate
    ) {

        log.info("Create event {}", title);
        Event event = new Event();
        event.setOrganizerUserId(organizerUserId);
        event.setTitle(title);
        event.setDescription(description);
        event.setEventDate(eventDate);
        event.setStartTime(startTime);
        event.setEndTime(endTime);
        event.setLocationName(locationName);
        event.setAddress(address);
        event.setCity(city);
        event.setRequiredGenre(requiredGenre);
        event.setDurationMinutes(durationMinutes);
        event.setTechnicalRequirements(technicalRequirements);
        event.setRewardType(rewardType);
        event.setFixedFeeAmount(fixedFeeAmount);
        event.setCreationDate(creationDate);
        eventRepository.save(event);

        return eventMapper.toEventDto(event);
    }

    public EventDTO updateEvent(UUID eventId, UUID organizerUserId,
                                String title,
                                String description,
                                LocalDate eventDate,
                                LocalTime startTime,
                                LocalTime endTime,
                                String locationName,
                                String address,
                                String city,
                                String requiredGenre,
                                Integer durationMinutes,
                                String technicalRequirements,
                                String rewardType,
                                BigDecimal fixedFeeAmount,
                                OffsetDateTime creationDate){

        log.info("Update event {}", eventId);
        Event event = eventRepository.findById(eventId).orElseThrow(()-> new EventNotFoundException(eventId));

        if (!event.getOrganizerUserId().equals(organizerUserId)) {
            throw new AccessDeniedException();
        }

        event.setTitle(title);
        event.setDescription(description);
        event.setEventDate(eventDate);
        event.setStartTime(startTime);
        event.setEndTime(endTime);
        event.setLocationName(locationName);
        event.setAddress(address);
        event.setCity(city);
        event.setRequiredGenre(requiredGenre);
        event.setDurationMinutes(durationMinutes);
        event.setTechnicalRequirements(technicalRequirements);
        event.setRewardType(rewardType);
        event.setFixedFeeAmount(fixedFeeAmount);
        event.setCreationDate(creationDate);
        eventRepository.save(event);
        return eventMapper.toEventDto(event);
    }

    @Transactional
    public void deleteEvent(UUID eventId, UUID organizerUserId) {
        log.info("Delete event {}", eventId);
        Event event = eventRepository.findById(eventId).orElseThrow(()-> new EventNotFoundException(eventId));

        if(!event.getOrganizerUserId().equals(organizerUserId)) {
            throw new AccessDeniedException();
        }
        eventRepository.delete(event);
    }



    public Page<EventDTO> getEvents(Pageable pageable) {
        log.info("get events");
        return eventRepository.findAll(pageable)
                .map(eventMapper::toEventDto);
    }

    public EventDTO getEvent(UUID eventId, UUID userId) {
        log.info("get event {}", eventId);
        boolean isApp = applicationRepository.existsByEventIdAndArtistUserId(eventId, userId);
        EventDTO eventDTO = eventMapper.toEventDto(eventRepository.getById(eventId));
        eventDTO.setApplied(isApp);
        return eventDTO;
    }

    public Page<EventDTO> getMyEvents(Pageable pageable, UUID organizerUserId) {
        log.info("get My events {}", organizerUserId);
        return eventRepository.findByOrganizerUserId(organizerUserId, pageable)
                .map(eventMapper::toEventDto);
    }
 }
