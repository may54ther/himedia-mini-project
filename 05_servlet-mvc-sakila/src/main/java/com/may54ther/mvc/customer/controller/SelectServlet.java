package com.may54ther.mvc.customer.controller;


import com.may54ther.mvc.customer.model.dto.CustomerDTO;
import com.may54ther.mvc.customer.model.service.CustomerService;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

@WebServlet("/customer/select")
public class SelectServlet extends HttpServlet {

    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse res) throws ServletException, IOException {
        String customerId = req.getParameter("customerId");

        CustomerService employeeService = new CustomerService();
        CustomerDTO customer = employeeService.selectByCustomerId(customerId);

        String path = "";
        if (customer!=null) {
            path = "/WEB-INF/views/customer/view.jsp";
            req.setAttribute("customer", customer);
        } else {
            path = "/WEB-INF/views/common/errorPage.jsp";
            req.setAttribute("message", "해당 고객이 존재하지 않습니다.");
        }

        req.getRequestDispatcher(path).forward(req, res);
    }
}
