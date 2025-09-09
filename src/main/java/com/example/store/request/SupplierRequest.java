package com.example.store.request;

import com.example.store.dto.SupplierResponseDto;
import io.swagger.v3.oas.annotations.media.Schema;
import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.NotBlank;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import com.fasterxml.jackson.annotation.JsonProperty;
import org.hibernate.validator.constraints.URL;

// Request - запрос
@Schema(description = "DTO запроса создания поставщика")
@Data
@AllArgsConstructor
@NoArgsConstructor

public class SupplierRequest {

    @NotBlank
    private String name;

    @NotBlank
    @Email
    private String email;

    private String phone;

    private String address;

    @URL
    private String website;

}

