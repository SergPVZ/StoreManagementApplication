package com.example.store.service;

import com.example.store.dto.SupplierResponseDto;
import com.example.store.entity.Supplier;
import com.example.store.mapper.SupplierMapper;
import com.example.store.repository.SupplierRepository;
import com.example.store.request.SupplierRequest;
import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.validation.annotation.Validated;

import java.util.UUID;

@Service
@Transactional(readOnly = true)
@Validated
public class SupplierService {

    @Autowired
    private SupplierRepository supplierRepository;

    @Autowired
    private SupplierMapper mapper;

    @Transactional(rollbackFor = Exception.class)       // для методов, которые меняют БД
    public SupplierResponseDto createSupplier(@Valid SupplierRequest request) {

        Supplier supplier = new Supplier(UUID.randomUUID(), request.getName(), request.getEmail(), request.getPhone(), request.getAddress(), request.getWebsite(), null);

        supplierRepository.saveAndFlush(supplier);         // сохранение в БД

        return mapper.mapToSupplierResponseDto(supplier);

    }
}
