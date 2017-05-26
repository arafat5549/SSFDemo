-- 账户表
DROP TABLE IF EXISTS `test_account`;
CREATE TABLE `test_account` (
	`id` int(11) NOT NULL AUTO_INCREMENT,
	`user_id` int(11) NOT NULL COMMENT '外键 用户id',
	`balance` decimal(10,2)  NOT NULL COMMENT '账号余额',
	PRIMARY KEY (`id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8;


INSERT INTO test_account(user_id,balance) VALUES(1, 100000);
INSERT INTO test_account(user_id,balance) VALUES(2, 100000);
INSERT INTO test_account(user_id,balance) VALUES(3, 100000);
