package com.may54ther.budget.view;


import com.may54ther.budget.model.dto.BudgetAndCategoryDTO;
import com.may54ther.budget.model.dto.CategoryDTO;

import java.util.List;

public class BudgetPrint {

    public void printCategoryList(List<CategoryDTO> categoryList) {
        categoryList.forEach(System.out::println);
    }

    public void printBudgetList(List<BudgetAndCategoryDTO> budgetList) {
        System.out.println("\n──────　조회 결과　─────────────────────────────────────────────────────────────────");
        System.out.printf("| %-4s | %-5s | %-10s | %-4s | %-10s | %-10s | %-15s \n",
                          "번호", "회원 코드", "카테고리 코드", "구분", "금액", "날짜", "메모");
        System.out.println("──────────────────────────────────────────────────────────────────────────────────");
        budgetList.forEach(System.out::println);
        System.out.println("──────────────────────────────────────────────────────────────────────────────────");
    }

    public void printSuccessMessage(String successCode) {
        String successMessage = "";
        switch (successCode) {
            case "insert" -> successMessage = "가계부 등록을 완료하였습니다.";
            case "update" -> successMessage = "수정 완료! ";
            case "delete" -> successMessage = "삭제 완료!";
        }

        System.out.println(successMessage);
    }

    public void printErrorMessage(String errorCode) {
        String errorMessage = "";
        switch (errorCode) {
            case "selectAll" -> errorMessage = "조회 결과가 없습니다.";
            case "insert" -> errorMessage = "가계부 등록을 실패했습니다.";
            case "update" -> errorMessage = "가계부 수정을 실패했습니다. ";
            case "delete" -> errorMessage = "가계부 삭제를 실패했습니다.";
        }

        System.err.println(errorMessage);
    }
}
