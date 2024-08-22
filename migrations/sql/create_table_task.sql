CREATE TABLE IF NOT EXISTS tasks
(
    task_id     bigint GENERATED ALWAYS AS IDENTITY,
    user_id     bigint REFERENCES users ON DELETE CASCADE,
    title       text UNIQUE              NOT NULL,
    created_at  timestamp WITH TIME ZONE NOT NULL,
    description text,

    PRIMARY KEY (task_id)
)