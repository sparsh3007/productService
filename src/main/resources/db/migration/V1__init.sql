CREATE TABLE category
(
    uuid BINARY(16)   NOT NULL,
    name VARCHAR(255) NULL,
    CONSTRAINT pk_category PRIMARY KEY (uuid)
);

CREATE TABLE orders
(
    uuid BINARY(16) NOT NULL,
    CONSTRAINT pk_orders PRIMARY KEY (uuid)
);

CREATE TABLE price
(
    uuid     BINARY(16)   NOT NULL,
    price DOUBLE NOT NULL,
    currency VARCHAR(255) NULL,
    CONSTRAINT pk_price PRIMARY KEY (uuid)
);

CREATE TABLE product
(
    uuid          BINARY(16)   NOT NULL,
    title         VARCHAR(255) NULL,
    `description` VARCHAR(255) NULL,
    image         VARCHAR(255) NULL,
    category_uuid BINARY(16)   NULL,
    price_uuid    BINARY(16)   NULL,
    rating_uuid   BINARY(16)   NULL,
    CONSTRAINT pk_product PRIMARY KEY (uuid)
);

CREATE TABLE products_order
(
    order_id   BINARY(16) NOT NULL,
    product_id BINARY(16) NOT NULL
);

CREATE TABLE rating
(
    uuid  BINARY(16) NOT NULL,
    average DOUBLE NOT NULL,
    count INT NOT NULL,
    CONSTRAINT pk_rating PRIMARY KEY (uuid)
);

ALTER TABLE product
    ADD CONSTRAINT FK_PRODUCT_ON_CATEGORY_UUID FOREIGN KEY (category_uuid) REFERENCES category (uuid);

ALTER TABLE product
    ADD CONSTRAINT FK_PRODUCT_ON_PRICE_UUID FOREIGN KEY (price_uuid) REFERENCES price (uuid);

ALTER TABLE product
    ADD CONSTRAINT FK_PRODUCT_ON_RATING_UUID FOREIGN KEY (rating_uuid) REFERENCES rating (uuid);

ALTER TABLE products_order
    ADD CONSTRAINT fk_proord_on_order FOREIGN KEY (order_id) REFERENCES orders (uuid);

ALTER TABLE products_order
    ADD CONSTRAINT fk_proord_on_product FOREIGN KEY (product_id) REFERENCES product (uuid);