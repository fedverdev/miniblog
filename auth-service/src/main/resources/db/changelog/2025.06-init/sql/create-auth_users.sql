CREATE TABLE IF NOT EXISTS auth_users(
    username VARCHAR(100) PRIMARY KEY,
    password VARCHAR(255),
    inserted_at TIMESTAMP DEFAULT current_timestamp,
    updated_at TIMESTAMP DEFAULT current_timestamp
);
