package com.may54ther.mvc.customer.model.dao;

import com.may54ther.mvc.customer.model.dto.CustomerDTO;

import java.util.List;

public interface CustomerMapper {

    List<CustomerDTO> selectAll();

    CustomerDTO selectByCustomerId(String customerId);

    int insert(CustomerDTO customer);

    int update(CustomerDTO customer);

    int delete(String customerId);
}
