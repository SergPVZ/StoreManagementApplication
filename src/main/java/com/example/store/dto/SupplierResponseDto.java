package com.example.store.dto;

import io.swagger.v3.oas.annotations.media.Schema;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import java.util.UUID;

@Schema(description = "DTO с основной информацией о поставщике")
@Data
@AllArgsConstructor
@NoArgsConstructor
public class SupplierResponseDto {

    @Schema(description = "Идентификатор поставщика")
    private UUID id;

    @Schema(description = "Имя поставщика")
    private String name;

    @Schema(description ="Электронная почта поставщика")
    private String email;

    @Schema(description ="Номер телефона")
    private String phone;

    @Schema(description ="Адрес поставщика")
    private String address;

    @Schema(description ="Веб-сайт")
    private String website;

}
