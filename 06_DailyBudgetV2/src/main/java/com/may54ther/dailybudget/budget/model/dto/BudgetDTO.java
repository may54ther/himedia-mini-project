package com.may54ther.dailybudget.budget.model.dto;

import lombok.Getter;
import lombok.RequiredArgsConstructor;
import lombok.Setter;
import lombok.ToString;

import java.time.LocalDate;
import java.time.LocalDateTime;

@Getter
@Setter
@ToString
@RequiredArgsConstructor
public class BudgetDTO {

    private int code;
    private int categoryCode;
    private LocalDate usageDate;
    private String type;
    private int amount;
    private String description;
    private LocalDateTime createdAt;
    private LocalDateTime updatedAt;
}
