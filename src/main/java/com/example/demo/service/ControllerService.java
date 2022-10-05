package com.example.demo.service;

import com.example.demo.model.Buyer;
import com.example.demo.model.Goods;
import com.example.demo.model.ShoppingList;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public interface ControllerService {
    List shoppingListFindAlL();
    List<ShoppingList> shoppingListFindAlLForWeek();

    Optional<Goods> goodsFindTopSaleForMonth();

    Optional<Buyer> findTopBuyer();

    Optional<Goods> findTopSalesAmong18YearOlds();

    Optional<Buyer> buyerFindById(Long id);

    void shoppingListDeleteById(long id);
    void buyerDeleteById(long id);
    void goodsDeleteById(long id);


}
