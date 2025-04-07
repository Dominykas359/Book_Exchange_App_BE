CREATE TABLE wishlist
(
    id UUID PRIMARY KEY,
    user_id UUID NOT NULL,
    FOREIGN KEY (user_id) REFERENCES users(id) ON DELETE CASCADE,
    notice_id UUID NOT NULL,
    FOREIGN KEY (notice_id) REFERENCES notices(id) ON DELETE CASCADE
)