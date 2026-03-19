package org.example.events_service.DTO;

import jakarta.validation.constraints.NotNull;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.example.events_service.entity.PerformanceStatus;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class PerformanceStatusUpdateRequest {

    @NotNull(message = "Статус не может быть пустым")
    private PerformanceStatus status;
}
