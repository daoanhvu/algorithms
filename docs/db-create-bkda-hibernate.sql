CREATE DATABASE `bkda` /*!40100 DEFAULT CHARACTER SET utf8 */;


CREATE TABLE `comments` (
  `commentid` bigint(20) NOT NULL,
  `content` varchar(255) DEFAULT NULL,
  `_datetime` varchar(19) DEFAULT NULL,
  `userid` bigint(20) DEFAULT NULL,
  PRIMARY KEY (`commentid`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8;

CREATE TABLE `companies` (
  `objectid` bigint(20) NOT NULL,
  `name` varchar(255) DEFAULT NULL,
  `objecttypeid` bigint(20) DEFAULT NULL,
  `address` varchar(255) DEFAULT NULL,
  `fax` varchar(255) DEFAULT NULL,
  `phone1` varchar(255) DEFAULT NULL,
  `phone2` varchar(255) DEFAULT NULL,
  `taxnumber` varchar(255) DEFAULT NULL,
  PRIMARY KEY (`objectid`),
  KEY `FK_8q6q9j8jn2e6r3wxvmvdkt7q` (`objecttypeid`),
  CONSTRAINT `FK_8q6q9j8jn2e6r3wxvmvdkt7q` FOREIGN KEY (`objecttypeid`) REFERENCES `entitytypes` (`typeid`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8;

CREATE TABLE `entitytypes` (
  `typeid` bigint(20) NOT NULL,
  `name` varchar(255) DEFAULT NULL,
  PRIMARY KEY (`typeid`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8;

CREATE TABLE `genericobjects` (
  `objectid` bigint(20) NOT NULL,
  `name` varchar(255) DEFAULT NULL,
  `objecttypeid` bigint(20) DEFAULT NULL,
  PRIMARY KEY (`objectid`),
  KEY `FKkkce0h95qkucgwhxgfr00q5tr` (`objecttypeid`),
  CONSTRAINT `FKkkce0h95qkucgwhxgfr00q5tr` FOREIGN KEY (`objecttypeid`) REFERENCES `entitytypes` (`typeid`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8;

CREATE TABLE `hibernate_sequence` (
  `next_val` bigint(20) DEFAULT NULL
) ENGINE=InnoDB DEFAULT CHARSET=utf8;

CREATE TABLE `media_objects` (
  `mediaid` int(11) NOT NULL AUTO_INCREMENT,
  `ownertype` int(11) DEFAULT NULL,
  `filename` varchar(30) DEFAULT NULL,
  `category` int(11) DEFAULT NULL,
  `path` varchar(256) DEFAULT NULL,
  `extension` varchar(16) DEFAULT NULL,
  `type` smallint(6) DEFAULT NULL,
  `status` smallint(6) DEFAULT NULL,
  PRIMARY KEY (`mediaid`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8;

CREATE TABLE `medias` (
  `mediaid` bigint(20) NOT NULL,
  `extension` varchar(255) DEFAULT NULL,
  `filename` varchar(255) DEFAULT NULL,
  `path` varchar(255) DEFAULT NULL,
  `type` int(11) DEFAULT NULL,
  `userid` bigint(20) DEFAULT NULL,
  PRIMARY KEY (`mediaid`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8;

CREATE TABLE `posts` (
  `postid` bigint(20) NOT NULL,
  `content` varchar(255) DEFAULT NULL,
  `_datetime` varchar(19) DEFAULT NULL,
  `userid` bigint(20) DEFAULT NULL,
  PRIMARY KEY (`postid`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8;

CREATE TABLE `product_categories` (
  `prodcatid` int(11) NOT NULL AUTO_INCREMENT,
  `name` varchar(30) DEFAULT NULL,
  PRIMARY KEY (`prodcatid`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8;

CREATE TABLE `product_category` (
  `prodcatid` int(11) NOT NULL,
  `name` varchar(255) DEFAULT NULL,
  PRIMARY KEY (`prodcatid`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8;

CREATE TABLE `products` (
  `prodid` int(11) NOT NULL,
  `description` varchar(255) DEFAULT NULL,
  `instock` int(11) DEFAULT NULL,
  `price` int(11) DEFAULT NULL,
  `category` int(11) DEFAULT NULL,
  `company` bigint(20) DEFAULT NULL,
  PRIMARY KEY (`prodid`),
  KEY `FKhmyu8handxv3odspi8hki0et6` (`category`),
  KEY `FKsoaqbktraap7168jgei2j3shj` (`company`),
  CONSTRAINT `FKhmyu8handxv3odspi8hki0et6` FOREIGN KEY (`category`) REFERENCES `product_category` (`prodcatid`),
  CONSTRAINT `FKsoaqbktraap7168jgei2j3shj` FOREIGN KEY (`company`) REFERENCES `companies` (`objectid`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8;

CREATE TABLE `property` (
  `propertyid` bigint(20) NOT NULL,
  `name` varchar(255) DEFAULT NULL,
  `value` varchar(255) DEFAULT NULL,
  `owner` bigint(20) DEFAULT NULL,
  `type` varchar(255) DEFAULT NULL,
  `objectid` bigint(20) DEFAULT NULL,
  PRIMARY KEY (`propertyid`),
  KEY `FK88sma3iqnw7wxni6x5xx57gtf` (`type`),
  CONSTRAINT `FK88sma3iqnw7wxni6x5xx57gtf` FOREIGN KEY (`type`) REFERENCES `propertytypes` (`name`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8;

CREATE TABLE `propertytypes` (
  `name` varchar(255) NOT NULL,
  `propertytypes` bigint(20) DEFAULT NULL,
  PRIMARY KEY (`name`),
  KEY `FK7alh3hairq7vhypwbsnqmlj2x` (`propertytypes`),
  CONSTRAINT `FK7alh3hairq7vhypwbsnqmlj2x` FOREIGN KEY (`propertytypes`) REFERENCES `entitytypes` (`typeid`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8;

CREATE TABLE `staffs` (
  `objectid` bigint(20) NOT NULL,
  `name` varchar(255) DEFAULT NULL,
  `objecttypeid` bigint(20) DEFAULT NULL,
  `email` varchar(255) DEFAULT NULL,
  `firstname` varchar(255) DEFAULT NULL,
  `lastname` varchar(255) DEFAULT NULL,
  `phonenumber` varchar(255) DEFAULT NULL,
  `sex` char(1) DEFAULT NULL,
  `startdate` varchar(19) DEFAULT NULL,
  `status` int(11) DEFAULT NULL,
  `username` varchar(255) DEFAULT NULL,
  `avatar` bigint(20) DEFAULT NULL,
  `startworkingdate` varchar(255) DEFAULT NULL,
  `title` varchar(255) DEFAULT NULL,
  `company` bigint(20) DEFAULT NULL,
  PRIMARY KEY (`objectid`),
  KEY `FK62f9sa5ptd9sc5bs9wbaijneg` (`company`),
  KEY `FK_pwj4a0li620yl26whqk2qikr6` (`avatar`),
  KEY `FK_b0k8729by8exwi8wf4ijym1xc` (`objecttypeid`),
  CONSTRAINT `FK62f9sa5ptd9sc5bs9wbaijneg` FOREIGN KEY (`company`) REFERENCES `companies` (`objectid`),
  CONSTRAINT `FK_b0k8729by8exwi8wf4ijym1xc` FOREIGN KEY (`objecttypeid`) REFERENCES `entitytypes` (`typeid`),
  CONSTRAINT `FK_pwj4a0li620yl26whqk2qikr6` FOREIGN KEY (`avatar`) REFERENCES `medias` (`mediaid`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8;

CREATE TABLE `userlogs` (
  `userlogid` bigint(20) NOT NULL,
  `_datetime` varchar(16) DEFAULT NULL,
  `description` varchar(255) DEFAULT NULL,
  `logtype` smallint(6) DEFAULT NULL,
  `userid` bigint(20) DEFAULT NULL,
  PRIMARY KEY (`userlogid`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8;

CREATE TABLE `usernetworks` (
  `owner` bigint(20) NOT NULL,
  `member` bigint(20) NOT NULL,
  PRIMARY KEY (`owner`,`member`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8;

CREATE TABLE `users` (
  `objectid` bigint(20) NOT NULL,
  `name` varchar(255) DEFAULT NULL,
  `objecttypeid` bigint(20) DEFAULT NULL,
  `email` varchar(255) DEFAULT NULL,
  `firstname` varchar(255) DEFAULT NULL,
  `lastname` varchar(255) DEFAULT NULL,
  `phonenumber` varchar(255) DEFAULT NULL,
  `sex` char(1) DEFAULT NULL,
  `startdate` varchar(19) DEFAULT NULL,
  `status` int(11) DEFAULT NULL,
  `username` varchar(255) DEFAULT NULL,
  `avatar` bigint(20) DEFAULT NULL,
  PRIMARY KEY (`objectid`),
  KEY `FKj6se8u1csnk6atnf6yhejswfu` (`avatar`),
  KEY `FK_rlaxb75n1kmvr0i3kxgrkmdte` (`objecttypeid`),
  CONSTRAINT `FK_rlaxb75n1kmvr0i3kxgrkmdte` FOREIGN KEY (`objecttypeid`) REFERENCES `entitytypes` (`typeid`),
  CONSTRAINT `FKj6se8u1csnk6atnf6yhejswfu` FOREIGN KEY (`avatar`) REFERENCES `medias` (`mediaid`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8;
