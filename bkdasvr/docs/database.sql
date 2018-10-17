CREATE SCHEMA `bkda` DEFAULT CHARACTER SET utf8 ;

create table genericobjects (
        objectid bigint not null,
        name varchar(255),
        objecttypeid bigint,
        primary key (objectid)
    );
    
create table property (
        propertyid bigint not null,
        name varchar(255),
        value varchar(255),
        owner bigint,
        type varchar(255),
        objectid bigint,
        primary key (propertyid)
    );
    
create table propertytypes (
        name varchar(255) not null,
        propertytypes bigint,
        primary key (name)
    );
    
create table entitytypes (
        typeid bigint not null,
        name varchar(255),
        primary key (typeid)
    );
    
create table users (
        objectid bigint not null,
        name varchar(255),
        objecttypeid bigint,
        email varchar(255),
        firstname varchar(255),
        lastname varchar(255),
        password varchar(255),
        phonenumber varchar(255),
        sex char(255),
        startdate timestamp,
        status integer,
        username varchar(255),
        avatar bigint,
        primary key (objectid)
    );    
    
create table staffs (
        objectid bigint not null,
        name varchar(255),
        objecttypeid bigint,
        email varchar(255),
        firstname varchar(255),
        lastname varchar(255),
        password varchar(255),
        phonenumber varchar(255),
        sex char(255),
        startdate timestamp,
        status integer,
        username varchar(255),
        avatar bigint,
        startworkingdate timestamp,
        title varchar(255),
        company bigint,
        primary key (objectid)
    );
/*======================================================================================================*/
CREATE TABLE `users` (
  `userid` int(11) NOT NULL AUTO_INCREMENT,
  `username` varchar(32) NOT NULL,
  `facebookid` varchar(32) default null,
  `googleid` varchar(32) default null,create table users (
        objectid bigint not null,
        name varchar(255),
        objecttypeid bigint,
        email varchar(255),
        firstname varchar(255),
        lastname varchar(255),
        password varchar(255),
        phonenumber varchar(255),
        sex char(255),
        startdate timestamp,
        status integer,
        username varchar(255),
        avatar bigint,
        primary key (objectid)
    )
    
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


