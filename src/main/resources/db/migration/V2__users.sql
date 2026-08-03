-- Table is named app_user, not user: USER is a reserved word in MySQL 8+.
CREATE TABLE app_user (
    id            BIGINT AUTO_INCREMENT PRIMARY KEY,
    first_name    VARCHAR(255) NOT NULL,
    last_name     VARCHAR(255) NOT NULL,
    email         VARCHAR(255) NOT NULL,
    password_hash VARCHAR(255) NOT NULL,
    location      VARCHAR(255),
    service       VARCHAR(255),
    role          VARCHAR(20)  NOT NULL DEFAULT 'USER',
    CONSTRAINT uq_app_user_email UNIQUE (email)
) ENGINE = InnoDB DEFAULT CHARSET = utf8mb4;
