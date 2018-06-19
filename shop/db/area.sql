CREATE TABLE `tb_area1` (
  `area_id`        INT(2)       NOT NULL AUTO_INCREMENT,
  `area_name`      VARCHAR(200) NOT NULL,
  `priority`       INT(2)       NOT NULL DEFAULT '0',
  `create_time`    DATETIME              DEFAULT NULL,
  `last_edit_time` DATETIME              DEFAULT NULL,
  PRIMARY KEY (`area_id`),
  UNIQUE KEY `UK_AREA`(`area_name`)
)
  ENGINE = INNODB
  AUTO_INCREMENT = 1
  DEFAULT CHARSET = utf8;