package com.example.store.mapper;

import com.example.store.dto.AllStoresResponseDto;
import com.example.store.dto.ProductResponseDto;
import com.example.store.dto.StoreResponseDto;
import com.example.store.entity.Store;
import com.example.store.entity.StoreProduct;
import org.springframework.stereotype.Component;

// создание карты
@Component                      // отмечает класс как бин, который можно внедрить в другие компоненты через @Autowired
public class StoreMapper {                                      /* преобразует объекты сущностей в
                                                                  объекты для передачи данных (ResponseDto) */
    public StoreResponseDto mapToStoreResponseDto(Store store) {
        return new StoreResponseDto(store.getId(), store.getName(), store.getLocation(), store.getEmail());
    }

    public AllStoresResponseDto mapToAllStoresResponseDto(Store store) {
        return new AllStoresResponseDto(store.getId(), store.getName(), store.getLocation());
    }

    public ProductResponseDto mapToProductResponseDto(StoreProduct.Product product) {
        return new ProductResponseDto(product.getId(), product.getName(), product.getPrice(), product.getCategory());
    }

}
