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

    @Schema(description = "идентификатор поставщика")
    private UUID id;

    @Schema(description = "Имя поставщика")
    private String name;

    private String email;

    private String phone;

    private String address;

    private String website;

}
