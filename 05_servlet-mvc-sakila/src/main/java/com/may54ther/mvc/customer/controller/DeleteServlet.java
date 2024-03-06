package com.may54ther.mvc.customer.controller;

import com.may54ther.mvc.customer.model.service.CustomerService;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

@WebServlet("/customer/delete")
public class DeleteServlet extends HttpServlet {

    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse res) throws ServletException, IOException {
        req.setCharacterEncoding("UTF-8");

        String customerId = req.getParameter("customerId");

        CustomerService customerService = new CustomerService();

        if (customerService.delete(customerId)) {
            res.sendRedirect(req.getContextPath() + "/customer/list");
        } else {
            req.setAttribute("message", "고객 정보 삭제에 실패하였습니다.");
            req.getRequestDispatcher("/WEB-INF/views/common/errorPage.jsp").forward(req, res);
        }
    }
}
