package ru.pustovalov.todoservice.service;

import lombok.AllArgsConstructor;
import lombok.val;
import org.springframework.stereotype.Service;
import ru.pustovalov.todoservice.dto.ResponseListDto;
import ru.pustovalov.todoservice.dto.TodoDto;
import ru.pustovalov.todoservice.entity.TodoEntity;
import ru.pustovalov.todoservice.exception.BadRequestException;
import ru.pustovalov.todoservice.mapper.TodoMapper;
import ru.pustovalov.todoservice.repository.TodoRepository;
import ru.pustovalov.todoservice.util.OffsetAndLimitPageable;

import java.sql.Timestamp;
import java.time.Instant;

@Service
@AllArgsConstructor
public class TodoService {

    private final TodoRepository todoRepository;
    private final TodoMapper todoMapper;

    public ResponseListDto<TodoDto> getAllTodo(int offset, int limit) {
        val pageable = new OffsetAndLimitPageable(offset, limit);
        val result = todoRepository.findAll(pageable);
        val resultDtoList = result.map(todoMapper::entityToDto)
                .toList();

        return ResponseListDto.of(resultDtoList, result.getTotalElements(), offset, limit);
    }

    public TodoDto saveTodo(String action) {
        val entity = TodoEntity.builder()
                .action(action)
                .actionTimestamp(nowDate())
                .editor("temp")
                .build();
        val saveResult = todoRepository.save(entity);

        return todoMapper.entityToDto(saveResult);
    }

    public TodoDto modifyTodo(Long id, String action) {
        val entityOptional = todoRepository.findById(id);

        return entityOptional.map(entity -> {
            entity.setAction(action);
            entity.setActionTimestamp(nowDate());
            entity.setEditor("temp");

            val saveEntity = todoRepository.save(entity);
            return todoMapper.entityToDto(saveEntity);
        }).orElseThrow(() -> new BadRequestException("Todo not found"));
    }

    public void deleteTodo(Long id) {
        existsById(id);

        todoRepository.deleteById(id);
    }

    private void existsById(Long id) {
        if (!todoRepository.existsById(id)) {
            throw new BadRequestException("Todo doesn't exist");
        }
    }

    private Timestamp nowDate() {
        return Timestamp.from(Instant.now());
    }
}
