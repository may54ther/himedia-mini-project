package com.may54ther.budget.model.dto;

import java.time.LocalDate;
import java.time.LocalDateTime;

public class BudgetAndCategoryDTO {

    private int code;
    private CategoryDTO category;
    private int userCode;
    private LocalDate usageDate;
    private String type;
    private int amount;
    private String description;
    private LocalDateTime createdAt;
    private LocalDateTime updatedAt;

    public BudgetAndCategoryDTO() {}

    public int getCode() {
        return code;
    }

    public void setCode(int code) {
        this.code = code;
    }

    public int getUserCode() {
        return userCode;
    }

    public void setUserCode(int userCode) {
        this.userCode = userCode;
    }

    public LocalDate getUsageDate() {
        return usageDate;
    }

    public void setUsageDate(String usageDate) {
        this.usageDate = usageDate.isEmpty() ? null : LocalDate.parse(usageDate);
    }

    public void setUsageDate(LocalDate usageDate) {
        this.usageDate = usageDate;
    }

    public String getType() {
        return type;
    }

    public void setType(String type) {
        this.type = type;
    }

    public int getAmount() {
        return amount;
    }

    public void setAmount(int amount) {
        this.amount = amount;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public LocalDateTime getCreatedAt() {
        return createdAt;
    }

    public void setCreatedAt(LocalDateTime createdAt) {
        this.createdAt = createdAt;
    }

    public LocalDateTime getUpdatedAt() {
        return updatedAt;
    }

    public void setUpdatedAt(LocalDateTime updatedAt) {
        this.updatedAt = updatedAt;
    }

    public CategoryDTO getCategory() {
        return category;
    }

    public void setCategory(CategoryDTO category) {
        this.category = category;
    }

    @Override
    public String toString() {
        return String.format("| %-4d | %-5d | %-10s | %-4s | %-10s | %-10s | %-15s",
                             code, userCode, category, type, amount, usageDate, description);
    }
}
