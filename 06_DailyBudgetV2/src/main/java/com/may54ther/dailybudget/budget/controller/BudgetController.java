package com.may54ther.dailybudget.budget.controller;

import com.may54ther.dailybudget.budget.model.dto.BudgetDTO;
import com.may54ther.dailybudget.budget.model.dto.CategoryDTO;
import com.may54ther.dailybudget.budget.model.service.BudgetService;
import lombok.RequiredArgsConstructor;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.context.MessageSource;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import java.util.HashMap;
import java.util.List;
import java.util.Locale;
import java.util.Map;

@Controller
@RequestMapping("/budget")
@RequiredArgsConstructor
public class BudgetController {

    private static final Logger logger = LoggerFactory.getLogger(BudgetController.class);
    private final MessageSource messageSource;

    private final BudgetService budgetService;

    @GetMapping("/list")
    public String budgetList(Model model) {
        List<BudgetDTO> budgets = budgetService.findAllBudget();
        model.addAttribute("budgets", budgets);
        return "budget/list";
    }

    @GetMapping("/category")
    public @ResponseBody List<CategoryDTO> categoryList(Model model) {
        return budgetService.findCategoryList();
    }

    @GetMapping("/add")
    public String addForm() {
        return "budget/add";
    }

    @PostMapping("/add")
    public String addBudget(@ModelAttribute BudgetDTO budget, RedirectAttributes rttr) {
        budgetService.addBudget(budget);
        rttr.addFlashAttribute("successMessage", messageSource.getMessage("alert.addBudget", null, Locale.KOREA));

        return "redirect:/budget/list";
    }

    @DeleteMapping("/delete/{code}")
    public @ResponseBody Map<String, String> deleteBudget(@PathVariable("code") Integer code) {
        String result = budgetService.deleteBudget(code) > 0 ? "true" : "false";
        Map<String, String> responseMap = new HashMap<>();
        responseMap.put("result", result);

        return responseMap;
    }
}
