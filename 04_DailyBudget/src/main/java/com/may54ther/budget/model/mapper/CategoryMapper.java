package com.may54ther.budget.model.mapper;


import com.may54ther.budget.model.dto.CategoryDTO;

import java.util.List;

public interface CategoryMapper {

    List<CategoryDTO> selectAllCategoryList();
}
