package com.example.store.entity;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.Id;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.NoArgsConstructor;
import org.springframework.data.annotation.LastModifiedDate;

import java.time.LocalDateTime;
import java.util.Locale;
import java.util.UUID;

@Data                       // автоматически создаёт get set для всех полей класса
@Entity(name = "stores")    // аннотация помечаем класс как сущность
@AllArgsConstructor         // создаёт конструктор всех аргументов
@NoArgsConstructor          // создаёт конструктор без аргументов
@EqualsAndHashCode
public class Store {        /* описывает структуру данных в БД
                              1) через Репозиторий данные сохраняет, загружает и удаляет
                              2) через Маппер преобразует в ДТО*/

    @Id                     // ключ
    @Column                 // поле изображается колонкой в БД
    private UUID id;

    @Column
    private String name;

    @Column
    private String location;

    @Column
    private String email;

    @LastModifiedDate        // дата обновления автомотическая
    @Column
    private LocalDateTime updated_at;

}
