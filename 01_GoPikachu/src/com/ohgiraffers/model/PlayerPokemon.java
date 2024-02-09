package com.ohgiraffers.model;

import com.ohgiraffers.util.RandomUtils;

import java.time.LocalDateTime;
import java.util.Scanner;

public class PlayerPokemon extends Pokemon {

    private int pokeballCount;

    public PlayerPokemon(String name, int hp, int damage) {
        super(name, hp, damage);
        this.pokeballCount = 5;
    }

    public int getPokeballCount() {
        return pokeballCount;
    }

    @Override
    public int getDamage() {
        int hit = RandomUtils.generateRandom(1, 3);
        return super.getDamage() * hit;
    }

    public void throwPokeball(Scanner scanner, EnemyPokemon enemy) {
        if (enemy.getHp() <= 0) {
            System.out.println(enemy.getName() + " 을(를) 잡았습니다!");
            enemy.setCapturedState(true, LocalDateTime.now());
            pokeballCount--;
            return;
        }

        int bound = enemy.getLevel() + 1;
        System.out.println(">> [포켓볼 던지기] 포켓볼을 던졌습니다! ");
        System.out.println("숫자 입력 (1 ~ " + bound + ") : ");

        while (true) {
            int choice = scanner.nextInt();

            if (choice < 1 || choice > bound) {
                System.out.println("잘못된 값을 입력하셨습니다. 다시 입력해주세요.");
                continue;
            }

            if (RandomUtils.validate(choice, bound)) {
                enemy.setCapturedState(true, LocalDateTime.now());
                System.out.println("성공!! " + enemy.getName() + " 을(를) 잡았습니다!");
            } else {
                System.out.println("실패!! 포켓볼을 던졌지만 휙휙 피했지롱...ㅠㅠ ");
            }
            break;
        }

        pokeballCount--;
    }
}
