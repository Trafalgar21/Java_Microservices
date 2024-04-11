CREATE TABLE Customer (
    customer_number BIGINT PRIMARY KEY,
    customer_name CHAR(20) NOT NULL,
    customer_mobile CHAR(11) NOT NULL,
    customer_email CHAR(30),
    address1 CHAR(50) NOT NULL,
    address2 CHAR(50)
);

CREATE TABLE Account (
    account_number BIGINT AUTO_INCREMENT PRIMARY KEY,
    account_type INT NOT NULL,
    available_balance DECIMAL(10, 2) NOT NULL,
    customer_number BIGINT
);

CREATE sequence sequence;





