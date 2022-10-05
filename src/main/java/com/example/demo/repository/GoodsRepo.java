package com.example.demo.repository;

import com.example.demo.model.Goods;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;

import java.util.Calendar;
import java.util.Optional;

public interface GoodsRepo extends CrudRepository<Goods, Long> {

    Optional<Goods> findByName(String name);
    @Query(value = "with topSales as ( select goods_id, sum(cost)as summ from shopping_list " +
            "where registered_at between ?1 and ?2 group by goods_id order by summ desc limit 1) " +
            "select * from goods where goods.id in (select goods_id from topSales)", nativeQuery = true)
    Optional<Goods> findTopSale(Calendar from, Calendar to);

    @Query(value = "with product as (SELECT sl.goods_id, count(*) as count\n" +
            "FROM buyer b inner JOIN shopping_list sl ON sl.buyer_id = b.id\n" +
            "WHERE b.age = 18\n" +
            "GROUP BY sl.goods_id\n" +
            "ORDER BY count DESC limit 1)\n" +
            "select * from goods where id in (select goods_id from product)", nativeQuery = true)
    Optional<Goods> findTopSalePeople18age();

}
