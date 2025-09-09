package com.example.store.controller;

import com.example.store.dto.SupplierResponseDto;
import com.example.store.request.SupplierRequest;
import com.example.store.service.SupplierService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController                                         // для взаимодействия с API
@RequestMapping("/supplier")
public class SupplierController {

    @Autowired                                          // подключает к адресу из @RequestMapping
    private SupplierService supplierService;

    @PostMapping
    public ResponseEntity<SupplierResponseDto> createStore(@RequestBody SupplierRequest request) {
        SupplierResponseDto storeResponse = supplierService.createSupplier(request);

        return ResponseEntity.ok(storeResponse);

    }

}
