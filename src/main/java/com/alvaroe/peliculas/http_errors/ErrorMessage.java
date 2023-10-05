package com.alvaroe.peliculas.http_errors;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.ToString;

@Getter
@ToString
@AllArgsConstructor
public class ErrorMessage {
    private final String message;
    private final int code;
}
