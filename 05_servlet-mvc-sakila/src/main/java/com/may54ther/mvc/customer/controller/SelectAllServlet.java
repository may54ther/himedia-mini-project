package com.may54ther.mvc.customer.controller;


import com.may54ther.mvc.customer.model.dto.CustomerDTO;
import com.may54ther.mvc.customer.model.service.CustomerService;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.util.List;

@WebServlet("/customer/list")
public class SelectAllServlet extends HttpServlet {

    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse res) throws ServletException, IOException {
        CustomerService customerService = new CustomerService();
        List<CustomerDTO> customerList = customerService.selectAll();

        String path = "";
        if (!customerList.isEmpty()) {
            path = "/WEB-INF/views/customer/list.jsp";
            req.setAttribute("customerList", customerList);
        } else {
            path = "/WEB-INF/views/common/errorPage.jsp";
            req.setAttribute("message", "고객 목록이 조회되지 않았습니다.");
        }

        req.getRequestDispatcher(path).forward(req, res);
    }
}
