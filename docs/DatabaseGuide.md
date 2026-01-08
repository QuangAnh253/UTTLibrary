# 📘 UTT Library Database Guide

**Phiên bản:** 1.0  
**Cập nhật:** 2025  
**Áp dụng cho đồ án:** _Hệ thống Quản lý Thư viện Thông minh UTT_

---

## #️⃣ 1. Giới thiệu

Tài liệu này mô tả:

- Cấu trúc database của hệ thống
- Chức năng từng bảng
- Quan hệ giữa các bảng
- Hướng dẫn reset và seed dữ liệu mẫu
- Lưu ý khi sử dụng trong dự án **Java Swing + JDBC**

Database được thiết kế theo mô hình quan hệ chuẩn hóa, phù hợp với nghiệp vụ của thư viện trường Đại học Công nghệ GTVT (UTT).

---

## #️⃣ 2. Thông tin chung về database

| Thuộc tính    | Giá trị                                 |
| ------------- | --------------------------------------- |
| Tên DB        | UTTLibraryDB                            |
| Charset       | utf8mb4                                 |
| Collation     | utf8mb4_unicode_ci                      |
| Số bảng       | 14                                      |
| Có Seed Data  | Có                                      |
| Loại hệ thống | Library Management – Java Swing + MySQL |

---

## #️⃣ 3. Danh sách bảng & chức năng

Dưới đây là mô tả ngắn gọn 14 bảng.

### 3.1. Danh mục cơ bản

#### 📌 Author

Thông tin tác giả / giảng viên UTT.

| Cột         | Kiểu         | Mô tả           |
| ----------- | ------------ | --------------- |
| author_id   | INT          | Primary key     |
| author_name | VARCHAR(100) | Tên tác giả     |
| website     | VARCHAR(100) | Website cá nhân |
| note        | TEXT         | Ghi chú         |

#### 📌 Publisher

Nhà xuất bản.

| Cột            | Kiểu         | Mô tả       |
| -------------- | ------------ | ----------- |
| publisher_id   | INT          | Primary key |
| publisher_name | VARCHAR(100) | Tên NXB     |
| address        | VARCHAR(255) | Địa chỉ     |
| email          | VARCHAR(100) | Email       |

#### 📌 Category

Thể loại sách – bám sát ngành đào tạo tại UTT.

---

### 3.2. Vị trí và kho

#### 📌 ShelfLocation

Vị trí kệ theo khu vực thư viện.

---

### 3.3. Sách & tác nghiệp

#### 📌 Book

Thông tin sách – bao gồm quan hệ tới các bảng:

- Author
- Publisher
- Category
- ShelfLocation

---

### 3.4. Bạn đọc

#### 📌 Reader

Danh sách sinh viên & giảng viên.

---

### 3.5. Mượn – trả

#### 📌 LoanTicket

Phiếu mượn sách.

#### 📌 LoanDetail

Chi tiết từng cuốn trong một phiếu.

---

### 3.6. Tiền phạt

#### 📌 Penalty

Lưu tiền phạt khi trả muộn.

---

### 3.7. Quy định

#### 📌 Regulation

Quy định của thư viện, bao gồm:

- Số sách mượn tối đa
- Số ngày mượn
- Mức phạt mỗi ngày

---

### 3.8. Nhà cung cấp & Nhập sách

#### 📌 Supplier

Đối tác cung cấp sách.

#### 📌 ImportBook

Thông tin nhập sách (số lượng – giá nhập – ngày nhập).

---

### 3.9. Đặt trước

#### 📌 Booking

Sinh viên đặt trước sách.

---

### 3.10. Đăng nhập

#### 📌 Staff

Tài khoản đăng nhập (Admin + Thủ thư).

---

## #️⃣ 4. Quan hệ giữa các bảng

