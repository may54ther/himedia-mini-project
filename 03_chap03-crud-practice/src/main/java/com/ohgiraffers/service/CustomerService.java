package com.ohgiraffers.service;

import com.ohgiraffers.model.dto.CustomerDTO;

import java.io.FileInputStream;
import java.io.IOException;
import java.sql.*;
import java.util.ArrayList;
import java.util.List;
import java.util.Properties;

import static com.ohgiraffers.common.JDBCTemplate.close;

public class CustomerService {

    private Properties props = new Properties();

    private Connection conn = null;
    private PreparedStatement pstmt = null;
    private ResultSet rs = null;

    public CustomerService(Connection conn, PreparedStatement pstmt, ResultSet rs) {
        this.conn = conn;
        this.pstmt = pstmt;
        this.rs = rs;
    }

    public List<CustomerDTO> selectAll() {
        List<CustomerDTO> list = new ArrayList<>();

        try {
            String query = getQuery("selectAll");
            pstmt = conn.prepareStatement(query);
            rs = pstmt.executeQuery();

            while (rs.next()) {
                CustomerDTO customer = new CustomerDTO();
                customer.setCustomerId(rs.getInt("customer_id"));
                customer.setStoreId(rs.getInt("store_id"));
                customer.setFirstName(rs.getString("first_name"));
                customer.setLastName(rs.getString("last_name"));
                customer.setEmail(rs.getString("email"));
                customer.setAddressId(rs.getInt("address_id"));

                list.add(customer);
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }

        return list;
    }

    public CustomerDTO selectById(int customerId) {
        CustomerDTO customer = null;

        try {
            String query = getQuery("selectByCustomerId");
            pstmt = conn.prepareStatement(query);
            pstmt.setInt(1, customerId);

            rs = pstmt.executeQuery();

            if (rs.next()) {
                customer = new CustomerDTO();
                customer.setCustomerId(rs.getInt("customer_id"));
                customer.setStoreId(rs.getInt("store_id"));
                customer.setFirstName(rs.getString("first_name"));
                customer.setLastName(rs.getString("last_name"));
                customer.setEmail(rs.getString("email"));
                customer.setAddressId(rs.getInt("address_id"));
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }

        return customer;
    }

    public boolean insert(int storeId, String firstName, String lastName, String email, int addressId) {
        int result = 0;

        try {
            String query = getQuery("insertCustomer");
            pstmt = conn.prepareStatement(query);
            pstmt.setInt(1, storeId);
            pstmt.setString(2, firstName);
            pstmt.setString(3, lastName);
            pstmt.setString(4, email);
            pstmt.setInt(5, addressId);

            result = pstmt.executeUpdate();
        } catch (SQLException e) {
            e.printStackTrace();
        }

        return result > 0;

    }

    public boolean updateById(int customerId, String firstName, String lastName, String email) {
        int result = 0;

        try {
            String query = getQuery("updateCustomer");
            pstmt = conn.prepareStatement(query);
            pstmt.setString(1, firstName);
            pstmt.setString(2, lastName);
            pstmt.setString(3, email);
            pstmt.setInt(4, customerId);

            result = pstmt.executeUpdate();
        } catch (SQLException e) {
            e.printStackTrace();
        } finally {
            close(rs);
            close(pstmt);
            close(conn);
        }

        return result > 0 ? true : false;
    }

    public boolean deleteById(int customerId) {
        int result = 0;

        try {
            String query = getQuery("deleteCustomer");
            pstmt = conn.prepareStatement(query);
            pstmt.setInt(1, customerId);

            result = pstmt.executeUpdate();
        } catch (SQLIntegrityConstraintViolationException e) {
            System.out.println("FK 있어서 삭제 불가능..");
        } catch (SQLException e) {
            e.printStackTrace();
        }

        return result > 0;
    }

    private String getQuery(String key) {
        String query = null;
        try {
            props.loadFromXML(new FileInputStream("src/main/java/com/ohgiraffers/mapper/sakila-query.xml"));
            query = props.getProperty(key);
        } catch (IOException e) {
            e.printStackTrace();
        }

        return query;
    }
}
