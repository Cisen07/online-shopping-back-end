drop database if EXISTS  mydbt;
CREATE database mydbt;
use mydbt;

-- 管理员
DROP TABLE IF EXISTS `admin`;
CREATE TABLE `admin` (
`adminID` INT(11) NOT NULL,
`adminName` VARCHAR(50) NOT NULL,
`adminpassword` VARCHAR(20) NOT NULL,
`phonenum` VARCHAR(11) NOT NULL,
PRIMARY KEY (`adminID`)
)COLLATE='utf8_general_ci'
ENGINE=INNODB
;
insert into admin value(4,'弟弟','didi',44444444444);
insert into admin value(1,'小明','xiaoming',11111111111);
insert into admin value(2,'小红','xiaohong',22222222222);
insert into admin value(3,'小刚','xiaogang',33333333333);

-- 用户
DROP TABLE IF EXISTS `user`;
CREATE TABLE `user` (
`userID` INT(11) NOT NULL,
`userName` VARCHAR(50) NOT NULL,
`userPassword` VARCHAR(20) NOT NULL,
`phoneNumber` VARCHAR(11) NOT NULL,
PRIMARY KEY (`userID`)
)COLLATE='utf8_general_ci'
ENGINE=INNODB
;
INSERT INTO user VALUES(0, '金靥', '1', '123');
insert into user value(1,'习大大','xidada',12345678901);
insert into user value(2,'彭麻麻','pengmama',12345678901);
insert into user value(3,'沙雕','shadiao',12345678911);
insert into user value(4,'王五','wangwu',1234511111);

-- 地址
DROP TABLE IF EXISTS `address`;
CREATE TABLE `address` (
`userID` INT(11) NOT NULL,
`name` VARCHAR(50) NOT NULL,
`address` VARCHAR(100) NOT NULL,
`phonenum` VARCHAR(11) NOT NULL,
PRIMARY KEY (`userID`, `name`, `address`, `phonenum`),
CONSTRAINT `FK_address_user` FOREIGN KEY (`userID`) REFERENCES `user` (`userID`)
)COLLATE='utf8_general_ci'
ENGINE=INNODB
;
insert into address value(2,'习大大','北京天安门',66666666666);
insert into address value(2,'习大大','北京地中海',66668886677);
insert into address value(1,'彭麻麻','北京天安门',66886666688);
insert into address value(4,'沙雕','四川大学',66688666999);

-- 种类
DROP TABLE IF EXISTS `kind`;
CREATE TABLE `kind` (
`kindID` VARCHAR(50) NOT NULL,
`kindName` VARCHAR(50) NOT NULL,
PRIMARY KEY (`kindID`)
)COLLATE='utf8_general_ci'
ENGINE=INNODB
;
insert into kind value('1','电脑');
insert into kind value('1-1','戴尔');
insert into kind value('1-2','华硕');
insert into kind value('1-1-1','14寸');
insert into kind value('1-1-2','15寸');
insert into kind value('1-1-3','台式');
insert into kind value('1-1-4','笔记本');
insert into kind value('2','手机');
insert into kind value('2-1','华为');
insert into kind value('2-2','三星');
insert into kind value('2-1-1','华为P30');
insert into kind value('2-1-2','华为P30pro');
insert into kind value('3','耳机');
insert into kind value('3-1','森海塞尔');
insert into kind value('3-2','铁三角');
insert into kind value('3-3','漫步者');
insert into kind value('4','相机');

-- 商品
DROP TABLE IF EXISTS `good`;
CREATE TABLE `good` (
`goodID` INT(11) NOT NULL,
`goodName` VARCHAR(50) NOT NULL,
`price` DECIMAL(10,2) NULL DEFAULT NULL,
`kindID` VARCHAR(50) NOT NULL,
`description` TEXT NULL DEFAULT NULL,
`picture` BLOB NULL DEFAULT NULL,
PRIMARY KEY (`goodID`),
INDEX `foreign key` (`kindID`),
CONSTRAINT `foreign key` FOREIGN KEY (`kindID`) REFERENCES `kind` (`kindID`)
)COLLATE='utf8_general_ci'
ENGINE=INNODB
;
insert into good value(1,'戴尔电脑',4000.00,'1-1-4',null,null);
insert into good value(2,'戴尔14寸笔记本',3500.00,'1-1-1', '超轻便商务本',null);
insert into good value(3,'华为P30128G',5000.00,'2-1-1',null,null);
insert into good value(4,'华为P30128G',5000.00,'2-1-1','抢购中',null);

