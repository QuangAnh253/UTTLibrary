# 📚 HỆ THỐNG QUẢN LÝ THƯ VIỆN THÔNG MINH UTT (UTT-LIB)

**Dự án Bài tập lớn – Lập trình trực quan C#**
**Nhóm thực hiện:** Nhóm 4
**GVHD:** ThS. Phạm Đức Anh

---

## 📅 BẢNG PHÂN CÔNG & TIẾN ĐỘ (TASK TRACKING)

Toàn bộ lịch làm việc, phân công nhiệm vụ và tiến độ được theo dõi qua Google Sheet bên dưới.
Các thành viên cần cập nhật và kiểm tra task hằng ngày.

👉 **LINK THEO DÕI TIẾN ĐỘ:** *https://docs.google.com/spreadsheets/d/1exitOhMZ5CnytaU-PrWMDOcqPjOUvZh-dG-emB6qzh8/edit?usp=sharing*

---

## 🛠️ CÔNG NGHỆ SỬ DỤNG (TECH STACK)

* **Ngôn ngữ:** C# (.NET Framework – Windows Forms Application)
* **Cơ sở dữ liệu:** Microsoft SQL Server
* **Kiến trúc:** Mô hình 3 lớp (3-Layer Architecture)

  * **GUI:** Giao diện người dùng
  * **BLL:** Xử lý nghiệp vụ
  * **DAL:** Truy xuất dữ liệu
  * **DTO:** Truyền tải dữ liệu
* **Công cụ:** Visual Studio 2019/2022, Git/GitHub

---

## 🚀 HƯỚNG DẪN CÀI ĐẶT (GETTING STARTED)

### **1. Làm việc với Git (Clone, Pull, Commit, Push)**

#### **1.1. Clone dự án lần đầu**

```bash
git clone https://github.com/QuangAnh253/UTT-Library-Management.git
```

Sau khi clone xong, chạy:

```bash
cd UTT.QuanLyThuVien
```

---

### **1.2. Quy trình làm việc chuẩn mỗi khi lập trình**

Để tránh xung đột code và ghi đè lẫn nhau, mỗi thành viên phải tuân thủ đúng thứ tự sau:

---

## **QUY TRÌNH CHUẨN: PULL → CODE → COMMIT → PUSH**

### **Bước 1 — Luôn PULL trước khi bắt đầu code**

Mục tiêu: cập nhật code mới nhất từ server về máy.

```bash
git pull origin main
```

Nếu nhóm dùng nhánh khác (develop, feature…), chỉnh lại tương ứng.

---

### **Bước 2 — Tiến hành lập trình**

* Chỉ sửa đúng module được giao.
* Không đụng vào các file nằm trong “DO NOT TOUCH”.
* Kiểm tra kỹ các file trước khi commit.

---

### **Bước 3 — Thêm file vào staging**

```bash
git add .
```

Hoặc thêm từng file:

```bash
git add path/to/file
```

---

### **Bước 4 — Commit với message chuẩn**

**Quy tắc message:** viết rõ ràng, mạch lạc, có phân loại:

| Loại commit   | Cú pháp   | Ví dụ                                            |
| ------------- | --------- | ------------------------------------------------ |
| Tính năng mới | feat:     | `feat: thêm chức năng quản lý thể loại`          |
| Sửa lỗi       | fix:      | `fix: sửa lỗi không load được danh sách tác giả` |
| Tối ưu        | refactor: | `refactor: tối ưu code DAL_TacGia`               |
| Giao diện     | ui:       | `ui: chỉnh layout form quản lý sách`             |
| Cấu hình      | config:   | `config: cập nhật connection string`             |
| Dữ liệu       | data:     | `data: thêm dữ liệu mẫu cho bảng TheLoai`        |

**Ví dụ commit đúng chuẩn:**

```bash
git commit -m "feat: thêm module quản lý nhà xuất bản"
```

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

### **Bước 5 — PULL thêm lần nữa để tránh xung đột**

**Trước khi push**, luôn chạy lệnh sau:

- Nhánh `main` được **bảo vệ** và **yêu cầu review** trước khi merge.

---

## Bước 1: Bắt đầu task mới

Luôn bắt đầu từ nhánh `main` đã được cập nhật:

```bash
# Chuyển về nhánh main
git checkout main

# Lấy code mới nhất
git pull origin main

Tạo **nhánh mới** cho nhiệm vụ theo cấu trúc:

[ten-thanh-vien]/[mo-ta-ngan-task]
```

