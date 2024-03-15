package com.may54ther.dailybudget.budget.model.mapper;

import com.may54ther.dailybudget.budget.model.dto.BudgetDTO;
import com.may54ther.dailybudget.budget.model.dto.CategoryDTO;
import org.apache.ibatis.annotations.Mapper;

import java.util.List;

@Mapper
public interface BudgetMapper {

    List<BudgetDTO> findAllBudget();

    List<CategoryDTO> findCategoryList();

    int insertBudget(BudgetDTO budget);

    int deleteBudget(Integer budgetCode);
}
