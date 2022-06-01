DROP TABLE IF EXISTS products;
CREATE TABLE IF NOT EXISTS products (
	id bigserial NOT NULL,
	description varchar(255) NULL,
	name varchar(255) NOT NULL,
	price float8 NOT NULL,
	UNIQUE (name),
	CONSTRAINT products_pkey PRIMARY KEY (id)
);


DROP TABLE IF EXISTS orders;
CREATE TABLE IF NOT EXISTS orders (
	id bigserial NOT NULL,
	cost float8 NOT NULL,
	description varchar(255) NOT NULL,
	order_date timestamp NOT NULL,
	CONSTRAINT orders_pkey primary key (id)
);