DROP TABLE IF EXISTS post;
DROP TABLE IF EXISTS user;

CREATE TABLE IF NOT EXISTS user
(
    id       INT PRIMARY KEY AUTO_INCREMENT,
    username VARCHAR(20)  NOT NULL UNIQUE,
    password VARCHAR(100) NOT NULL
);

CREATE TABLE IF NOT EXISTS post
(
    id          INT PRIMARY KEY AUTO_INCREMENT,
    title       VARCHAR(100) NULL,
    content     TEXT         NULL,
    create_time BIGINT(10)   NOT NULL,
    update_time BIGINT(10)   NOT NULL,
    user_id     INT          NOT NULL REFERENCES user (id)
);