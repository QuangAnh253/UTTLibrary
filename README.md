# 📘 UTT Library Management – Internal README

Dự án quản lý thư viện theo mô hình 3-layer: **Model – DAO – Controller – View**.  
Phát triển bằng **Java Swing**, kết nối **MySQL**, chạy trên NetBeans hoặc IntelliJ.

---

## 🚀 1) Mục tiêu dự án

- Xây dựng hệ thống quản lý thư viện hoàn chỉnh.
- Mỗi thành viên tự chịu trách nhiệm module của mình.
- Code theo chuẩn thống nhất để tránh xung đột và dễ bảo trì.

---

## 🗂 2) Cấu trúc thư mục chính

src/
└── com/uttlibrary/
├── main/ → Main.java
├── auth/ → Login
├── util/ → DB, Validator, Helper, MessageBox
├── component/ → UI component dùng chung
├── model/ → Class đại diện bảng DB
├── dao/ → Data Access
├── controller/ → Xử lý nghiệp vụ
└── view/ → GUI panel + form

---

## 🔌 3) Cấu hình Database (MySQL)

- URL: `jdbc:mysql://localhost:3306/UTTLibraryDB`
- User: `root`
- Pass: `Admin@123`

File cấu hình: `/src/com/uttlibrary/util/DBConnect.java`

---

## 🧰 4) Bộ công cụ dùng chung (ai code cũng phải gọi)

### 👉 DBConnect

Dùng để mở connection MySQL.

### 👉 Validator

Check trống, số, email, độ dài.

### 👉 DateFormatter

Chuyển đổi định dạng ngày SQL ↔ hiển thị.

### 👉 MessageBox

Hiển thị thông báo, lỗi, cảnh báo, confirm.

### 👉 SessionHelper

Lưu user đăng nhập.

### 👉 BaseDAO

Tất cả DAO phải extends.

### 👉 BaseController

Tất cả Controller phải extends.

---

## 🧠 5) **Quy tắc code CHUẨN cho cả team**

### 5.1 Quy tắc đặt tên

- CamelCase (BookDAO, BorrowDetailController)
- Biến: `bookList`, `readerId`
- Hằng số: `DEFAULT_PATTERN`
- SQL dùng lowercase_underscore

### 5.2 Quy tắc viết DAO

- Tất cả DAO extends BaseDAO
- Khuôn mẫu fix cứng:
  - `findAll()`
  - `findById()`
  - `insert()`
  - `update()`
  - `delete()`

### 5.3 Quy tắc Controller

- Phải override:
  - `loadTable()`
  - `add()`
  - `update()`
  - `delete()`

### 5.4 Quy tắc Panel

- Mỗi panel tương ứng 1 module
- Không viết code SQL trong panel
- Không xử lý nghiệp vụ trong view

---

Đây là phiên bản sửa lại, chi tiết hơn từ lúc clone dự án đến push, bao gồm cả bước **pull trước khi push**:

## 🔄 6) **Quy trình Git – BẮT BUỘC CHUẨN HÓA**

### ✔ 1) Clone dự án về máy

git clone <repo-url>
cd <ten-du-an>

### ✔ 2) Tạo branch cho từng người (theo module / tên)

git checkout -b <ten-module>/<ten-ban>

Ví dụ:
git checkout -b book/duythanh
git checkout -b loan/quanganh

> Mỗi người làm việc trên **branch riêng** để tránh xung đột.

### ✔ 3) Khi bắt đầu làm việc mỗi ngày, pull code mới từ remote về

git checkout <branch-cua-ban>
git pull origin main

> Đảm bảo luôn đồng bộ với main trước khi code hoặc push.

### ✔ 4) Thêm, commit và push code sau khi hoàn thành

git add .
git commit -m "[module] mô tả ngắn gọn thay đổi"
git push origin <branch-cua-ban>

**Lưu ý:**

- Không commit rác, không ghi `"update"`, `"fix"`, `"xong"`.
- Ghi theo mẫu:

  - `[Book] Thêm DAO + model + controller`
  - `[Login] Hoàn thiện UI + validate`
  - `[Reader] Xử lý CRUD + load table`

### ✔ 5) Merge vào main

git checkout main
git pull origin main
git merge <branch-cua-thanh-vien>
git push origin main

---

## 👥 7) PHÂN CÔNG CHI TIẾT THEO FILE

### 👑 1) **QUANG ANH**

#### A. CORE

- main/Main.java
- auth/LoginView.java
- auth/LoginController.java
- auth/AuthService.java
- util/\* (DBConnect, Validator, DateFormatter, MessageBox, SessionHelper)
- component/\*
- view/MainView.java
- view/DashboardPanel.java
- view/StatisticPanel.java
- dao/BaseDAO.java
- controller/BaseController.java

#### B. 3 module:

**Book – LoanTicket – LoanDetail**

- model/\*
- dao/\*
- controller/\*
- view/panel/\*

---

### 🟩 2) **DUY THÀNH**

**Reader – Staff – Booking**

- model/Reader.java
- model/Staff.java
- model/Booking.java
- dao/\*
- controller/\*
- view/panel/\*

---

### 🟧 3) **THÙY TRANG – Supplier – ImportBook – Regulation**

- model/\*
- dao/\*
- controller/\*
- view/panel/\*

---

### 🟦 4) **HỒNG – Category – ShelfLocation **

- model/\*
- dao/\*
- controller/\*
- view/panel/\*

---

### 🟪 5) **LỘC – Author – Publisher**

- model/\*
- dao/\*
- controller/\*
- view/panel/\*

---

## 🧪 8) Hướng dẫn chạy dự án

1. Import project vào NetBeans / IntelliJ
2. Import thư viện trong thư mục `lib/`
3. Import file SQL
4. Chạy `Main.java`

---

## 📌 9) Lưu ý cuối

- Không push file `.class`, `dist/`, `build/`
- CÓ push thư viện `.jar` trong `lib/`
- Đặt tên file theo đúng mẫu nhóm đã thống nhất
- Không sửa file của người khác khi chưa trao đổi

---