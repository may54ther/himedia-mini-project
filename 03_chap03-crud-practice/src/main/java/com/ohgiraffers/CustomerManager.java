package com.ohgiraffers;

import com.ohgiraffers.model.dto.CustomerDTO;
import com.ohgiraffers.service.CustomerService;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.util.List;

import static com.ohgiraffers.common.JDBCTemplate.close;
import static com.ohgiraffers.common.JDBCTemplate.getConnection;

public class CustomerManager {

    private Connection conn = getConnection();
    private PreparedStatement pstmt = null;
    private ResultSet rs = null;

    private CustomerService customerService = new CustomerService(conn, pstmt, rs);

    public void selectAll() {
        List<CustomerDTO> selectedList = customerService.selectAll();

        if (selectedList.isEmpty()) {
            System.out.println("목록이 존재하지 않습니다.");
            return;
        }

        for (CustomerDTO customerDTO : selectedList) {
            System.out.println(customerDTO);
        }
    }

    public void selectByCustomerId(int customerId) {
        CustomerDTO customer = customerService.selectById(customerId);

        if (customer != null) {
            System.out.println(customer);
        } else {
            System.out.println("존재하지 않는 회원입니다.");
        }
    }

    public void add(int storeId, String firstName, String lastName, String email, int addressId) {
        boolean isAdded = customerService.insert(storeId, firstName, lastName, email, addressId);

        if (isAdded) {
            System.out.println("추가 완료!");
        } else {
            System.out.println("추가 실패ㅠㅠ");
        }
    }

    public void update(int customerId, String firstName, String lastName, String email) {
        boolean isUpdated = customerService.updateById(customerId, firstName, lastName, email);

        if (isUpdated) {
            System.out.println("수정 완료!");
        } else {
            System.out.println("수정 실패ㅜㅜ");
        }
    }

    public void delete(int customerId) {
        boolean isDeleted = customerService.deleteById(customerId);

        if (isDeleted) {
            System.out.println("삭제 완료!");
        } else {
            System.out.println("삭제 실패ㅠㅜ");
        }
    }

    public void closeAll() {
        close(rs);
        close(pstmt);
        close(conn);
    }
}
