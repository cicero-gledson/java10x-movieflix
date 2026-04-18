CREATE TABLE movie (
    id BIGSERIAL PRIMARY KEY,
    title varchar(255) NOT NULL,
    description text,
    release_date date,
    rating float,
    created_at timestamp,
    updated_at timestamp
);