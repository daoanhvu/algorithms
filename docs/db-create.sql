CREATE TABLE users (
  userid INT(11) NOT NULL AUTO_INCREMENT PRIMARY KEY,
  username VARCHAR(30) NOT NULL,
  firstname VARCHAR(32),
  lastname VARCHAR(32),
  phonenumber VARCHAR(24),
  email  VARCHAR(50),
  sex VARCHAR(1),
  company INTEGER NULL,
  startdate VARCHAR(16) /*start date format: YYYY-MM-DD HH:mm*/
)ENGINE=InnoDB DEFAULT CHARSET=utf8;

CREATE TABLE companies (
  companyid INT(11) NOT NULL AUTO_INCREMENT PRIMARY KEY,
  name VARCHAR(30),
  phone1  VARCHAR(50),
  phone2  VARCHAR(50),
  fax  VARCHAR(50),
  address VARCHAR(128),
  taxnumber VARCHAR(100)
)ENGINE=InnoDB DEFAULT CHARSET=utf8;

CREATE TABLE staffs (
  userid INT(11) PRIMARY KEY,
  email  VARCHAR(50),
  company INTEGER NULL,
  title VARCHAR(48),
  startworkingdate VARCHAR(16) /*start date format: YYYY-MM-DD HH:mm*/
)ENGINE=InnoDB DEFAULT CHARSET=utf8;

CREATE TABLE usernetworks (
  `owner` INTEGER,
  member INTEGER,
  joindate VARCHAR(16) /*start date format: YYYY-MM-DD HH:mm*/
)ENGINE=InnoDB DEFAULT CHARSET=utf8;

CREATE TABLE products (
  prodid INT(11) NOT NULL AUTO_INCREMENT PRIMARY KEY,
  `name` VARCHAR(30),
  category integer,
  description  VARCHAR(256),
  company  integer,
  photo1 varchar(64),
  photo2 varchar(64)
)ENGINE=InnoDB DEFAULT CHARSET=utf8;
