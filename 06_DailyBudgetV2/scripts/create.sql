CREATE DATABASE dailybudgetV2;
GRANT ALL PRIVILEGES ON dailybudgetV2.* TO 'ohgiraffers'@'%';
FLUSH PRIVILEGES;

USE dailybudgetV2;

CREATE TABLE IF NOT EXISTS `tbl_category` (
    `code` INT          NOT NULL AUTO_INCREMENT COMMENT '카테고리코드',
    `name` VARCHAR(255) NOT NULL UNIQUE COMMENT '카테고리명',
    PRIMARY KEY (`code`)
) COMMENT = '카테고리';

CREATE TABLE IF NOT EXISTS `tbl_budget` (
    `code`          INT                    NOT NULL AUTO_INCREMENT COMMENT '식별코드',
    `category_code` INT                    NOT NULL COMMENT '카테고리코드',
    `usage_date`    DATE                   NOT NULL COMMENT '사용날짜',
    `type`          CHAR(3)                NOT NULL COMMENT '입출금여부',
    `amount`        INT                    NOT NULL COMMENT '금액',
    `description`   VARCHAR(255)           NULL COMMENT '설명',
    `created_at`    DATETIME DEFAULT NOW() NOT NULL COMMENT '등록일',
    `updated_at`    DATETIME DEFAULT NOW() NOT NULL ON UPDATE CURRENT_TIMESTAMP COMMENT '수정일',
    PRIMARY KEY (`code`),
    FOREIGN KEY (`category_code`)
        REFERENCES tbl_category (`code`),
    CONSTRAINT `budget_type_chk` CHECK ( `type` IN ('IN', 'OUT') )
) COMMENT = '가계부';

