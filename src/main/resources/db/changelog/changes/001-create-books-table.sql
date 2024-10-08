CREATE TABLE IF NOT EXISTS books (
    id BIGINT AUTO_INCREMENT PRIMARY KEY,
    title VARCHAR(255) NOT NULL,
    author VARCHAR(255) NOT NULL,
    isbn VARCHAR(255) NOT NULL UNIQUE,
    price DECIMAL(10, 2) NOT NULL,
    description TEXT,
    cover_image VARCHAR(255),
    is_deleted BOOLEAN DEFAULT FALSE
);

-- Rollback script:
-- DROP TABLE IF EXISTS books;
