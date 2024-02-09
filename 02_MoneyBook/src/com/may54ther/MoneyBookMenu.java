package com.may54ther;

import com.may54ther.comparator.AscCategory;
import com.may54ther.comparator.DescCategory;
import com.may54ther.dto.MoneyDTO;
import com.may54ther.type.CategoryType;

import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.util.*;

public class MoneyBookMenu {

    private MoneyBookManager manager = new MoneyBookManager();
    private Scanner sc = new Scanner(System.in);

    public void mainMenu() {
        manager.loadFile();

        mainMenu:
        while (true) {
            System.out.println("┌────────────────────────────────────────────┐");
            System.out.println("                가    계    부               ");
            System.out.println("├────────────────────────────────────────────┤");
            System.out.println("      1. 추가       2. 조회       3. 정렬     ");
            System.out.println("      4. 검색       5. 수정       6. 삭제     ");
            System.out.println("               0. 프로그램 종료               ");
            System.out.println("└────────────────────────────────────────────┘");

            System.out.print(">> 선택: ");
            int menu = sc.nextInt();
            sc.nextLine();

            switch (menu) {
                case 1:
                    addItem();
                    break;
                case 2:
                    selectList();
                    break;
                case 3:
                    sortMenu();
                    break;
                case 4:
                    searchMenu();
                    break;
                case 5:
                    updateItem();
                    break;
                case 6:
                    removeItem();
                    break;
                case 0:
                    manager.saveFile();
                    System.out.println("프로그램을 종료합니다.");
                    break mainMenu;
                default:
                    System.out.println("잘못 선택하셨습니다. 번호를 다시 입력해주세요.");
                    break;
            }
        }
    }

    /* 메뉴 */
    private void categoryMenu() {
        System.out.println(">> [카테고리] 선택: ");
        EnumSet<CategoryType> categoryTypes = EnumSet.allOf(CategoryType.class);
        for (CategoryType categoryType : categoryTypes) {
            System.out.print(categoryType.toString() + " ");
        }
        System.out.println();
    }

    public void sortMenu() {
        System.out.println("┌────────────────────────────────────────────┐");
        System.out.println("                가 계 부  정 렬               ");
        System.out.println("├────────────────────────────────────────────┤");
        System.out.println("   1. 금　　액 오름차순    2. 금　　액 내림차순   ");
        System.out.println("   3. 카테고리 오름차순    4. 카테고리 내림차순   ");
        System.out.println("└────────────────────────────────────────────┘");

        System.out.print(" >> 선택: ");
        int menu = sc.nextInt();

        selectSortedList(menu);
    }

    public void searchMenu() {
        System.out.println("┌────────────────────────────────────────────┐");
        System.out.println("                가 계 부  검 색               ");
        System.out.println("├────────────────────────────────────────────┤");
        System.out.println("        1. 카테고리 검색     2. 메모 검색        ");
        System.out.println("        3. 월별 데이터 검색                     ");
        System.out.println("└────────────────────────────────────────────┘");
        System.out.print(" >> 선택: ");
        int menu = sc.nextInt();
        sc.nextLine();

        searchList(menu);
    }

    /* --- */
    public void addItem() {
        System.out.println("─────────────────────────────────────────────");
        System.out.println("                가 계 부  입 력               ");
        System.out.println("─────────────────────────────────────────────");

        categoryMenu();
        int categoryId = sc.nextInt();
        System.out.println(">> [금액] 입력: ");
        int amount = sc.nextInt();
        sc.nextLine();
        System.out.println(">> [메모] 입력: ");
        String message = sc.nextLine();
        System.out.println(">> [날짜] 입력(ex. 20240101): ");
        String date = sc.nextLine();
        LocalDate localDate = date.isEmpty() ?
                LocalDate.now() : LocalDate.parse(date, DateTimeFormatter.ofPattern("yyyyMMdd"));

        MoneyDTO moneyItem = new MoneyDTO(categoryId, amount, message, localDate);
        manager.addList(moneyItem);
    }

