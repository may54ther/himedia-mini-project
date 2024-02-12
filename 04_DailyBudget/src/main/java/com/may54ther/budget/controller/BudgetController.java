package com.may54ther.budget.controller;


import com.may54ther.budget.model.dto.BudgetAndCategoryDTO;
import com.may54ther.budget.model.dto.BudgetDTO;
import com.may54ther.budget.model.service.BudgetService;
import com.may54ther.budget.view.BudgetPrint;

import java.util.List;
import java.util.Map;

public class BudgetController {

    private final BudgetService budgetService;
    private final BudgetPrint budgetPrint;

    public BudgetController() {
        this.budgetService = new BudgetService();
        this.budgetPrint = new BudgetPrint();
    }

    public void getBudgetList(int userCode) {
        List<BudgetAndCategoryDTO> budgetList = budgetService.selectBudgetByUserCode(userCode);

        if (budgetList != null && !budgetList.isEmpty()) {
            budgetPrint.printBudgetList(budgetList);
        } else {
            budgetPrint.printErrorMessage("selectAll");
        }
    }

    public void searchByCondition(Map<String, String> condition) {
        List<BudgetAndCategoryDTO> budgetList = budgetService.selectBudgetByCondition(condition);

        if (budgetList != null && !budgetList.isEmpty()) {
            budgetPrint.printBudgetList(budgetList);
        } else {
            budgetPrint.printErrorMessage("selectAll");
        }
    }

    public void addNewBudget(BudgetDTO newBudget) {
        if (budgetService.addNewBudget(newBudget)) {
            budgetPrint.printSuccessMessage("insert");
        } else {
            budgetPrint.printErrorMessage("insert");
        }
    }

    public void modifyBudget(BudgetDTO modifyBudget) {
        if (budgetService.modifyBudget(modifyBudget)) {
            budgetPrint.printSuccessMessage("update");
        } else {
            budgetPrint.printErrorMessage("update");
        }
    }

    public void deleteBudget(int budgetCode) {
        if (budgetService.deleteBudget(budgetCode)) {
            budgetPrint.printSuccessMessage("delete");
        } else {
            budgetPrint.printErrorMessage("delete");
        }
    }

}
