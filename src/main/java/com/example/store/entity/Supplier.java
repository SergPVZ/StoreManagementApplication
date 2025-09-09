package com.example.store.entity;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.EntityListeners;
import jakarta.persistence.Id;
import jakarta.validation.constraints.Email;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.NoArgsConstructor;
import org.hibernate.validator.constraints.URL;
import org.springframework.data.annotation.LastModifiedDate;
import org.springframework.data.jpa.domain.support.AuditingEntityListener;
import org.springframework.data.jpa.repository.config.EnableJpaAuditing;

import java.time.LocalDateTime;
//import java.util.Locale;
import java.util.UUID;

@Data                       // автоматически создаёт get set для всех полей класса
@Entity(name = "supplier")    // аннотация помечаем класс как сущность
@AllArgsConstructor         // создаёт конструктор всех аргументов
@NoArgsConstructor          // создаёт конструктор без аргументов
@EqualsAndHashCode
@EnableJpaAuditing
@EntityListeners(AuditingEntityListener.class)

/*
    id: UUID, первичный ключ.

    name: строка, обязательно непустое.

    email: строка, обязательно непустое, формат e-mail; должен быть уникален среди поставщиков.

    phone: строка, необязательное.

    address: строка, необязательное.

    website: строка, необязательное (URL).

    updated_at: метка времени последнего изменения (обновляется автоматически механизмом аудита).   */

public class Supplier {

    @Id
    @Column
    private UUID id;

    @Column
    private String name;

    @Column
    @Email
    private String email;

    @Column
    private String phone;

    @Column
    private String address;

    @Column
    @URL
    private String website;

    @LastModifiedDate
    @Column
    private LocalDateTime updated_at;

}
