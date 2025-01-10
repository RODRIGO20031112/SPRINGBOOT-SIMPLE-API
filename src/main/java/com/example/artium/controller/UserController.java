package com.example.artium.controller;

import com.example.artium.model.User;
import com.example.artium.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Optional;

@RestController
@RequestMapping("/users")
public class UserController {

    @Autowired
    private UserService userService;

    @GetMapping("/email/{email}")
    public ResponseEntity<User> getUserByEmail(@PathVariable String email) {
        Optional<User> user = userService.getUserByEmail(email);
        return user.map(ResponseEntity::ok).orElseGet(() -> ResponseEntity.notFound().build());
    }

    @GetMapping("/name/{name}")
    public ResponseEntity<List<User>> getUsersByName(@PathVariable String name) {
        List<User> users = userService.getUsersByName(name);
        return users.isEmpty() ? ResponseEntity.noContent().build() : ResponseEntity.ok(users);
    }

    @GetMapping("/count/{name}")
    public ResponseEntity<Long> countUsersByName(@PathVariable String name) {
        long count = userService.countUsersByName(name);
        return ResponseEntity.ok(count);
    }

    @GetMapping("/name/{name}/email/{email}")
    public ResponseEntity<User> getUserByNameAndEmail(@PathVariable String name, @PathVariable String email) {
        Optional<User> user = userService.getUserByNameAndEmail(name, email);
        return user.map(ResponseEntity::ok).orElseGet(() -> ResponseEntity.notFound().build());
    }

    @GetMapping("/names")
    public ResponseEntity<List<User>> getUsersByNames(@RequestParam List<String> names) {
        List<User> users = userService.getUsersByNames(names);
        return users.isEmpty() ? ResponseEntity.noContent().build() : ResponseEntity.ok(users);
    }

    @PostMapping
    public ResponseEntity<User> createUser(@RequestBody User user) {
        User createdUser = userService.saveUser(user);
        return ResponseEntity.status(201).body(createdUser);
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Void> deleteUser(@PathVariable Long id) {
        userService.deleteUser(id);
        return ResponseEntity.noContent().build();
    }

    @PutMapping("/{id}")
    public ResponseEntity<User> updateUser(@PathVariable Long id, @RequestBody User user) {
        user.setId(id);  // Garante que o id no corpo da requisição será utilizado para a atualização
        User updatedUser = userService.saveUser(user);
        return ResponseEntity.ok(updatedUser);
    }
}
