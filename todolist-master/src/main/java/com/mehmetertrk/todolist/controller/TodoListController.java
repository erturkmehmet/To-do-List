package com.mehmetertrk.todolist.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.Authentication;
import org.springframework.web.bind.annotation.*;

import com.mehmetertrk.todolist.model.TodoList;
import com.mehmetertrk.todolist.model.User;
import com.mehmetertrk.todolist.service.TodoListService;
import com.mehmetertrk.todolist.service.UserService;

import java.util.List;
import java.util.Optional;

@RestController
public class TodoListController {

    private TodoListService todoListService;
    private UserService userService;

    @Autowired
    public TodoListController(TodoListService todoListService, UserService userService) {
        this.todoListService = todoListService;
        this.userService = userService;
    }

    @GetMapping("/todos")
    public ResponseEntity<List<TodoList>> getAllTodoLists(Authentication authentication) {
        User user = userService.getUser(1L).get();

        return new ResponseEntity<>(todoListService.getAllTodoLists(user), HttpStatus.OK);
    }

    @GetMapping("/todos/{id}")
    public ResponseEntity<Optional<TodoList>> getTodoList(@PathVariable("id") Long id, Authentication authentication) {
       User user = userService.getUser(1L).get();

        return new ResponseEntity<>(todoListService.getTodoList(id, user), HttpStatus.OK);
    }

    @DeleteMapping("/todos/{id}")
    public ResponseEntity<String> deleteTodoList(@PathVariable("id") Long id, Authentication authentication)  {
        User user = userService.getUser(1L).get();

        todoListService.deleteTodoList(id, user);

        return new ResponseEntity<>(HttpStatus.OK);
    }

    @PostMapping("/todos")
    public ResponseEntity<TodoList> createTodoList(@RequestBody TodoList todoList, Authentication authentication) {
        User user = userService.getUser(1L).get();
        TodoList todoListCreated = todoListService.createTodoList(todoList, user);

        return new ResponseEntity<>(todoListCreated, HttpStatus.CREATED);
    }
}
