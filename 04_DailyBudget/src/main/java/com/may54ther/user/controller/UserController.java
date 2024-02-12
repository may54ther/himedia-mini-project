package com.may54ther.user.controller;


import com.may54ther.user.model.dto.UserDTO;
import com.may54ther.user.model.service.UserService;
import com.may54ther.user.view.UserPrint;

public class UserController {

    private final UserService userService;
    private final UserPrint userPrint;

    public UserController() {
        this.userService = new UserService();
        this.userPrint = new UserPrint();
    }

    public UserDTO login(String id, String password) {
        UserDTO user = new UserDTO(id, password);
        UserDTO loginUser = userService.login(user);

        if (loginUser != null) {
            userPrint.printSuccessMessage("login");
        } else {
            userPrint.printErrorMessage("login");
        }

        return loginUser;
    }

    public void registerUser(UserDTO newUser) {
        if (userService.registerUser(newUser)) {
            userPrint.printSuccessMessage("register");
        } else {
            userPrint.printErrorMessage("register");
        }
    }

    public void modifyUser(UserDTO modifyUser) {
        if (userService.modifyUser(modifyUser)) {
            userPrint.printSuccessMessage("update");
        } else {
            userPrint.printErrorMessage("update");
        }
    }

    public void deleteUser(int userCode) {
        if (userService.deleteUser(userCode)) {
            userPrint.printSuccessMessage("delete");
            System.exit(0);
        } else {
            userPrint.printErrorMessage("delete");
        }
    }
}
