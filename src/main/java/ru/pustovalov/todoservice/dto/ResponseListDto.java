package ru.pustovalov.todoservice.dto;

import lombok.AllArgsConstructor;
import lombok.Value;

import java.util.List;

@Value
@AllArgsConstructor(staticName = "of")
public class ResponseListDto<T> {

    List<T> data;
    long total;
    long offset;
    long limit;

}
