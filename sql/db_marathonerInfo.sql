/*
SQLyog Ultimate v13.1.1 (64 bit)
MySQL - 8.0.19 : Database - db_marathonerinfo
*********************************************************************
*/

/*!40101 SET NAMES utf8 */;

/*!40101 SET SQL_MODE=''*/;

/*!40014 SET @OLD_UNIQUE_CHECKS=@@UNIQUE_CHECKS, UNIQUE_CHECKS=0 */;
/*!40014 SET @OLD_FOREIGN_KEY_CHECKS=@@FOREIGN_KEY_CHECKS, FOREIGN_KEY_CHECKS=0 */;
/*!40101 SET @OLD_SQL_MODE=@@SQL_MODE, SQL_MODE='NO_AUTO_VALUE_ON_ZERO' */;
/*!40111 SET @OLD_SQL_NOTES=@@SQL_NOTES, SQL_NOTES=0 */;
CREATE DATABASE /*!32312 IF NOT EXISTS*/`db_marathonerinfo` /*!40100 DEFAULT CHARACTER SET utf8mb4 COLLATE utf8mb4_0900_ai_ci */ /*!80016 DEFAULT ENCRYPTION='N' */;

USE `db_marathonerinfo`;

/*Table structure for table `t_admininfo` */

DROP TABLE IF EXISTS `t_admininfo`;

CREATE TABLE `t_admininfo` (
  `adminId` int NOT NULL AUTO_INCREMENT,
  `jobNo` varchar(20) DEFAULT NULL,
  `name` varchar(20) DEFAULT NULL,
  `sex` varchar(20) DEFAULT NULL,
  `birthday` date DEFAULT NULL,
  `userId` int DEFAULT NULL,
  `email` varchar(20) CHARACTER SET utf8mb4 COLLATE utf8mb4_0900_ai_ci DEFAULT NULL,
  `synopses` varchar(1000) DEFAULT NULL,
  PRIMARY KEY (`adminId`),
  KEY `adminId` (`userId`),
  CONSTRAINT `t_admininfo_ibfk_1` FOREIGN KEY (`userId`) REFERENCES `t_user` (`id`)
) ENGINE=InnoDB AUTO_INCREMENT=69 DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_0900_ai_ci;

/*Data for the table `t_admininfo` */

insert  into `t_admininfo`(`adminId`,`jobNo`,`name`,`sex`,`birthday`,`userId`,`email`,`synopses`) values 
(1,'2200002','博子','男','2002-11-22',1,'2425277@gmil.com','GOOD'),
(2,'2200002','博子','男','2002-11-22',1,'2425277@gmil.com','GOOD'),
(3,'2200002','博子','男','2002-11-22',1,'2425277@gmil.com','GOOD'),
(4,'2200002','博子','女','2002-11-22',1,'2425277@gmil.com','GOOD'),
(7,'2200002','admin','女','2010-07-30',3,'2425277@gmil.com','Ordinary'),
(8,'2200002','admin3','男','2010-07-06',2,'2425277@gmil.com','Ordinary'),
(11,'2200002','admin','女','2002-11-22',2,'2425277@gmil.com','Ordinary'),
(34,'2200002','admin2','男','2002-11-22',2,'2425277@gmil.com','Ordinary'),
(35,'2200002','admin','男','2002-11-22',2,'2425277@gmil.com','Ordinary'),
(36,'2200002','admin','男','2002-11-22',2,'2425277@gmil.com','Ordinary'),
(37,'2200002','admin','男','2002-11-22',2,'2425277@gmil.com','Ordinary'),
(38,'2200002','admin','男','2002-11-22',2,'2425277@gmil.com','Ordinary'),
(42,'2200002','admin','男','2002-11-22',2,'2425277@gmil.com','Ordinary'),
(43,'2200002','admin','男','2002-11-22',2,'2425277@gmil.com','Ordinary'),
(47,'2200002','admin','男','2002-11-22',2,'2425277@gmil.com','Ordinary'),
(48,'2200002','admin','女','2002-11-22',2,'2425277@gmil.com','Ordinary'),
(49,'2200002','admin','女','2002-11-22',2,'2425277@gmil.com','Ordinary'),
(50,'2200002','admin','女','2002-11-22',2,'2425277@gmil.com','Ordinary'),
(51,NULL,NULL,NULL,NULL,NULL,NULL,NULL),
(52,'2200003','张三2','男','2022-02-15',3,'214124@qq.com',''),
(55,'dsg2','aef2','男','2022-02-02',2,'','22'),
(58,'2200013','whc2','女','2022-02-02',3,'1787@qq2.com','GOOD2'),
(60,'asdasd','asdasd','','2022-02-15',6,'',''),
(61,'122','212','女','2022-02-02',3,'',''),
(62,'2200003','wyl','','2008-12-09',6,'','吴小妮儿'),
(63,'2200005','admin2','男','2010-07-06',3,'2425277@gmil.com','Ordinary'),
(64,'1','1','女','2022-02-01',2,'',''),
(66,'2','331','男','2022-02-08',1,'',''),
(67,'12312','12312','男','2022-02-01',1,'',''),
(68,'2200001','www','男','2022-03-15',6,'','');

