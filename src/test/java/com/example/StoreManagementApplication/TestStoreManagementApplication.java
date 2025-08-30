package com.example.StoreManagementApplication;

import com.example.store.StoreManagementApplication;
import org.springframework.boot.SpringApplication;

public class TestStoreManagementApplication {

	public static void main(String[] args) {
		SpringApplication.from(StoreManagementApplication::main).with(TestcontainersConfiguration.class).run(args);
	}

}
