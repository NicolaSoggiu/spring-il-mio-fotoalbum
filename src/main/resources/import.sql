INSERT INTO photos (title, description, url) VALUES('Mare', 'Bellissimo tramonto sulla spiaggia', 'https://images.unsplash.com/photo-1616036740257-9449ea1f6605?q=80&w=1470&auto=format&fit=crop&ixlib=rb-4.0.3&ixid=M3wxMjA3fDB8MHxwaG90by1wYWdlfHx8fGVufDB8fHx8fA%3D%3D');
INSERT INTO photos (title, description, url) VALUES('Montagna', 'Vista innevata', 'https://images.unsplash.com/photo-1485516195719-e9edaa29d2ba?q=80&w=1528&auto=format&fit=crop&ixlib=rb-4.0.3&ixid=M3wxMjA3fDB8MHxwaG90by1wYWdlfHx8fGVufDB8fHx8fA%3D%3D');

INSERT INTO categories(name) VALUES('mare');
INSERT INTO categories(name) VALUES('montagna');
INSERT INTO categories(name) VALUES('città');

INSERT INTO photos_categories(photos_id, categories_id) VALUES(1,1);
INSERT INTO photos_categories(photos_id, categories_id) VALUES(2,3);


INSERT INTO roles (id, name) VALUES(1, 'ADMIN');
INSERT INTO roles (id, name) VALUES(2, 'USER');

INSERT INTO users (email, first_name, last_name, registered_at, password) VALUES('nicola@email.com', 'Nicola', 'Soggiu', '2023-11-20 10:35', '{noop}nicola');
INSERT INTO users (email, first_name, last_name, registered_at, password) VALUES('mario@email.com', 'Mario', 'Rossi', '2023-11-20 10:35','{noop}mario');

INSERT INTO users_roles (user_id, roles_id) VALUES(1, 1);
INSERT INTO users_roles (user_id, roles_id) VALUES(2, 2);