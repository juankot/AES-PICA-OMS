insert into orders.CUSTOMER_TYPE (ID, NAME) VALUES (1, 'PLATINO');
insert into orders.CUSTOMER_TYPE (ID, NAME) VALUES (2, 'DORADO');
insert into orders.CUSTOMER_TYPE (ID, NAME) VALUES (3, 'PLATEADO');

insert into orders.IDENTIFICATION_CARD_TYPE (ID, NAME) VALUES ('CC', 'Cédula de Ciudadanía');
insert into orders.IDENTIFICATION_CARD_TYPE (ID, NAME) VALUES ('CE', 'Cédula de Extranjería');
insert into orders.IDENTIFICATION_CARD_TYPE (ID, NAME) VALUES ('PA', 'Pasaporte');
insert into orders.IDENTIFICATION_CARD_TYPE (ID, NAME) VALUES ('RC', 'Registro Civil');
insert into orders.IDENTIFICATION_CARD_TYPE (ID, NAME) VALUES ('TI', 'Tarjeta de Identidad');

insert into orders.STATUS (ID, NAME) VALUES (1, 'EN VALIDACIÓN');
insert into orders.STATUS (ID, NAME) VALUES (2, 'EN TRANSITO');
insert into orders.STATUS (ID, NAME) VALUES (3, 'RECHAZADA');
insert into orders.STATUS (ID, NAME) VALUES (4, 'CERRADA');
insert into orders.STATUS (ID, NAME) VALUES (5, 'CANCELADA');

insert into orders.COUNTRY (CODE, NAME) VALUES ('COL', 'Colombia');
insert into orders.COUNTRY (CODE, NAME) VALUES ('PER', 'Perú');
insert into orders.COUNTRY (CODE, NAME) VALUES ('CHL', 'Chile');
insert into orders.COUNTRY (CODE, NAME) VALUES ('MEX', 'México');

insert into orders.INVENTORY_PROVIDER (ID, NAME) VALUES (1, 'SONY');
insert into orders.INVENTORY_PROVIDER (ID, NAME) VALUES (2, 'RapidService');

insert into orders.COURIER_PROVIDER (ID, NAME) VALUES (1, 'DEPRISA');
insert into orders.COURIER_PROVIDER (ID, NAME) VALUES (2, 'SERVIENTREGA');
insert into orders.COURIER_PROVIDER (ID, NAME) VALUES (3, 'DHL');