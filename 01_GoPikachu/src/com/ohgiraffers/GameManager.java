package com.ohgiraffers;

import com.ohgiraffers.model.EnemyPokemon;
import com.ohgiraffers.model.enemy.Charmander;
import com.ohgiraffers.model.enemy.Mew;
import com.ohgiraffers.model.enemy.Squirtle;
import com.ohgiraffers.model.PlayerPokemon;

import java.util.Arrays;
import java.util.Scanner;

public class GameManager {

    private final Scanner scanner = new Scanner(System.in);

    private final PlayerPokemon player = new PlayerPokemon("피카츄", 60, 15);
    private final EnemyPokemon[] enemies = generateEnemies();

    public void run() {
        introduction();

        while (true) {
            printMenu();
            handleMenuInput();
            printGameStatus();
            checkAllPokemonsCaptured();
        }
    }

    private void introduction() {
        System.out.println("━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━");
        System.out.println(" 피카츄와 3마리의 동료를 모아서 모험을 떠나려고 합니다.");
        System.out.println(" 하지만 가지고 있는 포켓볼은 5개!!");
        System.out.println(" 5개의 포켓볼을 사용하여 3마리의 동료를 모아보세요!");
        System.out.println("━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━");
    }

    private void printMenu() {
        System.out.println(">> [포켓몬 잡기] 포켓몬을 선택하세요. ");
        System.out.println("[1] 파이리 / [2] 꼬부기 / [3] 뮤 / [4] 게임 종료");
        System.out.print("==> 입력 : ");
    }

    private void handleMenuInput() {
        int choice = scanner.nextInt();
        switch (choice) {
            case 1, 2, 3 -> startBattle(enemies[choice - 1]);
            case 4 -> exitGame();
            default -> handleInvalidInput();
        }
    }

    private void startBattle(EnemyPokemon enemy) {
        // 잡은 포켓몬이면 다시 메뉴로
        if (enemy.isCaptured()) {
            System.out.println(enemy.getName() + " 은(는) 이미 잡았습니다. 다른 포켓몬을 선택하세요! ");
            return;
        }

        // HP 초기화
        player.resetHp();
        enemy.resetHp();

        System.out.println(" >> [전투] " + enemy.getName() + "을 만났다!! 전투를 시작합니다. ");

        while (!enemy.isCaptured()) {
            // 남은 포켓볼 갯수 체크
            if (player.getPokeballCount() == 0) {
                System.out.println("모든 포켓볼을 사용하여 더 이상 진행할 수 없습니다.");
                exitGame();
            }
            if (enemy.getHp() <= 0) {
                System.out.println(enemy.getName() + " 이(가) 쓰러졌습니다.");
                player.throwPokeball(scanner, enemy);
                return;
            }
            if (player.getHp() <= 0) {
                System.out.println(player.getName() + " 이(가) 쓰러졌습니다.");
                return;
            }

            // 턴마다 메뉴 출력
            printTurnOptions();
            handleTurnInput(enemy);
        }
    }

    private void printTurnOptions() {
        System.out.println("\n==== 턴 시작 ====");
        System.out.println("[1] 공격 / [2] 포켓볼 던지기 ");
        System.out.print("==> 입력 : ");
    }

    private void handleTurnInput(EnemyPokemon enemy) {
        switch (scanner.nextInt()) {
            case 1 -> {
                player.attack(enemy);
                enemy.attack(player);
            }
            case 2 -> handleThrowPokeball(enemy);
            default -> handleInvalidInput();
        }
    }

    private void handleThrowPokeball(EnemyPokemon enemy) {
        if (enemy.getHp() > (enemy.getMaxHp() * 0.7)) {
            System.out.println(enemy.getName() + " 의 체력이 70% 미만일 때 포켓볼을 사용할 수 있습니다.");
        } else {
            player.throwPokeball(scanner, enemy);
        }
    }

    private void printGameStatus() {
        System.out.println("━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━");
        System.out.println("[ 남은 포켓볼 갯수 ] " + player.getPokeballCount() + " 개");
        System.out.println("[ 동료 포켓몬 상태 ]");
        Arrays.stream(enemies)
              .filter(EnemyPokemon::isCaptured)
              .forEach(enemy -> System.out.println(enemy.getName() + " (" + enemy.getCapturedDate() + ")"));
        System.out.println("━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━");
    }

    private void checkAllPokemonsCaptured() {
        int capturedCount = (int) Arrays.stream(enemies)
                                        .filter(EnemyPokemon::isCaptured)
                                        .count();

        if (capturedCount == enemies.length) {
            System.out.println("모든 포켓몬을 잡았습니다! 축하합니다!");
            exitGame();
        }
    }

    private void handleInvalidInput() {
        System.out.println("잘못된 값을 입력하셨습니다. 다시 입력해주세요.");
    }

    private void exitGame() {
        System.out.println("게임을 종료합니다.");
        System.exit(0);
    }

    // 적 포켓몬 생성 및 반환
    private EnemyPokemon[] generateEnemies() {
        return new EnemyPokemon[]{
                new Charmander(),
                new Squirtle(),
                new Mew()
        };
    }
}
