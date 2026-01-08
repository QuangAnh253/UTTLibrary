USE UTTLibraryDB;

SET FOREIGN_KEY_CHECKS = 0;

-- =======================================================
-- 1) AUTHOR
-- =======================================================
INSERT INTO Author (author_name, website, note) VALUES
('PGS.TS Nguyễn Xuân Kiên', 'https://utt.edu.vn', 'Giảng viên Viện Công trình UTT'),
('TS Trần Hải Sơn', 'https://utt.edu.vn', 'Giảng viên Viện CNTT & TT'),
('TS Nguyễn Hữu Đức', 'https://utt.edu.vn', 'Giảng viên Viện Cơ khí Động lực'),
('J.K. Rowling', 'https://jkrowling.com', 'Tác giả văn học quốc tế');

-- =======================================================
-- 2) PUBLISHER
-- =======================================================
INSERT INTO Publisher (publisher_name, address, email) VALUES
('NXB Giao Thông Vận Tải', 'Hà Nội', 'gtvt@publisher.vn'),
('NXB Bách Khoa Hà Nội', 'Hà Nội', 'bkhanoi@publisher.vn'),
('Bloomsbury', 'London, UK', 'contact@bloomsbury.com');

-- =======================================================
-- 3) CATEGORY
-- =======================================================
INSERT INTO Category (category_name) VALUES
('Công nghệ thông tin'),
('Cơ khí – Động lực'),
('Công trình'),
('Kinh tế vận tải'),
('Khoa học ứng dụng – Luật chính trị'),
('Kế toán – Quản trị'),
('Văn học'),
('Ngoại ngữ'),
('Sách quốc tế');

-- =======================================================
-- 4) SHELF LOCATION
-- =======================================================
INSERT INTO ShelfLocation (shelf_name, description) VALUES
('Kệ CNTT', 'Sách Công nghệ thông tin'),
('Kệ CKĐL', 'Sách Cơ khí động lực'),
('Kệ CT', 'Sách Công trình'),
('Kệ KTVT', 'Sách Kinh tế vận tải'),
('Kệ KHUD & LCT', 'Luật – Chính trị – Khoa học ứng dụng'),
('Kệ Văn học', 'Sách văn học'),
('Kệ Quốc tế', 'Sách học thuật nước ngoài');

-- =======================================================
-- 5) STAFF (KHÔNG CHÈN admin — đã có sẵn)
-- =======================================================
INSERT INTO Staff (username, password, full_name, role) VALUES
('thuthu_hn1', '123', 'Nguyễn Thị Hạnh', 'Staff'),
('thuthu_vy1', '123', 'Lê Đức Minh', 'Staff');

-- =======================================================
-- 6) READER
-- =======================================================
INSERT INTO Reader (full_name, birthday, gender, phone, reader_type) VALUES
('Nguyễn Văn Cường', '2003-03-10', 'Nam', '0912345678', 'SV – Viện CKĐL'),
('Trần Thị Phương', '2002-11-22', 'Nữ', '0987654321', 'SV – Viện CNTT'),
('Hoàng Quốc Việt', '2001-09-17', 'Nam', '0902233445', 'SV – Viện KTVT'),
('Vũ Minh Hoàng', '1990-07-14', 'Nam', '0977788899', 'GV – Viện Công trình'),
('Phạm Thị Hương', '1992-05-29', 'Nữ', '0911223344', 'GV – Viện KHUD & LCT'),
('Nguyễn Thùy Linh', '2003-10-19', 'Nữ', '0966778899', 'SV – Quản trị');

-- =======================================================
-- 7) BOOK
-- =======================================================
INSERT INTO Book (book_title, publish_year, price, quantity, cover_image, 
                  author_id, publisher_id, category_id, shelf_id)
VALUES
('Giáo trình Cấu trúc dữ liệu & Giải thuật', 2021, 125000, 45, NULL, 2, 2, 1, 1),
('Giáo trình Hệ thống cơ điện tử', 2020, 150000, 30, NULL, 3, 1, 2, 2),
('Giáo trình Thủy lực công trình', 2019, 135000, 25, NULL, 1, 1, 3, 3),
('Quản trị Logistics vận tải', 2022, 160000, 35, NULL, 2, 1, 4, 4),
('Pháp luật đại cương', 2020, 95000, 40, NULL, 5, 1, 5, 5),
('Harry Potter – Tập 1', 1997, 180000, 60, NULL, 4, 3, 9, 7),
('Giáo trình Marketing căn bản', 2021, 110000, 20, NULL, 2, 1, 6, 4);

-- =======================================================
-- 8) BOOKING
-- =======================================================
INSERT INTO Booking (reader_id, book_id, status) VALUES
(2, 1, 'Pending'),
(1, 2, 'Approved'),
(3, 6, 'Pending');

-- =======================================================
-- 9) LOAN TICKET
-- =======================================================
INSERT INTO LoanTicket (reader_id, staff_id, borrow_date, due_date, status, total_fine) VALUES
(1, 2, '2025-01-05 09:00:00', '2025-01-15', 'Borrowing', 0),
(2, 3, '2025-01-07 14:20:00', '2025-01-17', 'Returned', 5000),
(4, 2, '2025-01-10 10:00:00', '2025-01-20', 'Borrowing', 0);

-- =======================================================
-- 10) LOAN DETAIL
-- =======================================================
INSERT INTO LoanDetail (ticket_id, book_id, quantity, note) VALUES
(1, 1, 1, 'Sinh viên ưu tiên mượn trước'),
(1, 2, 1, NULL),
(2, 6, 1, 'Đã trả'),
(3, 3, 1, 'Phục vụ giảng dạy');

-- =======================================================
-- 11) PENALTY
INSERT INTO Penalty (ticket_id, amount, reason) VALUES
(2, 5000, 'Trả sách trễ 1 ngày');

-- =======================================================
-- 12) REGULATION
-- =======================================================
INSERT INTO Regulation (regulation_name, value, description) VALUES
('SoLuongSachToiDa', 5, 'Số lượng sách tối đa được mượn'),
('NgayMuonToiDa', 10, 'Số ngày mượn tối đa'),
('TienPhatMoiNgay', 5000, 'Tiền phạt mỗi ngày trả trễ');

-- =======================================================
-- 13) SUPPLIER
-- =======================================================
INSERT INTO Supplier (supplier_name, address, phone) VALUES
('Công ty Sách Giáo dục Việt Nam', 'Hà Nội', '02433789999'),
('Nhà sách Minh Khai', 'TP. Hồ Chí Minh', '02839255555');

-- =======================================================
-- 14) IMPORT BOOK
-- =======================================================
INSERT INTO ImportBook (book_id, quantity, import_price, import_date) VALUES
(1, 40, 80000, '2024-11-01'),
(2, 50, 90000, '2024-11-01'),
(3, 35, 70000, '2024-11-03'),
(4, 20, 120000, '2024-11-05');

SET FOREIGN_KEY_CHECKS = 1;
