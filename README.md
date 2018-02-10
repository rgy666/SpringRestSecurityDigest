# REST web services using Digest authentication

### The client credentials are:
Username: admin
Password: 1234

### To create DB in MySQL copy paste the following code:

CREATE TABLE `messenger`.`messages` (
  `messageID` INT NOT NULL AUTO_INCREMENT COMMENT '',
  `Message` VARCHAR(45) NULL COMMENT '',
  `Author` VARCHAR(45) NULL COMMENT '',
  PRIMARY KEY (`messageID`)  COMMENT '',
  UNIQUE INDEX `messageID_UNIQUE` (`messageID` ASC)  COMMENT '');


### URL 
To access resource: 
http://localhost:8080/SpringRestSecurityDigest/api/messages/