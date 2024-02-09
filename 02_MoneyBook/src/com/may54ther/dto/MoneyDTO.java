package com.may54ther.dto;


import com.may54ther.type.CategoryType;

import java.io.Serializable;
import java.time.LocalDate;
import java.util.Arrays;

public class MoneyDTO implements Serializable {

    private static long nextId = 1;

    private long id;
    private CategoryType category;
    private int amount;
    private String memo;
    private LocalDate date;

    public MoneyDTO(int categoryId, int amount, String memo, LocalDate date) {
        this(MoneyDTO.nextId++, categoryId, amount, memo, date);
    }

    public MoneyDTO(long id, int categoryId, int amount, String memo, LocalDate date) {
        this.id = id;
        this.setCategory(categoryId);
        this.amount = amount;
        this.memo = memo;
        this.date = date;
    }

    public static long getNextId() {
        return nextId;
    }

    public static void setNextId(long nextId) {
        MoneyDTO.nextId = nextId;
    }

    public static void increaseNextId() {
        MoneyDTO.nextId++;
    }

    public long getId() {
        return id;
    }

    public void setId(long id) {
        this.id = id;
    }

    public CategoryType getCategory() {
        return category;
    }

    public void setCategory(int categoryId) {
        this.category = Arrays.stream(CategoryType.values())
                              .filter(category -> category.getId() == categoryId)
                              .findAny().get();
    }

    public void setCategory(CategoryType category) {
        this.category = category;
    }

    public int getAmount() {
        return amount;
    }

    public void setAmount(int amount) {
        this.amount = amount;
    }

    public String getMemo() {
        return memo;
    }

    public void setMemo(String memo) {
        this.memo = memo;
    }

    public LocalDate getDate() {
        return date;
    }

    public void setDate(LocalDate date) {
        this.date = date;
    }

    @Override
    public String toString() {
        return "[ID] " + id +
                "　　[카테고리] " + category.getName() +
                "　　[금액] " + amount +
                "　　[메모] " + memo +
                "　　[날짜] " + date;
    }
}
