CREATE DATABASE `oa` DEFAULT CHARACTER SET UTF8MB4 COLLATE utf8mb4_general_ci;

use `oa`;

CREATE TABLE `user`
(
    `id`        INT(11)     NOT NULL AUTO_INCREMENT COMMENT 'User id',
    `username`  VARCHAR(20) NOT NULL COMMENT 'User name',
    `nick_name` VARCHAR(20) DEFAULT NULL COMMENT 'Nickname',
    `password`  VARCHAR(32) NOT NULL COMMENT 'Login password',
    `salt`      VARCHAR(18) NOT NULL COMMENT 'Salt of password',
    `email`     VARCHAR(50) DEFAULT NULL COMMENT 'Email',
    PRIMARY KEY (`id`),
    UNIQUE KEY `idx_username` (`username`)
) ENGINE = InnoDB
  DEFAULT CHARSET = utf8mb4
  COLLATE = utf8mb4_general_ci COMMENT ='User management table';