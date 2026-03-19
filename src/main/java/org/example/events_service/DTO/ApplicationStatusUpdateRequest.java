package org.example.events_service.DTO;

import jakarta.validation.constraints.NotNull;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.example.events_service.entity.ApplicationStatus;
// import твой_пакет.ApplicationStatus;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class ApplicationStatusUpdateRequest {

    @NotNull(message = "Статус не может быть пустым")
    private ApplicationStatus status;
}