    public void selectList() {
        System.out.println("────────────────────────────────────────────");
        System.out.println("            전 체  가 계 부  조 회            ");
        System.out.println("────────────────────────────────────────────");

        List<MoneyDTO> moneyList = manager.selectList();
        if (moneyList.isEmpty()) {
            System.out.println("목록이 존재하지 않습니다.");
        } else {
            for (MoneyDTO moneyItem : moneyList) {
                System.out.println(moneyItem);
            }
        }
    }

    public void selectSortedList(int menu) {
        List<MoneyDTO> moneyList = manager.selectList();
        if (moneyList.isEmpty()) {
            System.out.println("목록이 존재하지 않습니다.");
            return;
        }

        List<MoneyDTO> sortList = new ArrayList<>();
        sortList.addAll(moneyList);

        if (menu == 1) {
            sortList.sort(new Comparator<MoneyDTO>() {
                @Override
                public int compare(MoneyDTO o1, MoneyDTO o2) {
                    return o1.getAmount() - o2.getAmount();
                }
            });
        } else if (menu == 2) {
            sortList.sort(new Comparator<MoneyDTO>() {
                @Override
                public int compare(MoneyDTO o1, MoneyDTO o2) {
                    return o2.getAmount() - o1.getAmount();
                }
            });
        } else if (menu == 3) {
            sortList.sort(new AscCategory());
        } else {
            sortList.sort(new DescCategory());
        }

        for (MoneyDTO moneyItem : sortList) {
            System.out.println(moneyItem);
        }
    }

    public void searchList(int menu) {
        List<MoneyDTO> searchList = new ArrayList<>();

        if (menu == 1) {
            categoryMenu();
            searchList = manager.searchByCategory(sc.nextInt());
            sc.nextLine();
        } else if (menu == 2) {
            System.out.print(">> [메모] 입력: ");
            searchList = manager.searchByMemo(sc.nextLine());
        } else if (menu == 3) {
            System.out.print(">> 조회할 달(Month) 입력: ");
            searchList = manager.searchByMonth(sc.nextInt());
            sc.nextLine();
        } else {
            System.out.println("잘못된 값을 입력하셨습니다.");
        }

        if (searchList.isEmpty()) {
            System.out.println("검색 결과가 없습니다.");
        } else {
            for (MoneyDTO moneyItem : searchList) {
                System.out.println(moneyItem);
            }
        }
    }

    public void updateItem() {
        System.out.println("────────────────────────────────────────────");
        System.out.println("               가 계 부  수 정               ");
        System.out.println("────────────────────────────────────────────");
        System.out.print(">> [ID] 입력: ");
        int id = sc.nextInt();
        categoryMenu();
        int categoryId = sc.nextInt();
        System.out.println(">> [금액] 입력: ");
        int amount = sc.nextInt();
        sc.nextLine();
        System.out.println(">> [메모] 입력: ");
        String message = sc.nextLine();
        System.out.println(">> [날짜] 입력(ex. 20240101): ");
        String date = sc.nextLine();
        LocalDate localDate = date.isEmpty() ?
                LocalDate.now() : LocalDate.parse(date, DateTimeFormatter.ofPattern("yyyyMMdd"));

        MoneyDTO moneyItem = new MoneyDTO(id, categoryId, amount, message, localDate);
        if (manager.updateItem(moneyItem)) {
            System.out.println("성공적으로 수정 되었습니다.");
        } else {
            System.out.println("데이터가 존재하지 않습니다.");
        }
    }

    public void removeItem() {
        System.out.println("────────────────────────────────────────────");
        System.out.println("               가 계 부  삭 제               ");
        System.out.println("────────────────────────────────────────────");

        System.out.print(">> [ID] 입력: ");
        if (manager.removeItem(sc.nextInt())) {
            System.out.println("성공적으로 삭제 되었습니다.");
        } else {
            System.out.println("데이터가 존재하지 않습니다.");
        }
        sc.nextLine();
    }
}



