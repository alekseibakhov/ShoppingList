package com.example.demo.repository;

import com.example.demo.model.Buyer;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;

import java.util.Calendar;
import java.util.Optional;

public interface BuyerRepo extends CrudRepository<Buyer, Long> {

    @Query("select b from Buyer b where b.name = ?1 and b.lastname = ?2 and b.age = ?3")
    Optional<Buyer> findByNameAndLastNameAndAge(String name, String lastname, int age);

    @Query(value = "with topBuyer as (select buyer_id, sum(cost)as summ from shopping_list\n" +
            "                  where registered_at between ?1 and ?2\n" +
            "                  group by buyer_id order by summ desc limit 1)\n" +
            "select * from buyer where buyer.id in (select buyer_id from topBuyer)", nativeQuery = true)
    Optional<Buyer> findTopBuyer(Calendar from, Calendar to);
}
