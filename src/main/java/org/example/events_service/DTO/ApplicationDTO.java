package org.example.events_service.DTO;

import jakarta.persistence.*;
import lombok.*;
import org.example.events_service.entity.ApplicationStatus;
import org.example.events_service.entity.Event;
import org.hibernate.annotations.CreationTimestamp;

import java.time.Instant;
import java.util.UUID;
@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
public class ApplicationDTO {

    private UUID id;
    private UUID eventId;
    private UUID artistUserId;
    private Instant applicationDate;
    private ApplicationStatus status;
    private String coverLetter;
}
