drop TABLE if EXISTS product; 

create table product (
    id serial  PRIMARY KEY,
    title VARCHAR(500),
    price INTEGER 
);

INSERT INTO product (title, price) 
VALUES ('Product 1', 100),
       ('Product 2', 200),
       ('Product 3', 300);

Select * from product;