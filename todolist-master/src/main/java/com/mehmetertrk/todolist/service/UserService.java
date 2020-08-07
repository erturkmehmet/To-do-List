package com.mehmetertrk.todolist.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

import com.mehmetertrk.todolist.model.User;
import com.mehmetertrk.todolist.repository.UserRepository;

import java.util.Optional;

@Service
public class UserService implements UserDetailsService {

    private UserRepository repository;

    @Autowired
    public UserService(UserRepository userRepository) {
        repository = userRepository;
    }

    public User saveUser(User user) {
        return repository.save(user);
    }

    public Optional<User> getUser(Long id) {
        return repository.findById(id);
    }

    @Override
    public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
        return (UserDetails) repository.findByEmail(username);
    }
}
