package com.may54ther.dailybudgetv3.budget.controller;

import com.may54ther.dailybudgetv3.budget.service.BudgetService;
import com.may54ther.dailybudgetv3.common.response.ApiResponse;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;

@Slf4j
@Controller
@RequestMapping("/api/budgets")
@RequiredArgsConstructor
public class BudgetApiController {

    private final BudgetService budgetService;

    @GetMapping("/categories")
    public ResponseEntity<ApiResponse> findCategoryList() {
        return ResponseEntity.ok(
                new ApiResponse(true, null, budgetService.findAllCategory())
        );
    }

    @DeleteMapping("/{budgetCode}")
    public ResponseEntity<ApiResponse> deleteBudget(@PathVariable Long budgetCode) {
        budgetService.deleteBudget(budgetCode);
        return ResponseEntity.ok(
                new ApiResponse(true, "삭제가 완료되었습니다.", null)
        );
    }
}
