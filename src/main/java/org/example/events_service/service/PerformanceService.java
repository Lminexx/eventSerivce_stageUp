package org.example.events_service.service;

import jakarta.persistence.EntityNotFoundException;
import lombok.extern.slf4j.Slf4j;
import org.example.events_service.DTO.ApplicationDTO;
import org.example.events_service.DTO.PerformanceDTO;
import org.example.events_service.entity.Application;
import org.example.events_service.entity.ApplicationStatus;
import org.example.events_service.entity.Performance;
import org.example.events_service.entity.PerformanceStatus;
import org.example.events_service.mapping.PerformanceMapper;
import org.example.events_service.repository.ApplicationRepository;
import org.example.events_service.repository.EventRepository;
import org.example.events_service.repository.PerformanceRepository;
import org.springframework.context.ApplicationEventPublisher;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.math.BigDecimal;
import java.time.LocalDate;
import java.time.LocalTime;
import java.util.UUID;

@Slf4j
@Service
public class PerformanceService {

    private final PerformanceRepository performanceRepository;
    private final EventRepository eventRepository;
    private final ApplicationRepository applicationRepository;
    private final PerformanceMapper performanceMapper;

    public PerformanceService(PerformanceRepository performanceRepository, EventRepository eventRepository, ApplicationRepository applicationRepository, PerformanceMapper performanceMapper) {
        this.performanceRepository = performanceRepository;
        this.eventRepository = eventRepository;
        this.applicationRepository = applicationRepository;
        this.performanceMapper = performanceMapper;
    }

    @Transactional
    public PerformanceDTO createPerformance(UUID eventId,
                                            UUID applicationId,
                                            UUID artistId,
                                            LocalDate performanceDate,
                                            LocalTime startTime,
                                            LocalTime endTime,
                                            BigDecimal actualFeePaid) {
        log.info("createPerformance  eventID : {}, AppId:  {}, artistId :  {} ", eventId, applicationId, artistId);

        Performance performance = new Performance();
        performance.setEvent(eventRepository.findById(eventId).orElseThrow(()->
                new EntityNotFoundException("Event with id " + eventId + " not found")));

        performance.setApplication(applicationRepository.findById(applicationId).orElseThrow(()->
                new EntityNotFoundException("Application with id " + applicationId + " not found")));

        performance.setArtistUserId(artistId);
        performance.setPerformanceDate(performanceDate);
        performance.setStartTime(startTime);
        performance.setEndTime(endTime);
        performance.setActualFeePaid(actualFeePaid);
        performanceRepository.save(performance);

        return performanceMapper.toPerformanceDto(performance);
    }

    @Transactional
    public PerformanceDTO updatePerformanceStatus(UUID performanceId, PerformanceStatus newStatus) {
        log.info("updating application status: id: {}, new status: {}", performanceId, newStatus);

        Performance performance = performanceRepository.findById(performanceId)
                .orElseThrow(() -> new RuntimeException("Заявка с ID " + performanceId + " не найдена"));

        performance.setStatus(newStatus);
        performanceRepository.save(performance);

        return performanceMapper.toPerformanceDto(performance);
    }


}
