package com.mehmetertrk.todolist.service;

import org.springframework.security.core.Authentication;

import com.mehmetertrk.todolist.model.TodoList;
import com.mehmetertrk.todolist.model.User;

import java.util.List;
import java.util.Optional;

public interface TodoListService {

     void deleteTodoList(Long id, User user);

     List<TodoList> getAllTodoLists(User user);

     TodoList createTodoList(TodoList todoList, User user);

     Optional<TodoList> getTodoList(Long id, User user);
}
