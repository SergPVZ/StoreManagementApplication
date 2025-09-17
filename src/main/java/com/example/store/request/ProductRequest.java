package com.example.store.request;

import com.fasterxml.jackson.annotation.JsonProperty;
import io.swagger.v3.oas.annotations.media.Schema;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.math.BigDecimal;

@Schema(description = "DTO запроса создания поставщика")
@Data
@AllArgsConstructor
@NoArgsConstructor

public class ProductRequest {

    @Schema(description = "Название продукта")       // описание данных поля
    @JsonProperty("name")                            // задаёт альтернативное имя для поля JSON
    @NotBlank                                        // проверяет, что поле не пустое
    private String name;

    @Schema(description = "Цена продукта")
    @JsonProperty("price")
    @NotNull
    private BigDecimal price;

}
