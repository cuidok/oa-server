# Database design document

## database

The SQL statement for creating the database is as follows:

```sql

CREATE DATABASE `oa` DEFAULT CHARACTER SET UTF8MB4 COLLATE utf8mb4_general_ci;

```

## Table

### User table

| Number | Name      | Data type | Length | Decimal | Allow null | Primary key | Default value | Description      |
|--------|-----------|-----------|--------|---------|------------|-------------|---------------|------------------|
| 1      | id        | int       | 11     | 0       | N          | Y           |               | User id          |
| 2      | username  | varchar   | 20     | 0       | N          | N           |               | User name        |
| 3      | nick_name | varchar   | 20     | 0       | Y          | N           |               | Nickname         |
| 4      | password  | varchar   | 100    | 0       | N          | N           |               | Login password   |
| 5      | email     | varchar   | 50     | 0       | Y          | N           |               | Email            |