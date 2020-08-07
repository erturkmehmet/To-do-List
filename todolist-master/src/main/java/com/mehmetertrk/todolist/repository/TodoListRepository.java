package com.mehmetertrk.todolist.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.mehmetertrk.todolist.model.TodoList;
import com.mehmetertrk.todolist.model.User;

import javax.transaction.Transactional;
import java.util.List;
import java.util.Optional;

@Repository
public interface TodoListRepository extends JpaRepository<TodoList, Long> {
    TodoList save(TodoList todoList);
    List<TodoList> findAllByUser(User user);
    Optional<TodoList> findByIdAndUser(Long id, User user);

    @Transactional
    void deleteByIdAndUser(Long id, User user);
}
