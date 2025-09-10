package com.example.store.controller;

import com.example.store.dto.SupplierResponseDto;
import com.example.store.request.SupplierRequest;
import com.example.store.service.SupplierService;
import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.UUID;

@RestController
@RequestMapping("/supplier")
public class SupplierController {

    @Autowired
    private SupplierService supplierService;

    @PostMapping
    public ResponseEntity<SupplierResponseDto> createStore(@RequestBody SupplierRequest request) {   //создание
        SupplierResponseDto storeResponse = supplierService.createSupplier(request);

        return ResponseEntity.ok(storeResponse);

    }

    @DeleteMapping("/{id}")
    public ResponseEntity<SupplierResponseDto> deleteSupplier(@PathVariable UUID id) {    // удаление
        supplierService.deleteSupplier(id);
        return ResponseEntity.noContent().build();
    }

    @GetMapping("/{id}")                               // получить данные магазина из БД по id
    public ResponseEntity<SupplierResponseDto> findSuplireById(@PathVariable UUID id) {

        SupplierResponseDto supplierResponseDto = supplierService.findById(id);

        return ResponseEntity.ok(supplierResponseDto);

    }

    @PutMapping("/{id}")                               // обновление данных по ключу
    public ResponseEntity<SupplierResponseDto> updateSupplier(@PathVariable UUID id, @Valid @RequestBody SupplierRequest request) {

        supplierService.updateById(id, request);

        return ResponseEntity.ok(supplierService.findById(id));

    }

    @GetMapping
    public ResponseEntity<List<SupplierResponseDto>> findAllSupplier() {   // список всех

        List<SupplierResponseDto> supplierResponse = supplierService.findAllSupplier(); // allStoreResponse

        return ResponseEntity.ok(supplierResponse);

    }

}
