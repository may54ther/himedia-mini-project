package com.may54ther.user.view;

public class UserPrint {

    public void printSuccessMessage(String successCode) {
        String successMessage = "";
        switch (successCode) {
            case "register" -> successMessage = "회원가입이 완료되었습니다.";
            case "login" -> successMessage = "로그인 성공!\n";
            case "update" -> successMessage = "회원정보를 수정하였습니다.";
            case "delete" -> successMessage = "회원 탈퇴가 완료되었습니다.";
        }

        System.out.println(successMessage);
    }

    public void printErrorMessage(String errorCode) {
        String errorMessage = "";
        switch (errorCode) {
            case "register" -> errorMessage = "회원가입이 실패하였습니다.";
            case "login" -> errorMessage = "로그인 실패";
            case "update" -> errorMessage = "회원정보 수정에 실패하였습니다. ";
            case "delete" -> errorMessage = "회원 탈퇴가 실패하였습니다.";
        }

        System.err.println(errorMessage);
    }
}
