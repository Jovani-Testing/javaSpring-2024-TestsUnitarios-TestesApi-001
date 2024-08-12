package com.example.api_user.controllers;

import com.example.api_user.entities.Users;
import com.example.api_user.exceptionHandler.ResourceNotFoundException;
import com.example.api_user.reposiories.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping(value = "/users")
public class UserController {

    @Autowired
    private UserRepository userRepository;

    @GetMapping
    public List<Users> findAll() {
        List<Users> users = userRepository.findAll();
        if (users.isEmpty()) {
            throw new ResourceNotFoundException("Nenhum usuário encontrado.");
        }
        return users;
    }

    @GetMapping(value = "/{id}")
    public Users findById(@PathVariable Long id) {
        return userRepository.findById(id)
                .orElseThrow(() -> new ResourceNotFoundException("Usuário não encontrado com ID: " + id));
    }

    @PostMapping
    public Users create(@RequestBody Users users) {
        return userRepository.save(users);
    }

    @PutMapping(value = "/{id}")
    public Users update(@PathVariable Long id, @RequestBody Users users) {
        Users existingUser = userRepository.findById(id).orElseThrow(() -> new IllegalArgumentException("Usuário não encontrado"));
        existingUser.setName(users.getName());
        existingUser.setEmail(users.getEmail());
        existingUser.setDepartment(users.getDepartment());
        return userRepository.save(existingUser);
    }

    @DeleteMapping(value = "/{id}")
    public void deleteById(@PathVariable Long id) {
        userRepository.deleteById(id);
    }

    @DeleteMapping
    public void deleteAll() {
        userRepository.deleteAll();
    }
}



