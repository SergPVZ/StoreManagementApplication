package com.example.store.repository;

import com.example.store.entity.StoreProduct;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.UUID;

@Repository
public interface ProductRepository extends JpaRepository<StoreProduct.Product, UUID> {
}
