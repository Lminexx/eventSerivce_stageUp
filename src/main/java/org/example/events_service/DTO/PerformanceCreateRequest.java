package org.example.events_service.DTO;

import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.PositiveOrZero;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.math.BigDecimal;
import java.time.LocalDate;
import java.time.LocalTime;
import java.util.UUID;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class PerformanceCreateRequest {

    @NotNull(message = "ID мероприятия обязательно")
    private UUID eventId;

    private UUID applicationId;

    @NotNull(message = "ID артиста обязательно")
    private UUID artistUserId;

    @NotNull(message = "Дата выступления обязательна")
    private LocalDate performanceDate;

    @NotNull(message = "Время начала обязательно")
    private LocalTime startTime;

    @NotNull(message = "Время окончания обязательно")
    private LocalTime endTime;

    @PositiveOrZero(message = "Гонорар не может быть отрицательным")
    private BigDecimal actualFeePaid;
}
