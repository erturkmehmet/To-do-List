package com.mehmetertrk.todolist.service.impl;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.Authentication;
import org.springframework.stereotype.Service;

import com.mehmetertrk.todolist.model.TodoList;
import com.mehmetertrk.todolist.model.User;
import com.mehmetertrk.todolist.repository.TodoListRepository;
import com.mehmetertrk.todolist.service.TodoListService;

import java.util.List;
import java.util.Optional;

@Service
public class TodoListServiceImpl implements TodoListService {

    private TodoListRepository todoListRepository;

    @Autowired
    public TodoListServiceImpl(TodoListRepository todoListRepository) {
        this.todoListRepository = todoListRepository;
    }

    @Override
    public void deleteTodoList(Long id, User user) {
        //User user = getOwnerFromAuthentication(user);
        todoListRepository.deleteByIdAndUser(id, user);
    }

    @Override
    public List<TodoList> getAllTodoLists(User user) {
        //User user = getOwnerFromAuthentication(authentication);

        return todoListRepository.findAllByUser(user);
    }

    @Override
    public TodoList createTodoList(TodoList todoList, User user) {
        //User user = getOwnerFromAuthentication(user);
        todoList.setUser(user);
        return todoListRepository.save(todoList);
    }

    @Override
    public Optional<TodoList> getTodoList(Long id,User user) {
        //User user = getOwnerFromAuthentication(authentication);

        return todoListRepository.findByIdAndUser(id, user);
    }

    public User getOwnerFromAuthentication(Authentication authentication) {
        return (User) authentication.getPrincipal();
    }
}
