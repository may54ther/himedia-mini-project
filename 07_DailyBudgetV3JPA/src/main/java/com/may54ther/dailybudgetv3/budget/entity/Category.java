package com.may54ther.dailybudgetv3.budget.entity;

import com.may54ther.dailybudgetv3.budget.dto.CategoryDTO;
import jakarta.persistence.*;
import lombok.AccessLevel;
import lombok.Getter;
import lombok.NoArgsConstructor;

@Entity
@Table(name = "tbl_category")
@Getter
@NoArgsConstructor(access = AccessLevel.PROTECTED)
public class Category {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long code;

    private String name;

    public void modifyCategory(CategoryDTO category) {
        this.code = category.getCode();
        this.name = category.getName();
    }
}
