package org.example.events_service.controller;

import org.example.events_service.DTO.ApplicationCreateRequest;
import org.example.events_service.DTO.ApplicationDTO;
import org.example.events_service.DTO.ApplicationStatusUpdateRequest;
import org.example.events_service.service.ApplicationService;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.UUID;

@RestController
@RequestMapping("/api/v1/application")
public class ApplicationController {
    private final ApplicationService applicationService;

    public ApplicationController(ApplicationService applicationService) {
        this.applicationService = applicationService;
    }


    @PostMapping("/create")
    public ResponseEntity<ApplicationDTO> createApplication(@RequestBody ApplicationCreateRequest applicationCreateRequest) {
        return new ResponseEntity<>(applicationService.createApplication(
                applicationCreateRequest.getEventId(),
                applicationCreateRequest.getArtistUserId(),
                applicationCreateRequest.getCoverLetter()),
                HttpStatus.CREATED);
    }

    @GetMapping("/event/{eventId}")
    public ResponseEntity<Page<ApplicationDTO>> getEventApplications(
            @PathVariable UUID eventId,
            @RequestParam(defaultValue = "0") int page,
            @RequestParam(defaultValue = "20") int size) {

        Pageable pageable = PageRequest.of(page, size, Sort.by("applicationDate").descending());
        return ResponseEntity.ok(applicationService.getApplicationsByEvent(eventId, pageable));
    }


    @PatchMapping("/{id}/status")
    public ResponseEntity<ApplicationDTO> updateApplicationStatus(
            @PathVariable("id") UUID id,
            @RequestBody ApplicationStatusUpdateRequest updateRequest) {

        return new ResponseEntity<>(applicationService.updateApplicationStatus(
                id,
                updateRequest.getStatus()),
                HttpStatus.OK);
    }


}
