CREATE TABLE `user` (
  `id` bigint NOT NULL AUTO_INCREMENT,
  `email` char(100) NOT NULL UNIQUE,
  `password` char(100) NOT NULL,
  `role_id` bigint NOT NULL,
  PRIMARY KEY (`id`)
);

CREATE TABLE `role` (
  `id` bigint NOT NULL AUTO_INCREMENT,
  `type` char(30) NOT NULL UNIQUE,
  PRIMARY KEY (`id`)
);

CREATE TABLE `tour` (
  `id` bigint NOT NULL AUTO_INCREMENT,
  `title` char(150) NOT NULL,
  `photo_link` char(256),
  `hot` tinyint NOT NULL DEFAULT '0',
  `price` double NOT NULL,
  `description` TEXT,
  PRIMARY KEY (`id`)
);

CREATE TABLE `category` (
  `id` bigint NOT NULL AUTO_INCREMENT,
  `type` char(100) NOT NULL UNIQUE,
  PRIMARY KEY (`id`)
);

CREATE TABLE `order` (
  `id` bigint NOT NULL AUTO_INCREMENT,
  `order_date` DATE NOT NULL,
  `confirmed` tinyint NOT NULL DEFAULT '0',
  `user_id` bigint NOT NULL,
  `tour_id` bigint NOT NULL,
  PRIMARY KEY (`id`)
);

CREATE TABLE `user_details` (
  `id` bigint NOT NULL,
  `first_name` char(100) NOT NULL,
  `last_name` char(150) NOT NULL,
  `discount` double NOT NULL DEFAULT '0',
  `bdate` DATE,
  `phone` char(25),
  `skype` char(100),
  PRIMARY KEY (`id`)
);

CREATE TABLE `tour_2_category` (
  `tour_id` bigint NOT NULL,
  `categoty_id` bigint NOT NULL
);

ALTER TABLE `user` ADD CONSTRAINT `user_fk0` FOREIGN KEY (`role_id`) REFERENCES `role`(`id`);

ALTER TABLE `order` ADD CONSTRAINT `order_fk0` FOREIGN KEY (`user_id`) REFERENCES `user`(`id`);

ALTER TABLE `order` ADD CONSTRAINT `order_fk1` FOREIGN KEY (`tour_id`) REFERENCES `tour`(`id`);

ALTER TABLE `user_details` ADD CONSTRAINT `user_details_fk0` FOREIGN KEY (`id`) REFERENCES `user`(`id`);

ALTER TABLE `tour_2_category` ADD CONSTRAINT `tour_2_category_fk0` FOREIGN KEY (`tour_id`) REFERENCES `tour`(`id`);

ALTER TABLE `tour_2_category` ADD CONSTRAINT `tour_2_category_fk1` FOREIGN KEY (`categoty_id`) REFERENCES `category`(`id`);

