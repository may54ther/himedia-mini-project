package com.may54ther.dailybudget.budget.model.dto;

import lombok.Getter;
import lombok.RequiredArgsConstructor;
import lombok.Setter;
import lombok.ToString;

@Getter
@Setter
@ToString
@RequiredArgsConstructor
public class CategoryDTO {

    private int code;
    private String name;
}
