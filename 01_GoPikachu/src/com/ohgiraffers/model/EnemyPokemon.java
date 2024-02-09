package com.ohgiraffers.model;


import java.time.LocalDateTime;

public class EnemyPokemon extends Pokemon {

    private final int level;

    private boolean isCaptured;
    private LocalDateTime capturedDate;

    public EnemyPokemon(int level, String name, int hp, int attack) {
        super(name, hp, attack);
        this.level = level;
        this.isCaptured = false;
    }

    public int getLevel() {
        return level;
    }

    public boolean isCaptured() {
        return isCaptured;
    }

    public LocalDateTime getCapturedDate() {
        return capturedDate;
    }

    public void setCapturedState(boolean captured, LocalDateTime capturedDate) {
        this.isCaptured = captured;
        this.capturedDate = capturedDate;
    }
}
