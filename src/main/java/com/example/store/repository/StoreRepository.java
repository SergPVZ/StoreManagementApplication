package com.example.store.repository;

import com.example.store.entity.Store;
//import com.example.store.request.StoreRequest;
//import jakarta.validation.Valid;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import java.util.UUID;

@Repository                                                           // автоматичемкая р-та с БД
public interface StoreRepository extends JpaRepository<Store, UUID> {

}