-- 收藏
DROP TABLE IF EXISTS `collection`;
CREATE TABLE `collection` (
`goodID` INT(11) NOT NULL,
`userID` INT(11) NOT NULL,
PRIMARY KEY (`goodID`, `userID`),
INDEX `FK_collection_user` (`userID`),
CONSTRAINT `FK_collection_good` FOREIGN KEY (`goodID`) REFERENCES `good` (`goodID`),
CONSTRAINT `FK_collection_user` FOREIGN KEY (`userID`) REFERENCES `user` (`userID`)
)COLLATE='utf8_general_ci'
ENGINE=INNODB
;
insert into collection value(1,1);
insert into collection value(2,1);
insert into collection value(3,1);
insert into collection value(1,2);

-- 购物车
DROP TABLE IF EXISTS `shoppingcart`;
CREATE TABLE `shoppingcart` (
`goodID` INT(11) NOT NULL,
`userID` INT(11) NOT NULL,
`goodnum` INT(11) NOT NULL,
PRIMARY KEY (`goodID`, `userID`),
INDEX `FK_shoppingcart_user` (`userID`),
CONSTRAINT `FK_shoppingcart_good` FOREIGN KEY (`goodID`) REFERENCES `good` (`goodID`),
CONSTRAINT `FK_shoppingcart_user` FOREIGN KEY (`userID`) REFERENCES `user` (`userID`)
)COLLATE='utf8_general_ci'
ENGINE=INNODB
;
insert into shoppingcart value(1,1,2);
insert into shoppingcart value(1,2,1);
insert into shoppingcart value(2,1,1);

-- 订单
DROP TABLE IF EXISTS `orders`;
CREATE TABLE `orders` (
`orderID` INT(11) NOT NULL,
`goodID` INT(11) NOT NULL,
`goodNumber` INT(11) NOT NULL,
`totalPrice` DECIMAL(10,2) NULL DEFAULT NULL,
`userID` INT(11) NOT NULL,
`ordertime` TIMESTAMP NOT NULL DEFAULT CURRENT_TIMESTAMP,
`sendtime` TIMESTAMP NULL,
`receivetime` TIMESTAMP NULL,
`state` VARCHAR(40) NULL DEFAULT NULL,
`name` VARCHAR(50) NOT NULL,
`address` VARCHAR(100) NOT NULL,
`phonenum` VARCHAR(11) NOT NULL,
PRIMARY KEY (`orderID`),
INDEX `FK_orders_good` (`goodID`),
INDEX `FK_orders_address` (`userID`, `name`, `address`, `phonenum`),
CONSTRAINT `FK_orders_address` FOREIGN KEY (`userID`, `name`, `address`, `phonenum`) REFERENCES `address` (`userID`, `name`, `address`, `phonenum`),
CONSTRAINT `FK_orders_good` FOREIGN KEY (`goodID`) REFERENCES `good` (`goodID`)
)COLLATE='utf8_general_ci'
ENGINE=INNODB
;
insert into orders value(1,1,1,4000.00,1,null,null,null,'待发货','习大大','北京天安门',66666666666);
insert into orders value(2,2,1,3500.00,1,null,null,null,'已发货','习大大','北京天安门',66666666666);
insert into orders value(3,1,2,8000.00,1,null,null,null,'待评价','习大大','北京天安门',66666666666);
insert into orders value(4,1,2,8000.00,2,null,null,null,'已评价','彭麻麻','北京天安门',66886666688);

-- 评价
DROP TABLE IF EXISTS `evaluate`;
CREATE TABLE `evaluate` (
`evaluation` TEXT NULL DEFAULT NULL,
`stars` INT(11) NOT NULL,
`orderID` INT(11) NOT NULL,
`goodID` INT(11) NOT NULL,
PRIMARY KEY (`orderID`)
)COLLATE='utf8_general_ci'
ENGINE=INNODB
;
insert into evaluate value('太好了',4,4,1);