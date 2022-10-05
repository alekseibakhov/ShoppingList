package com.example.demo.service;
import org.apache.commons.lang3.StringUtils;
import com.concretepage.gs_ws.Purchase;
import com.concretepage.gs_ws.Сustomer;
import com.example.demo.model.Buyer;
import com.example.demo.model.Goods;
import com.example.demo.repository.BuyerRepo;
import com.example.demo.repository.GoodsRepo;
import org.springframework.stereotype.Service;

import java.util.Objects;
import java.util.Optional;

@Service
public class EntityService {
    final BuyerRepo buyerRepo;
    final GoodsRepo goodsRepo;

    public EntityService(BuyerRepo buyerRepo, GoodsRepo goodsRepo) {
        this.buyerRepo = buyerRepo;
        this.goodsRepo = goodsRepo;
    }

    Goods getGoods(Purchase purchase) {
        if (Objects.nonNull(purchase.getName())) {
            Optional<Goods> optionalGoods = goodsRepo.findByName(purchase.getName().value());
            return optionalGoods.orElseGet(() -> saveGoods(purchase));
        } throw new IllegalArgumentException("Не указано наименование товара в списке покупок");
    }

    Buyer getBuyer(Сustomer customer) {
        if (StringUtils.isNotBlank(customer.getName()) && StringUtils.isNotBlank(customer.getLastname()) &&  Objects.nonNull(customer.getAge())) {
            Optional<Buyer> optionalBuyer = buyerRepo.findByNameAndLastNameAndAge(customer.getName(), customer.getLastname(), customer.getAge());
            return optionalBuyer.orElseGet(() -> saveBuyer(customer));
        } throw new IllegalArgumentException("Указаны не все поля для Покупателя");
    }

    Goods saveGoods(Purchase purchase) {
        Goods goods = new Goods();
        goods.setName(purchase.getName().value());
        goods.setPrice(purchase.getCost());
        return goodsRepo.save(goods);
    }

    Buyer saveBuyer(Сustomer customer) {
        Buyer buyer = new Buyer();
        buyer.setAge(customer.getAge());
        buyer.setName(customer.getName());
        buyer.setLastname(customer.getLastname());
        return buyerRepo.save(buyer);
    }

}
