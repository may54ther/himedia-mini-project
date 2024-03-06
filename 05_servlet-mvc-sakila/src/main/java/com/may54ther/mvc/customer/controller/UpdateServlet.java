package com.may54ther.mvc.customer.controller;

import com.may54ther.mvc.customer.model.dto.CustomerDTO;
import com.may54ther.mvc.customer.model.service.CustomerService;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

@WebServlet("/customer/update")
public class UpdateServlet extends HttpServlet {

    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse res) throws ServletException, IOException {
        req.setCharacterEncoding("UTF-8");

        int customerId = Integer.parseInt(req.getParameter("customerId"));
        String firstName = req.getParameter("firstName");
        String lastName = req.getParameter("lastName");
        String email = req.getParameter("email");

        CustomerDTO customer = new CustomerDTO();
        customer.setCustomerId(customerId);
        customer.setFirstName(firstName);
        customer.setLastName(lastName);
        customer.setEmail(email);

        CustomerService employeeService = new CustomerService();

        if (employeeService.update(customer)) {
            res.sendRedirect(req.getContextPath() + "/customer/select?customerId=" + customerId);
        } else {
            req.setAttribute("message", "직원 정보 수정에 실패하였습니다.");
            req.getRequestDispatcher("/WEB-INF/views/common/errorPage.jsp").forward(req, res);
        }
    }
}
