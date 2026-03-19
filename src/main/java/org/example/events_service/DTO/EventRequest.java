package org.example.events_service.DTO;

import jakarta.persistence.Column;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import lombok.Getter;
import org.hibernate.annotations.CreationTimestamp;

import java.math.BigDecimal;
import java.time.LocalDate;
import java.time.LocalTime;
import java.time.OffsetDateTime;
import java.util.UUID;
@Getter
public class EventRequest {

    private UUID id;
    private UUID organizerUserId;
    private String title;
    private String description;
    private LocalDate eventDate;
    private LocalTime startTime;
    private LocalTime endTime;
    private String locationName;
    private String address;
    private String city;
    private String requiredGenre;
    private Integer durationMinutes;
    private String technicalRequirements;
    private String rewardType;
    private BigDecimal fixedFeeAmount;
    private OffsetDateTime creationDate;
}
