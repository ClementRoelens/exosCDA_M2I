package org.example.backend.repository;

import org.example.backend.entity.Todo;
import org.springframework.data.jpa.repository.JpaRepository;
import org.example.backend.entity.User;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.UUID;

@Repository
public interface TodoRepository extends JpaRepository<Todo, UUID> {
    List<Todo> findTodosByUser(User user);
}
