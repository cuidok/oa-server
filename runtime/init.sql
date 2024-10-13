CREATE DATABASE oa WITH ENCODING 'UTF8' LC_COLLATE='en_US.utf8' LC_CTYPE='en_US.utf8' TEMPLATE=template0;

\c oa

CREATE TABLE "user"
(
    id SERIAL PRIMARY KEY,
    username VARCHAR(20) NOT NULL UNIQUE,
    nick_name VARCHAR(20),
    password VARCHAR(100) NOT NULL,
    email VARCHAR(50)
);

COMMENT ON TABLE "user" IS 'User management table';
COMMENT ON COLUMN "user".id IS 'User id';
COMMENT ON COLUMN "user".username IS 'User name';
COMMENT ON COLUMN "user".nick_name IS 'Nickname';
COMMENT ON COLUMN "user".password IS 'Login password';
COMMENT ON COLUMN "user".email IS 'Email';

CREATE TABLE task
(
    id SERIAL PRIMARY KEY,
    user_id INT NOT NULL,
    title VARCHAR(50) NOT NULL,
    content TEXT NOT NULL,
    status VARCHAR(20) NOT NULL,
    start_time TIMESTAMP NOT NULL,
    end_time TIMESTAMP NOT NULL,
    complete_time TIMESTAMP,
    create_time TIMESTAMP NOT NULL DEFAULT CURRENT_TIMESTAMP,
    update_time TIMESTAMP NOT NULL DEFAULT CURRENT_TIMESTAMP
);

COMMENT ON TABLE task IS 'Task table';
COMMENT ON COLUMN task.id IS 'Task id';
COMMENT ON COLUMN task.user_id IS 'User id';
COMMENT ON COLUMN task.title IS 'Task title';
COMMENT ON COLUMN task.content IS 'Task content';
COMMENT ON COLUMN task.status IS 'Task status';
COMMENT ON COLUMN task.start_time IS 'The planned start time of task';
COMMENT ON COLUMN task.end_time IS 'The end time of task';
COMMENT ON COLUMN task.complete_time IS 'The complete time of task';
COMMENT ON COLUMN task.create_time IS 'The create time of task';
COMMENT ON COLUMN task.update_time IS 'The update time of task';

CREATE INDEX idx_user_id ON task(user_id);