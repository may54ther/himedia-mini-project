package com.may54ther.comparator;

import com.may54ther.dto.MoneyDTO;

import java.util.Comparator;

public class AscCategory implements Comparator<MoneyDTO> {

    @Override
    public int compare(MoneyDTO o1, MoneyDTO o2) {
        return o1.getCategory().compareTo(o2.getCategory());
    }
}
