package com.may54ther.budget.model.service;


import com.may54ther.budget.model.dto.BudgetAndCategoryDTO;
import com.may54ther.budget.model.dto.BudgetDTO;
import com.may54ther.budget.model.mapper.BudgetMapper;
import org.apache.ibatis.session.SqlSession;

import java.util.List;
import java.util.Map;

import static com.may54ther.common.Template.*;

public class BudgetService {

    private BudgetMapper budgetMapper;


    public List<BudgetAndCategoryDTO> selectBudgetByUserCode(int userCode) {
        SqlSession sqlSession = getSqlSession();
        budgetMapper = sqlSession.getMapper(BudgetMapper.class);

        List<BudgetAndCategoryDTO> budgets = budgetMapper.selectBudgetAndCategoryByUserCode(userCode);
        sqlSession.close();

        return budgets;
    }

    public List<BudgetAndCategoryDTO> selectBudgetByCondition(Map<String, String> condition) {
        SqlSession sqlSession = getSqlSession();
        budgetMapper = sqlSession.getMapper(BudgetMapper.class);

        List<BudgetAndCategoryDTO> budgets = budgetMapper.selectBudgetAndCategoryByCondition(condition);
        sqlSession.close();

        return budgets;
    }


    public boolean addNewBudget(BudgetDTO newBudget) {
        SqlSession sqlSession = getSqlSession();
        budgetMapper = sqlSession.getMapper(BudgetMapper.class);

        int result = budgetMapper.insertBudget(newBudget);
        if (result > 0) {
            commit(sqlSession);
        } else {
            rollback(sqlSession);
        }

        return result > 0;
    }

    public boolean modifyBudget(BudgetDTO modifyBudget) {
        SqlSession sqlSession = getSqlSession();
        budgetMapper = sqlSession.getMapper(BudgetMapper.class);

        int result = budgetMapper.updateBudget(modifyBudget);
        if (result > 0) {
            commit(sqlSession);
        } else {
            rollback(sqlSession);
        }

        return result > 0;
    }

    public boolean deleteBudget(int budgetCode) {
        SqlSession sqlSession = getSqlSession();
        budgetMapper = sqlSession.getMapper(BudgetMapper.class);

        int result = budgetMapper.deleteBudget(budgetCode);
        if (result > 0) {
            commit(sqlSession);
        } else {
            rollback(sqlSession);
        }

        return result > 0;
    }
}