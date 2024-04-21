package com.may54ther.dailybudgetv3.common.response;

import lombok.AllArgsConstructor;
import lombok.Getter;

@Getter
@AllArgsConstructor
public class ApiResponse {

    private boolean success;

    private String message;

    private Object response;
}
