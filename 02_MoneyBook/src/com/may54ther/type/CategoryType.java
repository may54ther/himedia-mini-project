package com.may54ther.type;

/**
 * 카테고리
 * 저축, 식비, 주거/통신, 교통, 의류/미용, 문화/여가, 의료/건강, 교육비,  기타
 * <p>
 * 저축, 생활, 여가, 건강, 교육
 */
public enum CategoryType {

    LIVING(1, "생활"),
    SHOPPING(2, "쇼핑"),
    HEALTH(3, "병원"),
    EDUCATION(4, "교육"),
    HOBBY(5, "취미"),
    NONE(6, "기타");


    private final int id;

    private final String name;

    CategoryType(int id, String name) {
        this.id = id;
        this.name = name;
    }

    public int getId() {
        return id;
    }

    public String getName() {
        return name;
    }

    @Override
    public String toString() {
        return id + ". " + name;
    }
}
