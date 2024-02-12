package com.may54ther.budget.model.service;


import com.may54ther.budget.model.dto.CategoryDTO;
import com.may54ther.budget.model.mapper.CategoryMapper;
import org.apache.ibatis.session.SqlSession;

import java.util.List;

import static com.may54ther.common.Template.getSqlSession;

public class CategoryService {

    private CategoryMapper categoryMapper;

    public List<CategoryDTO> selectAllCategoryList() {
        SqlSession sqlSession = getSqlSession();
        categoryMapper = sqlSession.getMapper(CategoryMapper.class);

        List<CategoryDTO> categoryList = categoryMapper.selectAllCategoryList();
        sqlSession.close();

        return categoryList;
    }
}