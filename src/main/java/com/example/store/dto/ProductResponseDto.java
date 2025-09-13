package com.example.store.dto;

import io.swagger.v3.oas.annotations.media.Schema;
import jakarta.persistence.Column;
import jakarta.persistence.Id;
import jakarta.validation.constraints.Email;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.hibernate.validator.constraints.URL;
import org.springframework.data.annotation.LastModifiedDate;

import java.math.BigDecimal;
import java.time.LocalDateTime;
import java.util.UUID;

@Schema(description = "DTO с основной информацией о товаре")
@Data
@AllArgsConstructor
@NoArgsConstructor

public class ProductResponseDto {

    @Schema(description =  "Идентификатор товара")
    private UUID id;

    @Schema(description =  "Наименование товара")
    private String name;

    @Schema(description =  "Цена товара")
    private BigDecimal price;

    @Schema(description =  "Категория товара")
    private String category;

}
