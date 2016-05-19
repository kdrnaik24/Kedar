-- MySQL Administrator dump 1.4
--
-- ------------------------------------------------------
-- Server version	5.6.20


/*!40101 SET @OLD_CHARACTER_SET_CLIENT=@@CHARACTER_SET_CLIENT */;
/*!40101 SET @OLD_CHARACTER_SET_RESULTS=@@CHARACTER_SET_RESULTS */;
/*!40101 SET @OLD_COLLATION_CONNECTION=@@COLLATION_CONNECTION */;
/*!40101 SET NAMES utf8 */;

/*!40014 SET @OLD_UNIQUE_CHECKS=@@UNIQUE_CHECKS, UNIQUE_CHECKS=0 */;
/*!40014 SET @OLD_FOREIGN_KEY_CHECKS=@@FOREIGN_KEY_CHECKS, FOREIGN_KEY_CHECKS=0 */;
/*!40101 SET @OLD_SQL_MODE=@@SQL_MODE, SQL_MODE='NO_AUTO_VALUE_ON_ZERO' */;


--
-- Create schema cybagenet
--

CREATE DATABASE IF NOT EXISTS cybagenet;
USE cybagenet;

--
-- Definition of table `book`
--

DROP TABLE IF EXISTS `book`;
CREATE TABLE `book` (
  `BookId` int(10) unsigned NOT NULL AUTO_INCREMENT,
  `BookName` varchar(45) NOT NULL,
  `AuthorName` varchar(45) NOT NULL,
  PRIMARY KEY (`BookId`)
) ENGINE=InnoDB AUTO_INCREMENT=8989 DEFAULT CHARSET=latin1;

--
-- Dumping data for table `book`
--

/*!40000 ALTER TABLE `book` DISABLE KEYS */;
INSERT INTO `book` (`BookId`,`BookName`,`AuthorName`) VALUES 
 (1,'Wings Of Fire','Dr. APJ Abdul Kalam'),
 (255,'Playing it my way','SRT'),
 (555,'abcd','aaaaa'),
 (866,'abcd','kkkk');
/*!40000 ALTER TABLE `book` ENABLE KEYS */;


--
-- Definition of table `login`
--

DROP TABLE IF EXISTS `login`;
CREATE TABLE `login` (
  `userId` int(10) unsigned NOT NULL AUTO_INCREMENT,
  `userName` varchar(45) NOT NULL,
  `password` varchar(45) NOT NULL,
  `type` varchar(45) NOT NULL,
  PRIMARY KEY (`userId`)
) ENGINE=InnoDB AUTO_INCREMENT=4 DEFAULT CHARSET=latin1;

--
-- Dumping data for table `login`
--

/*!40000 ALTER TABLE `login` DISABLE KEYS */;
INSERT INTO `login` (`userId`,`userName`,`password`,`type`) VALUES 
 (1,'user1','1234','normal'),
 (2,'admin','admin','admin'),
 (3,'user2','adp','normal');
/*!40000 ALTER TABLE `login` ENABLE KEYS */;


--
-- Definition of table `review`
--

DROP TABLE IF EXISTS `review`;
CREATE TABLE `review` (
  `reviewId` int(10) unsigned NOT NULL AUTO_INCREMENT,
  `description` varchar(45) NOT NULL,
  PRIMARY KEY (`reviewId`)
) ENGINE=InnoDB AUTO_INCREMENT=119 DEFAULT CHARSET=latin1;

--
-- Dumping data for table `review`
--

/*!40000 ALTER TABLE `review` DISABLE KEYS */;
INSERT INTO `review` (`reviewId`,`description`) VALUES 
 (101,'good'),
 (102,'fine'),
 (103,'nice'),
 (104,'  wowwww'),
 (105,'Inspirational  aaaaaaa'),
 (106,'  Awesome'),
 (107,'  aaaaa'),
 (108,' oh my god !!!'),
 (109,'  nice'),
 (110,'  aaaaa'),
 (111,'  niceeeeeeeeeee'),
 (112,'  ek numer'),
 (113,'  Hello'),
 (114,'  Greatttt'),
 (115,'  '),
 (116,'  '),
 (117,'  '),
 (118,'  ');
/*!40000 ALTER TABLE `review` ENABLE KEYS */;


--
-- Definition of table `session`
--

DROP TABLE IF EXISTS `session`;
CREATE TABLE `session` (
  `sessioncount` int(10) unsigned DEFAULT NULL,
  `hitcount` int(10) unsigned DEFAULT NULL
) ENGINE=InnoDB DEFAULT CHARSET=latin1;

--
-- Dumping data for table `session`
--

