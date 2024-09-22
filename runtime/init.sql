CREATE DATABASE `oa` DEFAULT CHARACTER SET UTF8MB4 COLLATE utf8mb4_general_ci;

use `oa`;

CREATE TABLE `user`
(
    `id`        INT(11)      NOT NULL AUTO_INCREMENT COMMENT 'User id',
    `username`  VARCHAR(20)  NOT NULL COMMENT 'User name',
    `nick_name` VARCHAR(20) DEFAULT NULL COMMENT 'Nickname',
    `password`  VARCHAR(100) NOT NULL COMMENT 'Login password',
    `email`     VARCHAR(50) DEFAULT NULL COMMENT 'Email',
    PRIMARY KEY (`id`),
    UNIQUE KEY `idx_username` (`username`)
) ENGINE = InnoDB
  DEFAULT CHARSET = utf8mb4
  COLLATE = utf8mb4_general_ci COMMENT ='User management table';

CREATE TABLE `task`
(
    `id`          INT(11)     NOT NULL AUTO_INCREMENT COMMENT 'Task id',
    `user_id`     INT(11)     NOT NULL COMMENT 'User id',
    `title`       VARCHAR(50) NOT NULL COMMENT 'Task title',
    `content`     TEXT        NOT NULL COMMENT 'Task content',
    `status`      INT(11)     NOT NULL COMMENT 'Task status',
    `start_time`  DATETIME    NOT NULL COMMENT 'The planned start time of task',
    `end_time`    DATETIME    NOT NULL COMMENT 'The end time of task',
    `create_time` DATETIME    NOT NULL DEFAULT CURRENT_TIMESTAMP COMMENT 'The create time of task',
    `update_time` DATETIME    NOT NULL DEFAULT CURRENT_TIMESTAMP ON UPDATE CURRENT_TIMESTAMP COMMENT 'The update time of task',
    PRIMARY KEY (`id`),
    INDEX `idx_user_id` (`user_id`)
) ENGINE = InnoDB
  DEFAULT CHARSET = utf8mb4
  COLLATE = utf8mb4_general_ci COMMENT ='Task table';