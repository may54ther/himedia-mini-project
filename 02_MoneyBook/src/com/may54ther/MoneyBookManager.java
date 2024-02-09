package com.may54ther;

import com.may54ther.dto.MoneyDTO;

import java.io.*;
import java.util.ArrayList;
import java.util.List;

public class MoneyBookManager {

    private final String FILE_NAME = "moneybook.dat";

    private List<MoneyDTO> moneyList = new ArrayList<>();

    public void addList(MoneyDTO money) {
        moneyList.add(money);
    }

    public List<MoneyDTO> selectList() {
        return moneyList;
    }

    public List<MoneyDTO> searchByCategory(int searchCategoryId) {
        List<MoneyDTO> searchList = new ArrayList<>();
        for (MoneyDTO money : moneyList) {
            int category = money.getCategory().getId();
            if (category == searchCategoryId) {
                searchList.add(money);
            }
        }
        return searchList;
    }

    public List<MoneyDTO> searchByMemo(String searchMemo) {
        return moneyList.stream()
                        .filter(money -> money.getMemo().contains(searchMemo))
                        .toList();
    }

    public List<MoneyDTO> searchByMonth(int searchMonth) {
        List<MoneyDTO> searchList = new ArrayList<>();
        for (MoneyDTO money : moneyList) {
            int month = money.getDate().getMonthValue();
            if (month == searchMonth) {
                searchList.add(money);
            }
        }
        return searchList;
    }

    public boolean updateItem(MoneyDTO updateMoney) {
        for (int i = 0; i < moneyList.size(); i++) {
            if (moneyList.get(i).getId() == updateMoney.getId()) {
                moneyList.set(i, updateMoney);
                return true;
            }
        }
        return false;
    }

    public boolean removeItem(int id) {
        MoneyDTO money = null;
        for (int i = 0; i < moneyList.size(); i++) {
            if (moneyList.get(i).getId() == id) {
                money = moneyList.remove(i);
            }
        }
        return money != null;
    }

    /* 파일로 저장 및 로드 */
    public void loadFile() {
        try (ObjectInputStream in = new ObjectInputStream(new FileInputStream(FILE_NAME))) {
            moneyList.addAll((List<MoneyDTO>) in.readObject());
        } catch (FileNotFoundException e) {
            moneyList = new ArrayList<>();
        } catch (ClassNotFoundException | IOException e) {
            moneyList = new ArrayList<>();
        }
    }

    public void saveFile() {
        try (ObjectOutputStream out = new ObjectOutputStream(new FileOutputStream(FILE_NAME))) {
            out.writeObject(moneyList);
        } catch (FileNotFoundException e) {
            System.out.println("File Not Found.");
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}
