package com.ohgiraffers.model;

public class Pokemon {

    private final int MAX_HP;

    private String name;
    private int hp;
    private int damage;

    public Pokemon(String name, int hp, int damage) {
        this.name = name;
        this.hp = hp;
        this.damage = damage;

        this.MAX_HP = hp;
    }

    public String getName() {
        return name;
    }

    public int getHp() {
        return Math.max(0, hp);
    }

    public void setHp(int hp) {
        this.hp = Math.max(0, hp);
    }

    public int getMaxHp() {
        return MAX_HP;
    }

    public int getDamage() {
        return damage;
    }

    public void resetHp() {
        this.setHp(this.MAX_HP);
    }

    public void attack(Pokemon target) {
        int damage = this.getDamage();
        System.out.println("\n >> " + this.name + " 이(가) " + target.getName() + "을(를) 공격합니다.");
        target.takeDamage(damage);
    }

    public void takeDamage(int damage) {
        this.hp -= damage;
        System.out.println(this.name + " 이(가) " + damage + " 의 피해를 입었습니다.");
        System.out.println(this.name + "의 현재 HP는 " + this.hp);
    }
}
