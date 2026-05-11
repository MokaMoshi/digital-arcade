package com.digital_arcade_spring.digital_arcade.repository;

import com.digital_arcade_spring.digital_arcade.model.Items;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface ItemsRepository extends JpaRepository<Items, Integer> {
}
