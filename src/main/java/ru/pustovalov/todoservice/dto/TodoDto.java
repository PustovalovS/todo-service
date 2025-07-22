package ru.pustovalov.todoservice.dto;

import com.fasterxml.jackson.annotation.JsonFormat;
import com.fasterxml.jackson.databind.PropertyNamingStrategies;
import com.fasterxml.jackson.databind.annotation.JsonNaming;
import lombok.Builder;
import lombok.Value;

import java.sql.Timestamp;

@Value
@Builder
@JsonNaming(PropertyNamingStrategies.SnakeCaseStrategy.class)
public class TodoDto {
    Long id;
    String action;
    @JsonFormat(pattern = "yyyy-MM-dd hh:mm:ss")
    Timestamp actionTimestamp;
    String editor;
}
