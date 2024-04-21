package com.may54ther.dailybudgetv3.budget.service;

import com.may54ther.dailybudgetv3.budget.dto.BudgetDTO;
import com.may54ther.dailybudgetv3.budget.dto.CategoryDTO;
import com.may54ther.dailybudgetv3.budget.dto.SearchBudgetDTO;
import com.may54ther.dailybudgetv3.budget.entity.Budget;
import com.may54ther.dailybudgetv3.budget.entity.Category;
import com.may54ther.dailybudgetv3.budget.repository.BudgetRepository;
import com.may54ther.dailybudgetv3.budget.repository.CategoryRepository;
import lombok.RequiredArgsConstructor;
import org.modelmapper.ModelMapper;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@Service
@RequiredArgsConstructor
public class BudgetService {

    private final BudgetRepository budgetRepository;
    private final CategoryRepository categoryRepository;
    private final ModelMapper modelMapper;

    /* 목록 */
    public Page<BudgetDTO> findAllBudgetWithCategoryName(Pageable pageable) {
        pageable = PageRequest.of(
                pageable.getPageNumber() <= 0 ? 0 : pageable.getPageNumber() - 1,
                pageable.getPageSize(),
                Sort.by("code").descending()
        );
        Page<Budget> budgetList = budgetRepository.findAll(pageable);

        return budgetList.map(budget -> {
            BudgetDTO foundBudget = modelMapper.map(budget, BudgetDTO.class);
            /* JPQL */
            String categoryName = categoryRepository.findNameByCode(foundBudget.getCategoryCode());
            foundBudget.setCategoryName(categoryName);
            return foundBudget;
        });
    }

    public List<CategoryDTO> findAllCategory() {
        List<Category> categoryList = categoryRepository.findAll();
        return categoryList.stream()
                           .map(category -> modelMapper.map(category, CategoryDTO.class))
                           .toList();
    }

    /* 조회 */
    public BudgetDTO findBudgetByCode(Long budgetCode) {
        Budget foundBudget = budgetRepository.findById(budgetCode)
                                             .orElseThrow(IllegalArgumentException::new);
        return modelMapper.map(foundBudget, BudgetDTO.class);
    }

    public CategoryDTO findCategoryByCode(Long categoryCode) {
        Category foundCategory = categoryRepository.findById(categoryCode)
                                                   .orElseThrow(IllegalArgumentException::new);

        return modelMapper.map(foundCategory, CategoryDTO.class);
    }

    /* 검색 */
    public List<BudgetDTO> findByAmount(Integer amount) {
        List<Budget> budgetList = budgetRepository.findByAmountGreaterThanOrderByAmount(amount);
        return budgetList.stream()
                         .map(budget -> {
                             BudgetDTO foundBudget = modelMapper.map(budget, BudgetDTO.class);
                             String categoryName = categoryRepository.findNameByCode(foundBudget.getCategoryCode());
                             foundBudget.setCategoryName(categoryName);
                             return foundBudget;
                         })
                         .toList();
    }

    public List<BudgetDTO> findByConditions(SearchBudgetDTO searchBudget) {
        List<Budget> budgetList = budgetRepository.findByConditions(
                searchBudget.getType().name(),
                searchBudget.getMinAmount(),
                searchBudget.getMaxAmount(),
                searchBudget.getDescription()
        );
        return budgetList.stream()
                         .map(budget -> {
                             BudgetDTO foundBudget = modelMapper.map(budget, BudgetDTO.class);
                             String categoryName = categoryRepository.findNameByCode(foundBudget.getCategoryCode());
                             foundBudget.setCategoryName(categoryName);
                             return foundBudget;
                         })
                         .toList();
    }

    // public List<BudgetDTO> findByType(BudgetType type) {
    //     List<Budget> budgetList = budgetRepository.findByTypeIn(type);
    //     return budgetList.stream()
    //                      .map(budget -> modelMapper.map(budget, BudgetDTO.class))
    //                      .toList();
    // }

    // public List<MoneyDTO> searchByCategory(int searchCategoryId) {
    //     List<MoneyDTO> searchList = new ArrayList<>();
    //     for (MoneyDTO money : moneyList) {
    //         int category = money.getCategory().getId();
    //         if (category == searchCategoryId) {
    //             searchList.add(money);
    //         }
    //     }
    //     return searchList;
    // }
    //
    // public List<MoneyDTO> searchByMemo(String searchMemo) {
    //     return moneyList.stream()
    //                     .filter(money -> money.getMemo().contains(searchMemo))
    //                     .toList();
    // }
    //
    // public List<MoneyDTO> searchByMonth(int searchMonth) {
    //     List<MoneyDTO> searchList = new ArrayList<>();
    //     for (MoneyDTO money : moneyList) {
    //         int month = money.getDate().getMonthValue();
    //         if (month == searchMonth) {
    //             searchList.add(money);
    //         }
    //     }
    //     return searchList;
    // }

    /* 등록  */
    @Transactional
    public void addBudget(BudgetDTO newBudget) {
        budgetRepository.save(modelMapper.map(newBudget, Budget.class));
    }

    /* 수정 */
    @Transactional
    public void modifyBudget(BudgetDTO modifyBudget) {
        Budget foundBudget = budgetRepository.findById(modifyBudget.getCode())
                                             .orElseThrow(IllegalArgumentException::new);
        System.out.println(foundBudget);
        foundBudget.modifyBudget(modifyBudget);
    }

    /* 삭제 */
    @Transactional
    public void deleteBudget(Long budgetCode) {
        budgetRepository.deleteById(budgetCode);
    }

}

