package com.may54ther.dailybudget.budget.model.service;

import com.may54ther.dailybudget.budget.model.dto.BudgetDTO;
import com.may54ther.dailybudget.budget.model.dto.CategoryDTO;
import com.may54ther.dailybudget.configuration.DailyBudgetApplication;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.transaction.annotation.Transactional;

import java.time.LocalDate;
import java.util.List;

import static org.junit.jupiter.api.Assertions.assertDoesNotThrow;
import static org.junit.jupiter.api.Assertions.assertNotNull;

@SpringBootTest
@ContextConfiguration(classes = { DailyBudgetApplication.class })
class BudgetServiceTest {

    @Autowired
    private BudgetService budgetService;

    @Test
    public void 전체_가계부_조회() {
        // given
        // when
        List<BudgetDTO> budgetList = budgetService.findAllBudget();
        // then
        assertNotNull(budgetList);
    }

    @Test
    public void 전체_카테고리_조회() {
        // given
        // when
        List<CategoryDTO> categoryList = budgetService.findCategoryList();
        // then
        assertNotNull(categoryList);
    }

    @Test
    @Transactional
    public void 신규_가계부_등록_성공() {
        // given
        BudgetDTO budget = new BudgetDTO();
        budget.setCategoryCode(1);
        budget.setUsageDate(LocalDate.now());
        budget.setType("IN");
        budget.setAmount(50000);
        budget.setDescription("오다 주웠다");
        // when & then
        assertDoesNotThrow(() -> budgetService.addBudget(budget));
    }

    @Test
    @Transactional
    public void 가계부_삭제_성공() {
        // given
        int budgetCode = 60;

        // when & then
        assertDoesNotThrow(() -> budgetService.deleteBudget(budgetCode));
    }
}