```

Author (1)───(N) Book (N)───(1) Category
Publisher (1)───(N) Book
ShelfLocation (1)───(N) Book

Reader (1)───(N) LoanTicket (1)───(N) LoanDetail (N)───(1) Book
Staff (1)───(N) LoanTicket

LoanTicket (1)───(N) Penalty
Reader (1)───(N) Booking
Book (1)───(N) Booking
Book (1)───(N) ImportBook
Supplier (1)───(N) ImportBook

```

**Chuẩn hóa: 3NF**

---

## #️⃣ 5. File vận hành database

```

database/
│── create-tables.sql
│── seed-data.sql
│── reset.sql
│── database_guide.md   ← (file bạn đang đọc)

```

---

## #️⃣ 6. Quy trình khởi tạo Database

### ✔ Bước 1 — Tạo DB & các bảng

Chạy file:

```

create-tables.sql

```

### ✔ Bước 2 — Seed dữ liệu mẫu UTT

Chạy:

```

seed-data.sql

```

Bao gồm:

- Khoa / viện của UTT
- Sách đào tạo theo ngành
- Giảng viên – sinh viên
- Tài khoản thủ thư
- Phiếu mượn, đặt sách, phạt…

### ✔ Bước 3 — Kiểm tra dữ liệu

Ví dụ:

```sql
SELECT * FROM Book;
SELECT * FROM Reader;
SELECT * FROM LoanTicket;
```

---

## #️⃣ 7. Cách reset hoàn toàn database

Nếu cần chạy lại từ đầu, dùng file:

```
reset.sql
```

Nội dung:

```sql
DROP DATABASE IF EXISTS UTTLibraryDB;
SOURCE create-tables.sql;
SOURCE seed-data.sql;
```

---

## #️⃣ 8. Lưu ý khi dùng với Java Swing + JDBC

### ⚠ 1. Dùng utf8mb4 để hỗ trợ tiếng Việt

→ Đã thiết lập trong DB.

### ⚠ 2. JDBC: useSSL=false & serverTimezone=UTC

Ví dụ:

```java
String url = "jdbc:mysql://localhost:3306/UTTLibraryDB?useSSL=false&allowPublicKeyRetrieval=true&serverTimezone=UTC";
```

### ⚠ 3. Không dùng ký tự unicode đặc biệt trong username Staff

→ Tránh lỗi JDBC (đã xử lý trong seed).

### ⚠ 4. Các bảng có FK — INSERT phải đúng thứ tự

→ Seed data đã sắp sẵn.

### ⚠ 5. Tránh xóa bảng sai thứ tự khi bật FK

→ `reset.sql` đã tự xử lý.

---

## #️⃣ 9. Liên hệ Module Code

| Bảng          | Module             | Package MVC                           |
| ------------- | ------------------ | ------------------------------------- |
| Author        | Quản lý tác giả    | model / dao / controller / view/panel |
| Publisher     | Quản lý NXB        | …                                     |
| Category      | Quản lý thể loại   | …                                     |
| ShelfLocation | Quản lý kệ sách    | …                                     |
| Book          | Quản lý sách       | …                                     |
| Reader        | Quản lý độc giả    | …                                     |
| Staff         | Login + quản lý NV | auth + staff module                   |
| LoanTicket    | Mượn sách          | loan-ticket                           |
| LoanDetail    | Chi tiết mượn      | loan-detail                           |
| Penalty       | Tính phạt          | penalty                               |
| Regulation    | Quy định thư viện  | regulation                            |
| Booking       | Đặt trước          | booking                               |
| Supplier      | Nhà cung cấp       | supplier                              |
| ImportBook    | Nhập sách          | import-book                           |

---

## #️⃣ 10. Kết luận

Tài liệu mô tả đầy đủ:

✔ Cấu trúc database
✔ Chức năng từng bảng
✔ Quan hệ logic
✔ Cách seed & reset
✔ Cách kết nối JDBC
✔ Mapping với MVC trong dự án Java Swing
