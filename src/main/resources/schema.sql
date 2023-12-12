CREATE TABLE users (
                       id UUID PRIMARY KEY,
                       name VARCHAR(255) NOT NULL,
                       email VARCHAR(255) NOT NULL UNIQUE,
                       password VARCHAR(255) NOT NULL,
                       token VARCHAR(255) NOT NULL,
                       created TIMESTAMP NOT NULL DEFAULT CURRENT_TIMESTAMP,
                       modified TIMESTAMP DEFAULT CURRENT_TIMESTAMP,
                       last_login TIMESTAMP NOT NULL DEFAULT CURRENT_TIMESTAMP,
                       is_active BOOLEAN NOT NULL DEFAULT TRUE
);

CREATE TABLE phones (
                        id UUID PRIMARY KEY,
                        user_id UUID NOT NULL,
                        number VARCHAR(255) NOT NULL,
                        city_code VARCHAR(255) NOT NULL,
                        contry_code VARCHAR(255) NOT NULL,
                        FOREIGN KEY (user_id) REFERENCES users(id)
);