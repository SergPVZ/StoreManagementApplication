package com.example.store.service;

import com.example.store.dto.AllStoresResponseDto;
import com.example.store.dto.ProductResponseDto;
import com.example.store.dto.StoreResponseDto;
import com.example.store.entity.Store;
import com.example.store.entity.StoreProduct;
import com.example.store.mapper.StoreMapper;
import com.example.store.repository.ProductRepository;
import com.example.store.repository.StoreProductRepository;
import com.example.store.repository.StoreRepository;
import com.example.store.request.ProductRequest;
import com.example.store.request.StoreRequest;
import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Sort;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.validation.annotation.Validated;

import java.util.ArrayList;
import java.util.List;
import java.util.UUID;
import java.util.stream.Collectors;

@Service                                         /* обозначает класс Сервис, который вызывает методы класса Контроллера
                                                    для выполнения бизнес-логики */
//@Transactional(rollbackOn = Exception.class)     // в случае поломки проводит откат предыдущих изменений
@Transactional(readOnly = true)                    // весь класс - только для чтения
@Validated
public class StoreService {                      /* основная логика работы с данными
                                                    1) принимает и возвращает данные в контроллер
                                                    2) работает с Репозиторием для работы с БД
                                                    3) работает с Маппером для преобразования данных */

    @Autowired                              // для автоматического внедрения зависимостей связывает метод с Репозиторием
    private StoreRepository storeRepository;

    @Autowired
    private StoreProductRepository storeProductRepository;

    @Autowired
    private ProductRepository productRepository;

    @Autowired                                   // связывает метод с Маппер
    private StoreMapper mapper;
    @Autowired
    private StoreMapper storeMapper;

    @Transactional(rollbackFor = Exception.class)       // для методов, которые меняют БД
    public StoreResponseDto createStore(@Valid StoreRequest request) {

        Store store = new Store(UUID.randomUUID(), request.getName(), request.getLocation(), request.getEmail(), null);

        storeRepository.saveAndFlush(store);         // сохранение в БД

        return mapper.mapToStoreResponseDto(store);

    }

    @Transactional(rollbackFor = Exception.class)
    public void deleteStore(UUID id) {
        storeRepository.deleteById(id);
    }

    public StoreResponseDto findById(UUID storeId) {

        Store store = storeRepository.findById(storeId).orElseThrow();

        return mapper.mapToStoreResponseDto(store);

    }

    @Transactional(rollbackFor = Exception.class)
    public StoreResponseDto updateById(UUID id, @Valid StoreRequest request) {

        Store store = storeRepository.findById(id).orElseThrow();
        store.setName(request.getName());
        store.setLocation(request.getLocation());

        storeRepository.saveAndFlush(store);

        return mapper.mapToStoreResponseDto(store);

    }

    // без e-mail'a
    public List<AllStoresResponseDto> findAllStores() {

        List<Store> stores = storeRepository.findAll();

        List<AllStoresResponseDto> list = stores.stream()
                .map(e -> mapper.mapToAllStoresResponseDto(e))
                .toList();

        return list;

    }

    // добавил e-mail
    public List<StoreResponseDto> findAllStoresByLocatiom(String location) {

        List<Store> stores = storeRepository.findByLocation(location);

        List<StoreResponseDto> listLocation = stores.stream()
                .map(store -> mapper.mapToStoreResponseDto(store))
                .toList();

        return listLocation;

    }

    public List<AllStoresResponseDto> findAllStoresByName() {

        List<Store> stores = storeRepository.findAll(Sort.by(Sort.Order.asc("name")));

        return stores.stream()
                .map(storeMapper::mapToAllStoresResponseDto)
                .toList();

    }

    @Transactional(rollbackFor = Exception.class)
    public StoreResponseDto copy(UUID storeId) {

        Store store = storeRepository.findById(storeId).orElseThrow();

        Store copyStore = new Store(UUID.randomUUID(), store.getName(), store.getLocation(), store.getEmail(), store.getUpdated_at());

        storeRepository.saveAndFlush(copyStore);

        return mapper.mapToStoreResponseDto(copyStore);

    }
    //                  -- = ВСЕ ПРОДУКТЫ ПО АДРЕСУ = --

    public List<ProductResponseDto> findAllProductByLocation(String location) {
        return storeRepository.findAll().stream()
                .filter(store -> store.getLocation() != null && store.getLocation().contains(location))
                .flatMap(store -> storeProductRepository.findByStoreId(store.getId()).stream()
                        .map(storeProduct -> {
                            StoreProduct.Product product = productRepository.findById(storeProduct.getProductId())
                                    .orElseThrow();
                            return storeMapper.mapToProductResponseDto(product);
                        })
                )
                .distinct()
                .collect(Collectors.toList());

    }

    @Transactional(rollbackFor = Exception.class)
    public ProductResponseDto createProduct(UUID storeId, @Valid ProductRequest request) {

        StoreProduct.Product product = new StoreProduct.Product(UUID.randomUUID(), request.getName(), request.getPrice(), "some");
        StoreProduct storeProduct = new StoreProduct(UUID.randomUUID(), storeId, product.getId());

        productRepository.saveAndFlush(product);
        storeProductRepository.saveAndFlush(storeProduct);

        return storeMapper.mapToProductResponseDto(product);

    }

    public List<ProductResponseDto> findUniqueProducts() {

        List<StoreProduct.Product> allProducts = productRepository.findAll();

        List<ProductResponseDto> result = new ArrayList<>();

        for (StoreProduct.Product product : allProducts) {

            int countStore = storeRepository.countStoresByProductId(product.getId());

            if (countStore == 1) {
                result.add(storeMapper.mapToProductResponseDto(product));
            }

        }

        return result;

    }

}
