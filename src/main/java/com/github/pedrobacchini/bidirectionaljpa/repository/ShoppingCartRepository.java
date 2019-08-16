package com.github.pedrobacchini.bidirectionaljpa.repository;

import com.github.pedrobacchini.bidirectionaljpa.domain.ShoppingCart;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.UUID;

@Repository
public interface ShoppingCartRepository extends JpaRepository<ShoppingCart, UUID> {

//    SELECT * from shopping_cart cart JOIN item ON item.cart_uuid = cart.uuid
//    SELECT * from shopping_cart cart LEFT JOIN item i on cart.uuid = i.cart_uuid

    //    Busca todos os carrinhos de compras que tem itens, com os items junto.
    @Transactional(readOnly = true)
    @Query("SELECT cart FROM ShoppingCart cart JOIN FETCH cart.itens")
    List<ShoppingCart> findAllByItemsEager();

    //    Busca todos os carrinhos de compras que tem itens e que não tem,
    //    e os que tiverem e para buscar os items tambem
    @Transactional(readOnly = true)
    @Query("SELECT cart FROM ShoppingCart cart LEFT JOIN FETCH cart.itens")
    List<ShoppingCart> findAllEager();

    //    Busca todos os carrinhos de compras que tem itens.
    @Transactional(readOnly = true)
    @Query("SELECT cart FROM ShoppingCart cart JOIN cart.itens")
    List<ShoppingCart> findAllByItems();

    //    Busca todos os carrinhos de compras usando JPQL.
    @Transactional(readOnly = true)
    @Query("SELECT cart FROM ShoppingCart cart")
    List<ShoppingCart> findAllJPQL();
}
