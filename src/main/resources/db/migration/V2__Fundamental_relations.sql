-- profiles table
CREATE TABLE profiles (
    id SERIAL PRIMARY KEY,
    firstname VARCHAR(255) NOT NULL,
    lastname VARCHAR(255) NOT NULL,
    email VARCHAR(255) NOT NULL UNIQUE,
    country VARCHAR(255),
    gender SMALLINT,
    user_id INT NOT NULL UNIQUE,
    CONSTRAINT fk_user FOREIGN KEY(user_id) REFERENCES users(id)
);

-- topics table
CREATE TABLE topics (
    id SERIAL PRIMARY KEY,
    name VARCHAR(255) NOT NULL UNIQUE,
    description TEXT NOT NULL,
    owner_id INT,
    CONSTRAINT fk_owner FOREIGN KEY(owner_id) REFERENCES profiles(id)
);

-- posts table
CREATE TABLE posts (
    id SERIAL PRIMARY KEY,
    content TEXT,
    topic_id INT NOT NULL,
    author_id INT,
    ups INT DEFAULT 0,
    downs INT DEFAULT 0,
    number_of_comments INT DEFAULT 0,
    created_on TIMESTAMP,
    CONSTRAINT fk_topic FOREIGN KEY(topic_id) REFERENCES topics(id),
    CONSTRAINT fk_author FOREIGN KEY(author_id) REFERENCES users(id)
);

-- comments table
CREATE TABLE comments (
    id SERIAL PRIMARY KEY,
    content TEXT,
    post_id INT NOT NULL,
    author_id INT NOT NULL,
    created_on TIMESTAMP,
    CONSTRAINT fk_post FOREIGN KEY(post_id) REFERENCES posts(id),
    CONSTRAINT fk_comment_author FOREIGN KEY(author_id) REFERENCES users(id)
);