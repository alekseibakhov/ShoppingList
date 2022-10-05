package com.example.demo.model;

import lombok.*;

import javax.persistence.*;
import java.util.List;

@Builder
@Entity
@Table(name = "goods")
@AllArgsConstructor
@NoArgsConstructor
@Getter
@Setter
public class Goods {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id", nullable = false)
    private Long id;

    @Column(name = "name")
    private String name;
    @Column(name = "price", nullable = false)
    private double price;

    //Параметр joinColumns указывает на поле, которое используется для прямой связи.
    // Параметр inverseJoinColumns указывает на поле, которое используется для обратной связи.
    // Для указания столбцов связи из таблицы используется аннотация @JoinColumn.
    @OneToMany(mappedBy = "goods", cascade = CascadeType.ALL)
//    @JoinTable(name = "shopping_list", joinColumns = @JoinColumn(name = "goods_id"),
//            inverseJoinColumns = @JoinColumn(name = "buyer_id"))
    List<ShoppingList> buyerList;

    @Override
    public String toString() {
        return getName() + " " + getPrice();
    }
}
