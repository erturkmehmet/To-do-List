package com.mehmetertrk.todolist.service;

import java.util.Optional;

import com.mehmetertrk.todolist.model.Item;

public interface ItemService {

    public void deleteItem(Long id);

    public Optional<Item> getItem(Long id);

    public Item createItem(Item item);
}
