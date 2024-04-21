package com.may54ther.dailybudgetv3.budget.entity;

import com.may54ther.dailybudgetv3.budget.dto.BudgetDTO;
import com.may54ther.dailybudgetv3.budget.type.BudgetType;
import jakarta.persistence.*;
import lombok.AccessLevel;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.ToString;
import org.springframework.data.annotation.CreatedDate;
import org.springframework.data.annotation.LastModifiedDate;
import org.springframework.data.jpa.domain.support.AuditingEntityListener;

import java.time.LocalDate;
import java.time.LocalDateTime;

@Entity
@Table(name = "tbl_budget")
@Getter
@NoArgsConstructor(access = AccessLevel.PROTECTED)
@EntityListeners(AuditingEntityListener.class)
@ToString
public class Budget {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long code;

    private Long categoryCode;
    // @ManyToOne(fetch = FetchType.LAZY)
    // @JoinColumn(name = "category_code")
    // private Category category;

    @Temporal(TemporalType.DATE)
    private LocalDate usageDate;

    @Enumerated(EnumType.STRING)
    private BudgetType type;

    private int amount;

    private String description;

    @CreatedDate
    private LocalDateTime createdAt;

    @LastModifiedDate
    private LocalDateTime updatedAt;

    public void modifyBudget(BudgetDTO budget) {
        this.categoryCode = budget.getCategoryCode();
        this.usageDate = budget.getUsageDate();
        this.type = budget.getType();
        this.amount = budget.getAmount();
        this.description = budget.getDescription();
    }
}
