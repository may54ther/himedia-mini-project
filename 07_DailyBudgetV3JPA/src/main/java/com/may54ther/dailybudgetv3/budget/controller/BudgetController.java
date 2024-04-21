package com.may54ther.dailybudgetv3.budget.controller;

import com.may54ther.dailybudgetv3.budget.dto.BudgetDTO;
import com.may54ther.dailybudgetv3.budget.dto.CategoryDTO;
import com.may54ther.dailybudgetv3.budget.dto.SearchBudgetDTO;
import com.may54ther.dailybudgetv3.budget.service.BudgetService;
import com.may54ther.dailybudgetv3.common.page.Pagination;
import com.may54ther.dailybudgetv3.common.page.PagingButton;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.web.PageableDefault;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import java.util.List;

@Slf4j
@Controller
@RequestMapping("/budget")
@RequiredArgsConstructor
public class BudgetController {

    private final BudgetService budgetService;

    /* 목록 */
    @GetMapping("/list")
    public String findBudgetList(Model model, @PageableDefault Pageable pageable) {
        Page<BudgetDTO> budgetList = budgetService.findAllBudgetWithCategoryName(pageable);
        PagingButton paging = Pagination.getPagingButtonInfo(budgetList);

        model.addAttribute("budgetList", budgetList);
        model.addAttribute("paging", paging);

        return "budget/list";
    }

    /* 조회 */
    @GetMapping("/{budgetCode}")
    public String findBudgetByCode(@PathVariable Long budgetCode, Model model) {
        BudgetDTO foundBudget = budgetService.findBudgetByCode(budgetCode);
        if (foundBudget != null) {
            CategoryDTO foundCategory = budgetService.findCategoryByCode(foundBudget.getCategoryCode());
            foundBudget.setCategoryName(foundCategory.getName());
            model.addAttribute("budget", foundBudget);
        } else {
            model.addAttribute("budget", new BudgetDTO());
        }

        return "budget/detail";
    }

    /* 검색 */
    @GetMapping("/search")
    public void searchForm() { }

    /* query method */
    @GetMapping("/search/amount")
    public String searchByCategory(@RequestParam Integer amount, Model model) {
        List<BudgetDTO> budgetList = budgetService.findByAmount(amount);
        model.addAttribute("budgetList", budgetList);
        return "budget/searchResult";
    }

    /* native query */
    @GetMapping("/search/result")
    public String searchByOptions(@ModelAttribute SearchBudgetDTO searchBudget, Model model) {
        List<BudgetDTO> budgetList = budgetService.findByConditions(searchBudget);
        model.addAttribute("budgetList", budgetList);
        return "budget/searchResult";
    }

    /* 등록 */
    @GetMapping("/add")
    public void addForm() { }

    @PostMapping("/add")
    public String addBudget(@ModelAttribute BudgetDTO budget, RedirectAttributes redirect) {
        budgetService.addBudget(budget);
        redirect.addFlashAttribute("alertMessage", "등록이 완료되었습니다.");
        return "redirect:/budget/list";
    }

    /* 수정 */
    @GetMapping("/modify")
    public String modifyForm(@RequestParam Long budgetCode, Model model) {
        BudgetDTO budget = budgetService.findBudgetByCode(budgetCode);

        if (budget == null) {
            model.addAttribute("budget", new BudgetDTO());
        } else {
            model.addAttribute("budget", budget);
        }

        return "budget/modify";
    }

    @PostMapping("/modify")
    public String modifyBudget(BudgetDTO modifyBudget, RedirectAttributes redirect) {
        budgetService.modifyBudget(modifyBudget);
        redirect.addFlashAttribute("alertMessage", "수정이 완료되었습니다.");
        return "redirect:/budget/list";
    }

    /* 삭제 */
    /* BudgetApiController.deleteBudget() */
}
