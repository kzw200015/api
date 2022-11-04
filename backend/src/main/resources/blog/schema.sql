DROP TABLE IF EXISTS posts;
DROP TABLE IF EXISTS users;

CREATE TABLE IF NOT EXISTS users
(
    id       INT PRIMARY KEY AUTO_INCREMENT,
    username VARCHAR(20)  NOT NULL UNIQUE,
    password VARCHAR(100) NOT NULL
);

CREATE TABLE IF NOT EXISTS posts
(
    id          INT PRIMARY KEY AUTO_INCREMENT,
    title       VARCHAR(100) NULL,
    content     TEXT         NULL,
    create_time BIGINT       NOT NULL,
    update_time BIGINT       NOT NULL,
    user_id     INT          NOT NULL REFERENCES users (id)
);