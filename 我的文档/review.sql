-- 评论表
DROP TABLE IF EXISTS `sys_review`;
CREATE TABLE `sys_review` (
	`id`	    INT(11) NOT NULL AUTO_INCREMENT COMMENT '主键自增长',
	`text`		VARCHAR(255) NOT NULL COMMENT '评论信息',
	`user_id`	INT(11) NOT NULL  COMMENT '外键 用户ID',
	`product_id`	INT(11) NOT NULL  COMMENT '外键 商品ID',
	`create_time` datetime NOT NULL COMMENT '创建时间',
	`update_time` datetime NOT NULL COMMENT '更新时间',
	primary key(`id`)
)ENGINE=InnoDB DEFAULT CHARSET=utf8;

INSERT INTO sys_review VALUES(1,"测试评论用户1——1",1,87,now(),now());
INSERT INTO sys_review VALUES(2,"测试评论用户1——2",1,87,now(),now());
INSERT INTO sys_review VALUES(3,"测试评论用户1——3",1,87,now(),now());
INSERT INTO sys_review VALUES(4,"测试评论用户1——4",1,87,now(),now());

INSERT INTO sys_review VALUES(5,"测试评论用户2",2,87,now(),now());