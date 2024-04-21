package com.may54ther.dailybudgetv3.budget.dto;

import com.may54ther.dailybudgetv3.budget.type.BudgetType;
import lombok.Getter;
import lombok.RequiredArgsConstructor;
import lombok.Setter;
import org.springframework.format.annotation.DateTimeFormat;

import java.time.LocalDate;

@Getter
@Setter
@RequiredArgsConstructor
public class BudgetDTO {

    private Long code;

    private Long categoryCode;

    private String categoryName;

    @DateTimeFormat(pattern = "yyyy-MM-dd")
    private LocalDate usageDate;

    private BudgetType type;

    private int amount;

    private String description;
}
