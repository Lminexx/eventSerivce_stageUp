package org.example.events_service.service;

import jakarta.persistence.EntityNotFoundException;
import lombok.extern.slf4j.Slf4j;
import org.example.events_service.DTO.ApplicationDTO;
import org.example.events_service.entity.Application;
import org.example.events_service.entity.ApplicationStatus;
import org.example.events_service.entity.Event;
import org.example.events_service.mapping.ApplicationMapper;
import org.example.events_service.repository.ApplicationRepository;
import org.example.events_service.repository.EventRepository;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.UUID;

@Slf4j
@Service
public class ApplicationService {

    private final ApplicationRepository applicationRepository;
    private final EventRepository eventRepository;
    private final ApplicationMapper applicationMapper;

    public ApplicationService(ApplicationRepository applicationRepository,
                              EventRepository eventRepository,
                              ApplicationMapper applicationMapper) {
        this.applicationRepository = applicationRepository;
        this.eventRepository = eventRepository;
        this.applicationMapper = applicationMapper;
    }

    @Transactional
    public ApplicationDTO createApplication(UUID eventId, UUID artistId, String coverLetter) {
        log.info("Creating application: event: {}, artist: {}", eventId, artistId);

        if (applicationRepository.existsByEventIdAndArtistUserId(eventId, artistId)) {
            throw new IllegalStateException("Вы уже отправили отклик на это мероприятие");
        }

        Event event = eventRepository.findById(eventId)
                .orElseThrow(() -> new EntityNotFoundException("Мероприятие не найдено"));

        Application application = new Application();
        application.setEvent(event);
        application.setArtistUserId(artistId);
        application.setCoverLetter(coverLetter);

        applicationRepository.save(application);

        return applicationMapper.toApplicationDto(application);
    }

    @Transactional
    public ApplicationDTO updateApplicationStatus(UUID applicationId, ApplicationStatus newStatus) {
        log.info("updating application status: id: {}, new status: {}", applicationId, newStatus);

        Application application = applicationRepository.findById(applicationId)
                .orElseThrow(() -> new RuntimeException("Заявка с ID " + applicationId + " не найдена"));

        application.setStatus(newStatus);

        applicationRepository.save(application);

        return applicationMapper.toApplicationDto(application);
    }

    public Page<ApplicationDTO> getApplicationsByEvent(UUID eventId, Pageable pageable) {
        Page<Application> application = applicationRepository.findByEventId(eventId, pageable);
        return application.map(applicationMapper::toApplicationDto);
    }
}
