package com.example.store.request;

import com.fasterxml.jackson.annotation.JsonProperty;
import io.swagger.v3.oas.annotations.media.Schema;
import jakarta.validation.constraints.NotBlank;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
                                                    // Request - запрос
@Schema(description = "DTO запроса создания магазина")
@Data
@AllArgsConstructor
@NoArgsConstructor
public class StoreRequest {                          // получает данные объекта от Контроллера и проверяет на валидность

    @Schema(description = "Название магазина")       // описание данных поля
    @JsonProperty("name")                            // задаёт альтернативное имя для поля JSON
    @NotBlank                                        // проверяет, что поле не пустое
    private String name;

    @Schema(description = "Местоположение магазина")
    @JsonProperty("location")
    @NotBlank
    private String location;

    @Schema(description = "email")
    @JsonProperty("email")
    @NotBlank
    private String email;

}
