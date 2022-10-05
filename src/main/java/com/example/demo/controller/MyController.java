package com.example.demo.controller;

import com.example.demo.model.Buyer;
import com.example.demo.model.Goods;
import com.example.demo.model.ShoppingList;
import com.example.demo.service.ControllerService;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;

import java.util.List;
import java.util.Optional;

@Controller
public class MyController {
    final ControllerService controllerService;

    public MyController(ControllerService controllerService) {
        this.controllerService = controllerService;
    }

    @GetMapping()
    public String getPage() {
        return "hello";
    }

    @GetMapping("/sales")
    public String getSales(Model model) {
        List list = controllerService.shoppingListFindAlL();
        model.addAttribute("shoppingList", list);
        return "sales";
    }

    @GetMapping("/reports")
    public String getReports() {
        return "reports";
    }

    @GetMapping("/reports/1")
    public String getShoppingListForWeek(Model model) {
        // Вывести список покупок за последнюю неделю
        List<ShoppingList> list = controllerService.shoppingListFindAlLForWeek();
        model.addAttribute("all", list);
        return "1";
    }

    @GetMapping("/reports/2")
    public String getMostPopularGoodsForMonth(Model model) {
        //Вывести самый покупаемый товар за последний месяц
        Optional<Goods> goods = controllerService.goodsFindTopSaleForMonth();
        goods.ifPresent(value -> model.addAttribute("goods", value));
        return "2";
    }

    @GetMapping("/reports/3")
    public String getPersonWhoMadeMostPurchasesInSixMonths(Model model) {
        //Вывести имя и фамилию человека, совершившего больше всего покупок за полгода
        Optional<Buyer> buyer = controllerService.findTopBuyer();
        buyer.ifPresent(value -> model.addAttribute("buyer", value));
        return "3";
    }

    @GetMapping("/reports/4")
    public String getTopSalesAmong18YearOlds(Model model) {
        //Что чаще всего покупают люди в возрасте 18 лет
        Optional<Goods> goods = controllerService.findTopSalesAmong18YearOlds();
        goods.ifPresent(value -> model.addAttribute("goods", value));
        return "4";
    }

    @GetMapping("/buyer/{id}")
    public String findById(@PathVariable int id, Model model) {
        Optional<Buyer> buyer = controllerService.buyerFindById((long) id);
        buyer.ifPresent(value -> model.addAttribute("buyer", value));
        return "buyer";
    }
    @PostMapping("/deletePurchase/{id}")
    public String deletePurchase(@PathVariable int id) {
        controllerService.shoppingListDeleteById((long) id);
        return "redirect:/reports/1";
    }
    @PostMapping("/deleteBuyer/{id}")
    public String deleteBuyer(@PathVariable int id) {
        controllerService.buyerDeleteById((long) id);
        return "redirect:/reports";
    }
    @PostMapping("/deleteGoods/{id}")
    public String deleteGoods(@PathVariable int id) {
        controllerService.goodsDeleteById((long) id);
        return "redirect:/reports";
    }



}
