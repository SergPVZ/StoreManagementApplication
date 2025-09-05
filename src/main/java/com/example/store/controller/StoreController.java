package com.example.store.controller;

import com.example.store.dto.AllStoresResponseDto;
import com.example.store.dto.StoreResponseDto;
import com.example.store.request.StoreRequest;
import com.example.store.service.StoreService;
import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.UUID;

@RestController                                         // для взаимодействия с API
@RequestMapping("/stores")                           // все методы в классе обрабатывают запросы с адреса /stores
public class StoreController {                          // - точка соприкосновения "пользователь - программа"

    @Autowired                                          // подключает к адресу из @RequestMapping
    private StoreService storeService;

    @PostMapping                                     // принимает данные из тела запроса
    public ResponseEntity<StoreResponseDto> createStore(@Valid @RequestBody StoreRequest request) {
                                                        /*   Valid - связка по id
                                                       RequestBody - преобразует тело запроса(JSON/XML) в объект */
        StoreResponseDto storeResponse = storeService.createStore(request);

        return ResponseEntity.ok(storeResponse);

    }

    @DeleteMapping("/{id}")                           // удаление данных из БД по ключу
    public ResponseEntity<Void> deleteStore(@PathVariable UUID id) {
                                                         // PathVariable - извлекает данные из URL по значению
        storeService.deleteStore(id);

        return ResponseEntity.noContent().build();

    }

    @GetMapping("/{id}")                               // получить данные магазина из БД по id
    public ResponseEntity<StoreResponseDto> findStoreById(@PathVariable UUID id) {

        StoreResponseDto storeResponseDto = storeService.findById(id);

        return ResponseEntity.ok(storeResponseDto);

    }

    @PutMapping("/{id}")                               // обновление данных по ключу
    public ResponseEntity<StoreResponseDto> updateStore(@PathVariable UUID id, @Valid @RequestBody StoreRequest request) {

        storeService.updateById(id, request);

        return ResponseEntity.ok(storeService.findById(id));

    }

    @GetMapping
    public ResponseEntity<List<AllStoresResponseDto>> findAllStores() {

        List <AllStoresResponseDto> storeResponse = storeService.findAllStores(); // allStoreResponse

        return ResponseEntity.ok(storeResponse);

    }

//    @GetMapping
//    public ResponseEntity<List<StoreResponseDto>> findStoresByLocation(@RequestParam String location) {
//
//        List <StoreResponseDto> storesInLocation = storeService.findByLocation();
//
//        return ResponseEntity.ok(storesInLocation);
//    }

}
