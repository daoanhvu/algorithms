
CREATE TABLE `entitytypes` (
  `typeid` bigint(20) NOT NULL PRIMARY KEY AUTO_INCREMENT,
  `name` varchar(255) DEFAULT NULL
) ENGINE=InnoDB DEFAULT CHARSET=utf8;

CREATE TABLE `genericobjects` (
  `objectid` bigint(20) NOT NULL PRIMARY KEY AUTO_INCREMENT,
  `name` varchar(255) DEFAULT NULL,
  `objecttypeid` bigint(20) DEFAULT NULL,
  KEY `FK_g_obj_type` (`objecttypeid`),
  CONSTRAINT `FK_g_obj_type` FOREIGN KEY (`objecttypeid`) REFERENCES `entitytypes` (`typeid`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8;

CREATE TABLE `users` (
  `objectid` bigint(20) NOT NULL PRIMARY KEY,
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
  KEY `FK_u_avatar` (`avatar`),
  KEY `FK_u_obj_type` (`objecttypeid`),
  CONSTRAINT `FK_u_obj_type` FOREIGN KEY (`objecttypeid`) REFERENCES `entitytypes` (`typeid`),
  CONSTRAINT `FK_u_avatar` FOREIGN KEY (`avatar`) REFERENCES `medias` (`mediaid`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8;

CREATE TABLE `usernetworks` (
  `owner` bigint(20) NOT NULL,
  `member` bigint(20) NOT NULL,
  PRIMARY KEY (`owner`,`member`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8;

CREATE TABLE `staffs` (
  `objectid` bigint(20) NOT NULL PRIMARY KEY,
  `name` varchar(255) DEFAULT NULL,
  `objecttypeid` bigint(20) DEFAULT NULL,
  `email` varchar(255) DEFAULT NULL,
  `firstname` varchar(255) DEFAULT NULL,
  `lastname` varchar(255) DEFAULT NULL,
  `phonenumber` varchar(64) DEFAULT NULL,
  `sex` char(1) DEFAULT NULL,
  `startdate` varchar(19) DEFAULT NULL,
  `status` int(11) DEFAULT NULL,
  `username` varchar(255) DEFAULT NULL,
  `avatar` bigint(20) DEFAULT NULL,
  `startworkingdate` varchar(19) DEFAULT NULL,
  `title` varchar(255) DEFAULT NULL,
  `company` bigint(20) DEFAULT NULL,
  KEY `FK_staff_company` (`company`),
  KEY `FK_staff_objid` (`avatar`),
  KEY `FK_staff_avatar` (`objecttypeid`),
  CONSTRAINT `FK_staff_company` FOREIGN KEY (`company`) REFERENCES `companies` (`objectid`),
  CONSTRAINT `FK_staff_objid` FOREIGN KEY (`objecttypeid`) REFERENCES `entitytypes` (`typeid`),
  CONSTRAINT `FK_staff_avatar` FOREIGN KEY (`avatar`) REFERENCES `medias` (`mediaid`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8;
