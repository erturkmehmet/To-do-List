package com.mehmetertrk.todolist.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.Authentication;
import org.springframework.web.bind.annotation.*;

import com.mehmetertrk.todolist.model.Item;
import com.mehmetertrk.todolist.model.TodoList;
import com.mehmetertrk.todolist.model.User;
import com.mehmetertrk.todolist.service.ItemService;
import com.mehmetertrk.todolist.service.TodoListService;
import com.mehmetertrk.todolist.service.UserService;

import java.util.List;
import java.util.Optional;

@RestController
public class ItemContoller {

    private ItemService itemService;
    private TodoListService todoListService;
    private UserService userService;

    @Autowired
    public ItemContoller(ItemService itemService, TodoListService todoListService, UserService userService) {
        this.itemService = itemService;
        this.todoListService = todoListService;
        this.userService = userService;
    }


    @GetMapping("/todos/{id}/items")
    public ResponseEntity<List<Item>> getAllTodoItems(@PathVariable("id") Long id, Authentication authentication) {
        User user = userService.getUser(1L).get();
       TodoList todoList = todoListService.getTodoList(id, user).orElse(null);
       List<Item> arrayList = todoList.getItems();

       return new ResponseEntity<>(arrayList, HttpStatus.OK);
    }


    @PostMapping("/todos/{id}/items")
    public ResponseEntity<Item> createTodoItem(@PathVariable("id") Long id,
                                               @RequestBody Item item,
                                               Authentication authentication) {
        User user = userService.getUser(1L).get();
        TodoList todoList = todoListService.getTodoList(id, user).orElse(null);
        item.setTodoList(todoList);
        itemService.createItem(item);

        return new ResponseEntity<>(itemService.createItem(item), HttpStatus.OK);
    }

    @GetMapping("/todos/{id}/items/{itemId}")
    public ResponseEntity<Optional<Item>> getTodoItem(@PathVariable("id") Long id, @PathVariable("itemId") Long itemId) {
        return new ResponseEntity<>(itemService.getItem(itemId), HttpStatus.OK);
    }

    @DeleteMapping("/todos/{id}/items/{itemId}")
    public ResponseEntity<?> deleteTodoItem(@PathVariable("id") Long id,
                                            @PathVariable("itemId") Long itemId) {
        itemService.deleteItem(itemId);

        return new ResponseEntity<>(HttpStatus.OK);
    }
}