Nếu có xung đột:

* Mở Visual Studio hoặc Git để resolve conflict
* Sau khi resolve:

**Ví dụ:**

```bash
  git add .
  git commit -m "fix: resolve conflict module the loai"
```

---

### **Bước 6 — Push lên Git**

Đẩy code lên repository:

```bash
git push origin main
```

---

## **TÓM TẮT QUY TRÌNH GIT CHUẨN**

```
git pull origin main
↓
Code
↓
git add .
↓
git commit -m "message"
↓
git pull origin main   # tránh conflict
↓
git push origin main
```

---

### 2. Khởi tạo cơ sở dữ liệu (BẮT BUỘC)

1. Mở **SQL Server Management Studio (SSMS)**
2. Chạy file `create-tables.sql` để tạo database và các bảng
3. Chạy file `data-seed.sql` để sinh dữ liệu mẫu

   * Tài khoản mẫu:

     * **Admin:** admin
     * **Password:** adminpass

### 3. Cấu hình Connection String

Mở `App.config` trong project `UTT.Library.GUI` và chỉnh dòng:

* SQL mặc định:

  ```
  Data Source=.;Initial Catalog=QuanLyThuVienUTT;Integrated Security=True
  ```
* SQL Express:

  ```
  Data Source=.\SQLEXPRESS;Initial Catalog=QuanLyThuVienUTT;Integrated Security=True
```

---

## 📘 HƯỚNG DẪN LẬP TRÌNH MODULE (DEVELOPER GUIDE)

**Lưu ý:** Sử dụng module mẫu **Quản lý Tác giả** để đảm bảo đồng bộ.
File mẫu: `GUI/Forms/DanhMuc/frmQuanLyTacGia.cs`

---

### **QUY TRÌNH 4 BƯỚC**

---

### **1. Tầng DTO (Data Transfer Object)**

* Mở project **UTT.Library.DTO**
* Tạo file class mới (VD: `DTO_TheLoai.cs`)
* Các property phải trùng tên cột SQL
* Có thể copy từ `DTO_TacGia.cs` rồi đổi tên

---

### **2. Tầng DAL (Data Access Layer)**

* Mở **UTT.Library.DAL → Repositories**
* Tạo file mới (VD: `DAL_TheLoai.cs`)
* Cài đặt 4 hàm chính:

  * `GetDanhSach()`
  * `Them()`
  * `Sua()`
  * `Xoa()`
* Bắt buộc sử dụng parameter dạng `@Parameter`, không được nối chuỗi lệnh SQL

---

### **3. Tầng BLL (Business Logic Layer)**

* Mở **UTT.Library.BLL → Services**
* Tạo file mới (VD: `BLL_TheLoai.cs`)
* Gọi hàm từ DAL
* Thêm logic nghiệp vụ (VD: Không cho thêm nếu tên trống)

---

### **4. Tầng GUI (Giao diện)**

* Mở **UTT.Library.GUI → Forms/DanhMuc**
* Tạo form mới (VD: `frmQuanLyTheLoai.cs`)
* Thiết kế giao diện tương tự `frmQuanLyTacGia`
* Copy code xử lý và đổi tên biến phù hợp
* Sử dụng `ValidationHelper.IsRequired(...)` để kiểm tra nhập liệu

---

## ⛔ QUY TẮC BẤT KHẢ XÂM PHẠM (DO NOT TOUCH)

Các file sau **tuyệt đối không chỉnh sửa**

* `UTT.Library.DAL/Database/DatabaseHelper.cs` – lớp lõi xử lý Database
* `UTT.Library.GUI/Forms/Common/frmMain.cs` – giao diện khung chính
* `UTT.Library.GUI/Common/Session.cs` – quản lý phiên đăng nhập

Nếu phát hiện lỗi trong các file trên, báo ngay vào nhóm Zalo.

---

## 📝 QUY TẮC ĐẶT TÊN (NAMING CONVENTION)

| Loại control / biến | Quy tắc       | Ví dụ                     |
| ------------------- | ------------- | ------------------------- |
| TextBox             | `txtTenBien`  | txtMaSach, txtTenDangNhap |
| Button              | `btnHanhDong` | btnThem, btnLuu           |
| ComboBox            | `cboTen`      | cboTheLoai                |
| DataGridView        | `dgvDanhSach` | dgvTacGia                 |
| Biến cục bộ         | camelCase     | maSach, danhSachTacGia    |

---
