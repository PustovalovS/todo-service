package ru.pustovalov.todoservice.mapper;

import org.mapstruct.Mapper;
import ru.pustovalov.todoservice.dto.TodoDto;
import ru.pustovalov.todoservice.entity.TodoEntity;

@Mapper(componentModel = "spring")
public interface TodoMapper {
    TodoDto entityToDto(TodoEntity todo);
}
