package com.may54ther.dailybudgetv3.budget.repository;

import com.may54ther.dailybudgetv3.budget.entity.Budget;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import java.util.List;


public interface BudgetRepository extends JpaRepository<Budget, Long> {

    List<Budget> findByAmountGreaterThanOrderByAmount(Integer amount);

    @Query(value = "SELECT * FROM tbl_budget " +
            "WHERE (:type IS NULL OR type = :type)" +
            "AND (:minAmount IS NULL OR amount >= :minAmount) " +
            "AND (:maxAmount IS NULL OR amount <= :maxAmount) " +
            "AND (:description IS  NULL OR description LIKE CONCAT('%', :description, '%')) ",
            nativeQuery = true)
    List<Budget> findByConditions(String type, Integer minAmount, Integer maxAmount, String description);
}
