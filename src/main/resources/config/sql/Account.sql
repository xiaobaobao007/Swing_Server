DROP TABLE IF EXISTS `account`;
CREATE TABLE `account`
(
  `account`    varchar(128) default null,
  `userId`     int(11)      default 0,
  `password`   varchar(255) default null,
  `email`      varchar(255) default null,
  `createTime` varchar(255) default null,
  `registIp`   varchar(255) default null,
  PRIMARY KEY (`account`)
) ENGINE = InnoDB
  DEFAULT CHARSET = utf8mb4;