/*!40000 ALTER TABLE `session` DISABLE KEYS */;
INSERT INTO `session` (`sessioncount`,`hitcount`) VALUES 
 (26,1),
 (26,2),
 (26,3),
 (26,4),
 (26,5),
 (26,6),
 (26,7),
 (26,8),
 (26,9),
 (26,10),
 (26,11),
 (26,12),
 (26,13),
 (26,14),
 (26,15),
 (26,16),
 (26,17),
 (26,18),
 (26,19),
 (26,20),
 (26,21),
 (26,22),
 (26,23),
 (26,24),
 (26,25),
 (26,26),
 (26,27),
 (26,28),
 (26,29),
 (26,30),
 (26,31),
 (26,32),
 (26,33),
 (26,34),
 (26,35),
 (26,36),
 (26,37),
 (26,38),
 (26,39),
 (26,40),
 (26,41),
 (26,42),
 (26,43),
 (26,44),
 (26,45),
 (26,46),
 (26,47),
 (26,48),
 (26,49),
 (26,50),
 (26,51),
 (26,52),
 (26,53),
 (26,54),
 (26,55),
 (26,56),
 (27,57),
 (28,58),
 (29,59),
 (30,60),
 (31,61),
 (32,62),
 (33,63),
 (34,64),
 (35,65),
 (36,66),
 (37,67);
/*!40000 ALTER TABLE `session` ENABLE KEYS */;


--
-- Definition of table `user`
--

DROP TABLE IF EXISTS `user`;
CREATE TABLE `user` (
  `userId` int(10) unsigned NOT NULL AUTO_INCREMENT,
  `name` varchar(45) NOT NULL,
  `mobileNo` varchar(45) NOT NULL,
  `loginId` int(10) unsigned NOT NULL,
  PRIMARY KEY (`userId`),
  KEY `FK_user_1` (`loginId`),
  CONSTRAINT `FK_user_1` FOREIGN KEY (`loginId`) REFERENCES `login` (`userId`)
) ENGINE=InnoDB AUTO_INCREMENT=4 DEFAULT CHARSET=latin1;

--
-- Dumping data for table `user`
--

/*!40000 ALTER TABLE `user` DISABLE KEYS */;
INSERT INTO `user` (`userId`,`name`,`mobileNo`,`loginId`) VALUES 
 (1,'alpeshp','9898558899',1),
 (2,'mucool','9898558899',2),
 (3,'alpesh','9921887849',3);
/*!40000 ALTER TABLE `user` ENABLE KEYS */;


--
-- Definition of table `user_review`
--

DROP TABLE IF EXISTS `user_review`;
CREATE TABLE `user_review` (
  `id` int(10) unsigned NOT NULL AUTO_INCREMENT,
  `bookid` int(10) unsigned NOT NULL,
  `reviewid` int(10) unsigned NOT NULL,
  `userid` int(10) unsigned NOT NULL,
  PRIMARY KEY (`id`),
  KEY `FK_user_review_1` (`bookid`),
  KEY `FK_user_review_2` (`reviewid`),
  KEY `FK_user_review_3` (`userid`),
  CONSTRAINT `FK_user_review_1` FOREIGN KEY (`bookid`) REFERENCES `book` (`BookId`),
  CONSTRAINT `FK_user_review_2` FOREIGN KEY (`reviewid`) REFERENCES `review` (`reviewId`),
  CONSTRAINT `FK_user_review_3` FOREIGN KEY (`userid`) REFERENCES `user` (`userId`)
) ENGINE=InnoDB AUTO_INCREMENT=128 DEFAULT CHARSET=latin1;

--
-- Dumping data for table `user_review`
--

/*!40000 ALTER TABLE `user_review` DISABLE KEYS */;
INSERT INTO `user_review` (`id`,`bookid`,`reviewid`,`userid`) VALUES 
 (111,1,101,2),
 (112,1,102,2),
 (113,1,104,3),
 (114,1,105,1),
 (115,1,106,1),
 (116,1,107,1),
 (117,1,108,1),
 (118,1,109,1),
 (119,1,110,1),
 (120,255,111,1),
 (121,1,112,1),
 (122,255,113,1),
 (123,1,114,1),
 (124,255,115,1),
 (125,1,116,1),
 (126,255,117,1),
 (127,255,118,1);
/*!40000 ALTER TABLE `user_review` ENABLE KEYS */;




/*!40101 SET SQL_MODE=@OLD_SQL_MODE */;
/*!40014 SET FOREIGN_KEY_CHECKS=@OLD_FOREIGN_KEY_CHECKS */;
/*!40014 SET UNIQUE_CHECKS=@OLD_UNIQUE_CHECKS */;
/*!40101 SET CHARACTER_SET_CLIENT=@OLD_CHARACTER_SET_CLIENT */;
/*!40101 SET CHARACTER_SET_RESULTS=@OLD_CHARACTER_SET_RESULTS */;
/*!40101 SET COLLATION_CONNECTION=@OLD_COLLATION_CONNECTION */;
/*!40101 SET CHARACTER_SET_CLIENT=@OLD_CHARACTER_SET_CLIENT */;
