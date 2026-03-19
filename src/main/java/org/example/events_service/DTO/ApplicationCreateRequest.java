package org.example.events_service.DTO;

import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Size;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.UUID;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class ApplicationCreateRequest {

    @NotNull(message = "ID мероприятия обязательно")
    private UUID eventId;

    @NotNull(message = "ID артиста обязательно")
    private UUID artistUserId; // Придет от Gateway

    @NotBlank(message = "Сопроводительное письмо не может быть пустым")
    @Size(max = 2000, message = "Письмо слишком длинное (максимум 2000 символов)")
    private String coverLetter;
}
