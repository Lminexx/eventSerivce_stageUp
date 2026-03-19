package org.example.events_service.DTO;

import jakarta.persistence.*;
import lombok.*;
import org.example.events_service.entity.Application;
import org.example.events_service.entity.Event;
import org.example.events_service.entity.PerformanceStatus;

import java.math.BigDecimal;
import java.time.LocalDate;
import java.time.LocalTime;
import java.util.UUID;
@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
public class PerformanceDTO {

    private UUID id;
    private UUID eventId;
    private UUID applicationId;
    private UUID artistUserId;
    private LocalDate performanceDate;
    private LocalTime startTime;
    private LocalTime endTime;
    private BigDecimal actualFeePaid;
    private PerformanceStatus status;
    private Boolean feedbackRequested;
}
