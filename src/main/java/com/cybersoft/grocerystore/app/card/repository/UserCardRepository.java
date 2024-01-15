package com.cybersoft.grocerystore.app.card.repository;

import com.cybersoft.grocerystore.app.card.entity.UserCardEntity;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface UserCardRepository extends JpaRepository<UserCardEntity,Integer> {
}
