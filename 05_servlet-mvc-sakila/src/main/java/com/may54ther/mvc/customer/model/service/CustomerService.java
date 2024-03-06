package com.may54ther.mvc.customer.model.service;

import com.may54ther.mvc.customer.model.dao.CustomerMapper;
import com.may54ther.mvc.customer.model.dto.CustomerDTO;
import org.apache.ibatis.session.SqlSession;

import java.util.List;

import static com.may54ther.mvc.common.mybatis.Template.*;

public class CustomerService {

    public List<CustomerDTO> selectAll() {
        SqlSession sqlSession = getSqlSession();
        CustomerMapper customerMapper = sqlSession.getMapper(CustomerMapper.class);

        List<CustomerDTO> customerList = customerMapper.selectAll();
        sqlSession.close();

        return customerList;
    }

    public CustomerDTO selectByCustomerId(String customerId) {
        SqlSession sqlSession = getSqlSession();
        CustomerMapper customerMapper = sqlSession.getMapper(CustomerMapper.class);

        CustomerDTO customer = customerMapper.selectByCustomerId(customerId);
        sqlSession.close();

        return customer;
    }

    public boolean insert(CustomerDTO customer) {
        SqlSession sqlSession = getSqlSession();
        CustomerMapper customerMapper = sqlSession.getMapper(CustomerMapper.class);

        boolean result = customerMapper.insert(customer) > 0;
        if (result) {
            commit(sqlSession);
        } else {
            rollback(sqlSession);
        }

        return result;
    }

    public boolean update(CustomerDTO customer) {
        SqlSession sqlSession = getSqlSession();
        CustomerMapper customerMapper = sqlSession.getMapper(CustomerMapper.class);

        boolean result = customerMapper.update(customer) > 0;
        if (result) {
            commit(sqlSession);
        } else {
            rollback(sqlSession);
        }

        return result;
    }

    public boolean delete(String customerId) {
        SqlSession sqlSession = getSqlSession();
        CustomerMapper customerMapper = sqlSession.getMapper(CustomerMapper.class);

        boolean result = customerMapper.delete(customerId) > 0;
        if (result) {
            commit(sqlSession);
        } else {
            rollback(sqlSession);
        }

        return result;
    }
}
