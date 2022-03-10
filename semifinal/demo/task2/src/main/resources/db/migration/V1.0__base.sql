CREATE TABLE category
(
  id          BIGINT       NOT NULL PRIMARY KEY AUTO_INCREMENT,
  name        VARCHAR(255) NOT NULL,
  description VARCHAR(255) NOT NULL,
  parent_id   BIGINT       NULL,
  FOREIGN KEY (parent_id) REFERENCES category (id) ON DELETE CASCADE
);

CREATE TABLE product
(
  id          BIGINT       NOT NULL PRIMARY KEY AUTO_INCREMENT,
  category_id BIGINT       NOT NULL,
  name        VARCHAR(255) NOT NULL,
  description VARCHAR(255) NOT NULL,
  FOREIGN KEY (category_id) REFERENCES category (id) ON DELETE CASCADE
);
