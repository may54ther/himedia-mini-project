package com.may54ther.budget.model.mapper;


import com.may54ther.budget.model.dto.BudgetAndCategoryDTO;
import com.may54ther.budget.model.dto.BudgetDTO;

import java.util.List;
import java.util.Map;

public interface BudgetMapper {

    List<BudgetAndCategoryDTO> selectBudgetAndCategoryByUserCode(int userCode);

    List<BudgetAndCategoryDTO> selectBudgetAndCategoryByCondition(Map<String, String> condition);

    int insertBudget(BudgetDTO newBudget);

    int updateBudget(BudgetDTO modifyBudget);

    int deleteBudget(int budgetCode);
}
