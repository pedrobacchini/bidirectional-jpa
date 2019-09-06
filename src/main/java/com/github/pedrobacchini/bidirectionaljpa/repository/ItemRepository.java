package com.github.pedrobacchini.bidirectionaljpa.repository;

import com.github.pedrobacchini.bidirectionaljpa.domain.Item;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.UUID;

@Repository
public interface ItemRepository extends JpaRepository<Item, UUID> {
}
