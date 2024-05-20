package com.practice.learningnavigator.Dto;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.util.Date;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
public class ErrorDetails {

    private Date timestamp;
    private int status;
    private String message;
    private String details;

    public ErrorDetails(int status, String message, String details) {
        super();
        this.timestamp = new Date();
        this.status = status;
        this.message = message;
        this.details = details;
    }
}

