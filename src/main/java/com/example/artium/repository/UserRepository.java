package com.example.artium.repository;

import com.example.artium.model.User;
import org.springframework.data.jpa.repository.JpaRepository;
import java.util.List;
import java.util.Optional;

public interface UserRepository extends JpaRepository<User, Long> {

    // Buscar usuário pelo e-mail
    Optional<User> findByEmail(String email);

    // Buscar usuários pelo nome (com base na correspondência parcial)
    List<User> findByNameContainingIgnoreCase(String name);

    // Contar usuários pelo nome (retorna o número de usuários com o nome fornecido)
    long countByName(String name);

    // Buscar usuário pelo nome e e-mail
    Optional<User> findByNameAndEmail(String name, String email);

    // Buscar usuários com o nome em uma lista de nomes
    List<User> findByNameIn(List<String> names);

}
