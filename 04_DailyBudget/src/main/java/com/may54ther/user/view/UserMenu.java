package com.may54ther.user.view;


import com.may54ther.user.controller.UserController;
import com.may54ther.user.model.dto.UserDTO;

import java.util.Scanner;

public class UserMenu {

    private final UserController userController;
    private UserDTO loginUser;

    public UserMenu() {
        this.userController = new UserController();
    }

    public void displayMenu() {
        Scanner sc = new Scanner(System.in);

        do {
            System.out.println("\n──────　회원 메뉴　──────────────────────────────────────────────────────────────────");
            System.out.println("　　　　　　1. 정보 수정　　　　　　　 　2. 회원 탈퇴　　　　　　　　9. 이전 메뉴로　　　　　　");
            System.out.println("───────────────────────────────────────────────────────────────────────────────────");
            System.out.print("입력 : ");

            int selectMenu = sc.nextInt();
            switch (selectMenu) {
                case 1 -> userController.modifyUser(inputModifyInfo());
                case 2 -> userController.deleteUser(inputDeleteInfo());
                case 9 -> {return;}
                default -> {System.out.println("잘못된 번호 입니다. 다시 입력해 주세요.");}
            }
        } while (true);
    }

    /* 로그인 */
    public UserDTO login() {
        Scanner sc = new Scanner(System.in);
        int loginCount = 0;

        while (loginUser == null) {
            if (loginCount++ >= 3) {
                System.err.println("로그인 시도가 3회 실패했습니다. 프로그램을 종료합니다.");
                System.exit(0);
            }

            System.out.println("\n┌─────　로그인　────────────────────────────────────┐");
            System.out.print("　　[아 이 디]　");
            String userId = sc.nextLine();
            System.out.print("　　[비밀번호]　");
            String userPw = sc.nextLine();
            System.out.println("└─────────────────────────────────────────────────┘");

            loginUser = userController.login(userId, userPw);
        }

        return loginUser;
    }

    /* 회원 가입 */
    public void signUp() {
        Scanner sc = new Scanner(System.in);
        UserDTO registerUser = new UserDTO();

        System.out.println("\n┌─────　회원가입　──────────────────────────────────┐");
        System.out.print("　　[아 이 디]　");
        String userId = sc.nextLine();
        registerUser.setId(userId);
        setUserInfo(registerUser);
        System.out.println("└─────────────────────────────────────────────────┘");

        userController.registerUser(registerUser);
    }

    private UserDTO inputModifyInfo() {
        UserDTO modifyUser = new UserDTO();

        System.out.println("\n┌─────　회원정보 수정　──────────────────────────────┐");
        int userCode = inputOrGetUserCode();
        modifyUser.setCode(userCode);
        setUserInfo(modifyUser);
        System.out.println("└─────────────────────────────────────────────────┘");

        return modifyUser;
    }

    private int inputDeleteInfo() {
        Scanner sc = new Scanner(System.in);

        System.out.println("\n┌─────　회원 탈퇴　─────────────────────────────────┐");
        int userCode = inputOrGetUserCode();

        System.out.print("　　정말로 회원을 탈퇴하시겠습니까? 진행을 계속하려면 'Y'를 입력하세요. : ");
        String confirm = sc.nextLine().toUpperCase();
        System.out.println("└─────────────────────────────────────────────────┘");

        return "Y".equals(confirm) ? userCode : 0;
    }

    /* 로그인 회원의 권한별 회원 코드 값 리턴  */
    private int inputOrGetUserCode() {
        Scanner sc = new Scanner(System.in);

        int userCode = 0;
        if (loginUser.isAdminRole()) {
            /* 관리자(ADMIN)은 회원 번호 검색 조건 추가 */
            System.out.print("　　️[회원 번호] 입력 : ");
            userCode = Integer.parseInt(sc.nextLine());
        } else {
            userCode = loginUser.getCode();
        }

        return userCode;
    }

    /* 비밀번호, 이름, 생년월일 입력 값 세팅 */
    private void setUserInfo(UserDTO userInfo) {
        Scanner sc = new Scanner(System.in);

        System.out.print("　　[비밀번호]　");
        String password = sc.nextLine();
        System.out.print("　　[이　　름]　");
        String name = sc.nextLine();
        System.out.print("　　[생년월일] (YYYY-mm-dd 형식)　");
        String birth = sc.nextLine();

        userInfo.setPassword(password);
        userInfo.setName(name);
        userInfo.setBirth(birth);
    }
}
