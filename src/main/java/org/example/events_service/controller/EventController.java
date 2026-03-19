package org.example.events_service.controller;

import org.example.events_service.DTO.EventDTO;
import org.example.events_service.DTO.EventRequest;
import org.example.events_service.service.EventService;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.UUID;

@RestController
@RequestMapping("/api/v1/event")
public class EventController {

    private final EventService eventService;

    public EventController(EventService eventService) {
        this.eventService = eventService;
    }
    @PostMapping("/create")
    public ResponseEntity<EventDTO> createEvent(@RequestBody EventRequest eventRequest) {
        return new ResponseEntity<>(eventService.createEvent(
                eventRequest.getOrganizerUserId(),
                eventRequest.getTitle(),
                eventRequest.getDescription(),
                eventRequest.getEventDate(),
                eventRequest.getStartTime(),
                eventRequest.getEndTime(),
                eventRequest.getLocationName(),
                eventRequest.getAddress(),
                eventRequest.getCity(),
                eventRequest.getRequiredGenre(),
                eventRequest.getDurationMinutes(),
                eventRequest.getTechnicalRequirements(),
                eventRequest.getRewardType(),
                eventRequest.getFixedFeeAmount(),
                eventRequest.getCreationDate()),
                HttpStatus.CREATED);
    }

    @PutMapping("/{eventId}")
    public ResponseEntity<EventDTO> updateEvent(
            @PathVariable UUID eventId,
            @RequestHeader("X-User-Id") UUID organizerUserId,
            @RequestBody EventRequest request) {

        return ResponseEntity.ok(eventService.updateEvent(
                eventId,
                organizerUserId,
                request.getTitle(),
                request.getDescription(),
                request.getEventDate(),
                request.getStartTime(),
                request.getEndTime(),
                request.getLocationName(),
                request.getAddress(),
                request.getCity(),
                request.getRequiredGenre(),
                request.getDurationMinutes(),
                request.getTechnicalRequirements(),
                request.getRewardType(),
                request.getFixedFeeAmount(),
                request.getCreationDate()
        ));
    }


    @GetMapping
    public ResponseEntity<Page<EventDTO>> getEvents(
            @RequestParam(defaultValue = "0") int page,
            @RequestParam(defaultValue = "20") int size,
            @RequestParam(defaultValue = "eventDate") String sortBy,
            @RequestParam(defaultValue = "asc") String direction) {

        Pageable pageable = PageRequest.of(
                page, size,
                direction.equalsIgnoreCase("desc") ? Sort.by(sortBy).descending() : Sort.by(sortBy).ascending()
        );
        return ResponseEntity.ok(eventService.getEvents(pageable));
    }
    @GetMapping("/{eventId}")
    public ResponseEntity<EventDTO> getEvent(@PathVariable UUID eventId,
                                             @RequestHeader(value = "X-User-Id", required = false) UUID userId){
        return ResponseEntity.ok(eventService.getEvent(eventId, userId));
    }
    @GetMapping("/myEvents")
    public ResponseEntity<Page<EventDTO>> getMyEvents(
            @RequestParam(defaultValue = "0") int page,
            @RequestParam(defaultValue = "20") int size,
            @RequestParam(defaultValue = "eventDate") String sortBy,
            @RequestParam(defaultValue = "asc") String direction,
            @RequestParam UUID organizerUserId){
        Pageable pageable = PageRequest.of(
                page, size,
                direction.equalsIgnoreCase("desc") ? Sort.by(sortBy).descending() : Sort.by(sortBy).ascending()
        );
        return ResponseEntity.ok(eventService.getMyEvents(pageable, organizerUserId));
    }
}
