package ru.pustovalov.todoservice.controller;

import lombok.AllArgsConstructor;
import org.springframework.web.bind.annotation.*;
import ru.pustovalov.todoservice.dto.ResponseListDto;
import ru.pustovalov.todoservice.dto.TodoDto;
import ru.pustovalov.todoservice.service.TodoService;

@RestController
@RequestMapping("todo")
@AllArgsConstructor
public class TodoController {

    private final TodoService todoService;

    @GetMapping()
    ResponseListDto<TodoDto> getAllTodo(int offset, int limit) {
        return todoService.getAllTodo(offset, limit);
    }

    @PostMapping()
    TodoDto saveTodo(@RequestParam String action) {
        return todoService.saveTodo(action);
    }

    @PutMapping("{id}")
    TodoDto modifyTodo(@PathVariable long id, String action) {
        return todoService.modifyTodo(id, action);
    }

    @DeleteMapping("{id}")
    void deleteTodo(@PathVariable long id) {
        todoService.deleteTodo(id);
    }
}
