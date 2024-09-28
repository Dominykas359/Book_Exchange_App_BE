ALTER TABLE books
DROP COLUMN user_id,
DROP COLUMN time_posted;

ALTER TABLE comics
DROP COLUMN user_id,
DROP COLUMN time_posted;

ALTER TABLE periodicals
DROP COLUMN user_id,
DROP COLUMN time_posted;

ALTER TABLE history
DROP COLUMN book_id,
DROP COLUMN comic_id,
DROP COLUMN periodical_id,
ADD COLUMN notice_id UUID;

ALTER TABLE chats
DROP COLUMN book_id,
DROP COLUMN comic_id,
DROP COLUMN periodical_id,
ADD COLUMN notice_id UUID;

ALTER TABLE comments
DROP COLUMN book_id,
DROP COLUMN comic_id,
DROP COLUMN periodical_id,
ADD COLUMN notice_id UUID;

CREATE TABLE notices(
    id UUID PRIMARY KEY,
    user_id UUID NOT NULL REFERENCES users(id) ON DELETE CASCADE,
    book_id UUID NOT NULL REFERENCES books(id) ON DELETE CASCADE,
    comic_id UUID NOT NULL REFERENCES comics(id) ON DELETE CASCADE,
    periodical_id UUID NOT NULL REFERENCES periodicals(id) ON DELETE CASCADE,
    time_posted DATE NOT NULL
);