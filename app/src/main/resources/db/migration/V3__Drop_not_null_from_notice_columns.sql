ALTER TABLE notices
ALTER COLUMN book_id DROP NOT NULL,
ALTER COLUMN comic_id DROP NOT NULL,
ALTER COLUMN periodical_id DROP NOT NULL;