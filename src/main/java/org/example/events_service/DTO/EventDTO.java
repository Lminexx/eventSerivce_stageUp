package org.example.events_service.DTO;

import lombok.Getter;
import lombok.Setter;

import java.math.BigDecimal;
import java.time.LocalDate;
import java.time.LocalTime;
import java.time.OffsetDateTime;
import java.util.UUID;
@Getter
@Setter
public class EventDTO {


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
    private String status;
    private OffsetDateTime creationDate;
    private boolean applied;
}