/*Table structure for table `t_marathonerinfo` */

DROP TABLE IF EXISTS `t_marathonerinfo`;

CREATE TABLE `t_marathonerinfo` (
  `id` int NOT NULL AUTO_INCREMENT,
  `name` varchar(20) CHARACTER SET utf8mb4 COLLATE utf8mb4_0900_ai_ci NOT NULL,
  `sex` varchar(20) DEFAULT NULL,
  `foreignName` varchar(20) DEFAULT NULL,
  `nationality` varchar(20) DEFAULT NULL,
  `birthday` varchar(20) CHARACTER SET utf8mb4 COLLATE utf8mb4_0900_ai_ci NOT NULL,
  `stature` int DEFAULT NULL,
  `weight` int DEFAULT NULL,
  `awards` varchar(1000) DEFAULT NULL,
  PRIMARY KEY (`id`)
) ENGINE=InnoDB AUTO_INCREMENT=114 DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_0900_ai_ci;

/*Data for the table `t_marathonerinfo` */

insert  into `t_marathonerinfo`(`id`,`name`,`sex`,`foreignName`,`nationality`,`birthday`,`stature`,`weight`,`awards`) values 
(11,'雷宁','男','XXX','中国','2000-08-19',172,62,'有潜力'),
(13,'XX','X','XXX','中国','XXXX-XX-XX',177,60,'xxxxxxxxxxxxx'),
(26,'大迫杰','男','Suguru Osako','日本','19910-05-23',170,53,'2小时05分50秒的成绩打破了亚洲纪录'),
(27,'埃鲁德·基普乔格','男','Eliud Kipchoge','肯尼亚','1984-11-5',170,56,'人类史上首次马拉松”破二“记录创造者'),
(31,'XXX','X','XX','美国','XXXX-XX-XX',178,60,'XXXXXXXXXXXXXXX'),
(33,'XXX2','男','XX','美国','2022-03-16',178,60,'XXXXXXXXXXXXXXX'),
(36,'XXX','X','XX','肯尼亚','XXXX-XX-XX',178,60,'XXXXXXXXXXXXXXX'),
(37,'XXX','X','XX','英国','XXXX-XX-XX',178,60,'XXXXXXXXXXXXXXX'),
(38,'XXX','X','XX','中国','XXXX-XX-XX',178,60,'XXXXXXXXXXXXXXX'),
(49,'XXX','X','XX','日本','XXXX-XX-XX',178,60,'XXXXXXXXXXXXXXX'),
(50,'XXX','X','XX','日本','XXXX-XX-XX',178,60,'XXXXXXXXXXXXXXX'),
(51,'XXX','X','XX','肯尼亚','XXXX-XX-XX',178,60,'XXXXXXXXXXXXXXX'),
(52,'XXX','X','XX','肯尼亚','XXXX-XX-XX',178,60,'XXXXXXXXXXXXXXX'),
(53,'XXX','X','XX','肯尼亚','XXXX-XX-XX',178,60,'XXXXXXXXXXXXXXX'),
(54,'XXX','X','XX','英国','XXXX-XX-XX',178,60,'XXXXXXXXXXXXXXX'),
(55,'XXX','X','XX','英国','XXXX-XX-XX',178,60,'XXXXXXXXXXXXXXX'),
(56,'XXX','X','XX','英国','XXXX-XX-XX',178,60,'XXXXXXXXXXXXXXX'),
(57,'XXX','X','XX','中国','XXXX-XX-XX',178,60,'XXXXXXXXXXXXXXX'),
(58,'XXX','X','XX','中国','XXXX-XX-XX',178,60,'XXXXXXXXXXXXXXX'),
(59,'XXX','X','XX','中国','XXXX-XX-XX',178,60,'XXXXXXXXXXXXXXX'),
(60,'XXX','X','XX','中国','XXXX-XX-XX',178,60,'XXXXXXXXXXXXXXX'),
(61,'XXX','X','XX','法国','XXXX-XX-XX',178,60,'XXXXXXXXXXXXXXX'),
(62,'XXX','X','XX','法国','XXXX-XX-XX',178,60,'XXXXXXXXXXXXXXX'),
(63,'XXX','X','XX','法国','XXXX-XX-XX',178,60,'XXXXXXXXXXXXXXX'),
(64,'XXX','X','XX','法国','XXXX-XX-XX',178,60,'XXXXXXXXXXXXXXX'),
(65,'XXX','X','XX','法国','XXXX-XX-XX',178,60,'XXXXXXXXXXXXXXX'),
(66,'XXX','X','XX','德国','XXXX-XX-XX',178,60,'XXXXXXXXXXXXXXX'),
(68,'stan',NULL,NULL,'德国','2022-2-2',NULL,NULL,NULL),
(70,'ä»¨','ç·','ss','德国','2022-02-26',221,100,''),
(71,'å¼ ä¸','å¥³','','德国','2022-02-23',173,60,'æªæ¥ä¹æ'),
(72,'阿达','男','sda','意大利','2022-02-24',176,65,'萨达所大所多'),
(74,'æååååå','ç·','as','意大利','2022-02-03',142,54,''),
(78,'阿萨德1','男','','加拿大','2022-02-03',123,543,''),
(79,'阿甘','男','Gump','美国','2022-02-02',183,81,''),
(104,'规范化','女','','加拿大','2022-02-26',324,59,''),
(109,'房规划','男','','加拿大','2022-02-26',123,45,'按时发达'),
(112,'12312','女','','加拿大','2022-02-08',123,112,''),
(113,'121','','','加拿大','2022-02-02',22,2,'1212');

