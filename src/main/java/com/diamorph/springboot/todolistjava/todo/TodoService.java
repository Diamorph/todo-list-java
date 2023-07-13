package com.diamorph.springboot.todolistjava.todo;

import jakarta.validation.Valid;
import org.springframework.stereotype.Service;

import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;
import java.util.function.Predicate;

@Service
public class TodoService {
    private static final List<Todo> todos = new ArrayList<>();
    static {
        todos.add(
            new Todo(todos.size() + 1, "diamorph", "Learn Java Spring", LocalDate.of(2023, 8, 1), false)
        );
        todos.add(
            new Todo(todos.size() + 1, "diamorph", "Learn Java Spring MySQL", LocalDate.of(2023, 10, 1), false)
        );
        todos.add(
            new Todo(todos.size() + 1, "diamorph", "Learn Full Stack", LocalDate.of(2023, 12, 1), false)
        );
    }

    public List<Todo> findByUsername(String username) {
        Predicate<? super Todo> predicate = todo -> todo.getUsername().equalsIgnoreCase(username);
        return todos.stream().filter(predicate).toList();
    }

    public void addTodo(String username, String description, LocalDate targetDate, boolean done) {
        todos.add(new Todo(todos.size() + 1, username, description, targetDate, done));
    }

    public void deleteById(int id) {
        todos.removeIf(t -> t.getId() == id);
    }

    public Todo findById(int id) {
        return todos.stream().filter(t -> t.getId() == id).findFirst().get();
    }

    public void updateTodo(@Valid Todo todo) {
        deleteById((int) todo.getId());
        todos.add(todo);
    }
}
