USE travel_agency_test;

insert into role (type) value ('ROLE_USER');
insert into role (type) value ('ROLE_ADMIN');

insert into category (type) value ('OTHER_TOUR');
insert into category (type) value ('BEACH_TOUR');
insert into category (type) value ('SHOP_TOUR');

insert into tour (id, title, is_hot, price) values (0, 'Test tour', 0, 800);