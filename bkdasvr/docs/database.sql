CREATE DATABASE `bkda` /*!40100 DEFAULT CHARACTER SET utf8 */;
CREATE TABLE `user` (
  `userid` int(11) NOT NULL AUTO_INCREMENT,
  `username` varchar(32) NOT NULL,
  `facebookid` varchar(32) default null,
  `googleid` varchar(32) default null,
  `password` varbinary(64) DEFAULT NULL,
  `firstname` varchar(64) DEFAULT NULL,
  `lastname` varchar(64) DEFAULT NULL,
  `email` varchar(32) DEFAULT NULL,
  `verified` boolean default false,
  `scope` varchar(1024) default NULL,
  `country` varchar(8) default null,
  `gender` smallint(1) DEFAULT NULL,
  `startdate` datetime DEFAULT NULL,
  `status` smallint(1) DEFAULT '1',
  PRIMARY KEY (`userid`),
  UNIQUE KEY `idUSERS_UNIQUE` (`userid`),
  UNIQUE KEY `username_UNIQUE` (`username`)
) ENGINE=InnoDB AUTO_INCREMENT=3 DEFAULT CHARSET=utf8;

CREATE TABLE `country` (
  `countrycode` varchar(8) NOT NULL,
  `name` varchar(128) NOT NULL,
  `postcode` varchar(16) default null,
  PRIMARY KEY (`countrycode`),
  UNIQUE KEY `countrycode_UNIQUE` (`countrycode`)
) ENGINE=InnoDB AUTO_INCREMENT=3 DEFAULT CHARSET=utf8;

CREATE TABLE `groups` (
  `groupid` int(11) NOT NULL AUTO_INCREMENT,
  `groupname` varchar(128) NOT NULL,
  `description` varchar(4000) DEFAULT NULL,
  `status` smallint(1) NOT NULL DEFAULT '0',
  PRIMARY KEY (`groupid`),
  UNIQUE KEY `groupid_UNIQUE` (`groupid`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8;

CREATE TABLE `userlogs` (
  `logid` bigint(8) NOT NULL,
  `userid` int(11) NOT NULL,
  `createddate` datetime NOT NULL,
  `actiontype` int(11) NOT NULL DEFAULT '0',
  `latitude` float DEFAULT NULL,
  `longitude` float DEFAULT NULL,
  `ipaddress` varchar(16) default NULL,
  `haslocation` tinyint(1) NOT NULL DEFAULT '0',
  PRIMARY KEY (`logid`),
  UNIQUE KEY `logid_UNIQUE` (`logid`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8;

CREATE TABLE `product_category` (
  `categoryid` int(11) NOT NULL AUTO_INCREMENT,
  `name` varchar(45) NOT NULL DEFAULT '0',
  PRIMARY KEY (`categoryid`),
  UNIQUE KEY `catid_UNIQUE` (`categoryid`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8;

CREATE TABLE `comments` (
  `commentid` int(11) NOT NULL AUTO_INCREMENT,
  `content` varchar(256) NOT NULL DEFAULT '0',
  `status` smallint(1) NOT NULL DEFAULT '0',
  `createddate` datetime NOT NULL,
  PRIMARY KEY (`commentid`),
  UNIQUE KEY `comentid_UNIQUE` (`commentid`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8;

CREATE TABLE `media` (
  `mediaid` int(11) NOT NULL AUTO_INCREMENT,
  `userid` int(11) NOT NULL,
  `parentid` int(11) NOT NULL,
  `extension` varchar(8),
  `mtype` smallint(1) NOT NULL DEFAULT '0',
  `filepath` varchar(128) NOT NULL DEFAULT '0',
  `status` smallint(1) NOT NULL DEFAULT '0',
  PRIMARY KEY (`mediaid`),
  UNIQUE KEY `media_UNIQUE` (`mediaid`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8;

/*/////////////////////////////////////////////////////////////////////////////*/

INSERT INTO `oauth_client_details` (
  `client_id`,
  `resource_ids`,
  `client_secret`,
  `scope` 		,
  `authorized_grant_types`,
  `web_server_redirect_uri`,
  `authorities` 			,
  `access_token_validity` 	,
  `refresh_token_validity` 	,
  `autoapprove` 			
) VALUES('my-trusted-client',
			'',
            'myplacesecret',
            'read,write,trust',
            'password,authorization_code,refresh_token,client_credentials,implicit',
            '',
			'ROLE_CLIENT,ROLE_TRUSTED_CLIENT',
            480,
            600,
            '.*');

INSERT INTO oauth_users(username, password, email)
VALUES('admin','$2a$10$D4OLKI6yy68crm.3imC9X.P2xqKHs5TloWUcr6z5XdOqnTrAK84ri',
 'daoanhvu122@gmail.com');