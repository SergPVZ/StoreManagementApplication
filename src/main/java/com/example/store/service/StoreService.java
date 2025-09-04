package com.example.store.service;

import com.example.store.dto.AllStoresResponseDto;
import com.example.store.dto.StoreResponseDto;
import com.example.store.entity.Store;
import com.example.store.mapper.StoreMapper;
import com.example.store.repository.StoreRepository;
import com.example.store.request.StoreRequest;
import jakarta.transaction.Transactional;
import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Sort;
import org.springframework.stereotype.Service;
import org.springframework.validation.annotation.Validated;

import java.util.List;
import java.util.UUID;
import java.util.stream.Collectors;

@Service                                         /* обозначает класс Сервис, который вызывает методы класса Контроллера
                                                    для выполнения бизнес-логики */
@Transactional(rollbackOn = Exception.class)     // в случае поломки проводит откат предыдущих изменений
@Validated
public class StoreService {                      /* основная логика работы с данными
                                                    1) принимает и возвращает данные в контроллер
                                                    2) работает с Репозиторием для работы с БД
                                                    3) работает с Маппером для преобразования данных */

    @Autowired
    // для автоматического внедрения зависимостей связывает метод с Репозиторием
    private StoreRepository storeRepository;

    @Autowired                                   // связывает метод с Маппер
    private StoreMapper mapper;

    // создание нового маг-на:
    public StoreResponseDto createStore(@Valid StoreRequest request) {

        Store store = new Store(UUID.randomUUID(), request.getName(), request.getLocation(), request.getEmail(), null);

        storeRepository.saveAndFlush(store);         // сохранение в БД

        return mapper.mapToStoreResponseDto(store);

    }

    public void deleteStore(UUID id) {
        storeRepository.deleteById(id);
    }

    public StoreResponseDto findById(UUID storeId) {

        Store store = storeRepository.findById(storeId).orElseThrow();

        return mapper.mapToStoreResponseDto(store);

    }

    public StoreResponseDto updateById(UUID id, @Valid StoreRequest request) {

        Store store = storeRepository.findById(id).orElseThrow();
        store.setName(request.getName());
        store.setLocation(request.getLocation());
        store.setEmail(request.getEmail());

        storeRepository.saveAndFlush(store);

        return mapper.mapToStoreResponseDto(store);

    }


    public List<StoreResponseDto> findAllStores() {

        List<Store> stores = storeRepository.findAll();

        List<StoreResponseDto> list = stores.stream()
                .map(e -> mapper.mapToStoreResponseDto(e))
                .toList();

        return (List<StoreResponseDto>) list;
    }

//    public List<AllStoresResponseDto> findAllStoresByLocatiom(String location) {

//        List<Store> stores = storeRepository.findByLocation(location);

//        List<AllStoresResponseDto> listLocation = stores.stream()
//                .map(store -> mapper.mapToStoreResponseDto(store))
//                .toList();
//
//        return listLocation;

//    }

    public List<AllStoresResponseDto> findAllStoresByName() {

        List<Store> stores = storeRepository.findAll(Sort.by(Sort.Order.asc("name")));
        return List.of();

    }

}
