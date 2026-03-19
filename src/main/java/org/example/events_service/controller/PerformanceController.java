package org.example.events_service.controller;

import org.example.events_service.DTO.*;
import org.example.events_service.service.PerformanceService;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.UUID;

@RestController
@RequestMapping("/api/v1/performance")
public class PerformanceController {

    private final PerformanceService performanceService;

    public PerformanceController(PerformanceService performanceService) {
        this.performanceService = performanceService;
    }

    @PostMapping("/create")
    public ResponseEntity<PerformanceDTO> createPerformance(@RequestBody PerformanceCreateRequest performanceCreateRequest) {
        return new ResponseEntity<>(performanceService.createPerformance(
                performanceCreateRequest.getEventId(),
                performanceCreateRequest.getApplicationId(),
                performanceCreateRequest.getArtistUserId(),
                performanceCreateRequest.getPerformanceDate(),
                performanceCreateRequest.getStartTime(),
                performanceCreateRequest.getEndTime(),
                performanceCreateRequest.getActualFeePaid()), HttpStatus.CREATED);
    }

    @PatchMapping("/{id}/status")
    public ResponseEntity<PerformanceDTO> updatePerformanceStatus(
            @PathVariable("id") UUID id,
            @RequestBody PerformanceStatusUpdateRequest updateRequest) {

        return new ResponseEntity<>(performanceService.updatePerformanceStatus(
                id,
                updateRequest.getStatus()),
                HttpStatus.OK);
    }


}
