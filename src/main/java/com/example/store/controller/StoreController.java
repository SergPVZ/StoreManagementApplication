package com.example.store.controller;

import com.example.store.dto.StoreResponseDto;
import com.example.store.request.StoreRequest;
import com.example.store.service.StoreService;
import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.UUID;

@RestController
@RequestMapping("/stores")           // все методы в классе обрабатывают запросы с адреса /stores
public class StoreController {          // точка соприкосновения "пользователь - программа"

    @Autowired
    private StoreService storeService;

    @PostMapping
    public ResponseEntity<StoreResponseDto> createStore(@Valid @RequestBody StoreRequest request) {    // Valid - связка по id

        StoreResponseDto storeResponse = storeService.createStore(request);

        return ResponseEntity.ok(storeResponse);

    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Void> deleteStore(@PathVariable UUID id) {

        storeService.deleteStore(id);

        return ResponseEntity.noContent().build();
    }

    @GetMapping("/{id}")
    public ResponseEntity<StoreResponseDto> findStoreById(@PathVariable UUID id) {

        StoreResponseDto storeResponseDto = storeService.findById(id);
        return ResponseEntity.ok(storeResponseDto);
    }

    @PutMapping("/{id}")
    public ResponseEntity<StoreResponseDto> updateStore(@PathVariable UUID id, @Valid @RequestBody StoreRequest request) {
        storeService.updateById(id, request);
        return ResponseEntity.ok(storeService.findById(id));
    }

}
