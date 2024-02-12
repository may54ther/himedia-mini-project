package com.may54ther.budget.controller;


import com.may54ther.budget.model.dto.CategoryDTO;
import com.may54ther.budget.model.service.CategoryService;
import com.may54ther.budget.view.BudgetPrint;

import java.util.List;

public class CategoryController {

    private final CategoryService categoryService;
    private final BudgetPrint budgetPrint;

    public CategoryController() {
        this.categoryService = new CategoryService();
        this.budgetPrint = new BudgetPrint();
    }

    public void getCategoryList() {
        List<CategoryDTO> categoryList = categoryService.selectAllCategoryList();

        if (categoryList != null && !categoryList.isEmpty()) {
            budgetPrint.printCategoryList(categoryList);
        } else {
            budgetPrint.printErrorMessage("selectAll");
        }
    }
}
