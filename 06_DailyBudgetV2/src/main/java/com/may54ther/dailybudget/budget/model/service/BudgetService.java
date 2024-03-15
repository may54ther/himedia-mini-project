package com.may54ther.dailybudget.budget.model.service;

import com.may54ther.dailybudget.budget.model.dto.BudgetDTO;
import com.may54ther.dailybudget.budget.model.dto.CategoryDTO;
import com.may54ther.dailybudget.budget.model.mapper.BudgetMapper;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@RequiredArgsConstructor
public class BudgetService {

    private final BudgetMapper budgetMapper;

    public List<BudgetDTO> findAllBudget() {
        return budgetMapper.findAllBudget();
    }

    public List<CategoryDTO> findCategoryList() { return budgetMapper.findCategoryList(); }

    public int addBudget(BudgetDTO budget) { return budgetMapper.insertBudget(budget); }

    public int deleteBudget(Integer budgetCode) { return budgetMapper.deleteBudget(budgetCode); }
}
