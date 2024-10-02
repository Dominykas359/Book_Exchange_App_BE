ALTER TABLE users
ADD CONSTRAINT unique_username UNIQUE (username);
ALTER TABLE books
ALTER COLUMN translator TYPE VARCHAR(50);