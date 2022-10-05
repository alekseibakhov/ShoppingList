package com.example.demo.model;

import lombok.*;

import javax.persistence.*;
import java.util.Date;

@Getter
@Builder
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Entity
public class ShoppingList {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id", nullable = false)
    Long id;

    @ManyToOne (fetch = FetchType.LAZY)
    @JoinColumn(name = "buyer_id")
    Buyer buyer;

    @ManyToOne (fetch = FetchType.LAZY)
    @JoinColumn(name = "goods_id")
    Goods goods;

    @Column(name = "registered_at")
    Date registeredAt;
    String name;
    double cost;

    @Override
    public String toString() {
        return getId() + " " + getGoods().getName()  + " " + getRegisteredAt() + getCost();
    }
}