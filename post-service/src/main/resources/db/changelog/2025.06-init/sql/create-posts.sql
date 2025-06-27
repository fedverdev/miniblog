CREATE EXTENSION IF NOT EXISTS "uuid-ossp";

CREATE TABLE IF NOT EXISTS user_profiles
(
    user_id UUID PRIMARY KEY,
    username VARCHAR(100) UNIQUE,
    inserted_at TIMESTAMP        DEFAULT current_timestamp,
    updated_at  TIMESTAMP        DEFAULT current_timestamp
);

CREATE TABLE IF NOT EXISTS posts
(
    post_id     UUID PRIMARY KEY DEFAULT uuid_generate_v4(),
    user_id     UUID REFERENCES user_profiles(user_id),
    content     TEXT,
    is_private  BOOLEAN          DEFAULT false
);


