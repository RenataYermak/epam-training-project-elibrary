CREATE TABLE `USER` (
	`USER_ID` bigint NOT NULL AUTO_INCREMENT,
	`LOGIN` char NOT NULL UNIQUE,
	`PASSWORD` char NOT NULL,
	`ROLE` enum NOT NULL,
	PRIMARY KEY (`USER_ID`)
);

CREATE TABLE `ACCOUNT` (
	`ACCOUNT_ID` bigint NOT NULL AUTO_INCREMENT,
	`USER_ID` bigint NOT NULL UNIQUE,
	`ACCOUNT_STATUS` enum NOT NULL,
	`ACTIVATION_DATE` TIMESTAMP NOT NULL,
	`DEACTIVATION_DATE` TIMESTAMP NOT NULL,
	PRIMARY KEY (`ACCOUNT_ID`)
);

CREATE TABLE `BOOK` (
	`BOOK_ID` bigint NOT NULL AUTO_INCREMENT,
	`TITLE` char NOT NULL,
	`AUTHOR` char NOT NULL,
	`CATEGORY` enum NOT NULL,
	`DATE` DATE NOT NULL,
	`DESCRIPTION` TEXT NOT NULL,
	`NUMBER` int NOT NULL,
	PRIMARY KEY (`BOOK_ID`)
);

CREATE TABLE `BOOK_STATISTIC` (
	`BOOK_ID` bigint NOT NULL,
	`USER_ID` bigint NOT NULL,
	`READ` enum NOT NULL,
	`RATING` int NOT NULL,
	`REVIEW` TEXT NOT NULL
);

CREATE TABLE `BOOK_ORDER` (
	`ID` bigint NOT NULL AUTO_INCREMENT,
	`BOOK_ID` bigint NOT NULL,
	`USER_ID` bigint NOT NULL,
	`STATUS` enum NOT NULL,
	`BOOKED` enum NOT NULL,
	`ORDERED_DATE` TIMESTAMP NOT NULL,
	`BOOKED_DATE` TIMESTAMP NOT NULL,
	`RETURNED_DATE` TIMESTAMP NOT NULL,
	PRIMARY KEY (`ID`)
);

ALTER TABLE `ACCOUNT` ADD CONSTRAINT `ACCOUNT_fk0` FOREIGN KEY (`USER_ID`) REFERENCES `USER`(`USER_ID`);

ALTER TABLE `BOOK_STATISTIC` ADD CONSTRAINT `BOOK_STATISTIC_fk0` FOREIGN KEY (`BOOK_ID`) REFERENCES `BOOK`(`BOOK_ID`);

ALTER TABLE `BOOK_STATISTIC` ADD CONSTRAINT `BOOK_STATISTIC_fk1` FOREIGN KEY (`USER_ID`) REFERENCES `USER`(`USER_ID`);

ALTER TABLE `BOOK_ORDER` ADD CONSTRAINT `BOOK_ORDER_fk0` FOREIGN KEY (`BOOK_ID`) REFERENCES `BOOK`(`BOOK_ID`);

ALTER TABLE `BOOK_ORDER` ADD CONSTRAINT `BOOK_ORDER_fk1` FOREIGN KEY (`USER_ID`) REFERENCES `USER`(`USER_ID`);





