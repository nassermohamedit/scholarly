-- roles relation
CREATE TABLE roles (
    id SMALLSERIAL PRIMARY KEY,
    name VARCHAR(255) NOT NULL,
    description TEXT
);

-- users relation
CREATE TABLE users (
    id BIGSERIAL PRIMARY KEY,
    username VARCHAR(255) NOT NULL UNIQUE,
    password VARCHAR(255) NOT NULL,
    role_id SMALLINT,
    CONSTRAINT fk_role
      FOREIGN KEY(role_id) REFERENCES roles(id)
);
