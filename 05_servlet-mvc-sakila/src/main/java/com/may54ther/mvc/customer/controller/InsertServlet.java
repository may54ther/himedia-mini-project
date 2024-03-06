package com.may54ther.mvc.customer.controller;


import com.may54ther.mvc.customer.model.dto.CustomerDTO;
import com.may54ther.mvc.customer.model.service.CustomerService;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

@WebServlet("/customer/insert")
public class InsertServlet extends HttpServlet {

    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse res) throws ServletException, IOException {
        req.setCharacterEncoding("UTF-8");

        int storeId = Integer.parseInt(req.getParameter("storeId"));
        String firstName = req.getParameter("firstName");
        String lastName = req.getParameter("lastName");
        String email = req.getParameter("email");
        int addressId = Integer.parseInt(req.getParameter("addressId"));

        CustomerDTO customer = new CustomerDTO();
        customer.setStoreId(storeId);
        customer.setFirstName(firstName);
        customer.setLastName(lastName);
        customer.setEmail(email);
        customer.setAddressId(addressId);

        CustomerService customerService = new CustomerService();

        if (customerService.insert(customer)) {
            res.sendRedirect(req.getContextPath() + "/customer/list");
        } else {
            req.setAttribute("message", "새로운 고객 등록을 실패하였습니다.");
            req.getRequestDispatcher("/WEB-INF/views/common/errorPage.jsp").forward(req, res);
        }
    }
}
