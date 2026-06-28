package com.company.employee.payload;

import lombok.Builder;
import lombok.Data;

@Data
@Builder
public class APIResponse<T> {

    private boolean success;

    private String message;

    private T data;

}