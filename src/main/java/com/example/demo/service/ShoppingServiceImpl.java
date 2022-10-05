package com.example.demo.service;

import com.concretepage.gs_ws.PostRequest;
import com.concretepage.gs_ws.Purchase;
import com.example.demo.model.Buyer;
import com.example.demo.model.Goods;
import com.example.demo.model.ShoppingList;
import com.example.demo.repository.ShoppingListRepository;
import org.springframework.stereotype.Service;

@Service
public class ShoppingServiceImpl implements ShoppingService{

    final ShoppingListRepository shoppingListRepository;
    final EntityService entityService;

    public ShoppingServiceImpl(ShoppingListRepository shoppingListRepository, EntityService entityService) {
        this.shoppingListRepository = shoppingListRepository;
        this.entityService = entityService;
    }

    public void processRequest(PostRequest request) {
        Buyer buyer = entityService.getBuyer(request.getCustomer());

        for (Purchase purchase : request.getPurchaseList()) {
            Goods goods = entityService.getGoods(purchase);
            ShoppingList shoppingList = new ShoppingList();
            shoppingList.setName(request.getPurchaseName());
            shoppingList.setGoods(goods);
            shoppingList.setBuyer(buyer);
            shoppingList.setRegisteredAt(request.getPurchaseDate().toGregorianCalendar().getTime());
            shoppingList.setCost(purchase.getCount()*purchase.getCost());
            shoppingListRepository.save(shoppingList);
        }

    }


}
