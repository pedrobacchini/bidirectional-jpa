package com.github.pedrobacchini.bidirectionaljpa.domain;

import lombok.Getter;
import lombok.ToString;
import org.hibernate.annotations.GenericGenerator;

import javax.persistence.*;
import java.io.Serializable;
import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
import java.util.UUID;

@ToString
@Entity
public class ShoppingCart implements Serializable {

    private static final long serialVersionUID = 7711051123129214946L;

    @Id
    @Getter
    @GeneratedValue(generator = "uuid2")
    @GenericGenerator(name = "uuid2", strategy = "uuid2")
    private UUID uuid;

    //    @OneToMany(cascade = CascadeType.ALL, mappedBy = "cart", fetch = FetchType.EAGER)
    @OneToMany(cascade = CascadeType.ALL, mappedBy = "cart")
    private List<Item> itens = new ArrayList<>();

    public void addItem(Item item) {
        this.itens.add(item);
        item.setCart(this);
    }

    public Item remove(int index) {
        Item item = this.itens.remove(index);
        item.setCart(null);
        return item;
    }

    public List<Item> getItems() { return Collections.unmodifiableList(this.itens); }
}
