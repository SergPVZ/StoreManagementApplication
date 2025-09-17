package com.example.store.repository;

import com.example.store.entity.Store;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.UUID;

@Repository                                        /* автоматичеcкая р-та с БД, */
public interface StoreRepository extends JpaRepository<Store, UUID> {           // сохраняет объект в БД (тбл)

    List<Store> findByLocation(String location);

    @Query("SELECT COUNT(sp) FROM store_products sp WHERE sp.productId =: id")
    int countStoresByProductId(UUID id);
}
