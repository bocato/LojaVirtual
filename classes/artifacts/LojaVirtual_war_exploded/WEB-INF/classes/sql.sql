DROP DATABASE LojaVirtual;

CREATE DATABASE LojaVirtual;

USE LojaVirtual;

CREATE TABLE user(
	user VARCHAR(32) PRIMARY KEY,
    pass VARCHAR(32) NOT NULL
) ENGINE=InnoDB;

CREATE TABLE permission(
	id_permission INT PRIMARY KEY,
	name_permission VARCHAR(32) NOT NULL
) ENGINE=InnoDB;

CREATE TABLE user_permission(
	user VARCHAR(32) PRIMARY KEY,
    id_permission INT NOT NULL DEFAULT 1,
    FOREIGN KEY (user) REFERENCES user(user),
	FOREIGN KEY (id_permission) REFERENCES permission(id_permission)
) ENGINE=InnoDB;

CREATE TABLE products(
	id_product INT PRIMARY KEY,
    name VARCHAR(64) NOT NULL,
	price FLOAT NOT NULL
) ENGINE=InnoDB;

CREATE TABLE orders(
	id_order INT AUTO_INCREMENT PRIMARY KEY,
    user VARCHAR(32) NOT NULL,
    name VARCHAR(32) NOT NULL,
    end VARCHAR(64) NOT NULL,
    cpf VARCHAR(15) NOT NULL,
	FOREIGN KEY (user) REFERENCES user(user)
) ENGINE=InnoDB;

CREATE TABLE order_detail(
	id_order INT NOT NULL,
    id_product INT NOT NULL,
	FOREIGN KEY (id_order) REFERENCES orders(id_order),
	FOREIGN KEY (id_product) REFERENCES products(id_product)
) ENGINE=InnoDB;

INSERT INTO permission VALUES(1, "client");
INSERT INTO permission VALUES(2, "admin");

INSERT INTO user VALUES("admin", "admin");

INSERT INTO user_permission VALUES("admin", 2);