package com.may54ther;


import com.may54ther.budget.view.BudgetMenu;
import com.may54ther.user.model.dto.UserDTO;
import com.may54ther.user.view.UserMenu;

import java.util.Scanner;

public class MainMenu {

    private static UserDTO loginUser;
    private UserMenu userMenu;

    public MainMenu() {
        this.userMenu = new UserMenu();
    }

    public void init() {
        Scanner sc = new Scanner(System.in);

        do {
            System.out.println("───────────────────────────────────────────────────────────────────────────────────");
            System.out.println("                                                                                  ");
            System.out.println("     '||''|.          || '||          '||''|.             '||              .      ");
            System.out.println("      ||   ||  ....  ...  ||.... ...   ||   ||... ...   .. ||  ... . .....||.     ");
            System.out.println("      ||    ||'' .||  ||  || '|.  |    ||'''|. ||  || .'  '|| || ||.|...||||      ");
            System.out.println("      ||    ||.|' ||  ||  ||  '|.|     ||    ||||  || |.   ||  |'' ||     ||      ");
            System.out.println("     .||...|' '|..'|'.||..||.  '|     .||...|' '|..'|.'|..'||.'||||.'|...''|.'    ");
            System.out.println("                            .. |                             .|....'              ");
            System.out.println("                             ''                                                   ");
            System.out.println("───────────────────────────────────────────────────────────────────────────────────");
            System.out.println("　　　　　　1. 로그인　　　　　　　　　　2. 회원가입　　　　　　　　　9. 프로그램 종료　　　　　　");
            System.out.println("───────────────────────────────────────────────────────────────────────────────────");
            System.out.println("입력 : ");

            int selectMenu = sc.nextInt();
            switch (selectMenu) {
                case 1 -> {loginSubMenu();}
                case 2 -> userMenu.signUp();
                case 9 -> {
                    System.out.println("프로그램을 종료합니다.");
                    return;
                }
                default -> System.out.println("잘못된 번호입니다. 다시 입력해 주세요.");
            }
        } while (true);
    }

    private void loginSubMenu() {
        Scanner sc = new Scanner(System.in);
        loginUser = userMenu.login();

        do {
            System.out.println("\n──────　관리 메뉴　─────────────────────────────────────────────────────────────────");
            System.out.println("　　　　　　1. 가계부 관리　　　　　　　2. 회원 정보 관리　　　　　　9. 이전 메뉴로　　　　　　");
            System.out.println("──────────────────────────────────────────────────────────────────────────────────");
            System.out.println("입력 : ");

            int selectMenu = sc.nextInt();
            switch (selectMenu) {
                case 1 -> new BudgetMenu(loginUser).displayMenu();
                case 2 -> userMenu.displayMenu();
                case 9 -> {return;}
                default -> {System.out.println("잘못된 번호입니다. 다시 입력해 주세요.");}
            }
        } while (true);
    }
}
