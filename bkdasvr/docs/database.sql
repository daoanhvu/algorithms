CREATE SCHEMA `bkda` DEFAULT CHARACTER SET utf8 ;
CREATE SCHEMA `authbkda` DEFAULT CHARACTER SET utf8 ;

CREATE TABLE `users` (
  `userid` int(11) NOT NULL AUTO_INCREMENT,
  `username` varchar(32) NOT NULL,
  `facebookid` varchar(32) default null,
  `googleid` varchar(32) default null,
  `password` varbinary(256) DEFAULT NULL,
  `email` varchar(32) DEFAULT NULL,
  `verified` boolean default false,
  `status` smallint(1) DEFAULT '1',
  PRIMARY KEY (`userid`),
  UNIQUE KEY `idUSERS_UNIQUE` (`userid`),
  UNIQUE KEY `username_UNIQUE` (`username`)
) ENGINE=InnoDB AUTO_INCREMENT=3 DEFAULT CHARSET=utf8;


INSERT INTO `users`(
  `username`,
  `facebookid`,
  `googleid`,
  `password`,
  `email`,
  `verified`,
  `status`
  
) VALUES('admin', 'fbadmin', 'gladmih',
	'$2a$10$D4OLKI6yy68crm.3imC9X.P2xqKHs5TloWUcr6z5XdOqnTrAK84ri',
    'admin@bkda.com.vn',
    true, 1);
    
INSERT INTO `users`(
  `username`,
  `facebookid`,
  `googleid`,
  `password`,
  `email`,
  `verified`,
  `status`
  
) VALUES('davu', 'vudaoanh', 'daoanhvu122',
	'$2a$10$D4OLKI6yy68crm.3imC9X.P2xqKHs5TloWUcr6z5XdOqnTrAK84ri',
    'daoanhvu122@bkda.com.vn',
    true, 1);


