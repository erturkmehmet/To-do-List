package com.mehmetertrk.todolist;

import org.springframework.context.annotation.Import;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.test.context.support.WithUserDetails;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.mehmetertrk.todolist.controller.TodoListController;
import com.mehmetertrk.todolist.model.TodoList;
import com.mehmetertrk.todolist.model.User;
import com.mehmetertrk.todolist.service.TodoListService;
import com.mehmetertrk.todolist.service.UserService;

import org.apache.catalina.filters.CorsFilter;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.springframework.http.MediaType;
import org.springframework.test.context.ActiveProfiles;
import org.springframework.test.context.junit4.SpringRunner;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.setup.MockMvcBuilders;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;
import static org.springframework.test.web.servlet.result.MockMvcResultHandlers.print;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.content;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

@RunWith(SpringRunner.class)
@Import(TestWebSecurityConfig.class)
@ActiveProfiles("test")
public class TodoListControllerTests {

    private MockMvc mockMvc;

    private ObjectMapper mapper = new ObjectMapper();

    @Mock
    private TodoListService todoListService;

    @Mock
    private UserService userService;

    @InjectMocks
    private TodoListController todoListController;

    public void setup() {
        SecurityContextHolder.getContext().setAuthentication(
                new UsernamePasswordAuthenticationToken("test@gmail.com", "123456"));
    }

    @Before
    public void init() {
        mockMvc = MockMvcBuilders
                .standaloneSetup(todoListController)             
                .build();
    }

    @Test
    @WithUserDetails("test@gmail.com")
    public void testCreateTodoList() throws Exception {
        User user = new User();
        user.setEmail("test@gmail.com");
        user.setId(1L);
        user.setPassword("123456");

        TodoList todoList = new TodoList();
        todoList.setId(1L);
        todoList.setName("TestTodoList");
        todoList.setUser(new User());
        String requestBody = mapper.writeValueAsString(todoList);

        TodoList todoListResponse = new TodoList();
        todoListResponse.setName("TestTodoList");
        todoListResponse.setId(1L);

        when(userService.getUser(any())).thenReturn(Optional.ofNullable(user));

        when(todoListService.createTodoList(any(), any())).thenReturn(todoList);

        mockMvc.perform(post("/todos")
                .content(requestBody)
                .contentType(MediaType.APPLICATION_JSON_VALUE))
                .andExpect(status().isCreated())
                .andExpect(content().json(mapper.writeValueAsString(todoListResponse)));

        verify(todoListService).createTodoList(any(TodoList.class), any());
    }

    @Test
    @WithUserDetails("test@gmail.com")
    public void testGetTodoList() throws Exception {
        User user = new User();
        user.setEmail("test@gmail.com");
        user.setId(1L);
        user.setPassword("123456");

        TodoList todoList = new TodoList();
        todoList.setId(1L);
        todoList.setName("TestTodoList");

        when(userService.getUser(any())).thenReturn(Optional.ofNullable(user));

        when(todoListService.getTodoList(any(), any())).thenReturn(Optional.ofNullable(todoList));

        mockMvc.perform(get("/todos/1")
                .contentType(MediaType.APPLICATION_JSON_VALUE))
                .andExpect(content().json(mapper.writeValueAsString(todoList)))
                .andDo(print());
    }

    @Test
    @WithUserDetails("test@gmail.com")
    public void testGetAllTodoList() throws Exception {
        User user = new User();
        user.setEmail("test@gmail.com");
        user.setId(1L);
        user.setPassword("123456");

        TodoList todoList = new TodoList();
        todoList.setId(1L);
        todoList.setName("TestTodoList");

        List<TodoList> todoLists = new ArrayList<>();
        todoLists.add(todoList);

        when(userService.getUser(any())).thenReturn(Optional.ofNullable(user));

        when(todoListService.getAllTodoLists(any())).thenReturn(todoLists);

        mockMvc.perform(get("/todos")
                .contentType(MediaType.APPLICATION_JSON_VALUE))
                .andExpect(content().json(mapper.writeValueAsString(todoLists)))
                .andDo(print());
    }
}
