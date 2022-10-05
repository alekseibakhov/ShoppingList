package com.example.demo.service;

import com.example.demo.model.Buyer;
import com.example.demo.model.Goods;
import com.example.demo.model.ShoppingList;
import com.example.demo.repository.BuyerRepo;
import com.example.demo.repository.GoodsRepo;
import com.example.demo.repository.ShoppingListRepository;
import org.springframework.stereotype.Service;

import java.util.Calendar;
import java.util.List;
import java.util.Optional;
@Service
public class ControllerServiceImpl implements ControllerService {
    final BuyerRepo buyerRepo;
    final GoodsRepo goodsRepo;
    final ShoppingListRepository shoppingListRepository;

    public ControllerServiceImpl(BuyerRepo buyerRepo, GoodsRepo goodsRepo, ShoppingListRepository shoppingListRepository) {
        this.buyerRepo = buyerRepo;
        this.goodsRepo = goodsRepo;
        this.shoppingListRepository = shoppingListRepository;
    }

    @Override
    public List shoppingListFindAlL() {
        return shoppingListRepository.findAlL();
    }

    @Override
    public List<ShoppingList> shoppingListFindAlLForWeek() {
        Calendar from = Calendar.getInstance();
        Calendar to = Calendar.getInstance();
        from.add(Calendar.DATE, -6);
        to.add(Calendar.DATE, +1);
        return shoppingListRepository.findAlLForWeek(from, to);
    }

    @Override
    public Optional<Goods> goodsFindTopSaleForMonth() {
        Calendar from = Calendar.getInstance();
        Calendar to = Calendar.getInstance();
        from.add(Calendar.DATE, -30);
        to.add(Calendar.DATE, +1);
        return goodsRepo.findTopSale(from, to);
    }

    @Override
    public Optional<Buyer> findTopBuyer() {
        Calendar from = Calendar.getInstance();
        Calendar to = Calendar.getInstance();
        from.add(Calendar.DATE, -180);
        to.add(Calendar.DATE, +1);
        return buyerRepo.findTopBuyer(from, to);
    }

    @Override
    public Optional<Goods> findTopSalesAmong18YearOlds() {
        return goodsRepo.findTopSalePeople18age();
    }

    @Override
    public Optional<Buyer> buyerFindById(Long id) {
        return buyerRepo.findById(id);
    }

    @Override
    public void shoppingListDeleteById(long id) {
        shoppingListRepository.deleteById(id);
    }

    @Override
    public void buyerDeleteById(long id) {
        buyerRepo.deleteById(id);
    }

    @Override
    public void goodsDeleteById(long id) {
        goodsRepo.deleteById(id);
    }

}
