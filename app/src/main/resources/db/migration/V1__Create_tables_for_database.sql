CREATE EXTENSION IF NOT EXISTS "uuid-ossp";
CREATE SCHEMA IF NOT EXISTS app;

CREATE TABLE users(
    id UUID PRIMARY KEY,
    username VARCHAR(50) NOT NULL,
    password VARCHAR(64) NOT NULL,
    first_name VARCHAR(50) NOT NULL,
    last_name VARCHAR(50) NOT NULL,
    birthday DATE NOT NULL,
    role VARCHAR(10) NOT NULL
);
CREATE TABLE books(
    id UUID PRIMARY KEY,
    user_id UUID NOT NULL REFERENCES users(id) ON DELETE CASCADE,
    time_posted DATE NOT NULL,
    release_year DATE NOT NULL,
    title VARCHAR(50) NOT NULL,
    publisher VARCHAR(50) NOT NULL,
    author VARCHAR(50) NOT NULL,
    book_language VARCHAR(50) NOT NULL,
    status VARCHAR(15) NOT NULL,
    price DECIMAL(5, 2) NOT NULL,
    page_count INTEGER NOT NULL,
    cover VARCHAR(15) NOT NULL,
    translator VARCHAR(50)
);
CREATE TABLE comics(
    id UUID PRIMARY KEY,
    user_id UUID NOT NULL REFERENCES users(id) ON DELETE CASCADE,
    time_posted DATE NOT NULL,
    release_year DATE NOT NULL,
    title VARCHAR(50) NOT NULL,
    publisher VARCHAR(50) NOT NULL,
    author VARCHAR(50) NOT NULL,
    book_language VARCHAR(50) NOT NULL,
    status VARCHAR(15) NOT NULL,
    price DECIMAL(5, 2) NOT NULL,
    page_count INTEGER NOT NULL,
    colored BOOLEAN NOT NULL
);
CREATE TABLE periodicals(
    id UUID PRIMARY KEY,
    user_id UUID NOT NULL REFERENCES users(id) ON DELETE CASCADE,
    time_posted DATE NOT NULL,
    release_year DATE NOT NULL,
    title VARCHAR(50) NOT NULL,
    publisher VARCHAR(50) NOT NULL,
    author VARCHAR(50) NOT NULL,
    book_language VARCHAR(50) NOT NULL,
    status VARCHAR(15) NOT NULL,
    price DECIMAL(5, 2) NOT NULL,
    number INTEGER NOT NULL
);
CREATE TABLE history(
    id UUID PRIMARY KEY,
    book_id UUID REFERENCES books(id) ON DELETE CASCADE,
    comic_id UUID REFERENCES comics(id) ON DELETE CASCADE,
    periodical_id UUID REFERENCES periodicals(id) ON DELETE CASCADE,
    user_id UUID NOT NULL REFERENCES users(id) ON DELETE CASCADE
);
CREATE TABLE chats(
    id UUID PRIMARY KEY,
    user_id UUID NOT NULL REFERENCES users(id) ON DELETE CASCADE,
    book_id UUID REFERENCES books(id) ON DELETE CASCADE,
    comic_id UUID REFERENCES comics(id) ON DELETE CASCADE,
    periodical_id UUID REFERENCES periodicals(id) ON DELETE CASCADE
);
CREATE TABLE messages(
    id UUID PRIMARY KEY,
    user_id UUID NOT NULL REFERENCES users(id) ON DELETE CASCADE,
    chat_id UUID NOT NULL REFERENCES chats(id) ON DELETE CASCADE,
    text VARCHAR(256) NOT NULL,
    time_sent TIME NOT NULL
);
CREATE TABLE comments(
    id UUID PRIMARY KEY,
    user_id UUID NOT NULL REFERENCES users(id) ON DELETE CASCADE,
    book_id UUID REFERENCES books(id) ON DELETE CASCADE,
    comic_id UUID REFERENCES comics(id) ON DELETE CASCADE,
    periodical_id UUID REFERENCES periodicals(id) ON DELETE CASCADE,
    time_sent TIME NOT NULL,
    content VARCHAR(256) NOT NULL,
    comment_id UUID REFERENCES comments(id) ON DELETE CASCADE
);
