ALTER TABLE comments
DROP COLUMN time_sent;

ALTER TABLE comments
ADD COLUMN time_sent TIMESTAMP WITHOUT TIME ZONE;

ALTER TABLE messages
DROP COLUMN time_sent;

ALTER TABLE messages
ADD COLUMN time_sent TIMESTAMP WITHOUT TIME ZONE;

ALTER TABLE users
RENAME COLUMN username TO email;