CREATE DATABASE dailybudget;
GRANT ALL PRIVILEGES ON dailybudget.* TO 'ohgiraffers'@'%';
FLUSH PRIVILEGES;

USE dailybudget;

CREATE TABLE IF NOT EXISTS `tbl_user` (
    `code`       INT                       NOT NULL AUTO_INCREMENT COMMENT '회원코드',
    `id`         VARCHAR(255)              NOT NULL UNIQUE COMMENT '아이디',
    `password`   VARCHAR(255)              NOT NULL COMMENT '비밀번호',
    `name`       VARCHAR(255)              NOT NULL COMMENT '이름',
    `birth`      DATE                      NOT NULL COMMENT '생년월일',
    `role`       CHAR(8)  DEFAULT 'MEMBER' NOT NULL COMMENT '권한',
    `created_at` DATETIME DEFAULT NOW()    NOT NULL COMMENT '가입일',
    `updated_at` DATETIME DEFAULT NOW()    NOT NULL ON UPDATE CURRENT_TIMESTAMP COMMENT '수정일',
    `enabled`    CHAR(1)  DEFAULT 'T'      NOT NULL COMMENT '사용여부',
    PRIMARY KEY (`code`),
    CONSTRAINT `user_role_chk` CHECK ( `role` IN ('ADMIN', 'MEMBER') )
) COMMENT = '회원';

CREATE TABLE IF NOT EXISTS `tbl_category` (
    `code` INT          NOT NULL AUTO_INCREMENT COMMENT '카테고리코드',
    `name` VARCHAR(255) NOT NULL UNIQUE COMMENT '카테고리명',
    PRIMARY KEY (`code`)
) COMMENT = '카테고리';

CREATE TABLE IF NOT EXISTS `tbl_budget` (
    `code`          INT                    NOT NULL AUTO_INCREMENT COMMENT '식별코드',
    `user_code`     INT                    NOT NULL COMMENT '회원코드',
    `category_code` INT                    NOT NULL COMMENT '카테고리코드',
    `usage_date`    DATE                   NOT NULL COMMENT '사용날짜',
    `type`          CHAR(3)                NOT NULL COMMENT '입출금여부',
    `amount`        INT                    NOT NULL COMMENT '금액',
    `description`   VARCHAR(255)           NULL COMMENT '설명',
    `created_at`    DATETIME DEFAULT NOW() NOT NULL COMMENT '등록일',
    `updated_at`    DATETIME DEFAULT NOW() NOT NULL ON UPDATE CURRENT_TIMESTAMP COMMENT '수정일',
    PRIMARY KEY (`code`),
    FOREIGN KEY (`user_code`) REFERENCES tbl_user (`code`),
    FOREIGN KEY (`category_code`) REFERENCES tbl_category (`code`),
    CONSTRAINT `budget_type_chk` CHECK ( `type` IN ('IN', 'OUT') )
) COMMENT = '가계부';

