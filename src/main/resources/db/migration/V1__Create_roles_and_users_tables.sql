CREATE TABLE roles (
    id SMALLSERIAL PRIMARY KEY,
    name VARCHAR(255) NOT NULL,
    description TEXT
);

CREATE TABLE users (
    id BIGSERIAL PRIMARY KEY,
    username VARCHAR(255) NOT NULL UNIQUE,
    password VARCHAR(255) NOT NULL,
    role_id SMALLINT,
    firstname VARCHAR(255) NOT NULL,
    lastname VARCHAR(255) NOT NULL,
    email VARCHAR(255) NOT NULL UNIQUE,
    country VARCHAR(255),
    gender SMALLINT,
    CONSTRAINT fk_role FOREIGN KEY(role_id) REFERENCES roles(id)
);


CREATE TABLE topics (
    id BIGSERIAL PRIMARY KEY,
    name VARCHAR(255) NOT NULL UNIQUE,
    description TEXT NOT NULL,
    owner_id BIGINT,
    CONSTRAINT fk_owner FOREIGN KEY(owner_id) REFERENCES users(id)
);

CREATE TABLE posts (
    id BIGSERIAL PRIMARY KEY,
    content TEXT,
    topic_id BIGINT NOT NULL,
    author_id BIGINT,
    ups BIGINT DEFAULT 0,
    downs BIGINT DEFAULT 0,
    number_of_comments INT DEFAULT 0,
    created_on TIMESTAMP,
    CONSTRAINT fk_topic FOREIGN KEY(topic_id) REFERENCES topics(id),
    CONSTRAINT fk_author FOREIGN KEY(author_id) REFERENCES users(id)
);

CREATE TABLE comments (
    id BIGSERIAL PRIMARY KEY,
    content TEXT,
    post_id BIGINT NOT NULL,
    author_id BIGINT NOT NULL,
    created_on TIMESTAMP,
    CONSTRAINT fk_post FOREIGN KEY(post_id) REFERENCES posts(id),
    CONSTRAINT fk_comment_author FOREIGN KEY(author_id) REFERENCES users(id)
);

CREATE TABLE memberships (
    user_id BIGINT NOT NULL,
    topic_id BIGINT NOT NULL,
    power SMALLINT,
    CONSTRAINT fk_user FOREIGN KEY(user_id) REFERENCES users(id),
    CONSTRAINT fk_topic_membership FOREIGN KEY(topic_id) REFERENCES topics(id),
    CONSTRAINT unique_user_topic UNIQUE (user_id, topic_id)
);

CREATE TABLE join_requests (
    user_id BIGINT NOT NULL,
    topic_id BIGINT NOT NULL,
    CONSTRAINT fk_user_requests FOREIGN KEY(user_id) REFERENCES users(id),
    CONSTRAINT fk_topic_requests FOREIGN KEY(topic_id) REFERENCES topics(id),
    CONSTRAINT unique_user_topic_requests UNIQUE (user_id, topic_id)
);