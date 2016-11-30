CREATE DATABASE IF NOT EXISTS `travel_agency`;
USE travel_agency;
CREATE TABLE `user` (
  `id`       BIGINT    NOT NULL AUTO_INCREMENT,
  `email`    CHAR(100) NOT NULL UNIQUE,
  `password` CHAR(100) NOT NULL,
  `role_id`  BIGINT    NOT NULL,
  PRIMARY KEY (`id`)
);

CREATE TABLE `role` (
  `id`   BIGINT   NOT NULL AUTO_INCREMENT,
  `type` CHAR(30) NOT NULL UNIQUE,
  PRIMARY KEY (`id`)
);

CREATE TABLE `tour` (
  `id`          BIGINT    NOT NULL AUTO_INCREMENT,
  `title`       CHAR(150) NOT NULL,
  `photo_link`  CHAR(255),
  `is_hot`      TINYINT   NOT NULL DEFAULT '0',
  `price`       DOUBLE    NOT NULL,
  `description` TEXT,
  PRIMARY KEY (`id`)
);

CREATE TABLE `category` (
  `id`   BIGINT    NOT NULL AUTO_INCREMENT,
  `type` CHAR(100) NOT NULL UNIQUE,
  PRIMARY KEY (`id`)
);

CREATE TABLE `booking` (
  `id`         BIGINT  NOT NULL AUTO_INCREMENT,
  `order_date` DATE    NOT NULL,
  `confirmed`  TINYINT NOT NULL DEFAULT '0',
  `user_id`    BIGINT  NOT NULL,
  `tour_id`    BIGINT  NOT NULL,
  PRIMARY KEY (`id`)
);

CREATE TABLE `user_details` (
  `id`         BIGINT    NOT NULL,
  `first_name` CHAR(100) NOT NULL,
  `last_name`  CHAR(150) NOT NULL,
  `discount`   DOUBLE    NOT NULL DEFAULT '0',
  `bdate`      DATE,
  `phone`      CHAR(25),
  `skype`      CHAR(100),
  PRIMARY KEY (`id`)
);

CREATE TABLE `tour_2_category` (
  `tour_id`     BIGINT NOT NULL,
  `category_id` BIGINT NOT NULL
);

ALTER TABLE `user`
  ADD CONSTRAINT `user_fk0` FOREIGN KEY (`role_id`) REFERENCES `role` (`id`);

ALTER TABLE `booking`
  ADD CONSTRAINT `booking_fk0` FOREIGN KEY (`user_id`) REFERENCES `user` (`id`);

ALTER TABLE `booking`
  ADD CONSTRAINT `booking_fk1` FOREIGN KEY (`tour_id`) REFERENCES `tour` (`id`);

ALTER TABLE `user_details`
  ADD CONSTRAINT `user_details_fk0` FOREIGN KEY (`id`) REFERENCES `user` (`id`);

ALTER TABLE `tour_2_category`
  ADD CONSTRAINT `tour_2_category_fk0` FOREIGN KEY (`tour_id`) REFERENCES `tour` (`id`);

ALTER TABLE `tour_2_category`
  ADD CONSTRAINT `tour_2_category_fk1` FOREIGN KEY (category_id) REFERENCES `category` (`id`);

