package ru.pustovalov.todoservice.controller;

import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("todo")
public class TodoController {
    private final TodoService todoService;

    @GetMapping()
    List<TodoDto> getAllTodo() {
        return todoService.getAllTodo;
    }

    @GetMapping("/{id}")
    TodoDto getTodoById(@PathVariable long id) {
        return todoService.getTodoByid(id);
    }

    @PostMapping()
    TodoDto saveTodo(@RequestBody TodoRequestDto request) {
        return todoService.saveTodo(request);
    }

    @PutMapping("{id}")
    TodoDto modifyTodo(@PathVariable long id, String action) {
        return todoService.modifyTodo(id, todo);
    }

    @DeleteMapping("{id}")
    void deleteTodo(@PathVariable long id) {
        todoService.deleteTodo(id);
    }
}
