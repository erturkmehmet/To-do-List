package com.mehmetertrk.todolist;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.EnableAutoConfiguration;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.ComponentScan;

import com.mehmetertrk.todolist.model.User;
import com.mehmetertrk.todolist.service.TodoListService;
import com.mehmetertrk.todolist.service.UserService;

@SpringBootApplication
public class TodoListApplication implements CommandLineRunner {

    private UserService userService;
    private TodoListService todoListService;

    @Autowired
    public TodoListApplication(UserService userService, TodoListService todoListService) {
        this.userService = userService;
        this.todoListService = todoListService;
    }

    public static void main(String[] args) {
        SpringApplication.run(TodoListApplication.class, args);
    }

    @Override
    public void run(String... args) throws Exception {
        User user = new User();
        user.setEmail("test@gmail.com");
        user.setPassword("123456");
        userService.saveUser(user);
    }
}
