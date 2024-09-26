# Database design document

## database

The SQL statement for creating the database is as follows:

```sql

CREATE DATABASE `oa` DEFAULT CHARACTER SET UTF8MB4 COLLATE utf8mb4_general_ci;

```

## Table

### User table

| Number | Name      | Data type | Length | Decimal | Allow null | Primary key | Default value | Description    |
|--------|-----------|-----------|--------|---------|------------|-------------|---------------|----------------|
| 1      | id        | int       | 11     | 0       | N          | Y           |               | User id        |
| 2      | username  | varchar   | 20     | 0       | N          | N           |               | User name      |
| 3      | nick_name | varchar   | 20     | 0       | Y          | N           |               | Nickname       |
| 4      | password  | varchar   | 100    | 0       | N          | N           |               | Login password |
| 5      | email     | varchar   | 50     | 0       | Y          | N           |               | Email          |

### Task table

| Number | Name          | Data type | Length | Decimal | Allow null | Primary key | Default value | Description                    |
|--------|---------------|-----------|--------|---------|------------|-------------|---------------|--------------------------------|
| 1      | id            | int       | 11     | 0       | N          | Y           |               | Task id                        |
| 2      | user_id       | int       | 11     | 0       | N          | N           |               | User id                        |
| 3      | title         | varchar   | 50     | 0       | N          | N           |               | Task title                     |
| 4      | content       | text      |        |         | N          | N           |               | Task content                   |
| 5      | status        | varchar   | 20     | 0       | N          | N           |               | Task status                    |
| 6      | start_time    | datetime  |        |         | N          | N           |               | The planned start time of task |
| 7      | end_time      | datetime  |        |         | N          | N           |               | The end time of task           |
| 8      | complete_time | datetime  |        |         | Y          | N           |               | The complete time of task      |
| 9      | create_time   | datetime  |        |         | N          | N           |               | The create time of task        |
| 10     | update_time   | datetime  |        |         | N          | N           |               | The update time of task        |
