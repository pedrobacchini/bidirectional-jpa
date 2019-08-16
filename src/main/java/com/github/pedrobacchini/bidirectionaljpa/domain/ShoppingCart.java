package com.github.pedrobacchini.bidirectionaljpa.domain;

import lombok.Getter;
import lombok.ToString;
import org.hibernate.annotations.GenericGenerator;

import javax.persistence.*;
import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;
import java.util.UUID;

@ToString
@Entity
@Getter
public class ShoppingCart implements Serializable {

    private static final long serialVersionUID = 7711051123129214946L;
    @Id
    @GeneratedValue(generator = "uuid2")
    @GenericGenerator(name = "uuid2", strategy = "uuid2")
    private UUID uuid;

//    @OneToMany(cascade = CascadeType.ALL, mappedBy = "cart", fetch = FetchType.EAGER)
    @OneToMany(cascade = CascadeType.ALL, mappedBy = "cart")
    private List<Item> itens = new ArrayList<>();
}
