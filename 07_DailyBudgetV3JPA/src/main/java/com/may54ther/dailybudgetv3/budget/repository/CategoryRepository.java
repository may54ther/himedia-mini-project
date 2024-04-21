package com.may54ther.dailybudgetv3.budget.repository;


import com.may54ther.dailybudgetv3.budget.entity.Category;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

public interface CategoryRepository extends JpaRepository<Category, Long> {

    @Query("SELECT c.name FROM Category c WHERE c.code = :code")
    String findNameByCode(Long code);


    /* [JPQL] findAll 메소드로 조회 가능하지만 JPQL 설정 테스트를 위해 작성 */

    /* [Native Query]  nativeQuery = true 속성을 추가함*/
    // @Query(
    //         value = "SELECT category_code, category_name, ref_category_code FROM tbl_category ORDER BY category_code",
    //         nativeQuery = true
    // )
    // List<Category> findAllCategory();
}
