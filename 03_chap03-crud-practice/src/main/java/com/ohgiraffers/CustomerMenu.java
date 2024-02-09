package com.ohgiraffers;

import java.util.Scanner;

public class CustomerMenu {

    CustomerManager manager = new CustomerManager();
    Scanner sc = new Scanner(System.in);

    public void start() {
        mainMenu:
        while (true) {
            System.out.println("┌────────────────────────────────────────────┐");
            System.out.println("  1. 전체 조회    2. 회원 조회  ");
            System.out.println("  3. 회원 추가    4. 회원 수정    5. 회원 삭제");
            System.out.println("─────────────────────────────────────────────");
            System.out.println("  >> 프로그램 종료는 0을 입력하세요.  ");
            System.out.println("└────────────────────────────────────────────┘");

            System.out.print(">> 선택: ");
            int menu = sc.nextInt();
            sc.nextLine();

            switch (menu) {
                case 1:
                    listMenu();
                    break;
                case 2:
                    getMenu();
                    break;
                case 3:
                    addMenu();
                    break;
                case 4:
                    updateMenu();
                    break;
                case 5:
                    deleteMenu();
                    break;
                case 0:
                    closeMenu();
                    break mainMenu;
                default:
                    System.out.println("잘못 선택하셨습니다. 번호를 다시 입력해주세요.");
                    break;
            }
        }
    }

    private void listMenu() {
        System.out.println("======== 전체 조회 ========");
        manager.selectAll();
    }

    private void getMenu() {
        System.out.println("======== 회원 조회 ========");
        System.out.println(">> 회원 번호 입력 : ");
        int customerId = sc.nextInt();
        sc.nextLine();

        manager.selectByCustomerId(customerId);
    }

    private void addMenu() {
        System.out.println("======== 회원 추가 ========");
        System.out.println(">> 상점 번호 입력 (1~2) : ");
        int storeId = sc.nextInt();
        sc.nextLine();
        System.out.println(">> 성(LastName) 입력 : ");
        String lastName = sc.nextLine();
        System.out.println(">> 이름(FirstName) 입력 : ");
        String firstName = sc.nextLine();
        System.out.println(">> 이메일 입력 : ");
        String email = sc.nextLine();
        System.out.println(">> 주소 입력 (1 ~ 501) : ");
        int addressId = sc.nextInt();
        sc.nextLine();

        manager.add(storeId, firstName, lastName, email, addressId);
    }

    private void updateMenu() {
        System.out.println("======== 회원 수정 ========");
        System.out.println(">> 수정할 회원 번호 입력 : ");
        int customerId = sc.nextInt();
        sc.nextLine();
        System.out.println(">> 성(LastName) 입력 : ");
        String lastName = sc.nextLine();
        System.out.println(">> 이름(FirstName) 입력 : ");
        String firstName = sc.nextLine();
        System.out.println(">> 이메일 입력 : ");
        String email = sc.nextLine();

        manager.update(customerId, firstName, lastName, email);
    }

    private void deleteMenu() {
        System.out.println("======== 회원 삭제 ========");
        System.out.println(">> 삭제할 회원 번호 입력 : ");
        int customerId = sc.nextInt();
        sc.nextLine();

        manager.delete(customerId);
    }

    private void closeMenu() {
        System.out.println("======= 종료 =======");
        manager.closeAll();
    }
}
