package com.store.shoppy.app.card.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.store.shoppy.app.card.entity.UserCardEntity;

@Repository
public interface UserCardRepository extends JpaRepository<UserCardEntity,Integer> {
}
