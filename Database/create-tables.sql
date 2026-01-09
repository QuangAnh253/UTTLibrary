-- ========================================
--  DATABASE: UTTLibraryDB
-- ========================================
CREATE DATABASE IF NOT EXISTS UTTLibraryDB 
    CHARACTER SET utf8mb4 
    COLLATE utf8mb4_unicode_ci;

USE UTTLibraryDB;

-- ========================================
-- 1) AUTHOR – PUBLISHER – CATEGORY
-- ========================================

CREATE TABLE Author (
    author_id INT AUTO_INCREMENT PRIMARY KEY,
    author_name VARCHAR(100) NOT NULL,
    website VARCHAR(100),
    note TEXT
);

CREATE TABLE Publisher (
    publisher_id INT AUTO_INCREMENT PRIMARY KEY,
    publisher_name VARCHAR(100) NOT NULL,
    address VARCHAR(255),
    email VARCHAR(100)
);

CREATE TABLE Category (
    category_id INT AUTO_INCREMENT PRIMARY KEY,
    category_name VARCHAR(100) NOT NULL
);

-- ========================================
-- 2) STAFF (LOGIN)
-- ========================================

CREATE TABLE Staff (
    staff_id INT AUTO_INCREMENT PRIMARY KEY,
    username VARCHAR(50) NOT NULL UNIQUE,
    password VARCHAR(255) NOT NULL,
    full_name VARCHAR(100) NOT NULL,
    role VARCHAR(20) DEFAULT 'Staff'
);

INSERT INTO Staff (username, password, full_name, role)
VALUES ('admin', '123', 'Administrator', 'Admin');

-- ========================================
-- 3) SHELF LOCATION
-- ========================================

CREATE TABLE ShelfLocation (
    shelf_id INT AUTO_INCREMENT PRIMARY KEY,
    shelf_name VARCHAR(100) NOT NULL,
    description VARCHAR(255)
);

-- ========================================
-- 4) BOOK
-- ========================================

CREATE TABLE Book (
    book_id INT AUTO_INCREMENT PRIMARY KEY,
    book_title VARCHAR(255) NOT NULL,
    publish_year INT,
    price DECIMAL(10,2),
    quantity INT DEFAULT 0,
    cover_image VARCHAR(255),

    author_id INT,
    publisher_id INT,
    category_id INT,
    shelf_id INT,

    FOREIGN KEY (author_id) REFERENCES Author(author_id),
    FOREIGN KEY (publisher_id) REFERENCES Publisher(publisher_id),
    FOREIGN KEY (category_id) REFERENCES Category(category_id),
    FOREIGN KEY (shelf_id) REFERENCES ShelfLocation(shelf_id)
);

-- ========================================
-- 5) READER
-- ========================================

CREATE TABLE Reader (
    reader_id INT AUTO_INCREMENT PRIMARY KEY,
    full_name VARCHAR(100) NOT NULL,
    birthday DATE,
    gender VARCHAR(10),
    phone VARCHAR(20),
    reader_type VARCHAR(20)
);

-- ========================================
-- 6) LOAN TICKET (PHIEU MUON)
-- ========================================

CREATE TABLE LoanTicket (
    ticket_id INT AUTO_INCREMENT PRIMARY KEY,
    reader_id INT,
    staff_id INT,
    borrow_date DATETIME DEFAULT CURRENT_TIMESTAMP,
    due_date DATETIME,
    status VARCHAR(20) DEFAULT 'Borrowing',
    total_fine DECIMAL(10,2) DEFAULT 0,

    FOREIGN KEY (reader_id) REFERENCES Reader(reader_id),
    FOREIGN KEY (staff_id) REFERENCES Staff(staff_id)
);

-- ========================================
-- 7) LOAN DETAIL (CHI TIẾT)
-- ========================================

CREATE TABLE LoanDetail (
    detail_id INT AUTO_INCREMENT PRIMARY KEY,
    ticket_id INT,
    book_id INT,
    quantity INT DEFAULT 1,
    note TEXT,

    FOREIGN KEY (ticket_id) REFERENCES LoanTicket(ticket_id) ON DELETE CASCADE,
    FOREIGN KEY (book_id) REFERENCES Book(book_id)
);

-- ========================================
-- 8) PENALTY – TIỀN PHẠT
-- ========================================

CREATE TABLE Penalty (
    penalty_id INT AUTO_INCREMENT PRIMARY KEY,
    ticket_id INT NOT NULL,
    amount DECIMAL(10,2) NOT NULL,
    reason TEXT,
    created_at DATETIME DEFAULT CURRENT_TIMESTAMP,

    FOREIGN KEY (ticket_id) REFERENCES LoanTicket(ticket_id)
);

-- ========================================
-- 9) REGULATION – QUY ĐỊNH
-- ========================================

CREATE TABLE Regulation (
    regulation_id INT AUTO_INCREMENT PRIMARY KEY,
    regulation_name VARCHAR(255) NOT NULL,
    value INT NOT NULL,
    description TEXT
);

-- ========================================
-- 10) IMPORT BOOK – NHẬP SÁCH
-- ========================================

CREATE TABLE ImportBook (
    import_id INT AUTO_INCREMENT PRIMARY KEY,
    book_id INT NOT NULL,
    quantity INT NOT NULL,
    import_price DECIMAL(10,2),
    import_date DATETIME DEFAULT CURRENT_TIMESTAMP,

    FOREIGN KEY (book_id) REFERENCES Book(book_id)
);

-- ========================================
-- 11) SUPPLIER – NHÀ CUNG CẤP
-- ========================================

CREATE TABLE Supplier (
    supplier_id INT AUTO_INCREMENT PRIMARY KEY,
    supplier_name VARCHAR(255) NOT NULL,
    address VARCHAR(255),
    phone VARCHAR(20)
);

-- ========================================
-- 12) BOOKING – ĐẶT SÁCH
-- ========================================

CREATE TABLE Booking (
    booking_id INT AUTO_INCREMENT PRIMARY KEY,
    reader_id INT NOT NULL,
    book_id INT NOT NULL,
    booking_date DATETIME DEFAULT CURRENT_TIMESTAMP,
    status VARCHAR(20) DEFAULT 'Pending',

    FOREIGN KEY (reader_id) REFERENCES Reader(reader_id),
    FOREIGN KEY (book_id) REFERENCES Book(book_id)
);
