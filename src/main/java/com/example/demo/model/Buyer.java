package com.example.demo.model;

import lombok.*;

import javax.persistence.*;
import java.util.Set;

@Builder
@Entity
@Table(name = "buyer")
@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
public class Buyer {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id", nullable = false)
    private Long id;
    private String name; // - Имя (например, Иван)
    private String lastname; //  - Фамилия (Например, Иванов)
    private int age; // - Возраст (например, 10 лет)

    //Параметр joinColumns указывает на поле, которое используется для прямой связи.
    // Параметр inverseJoinColumns указывает на поле, которое используется для обратной связи.
    // Для указания столбцов связи из таблицы используется аннотация @JoinColumn.
    @OneToMany(mappedBy = "buyer", cascade = CascadeType.ALL)
    private Set<ShoppingList> listGoods;

    @Override
    public String toString() {
        return getName() + " " + getLastname() + " " + getAge() + " " + getListGoods();
    }

}
