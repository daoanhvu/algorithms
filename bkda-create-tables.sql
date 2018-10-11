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
  `username` varchar(255) NOT NULL,
  `avatar` bigint(20) DEFAULT NULL,
  PRIMARY KEY (`objectid`),
  KEY `FKj6se8u1csnk6atnf6yhejswfu` (`avatar`),
  KEY `FK_rlaxb75n1kmvr0i3kxgrkmdte` (`objecttypeid`),
  CONSTRAINT `FK_rlaxb75n1kmvr0i3kxgrkmdte` FOREIGN KEY (`objecttypeid`) REFERENCES `entitytypes` (`typeid`),
  CONSTRAINT `FKj6se8u1csnk6atnf6yhejswfu` FOREIGN KEY (`avatar`) REFERENCES `medias` (`mediaid`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8;
