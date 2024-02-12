package com.may54ther.budget.view;

import com.may54ther.budget.controller.BudgetController;
import com.may54ther.budget.controller.CategoryController;
import com.may54ther.budget.model.dto.BudgetDTO;
import com.may54ther.user.model.dto.UserDTO;

import java.util.HashMap;
import java.util.Map;
import java.util.Scanner;

public class BudgetMenu {

    private final BudgetController budgetController;
    private final CategoryController categoryController;

    private final UserDTO loginUser;

    public BudgetMenu(UserDTO loginUser) {
        this.budgetController = new BudgetController();
        this.categoryController = new CategoryController();
        this.loginUser = loginUser;
    }

    public void displayMenu() {
        Scanner sc = new Scanner(System.in);

        do {
            System.out.println("\n──────　가계부 메뉴　─────────────────────────────────────────────────────────────────");
            System.out.println("　　　　　　1. 가계부 조회　　　　　　　2. 가계부 검색　　　　　　　　3. 가계부 추가　　　　　　");
            System.out.println("　　　　　　4. 가계부 수정　　　　　　　5. 가계부 삭제　　　　　　　　9. 이전 메뉴로　　　　　　");
            System.out.println("───────────────────────────────────────────────────────────────────────────────────");
            System.out.println("입력 : ");

            int selectMenu = sc.nextInt();
            switch (selectMenu) {
                case 1 -> budgetController.getBudgetList(inputUserCode());
                case 2 -> budgetController.searchByCondition(inputSearchCondition());
                case 3 -> budgetController.addNewBudget(inputNewBudget());
                case 4 -> budgetController.modifyBudget(inputModifyInfo());
                case 5 -> budgetController.deleteBudget(inputDeleteInfo());
                case 9 -> {return;}
                default -> {System.out.println("잘못된 번호입니다. 다시 입력해 주세요.");}
            }
        } while (true);
    }

    private Map<String, String> inputSearchCondition() {
        Scanner sc = new Scanner(System.in);
        Map<String, String> condition = new HashMap<>();
        System.out.println("\n┌─────　가계부 검색　────────────────────────────────┐");
        System.out.println("　　빈 값을 입력하면 해당 검색 조건은 적용되지 않습니다. ");
        System.out.println("───────────────────────────────────────────────────");
        System.out.print("　　[입출금 여부] 입력 (입금은 IN, 출금은 OUT) : ");
        String budgetType = sc.nextLine();
        System.out.print("　　[사용 년월] 입력 (YYYY-mm 형식) : ");
        String usageDate = sc.nextLine();
        System.out.print("　　[메모 내용] 입력 : ");
        String description = sc.nextLine();
        System.out.println("└─────────────────────────────────────────────────┘");

        int userCode = inputUserCode();
        condition.put("userCode", String.valueOf(userCode));
        condition.put("type", budgetType);
        condition.put("usageDate", usageDate);
        condition.put("description", description);

        return condition;
    }

    private BudgetDTO inputNewBudget() {
        BudgetDTO newBudget = new BudgetDTO();

        System.out.println("\n┌─────　가계부 추가　────────────────────────────────┐");
        newBudget.setUserCode(loginUser.getCode());
        newBudget.setCategoryCode(inputCategoryCode());
        setBudgetInfo(newBudget);
        System.out.println("└─────────────────────────────────────────────────┘");

        return newBudget;
    }

    private BudgetDTO inputModifyInfo() {
        Scanner sc = new Scanner(System.in);
        BudgetDTO modifyBudget = new BudgetDTO();

        System.out.println("\n┌─────　가계부 수정　────────────────────────────────┐");
        System.out.println("　　빈 값을 입력하면 해당 값은 수정되지 않습니다. ");
        System.out.println("───────────────────────────────────────────────────");
        System.out.print("　　[가계부 코드] 입력 : ");
        int budgetCode = Integer.parseInt(sc.nextLine());
        modifyBudget.setCode(budgetCode);
        setBudgetInfo(modifyBudget);
        modifyBudget.setCategoryCode(inputCategoryCode());
        System.out.println("└─────────────────────────────────────────────────┘");

        return modifyBudget;
    }

    private int inputDeleteInfo() {
        Scanner sc = new Scanner(System.in);

        System.out.println("\n┌─────　가계부 삭제　────────────────────────────────┐");
        System.out.print("　　[가계부 코드] 입력 : ");
        int budgetCode = Integer.parseInt(sc.nextLine());
        System.out.println("└─────────────────────────────────────────────────┘");

        return budgetCode;
    }


    private int inputUserCode() {
        int userCode = 0;
        if (!loginUser.isAdminRole()) {
            userCode = loginUser.getCode();
        }

        return userCode;
    }


    private int inputCategoryCode() {
        Scanner sc = new Scanner(System.in);
        int categoryCode = 0;

        /* 카테고리 목록 출력 */
        categoryController.getCategoryList();

        /* 입력 */
        System.out.print("　　[카테고리] 번호 입력 : ");
        String input = sc.nextLine();

        if (!input.isEmpty()) {
            categoryCode = Integer.parseInt(input);
        }

        return categoryCode;
    }

    private void setBudgetInfo(BudgetDTO budgetInfo) {
        Scanner sc = new Scanner(System.in);

        System.out.print("　　[구분] (입금은 IN, 출금은 OUT 입력) ");
        String type = sc.nextLine();
        System.out.print("　　[금액]  ");
        String amount = sc.nextLine();
        System.out.print("　　[날짜] (YYYY-mm-dd 형식) ");
        String usageDate = sc.nextLine();
        System.out.print("　　[메모] ");
        String description = sc.nextLine();

        budgetInfo.setType(type);
        if (amount.isEmpty()) {
            budgetInfo.setAmount(0);
        } else {
            budgetInfo.setAmount(Integer.parseInt(amount));
        }
        budgetInfo.setUsageDate(usageDate);
        budgetInfo.setDescription(description);
    }
}
