package com.may54ther.dailybudgetv3.budget.dto;

import com.may54ther.dailybudgetv3.budget.type.BudgetType;
import lombok.Getter;
import lombok.RequiredArgsConstructor;

@Getter
@RequiredArgsConstructor
public class SearchBudgetDTO {

    private final BudgetType type;

    private final Integer minAmount;
    
    private final Integer maxAmount;

    private final String description;
}
