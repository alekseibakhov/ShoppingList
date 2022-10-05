package com.example.demo.repository;

import com.example.demo.model.ShoppingList;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;

import java.util.Calendar;
import java.util.List;
public interface ShoppingListRepository extends CrudRepository<ShoppingList, Long> {
    @Query(value = "select * from shopping_list", nativeQuery = true)
    List<ShoppingList> findAlL();

    @Query(value = "select * from shopping_list where registered_at between ?1 and ?2", nativeQuery = true)
    List<ShoppingList> findAlLForWeek(Calendar from, Calendar to);
}