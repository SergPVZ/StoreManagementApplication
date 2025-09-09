package com.example.store.request;

import io.swagger.v3.oas.annotations.media.Schema;
import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.NotBlank;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.hibernate.validator.constraints.URL;

// Request - запрос
@Schema(description = "DTO запроса создания поставщика")
@Data
@AllArgsConstructor
@NoArgsConstructor

public class SupplierRequest {

    @Schema(description ="название поставщика")
    @NotBlank
    private String name;

    @Schema(description ="email поставщика")
    @NotBlank
    @Email
    private String email;

    @Schema(description ="телефон поставщика")
    private String phone;

    @Schema(description ="адрес поставщика")
    private String address;

    @Schema(description ="сайт")
    @URL
    private String website;

}
