package com.github.pedrobacchini.bidirectionaljpa.domain;

import lombok.*;
import org.hibernate.annotations.GenericGenerator;

import javax.persistence.*;
import java.io.Serializable;
import java.util.UUID;

@ToString
@Entity
@Getter
@NoArgsConstructor(access = AccessLevel.PRIVATE) //For Hibernate
public class Item implements Serializable {

    private static final long serialVersionUID = -4033397293385419209L;

    @Id
    @GeneratedValue(generator = "uuid2")
    @GenericGenerator(name = "uuid2", strategy = "uuid2")
    private UUID uuid;

    private String name;

//    É importante colocar false para optional na anotação ManyToOne para forçar um erro
//    quando existe um relacionamento bidirecional, quem estiver consumindo essa
//    classe e não tiver o cuidado de setar valor dos dois lados do relacionamento
//    vai ser lancado esse erro evitando que seja feito uma inserção indevida.
    @ToString.Exclude
    @Setter
    @ManyToOne(optional = false)
    private ShoppingCart cart;

    public Item(String name) { this.name = name; }
}
