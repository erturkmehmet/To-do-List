package com.mehmetertrk.todolist.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.mehmetertrk.todolist.model.Item;

@Repository
public interface ItemRepository extends JpaRepository<Item, Long> {
}
