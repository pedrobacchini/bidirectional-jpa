package com.github.pedrobacchini.bidirectionaljpa;

import com.github.pedrobacchini.bidirectionaljpa.domain.Item;
import com.github.pedrobacchini.bidirectionaljpa.domain.ShoppingCart;
import com.github.pedrobacchini.bidirectionaljpa.repository.ShoppingCartRepository;
import lombok.extern.slf4j.Slf4j;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@Slf4j
@SpringBootApplication
public class BidirectionalJpaApplication implements CommandLineRunner {

    private final ShoppingCartRepository shoppingCartRepository;

    public BidirectionalJpaApplication(ShoppingCartRepository shoppingCartRepository) {
        this.shoppingCartRepository = shoppingCartRepository;
    }

    public static void main(String[] args) { SpringApplication.run(BidirectionalJpaApplication.class, args); }

    @Override
    public void run(String... args) {
        log.info("Start Application");

//        createShoppingCart();
        printAllShoppingCart();

//        shoppingCartRepository.deleteAll();

    }

    @Transactional
    void printAllShoppingCart() {
//        List<ShoppingCart> shoppingCartList = shoppingCartRepository.findAllJPQL();
        List<ShoppingCart> shoppingCartList = shoppingCartRepository.findAllEager();

        shoppingCartList.forEach(System.out::println);
    }

    private void createShoppingCart() {
        Item ipad = new Item("iPad Retina Display");

        ShoppingCart shoppingCart = shoppingCartRepository.save(new ShoppingCart());

        shoppingCart.getItens().add(ipad);
        ipad.setCart(shoppingCart);

        shoppingCartRepository.save(shoppingCart);
    }
}
