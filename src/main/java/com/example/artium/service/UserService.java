package com.example.artium.service;

import com.example.artium.model.User;
import com.example.artium.repository.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class UserService {

    @Autowired
    private UserRepository userRepository;

    public Optional<User> getUserByEmail(String email) {
        return userRepository.findByEmail(email);
    }

    public List<User> getUsersByName(String name) {
        return userRepository.findByNameContainingIgnoreCase(name);
    }

    public long countUsersByName(String name) {
        return userRepository.countByName(name);
    }

    public Optional<User> getUserByNameAndEmail(String name, String email) {
        return userRepository.findByNameAndEmail(name, email);
    }

    public List<User> getUsersByNames(List<String> names) {
        return userRepository.findByNameIn(names);
    }

    // Método para salvar um novo usuário
    public User saveUser(User user) {
        return userRepository.save(user);
    }

    // Método para excluir um usuário pelo ID
    public void deleteUser(Long id) {
        userRepository.deleteById(id);
    }
}

