CREATE DATABASE IF NOT EXISTS travel_agency
DEFAULT CHARSET = utf8;
USE travel_agency;
CREATE TABLE `user` (
  `id`            BIGINT    NOT NULL AUTO_INCREMENT,
  `first_name`    CHAR(100) NOT NULL,
  `last_name`     CHAR(100) NOT NULL,
  `email`         CHAR(100) NOT NULL UNIQUE,
  `bday`          DATE,
  `discount`      DOUBLE    NOT NULL DEFAULT '0',
  `orders_amount` INT       NOT NULL DEFAULT '0',
  PRIMARY KEY (`id`)
);

CREATE TABLE `role` (
  `id`   BIGINT   NOT NULL AUTO_INCREMENT,
  `type` CHAR(30) NOT NULL UNIQUE,
  PRIMARY KEY (`id`)
);

CREATE TABLE `user_2_role` (
  `user_id` BIGINT NOT NULL,
  `role_id` BIGINT NOT NULL
);

CREATE TABLE `tour` (
  `id`         BIGINT    NOT NULL AUTO_INCREMENT,
  `title`       CHAR(150) NOT NULL,
  `photo_link` CHAR(255),
  `hot`        TINYINT   NOT NULL DEFAULT '0',
  `type_id`    BIGINT,
  PRIMARY KEY (`id`)
);

CREATE TABLE `tour_type` (
  `id`   BIGINT    NOT NULL AUTO_INCREMENT,
  `type` CHAR(100) NOT NULL UNIQUE,
  PRIMARY KEY (`id`)
);

CREATE TABLE `user_2_tour` (
  `user_id` BIGINT NOT NULL,
  `tour_id` BIGINT NOT NULL
);

CREATE TABLE `tour_description` (
  `id`                BIGINT NOT NULL,
  `full_description`  TEXT,
  `short_description` CHAR(255),
  PRIMARY KEY (`id`)
);

ALTER TABLE `user_2_role`
  ADD CONSTRAINT `user_2_role_fk0` FOREIGN KEY (`user_id`) REFERENCES `user` (`id`);

ALTER TABLE `user_2_role`
  ADD CONSTRAINT `user_2_role_fk1` FOREIGN KEY (`role_id`) REFERENCES `role` (`id`);

ALTER TABLE `tour`
  ADD CONSTRAINT `tour_fk0` FOREIGN KEY (`type_id`) REFERENCES `tour_type` (`id`);

ALTER TABLE `user_2_tour`
  ADD CONSTRAINT `user_2_tour_fk0` FOREIGN KEY (`user_id`) REFERENCES `user` (`id`);

ALTER TABLE `user_2_tour`
  ADD CONSTRAINT `user_2_tour_fk1` FOREIGN KEY (`tour_id`) REFERENCES `tour` (`id`);

ALTER TABLE `tour_description`
  ADD CONSTRAINT `tour_description_fk0` FOREIGN KEY (`id`) REFERENCES `tour` (`id`);

