CREATE TABLE IF NOT EXISTS user_profile (
    user_id UUID PRIMARY KEY,
    username VARCHAR(100) UNIQUE,
    first_name VARCHAR(100),
    last_name VARCHAR(100),
    email VARCHAR(100)
)