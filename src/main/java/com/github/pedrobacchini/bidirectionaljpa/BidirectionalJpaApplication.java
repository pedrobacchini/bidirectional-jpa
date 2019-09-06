package com.github.pedrobacchini.bidirectionaljpa;

import com.github.pedrobacchini.bidirectionaljpa.domain.Item;
import com.github.pedrobacchini.bidirectionaljpa.domain.ShoppingCart;
import com.github.pedrobacchini.bidirectionaljpa.repository.ItemRepository;
import com.github.pedrobacchini.bidirectionaljpa.repository.ShoppingCartRepository;
import lombok.extern.slf4j.Slf4j;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.UUID;

@Slf4j
@SpringBootApplication
public class BidirectionalJpaApplication implements CommandLineRunner {

    private final ItemRepository itemRepository;
    private final ShoppingCartRepository shoppingCartRepository;

    public BidirectionalJpaApplication(ItemRepository itemRepository,
                                       ShoppingCartRepository shoppingCartRepository) {
        this.itemRepository = itemRepository;
        this.shoppingCartRepository = shoppingCartRepository;
    }

    public static void main(String[] args) { SpringApplication.run(BidirectionalJpaApplication.class, args); }

    @Override
    public void run(String... args) {
        log.info("Start Application");

        createShoppingCart();

//        removeFirstItem("b792a9eb-c914-4e81-8d1c-71a4f83bf897");


        printAllShoppingCart();

//        shoppingCartRepository.deleteById(UUID.fromString("773a4c92-8ffa-4b93-9afb-a22b1895fb58"));
//        shoppingCartRepository.deleteAll();
//        itemRepository.deleteAll();
    }

    @Transactional
    void removeFirstItem(String uuid) {
        ShoppingCart shoppingCart = shoppingCartRepository
                .findByIdFetch(UUID.fromString(uuid)).get();

//        Item item = shoppingCart.getItems().remove(1);
//        List<Item> itens = shoppingCart.getItems();
//        Item item = itens.remove(1);
//        item.setCart(null);
        Item remove = shoppingCart.remove(0);

        itemRepository.delete(remove);
//        itemRepository.save(item);
//        shoppingCartRepository.save(shoppingCart);
    }

    @Transactional
    void printAllShoppingCart() {
//        List<ShoppingCart> shoppingCartList = shoppingCartRepository.findAllJPQL();
        List<ShoppingCart> shoppingCartList = shoppingCartRepository.findAllEager();

        shoppingCartList.forEach(System.out::println);
    }

    private void createShoppingCart() {
        Item ipad = new Item("iPad Retina Display");
        Item macbookPro = new Item("Macbook Pro");
        Item pcIBM = new Item("PC IBM");

        ShoppingCart shoppingCart = shoppingCartRepository.save(new ShoppingCart());

        shoppingCart.addItem(ipad);
        shoppingCart.addItem(macbookPro);
        shoppingCart.addItem(pcIBM);
//        shoppingCart.getItems().add(ipad);
//        shoppingCart.getItems().add(macbookPro);
//        shoppingCart.getItems().add(pcIBM);
//        ipad.setCart(shoppingCart);
//        macbookPro.setCart(shoppingCart);
//        pcIBM.setCart(shoppingCart);

        shoppingCartRepository.save(shoppingCart);
    }
}
