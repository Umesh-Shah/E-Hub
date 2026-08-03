CREATE TABLE category (
    id          BIGINT AUTO_INCREMENT PRIMARY KEY,
    name        VARCHAR(255)  NOT NULL,
    description VARCHAR(1000),
    CONSTRAINT uq_category_name UNIQUE (name)
) ENGINE = InnoDB DEFAULT CHARSET = utf8mb4;

CREATE TABLE subcategory (
    id          BIGINT AUTO_INCREMENT PRIMARY KEY,
    name        VARCHAR(255)  NOT NULL,
    description VARCHAR(1000),
    category_id BIGINT        NOT NULL,
    CONSTRAINT fk_subcategory_category FOREIGN KEY (category_id) REFERENCES category (id),
    CONSTRAINT uq_subcategory_category_name UNIQUE (category_id, name)
) ENGINE = InnoDB DEFAULT CHARSET = utf8mb4;

CREATE TABLE skill (
    id             BIGINT AUTO_INCREMENT PRIMARY KEY,
    name           VARCHAR(255)  NOT NULL,
    description    VARCHAR(1000),
    subcategory_id BIGINT        NOT NULL,
    CONSTRAINT fk_skill_subcategory FOREIGN KEY (subcategory_id) REFERENCES subcategory (id),
    CONSTRAINT uq_skill_subcategory_name UNIQUE (subcategory_id, name)
) ENGINE = InnoDB DEFAULT CHARSET = utf8mb4;

CREATE TABLE qualification (
    id          BIGINT AUTO_INCREMENT PRIMARY KEY,
    name        VARCHAR(255)  NOT NULL,
    description VARCHAR(1000),
    CONSTRAINT uq_qualification_name UNIQUE (name)
) ENGINE = InnoDB DEFAULT CHARSET = utf8mb4;

CREATE TABLE country (
    id   BIGINT AUTO_INCREMENT PRIMARY KEY,
    name VARCHAR(255) NOT NULL,
    CONSTRAINT uq_country_name UNIQUE (name)
) ENGINE = InnoDB DEFAULT CHARSET = utf8mb4;

CREATE TABLE city (
    id          BIGINT AUTO_INCREMENT PRIMARY KEY,
    name        VARCHAR(255) NOT NULL,
    postal_code VARCHAR(20),
    country_id  BIGINT       NOT NULL,
    CONSTRAINT fk_city_country FOREIGN KEY (country_id) REFERENCES country (id)
) ENGINE = InnoDB DEFAULT CHARSET = utf8mb4;