/*Table structure for table `t_user` */

DROP TABLE IF EXISTS `t_user`;

CREATE TABLE `t_user` (
  `id` int NOT NULL AUTO_INCREMENT,
  `userName` varchar(20) DEFAULT NULL,
  `password` varchar(20) DEFAULT NULL,
  PRIMARY KEY (`id`)
) ENGINE=InnoDB AUTO_INCREMENT=20 DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_0900_ai_ci;

/*Data for the table `t_user` */

insert  into `t_user`(`id`,`userName`,`password`) values 
(1,'whb','wu111'),
(2,'admin','admin'),
(3,'stan','wu123456'),
(6,'www','www11122'),
(7,'190792612','w1907926120'),
(17,'www2','www1'),
(18,'asdasd','asdasd'),
(19,'wjj','wujunjie');

/*Table structure for table `t_user2` */

DROP TABLE IF EXISTS `t_user2`;

CREATE TABLE `t_user2` (
  `id` int NOT NULL AUTO_INCREMENT,
  `userName` varchar(20) DEFAULT NULL,
  `password` varchar(20) DEFAULT NULL,
  PRIMARY KEY (`id`)
) ENGINE=InnoDB AUTO_INCREMENT=12 DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_0900_ai_ci;

/*Data for the table `t_user2` */

insert  into `t_user2`(`id`,`userName`,`password`) values 
(1,'ln','ln111'),
(2,'user','user'),
(7,'user2','user222'),
(8,'whb','wu222'),
(11,'asdasd21','asdasd2');

/*Table structure for table `t_userinfo` */

DROP TABLE IF EXISTS `t_userinfo`;

CREATE TABLE `t_userinfo` (
  `userId` int NOT NULL AUTO_INCREMENT,
  `jobNo` varchar(20) DEFAULT NULL,
  `name` varchar(20) DEFAULT NULL,
  `sex` varchar(20) DEFAULT NULL,
  `birthday` date DEFAULT NULL,
  `user2Id` int DEFAULT NULL,
  `email` varchar(20) CHARACTER SET utf8mb4 COLLATE utf8mb4_0900_ai_ci DEFAULT NULL,
  `synopses` varchar(1000) DEFAULT NULL,
  PRIMARY KEY (`userId`),
  KEY `userId` (`user2Id`),
  CONSTRAINT `t_userinfo_ibfk_1` FOREIGN KEY (`user2Id`) REFERENCES `t_user2` (`id`)
) ENGINE=InnoDB AUTO_INCREMENT=40 DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_0900_ai_ci;

/*Data for the table `t_userinfo` */

insert  into `t_userinfo`(`userId`,`jobNo`,`name`,`sex`,`birthday`,`user2Id`,`email`,`synopses`) values 
(27,'2200005','user2','男','2000-04-27',2,'enmail@gmail.com','NOTHING'),
(28,'2200005','user','男','2000-04-27',2,'enmail@gmail.com','NOTHING'),
(29,'2200005','user','女','2000-04-27',2,'enmail@gmail.com','NOTHING'),
(31,'2200002','ln','男','2022-03-17',1,'1234124@qq.com','GOOD'),
(32,'2200014','wy','男','2022-02-22',2,'',''),
(33,'12312','asd2','男','2022-02-08',1,'',''),
(34,'123123','213123','男','2022-02-16',11,'','asdasdas'),
(35,'3242','234','男','2022-02-15',7,'',''),
(37,'1','2','男','2022-02-02',2,'',''),
(38,'2','3','','2022-02-09',7,'',''),
(39,'1231','1231','男','2022-02-02',1,'','');

/*!40101 SET SQL_MODE=@OLD_SQL_MODE */;
/*!40014 SET FOREIGN_KEY_CHECKS=@OLD_FOREIGN_KEY_CHECKS */;
/*!40014 SET UNIQUE_CHECKS=@OLD_UNIQUE_CHECKS */;
/*!40111 SET SQL_NOTES=@OLD_SQL_NOTES */;
