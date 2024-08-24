CREATE TABLE IF NOT EXISTS users
(
    user_id  bigint GENERATED ALWAYS AS IDENTITY,
    email    text UNIQUE NOT NULL,
    password text        NOT NULL,
    name     text,
    role     text        NOT NULL,

    PRIMARY KEY (user_id)
);