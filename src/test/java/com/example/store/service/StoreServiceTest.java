package com.example.store.service;

import com.example.TestContainerInitialization;
import com.example.store.dto.ProductResponseDto;
import com.example.store.dto.StoreResponseDto;
import com.example.store.entity.Product;
import com.example.store.entity.Store;
import com.example.store.entity.StoreProduct;
import com.example.store.repository.ProductRepository;
import com.example.store.repository.StoreProductRepository;
import com.example.store.repository.StoreRepository;
import com.example.store.request.StoreRequest;
import jakarta.validation.ConstraintViolationException;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.Arguments;
import org.junit.jupiter.params.provider.MethodSource;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import java.math.BigDecimal;
import java.util.List;
import java.util.NoSuchElementException;
import java.util.UUID;
import java.util.stream.Stream;

import static org.junit.jupiter.api.Assertions.assertDoesNotThrow;
import static org.junit.jupiter.api.Assertions.assertEquals;

@SpringBootTest
class StoreServiceTest extends TestContainerInitialization {

    @Autowired
    private StoreRepository repository;

    @Autowired
    private StoreService service;

    @Autowired
    private ProductRepository productRepository;

    @Autowired
    private StoreProductRepository storeProductRepository;

    @AfterEach
        // после завершения теста сбрасывает параметры и очищает ресурсы
    void clear() {
        repository.deleteAll();
    }

    @Test
    void createStore_whenNameIsBlank_thenThrow() {

        StoreRequest storeRequest = createStoreRequest("", "ул. Урванцева");

        Assertions.assertThrows(ConstraintViolationException.class, () -> service.createStore(storeRequest));

    }

    @Test
    void createStore_whenLocationIsBlank_thenThrow() {

        StoreRequest storeRequest = createStoreRequest("Красный Яр", "");

        Assertions.assertThrows(ConstraintViolationException.class, () -> service.createStore(storeRequest));

    }

    @Test
    void create_whenNameAndLocationNotBlank_thenCreate() {

        StoreRequest storeRequest = createStoreRequest("Пятёрочка", "ул. Урванцева");

        StoreResponseDto storeResponseDto = assertDoesNotThrow(() -> service.createStore(storeRequest));

        assertEquals(storeRequest.getName(), storeResponseDto.getName());

    }

    @ParameterizedTest                          // подставляет разные наборы данных в один тест
    @MethodSource("invalidData")
        // передаёт для тестирования объёмные объеты
    void updateStore_whenRequestInvalid_thenThrow(String name, String location) {

        Store store = createStore("Пятёрочка", "Ленина");

        StoreRequest storeRequest = createStoreRequest(name, location);

        Assertions.assertThrows(ConstraintViolationException.class, () -> service.updateById(store.getId(), storeRequest));

    }

    @Test
    void updateStore_whenStoreNotFoundById_thenThrow() {

        createStore("Пятёрочка", "Ленина");

        StoreRequest storeRequest = createStoreRequest("Яр", "Ворошилова");

        Assertions.assertThrows(NoSuchElementException.class, () -> service.updateById(UUID.randomUUID(), storeRequest));

    }

    @Test
    void updateStore_whenStoreExist_thenUpdate() {

        Store store = createStore("Красная горка", "улица Лесная");

        StoreRequest storeRequest = createStoreRequest("Жёлтая речка", "улица Луговая");

        StoreResponseDto storeResponseDto = assertDoesNotThrow(() -> service.updateById(store.getId(), storeRequest));

        assertEquals(storeRequest.getName(), storeResponseDto.getName());

    }

    @Test
    void deleteStore_whenInvalidId_thenThrow() {
        Assertions.assertThrows(IllegalArgumentException.class, () -> service.deleteStore(UUID.fromString("121")));
    }

    @Test
    void findById_whenStoreFoundById_thenReturnResponseDto() {

        UUID id = createStore("Волна", "улица Большая").getId();

        assertEquals("StoreResponseDto", service.findById(id).getClass().getSimpleName());
        assertEquals(id, service.findById(id).getId());

    }

    @Test
    void findById_whenInvalidId_thenThrow() {
        Assertions.assertThrows(IllegalArgumentException.class, () -> service.findById(UUID.fromString("121")));
    }

    @Test
    void findAllProductByLocation_whenStoreDoesNotExists_thenReturnEmptyList() {

        createProduct("Кола", BigDecimal.valueOf(33), "Напитки");
        createProduct("Квас", BigDecimal.valueOf(42), "Напитки");
        createProduct("Лимон", BigDecimal.valueOf(32), "Фрукты");

        List<ProductResponseDto> result = assertDoesNotThrow(() ->
                service.findAllProductByLocation("ул. Вязова"));

        Assertions.assertTrue(result.isEmpty());

    }

    @Test
    void findAllProductByLocation_whenStoreExistsButStreetIsNull_thenThrow() {

        Store store = createStore("Пятёрка", "ул. Вязова");

        Product product1 = createProduct("Кола", BigDecimal.valueOf(33), "Напитки");
        Product product2 = createProduct("Квас", BigDecimal.valueOf(42), "Напитки");
        Product product3 = createProduct("Лимон", BigDecimal.valueOf(32), "Фрукты");

        createStoreProduct(store.getId(), product1.getId());
        createStoreProduct(store.getId(), product2.getId());
        createStoreProduct(store.getId(), product3.getId());

        List<ProductResponseDto> result = assertDoesNotThrow(() ->
                service.findAllProductByLocation(null));

        Assertions.assertTrue(result.isEmpty());

    }


    private StoreProduct createStoreProduct(UUID storeId, UUID productId) {

        StoreProduct storeProduct = new StoreProduct(UUID.randomUUID(), storeId, productId);
        storeProduct = storeProductRepository.saveAndFlush(storeProduct);

        return storeProduct;
    }

    private Product createProduct(String name, BigDecimal price, String category) {

        Product product = new Product(UUID.randomUUID(), name, price, category);
        product = productRepository.saveAndFlush(product);

        return product;
    }

    private Store createStore(String name, String location) {

        Store store = new Store(UUID.randomUUID(), name, location, null, null);

        return store;

    }

    private StoreRequest createStoreRequest(String name, String location) {
        return new StoreRequest(name, location, "12345");
    }

    private Stream<Arguments> invalidData() {
        return Stream.of(
                Arguments.of("", "ул.Урванцева"),
                Arguments.of("Красный Яр", ""),
                Arguments.of("", "")
        );

    }

}
