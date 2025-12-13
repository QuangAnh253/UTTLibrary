-- ===================================================================
-- SCRIPT TẠO CƠ SỞ DỮ LIỆU: HỆ THỐNG QUẢN LÝ THƯ VIỆN UTT
-- Dự án: Quản Lý Thư Viện Thông Minh - Trường ĐH Công nghệ GTVT
-- Kiến trúc: 3-Layer (GUI - BLL - DAL)
-- CSDL: Microsoft SQL Server
-- Phiên bản: 2.1 - ĐÃ SỬA LỖI THỨ TỰ TẠO BẢNG
-- ===================================================================

USE master;
GO

-- Xóa database cũ nếu tồn tại
IF EXISTS (SELECT name FROM sys.databases WHERE name = 'QuanLyThuVienUTT')
BEGIN
    ALTER DATABASE QuanLyThuVienUTT SET SINGLE_USER WITH ROLLBACK IMMEDIATE;
    DROP DATABASE QuanLyThuVienUTT;
END
GO

-- Tạo database mới
CREATE DATABASE QuanLyThuVienUTT;
GO

USE QuanLyThuVienUTT;
GO

-- ===================================================================
-- BƯỚC 1: TẠO CÁC BẢNG DANH MỤC KHÔNG PHỤ THUỘC (LEVEL 0)
-- ===================================================================

-- Module 01 (Quang Anh): Quản lý Tài khoản
CREATE TABLE TAIKHOAN (
    TenDangNhap VARCHAR(50) PRIMARY KEY,
    MatKhau VARCHAR(255) NOT NULL,
    Quyen INT NOT NULL CHECK (Quyen IN (1, 2, 3)), -- 1: Admin, 2: Thu thu, 3: Sinh vien
    TrangThai BIT DEFAULT 1, -- 1: Hoat dong, 0: Khoa
    NgayTao DATETIME DEFAULT GETDATE(),
    CONSTRAINT CHK_TenDangNhap CHECK (LEN(TenDangNhap) >= 3)
);
GO

-- Module 14 (Hồng): Quản lý Tác giả
CREATE TABLE TACGIA (
    MaTacGia VARCHAR(20) PRIMARY KEY,
    TenTacGia NVARCHAR(100) NOT NULL,
    GhiChu NVARCHAR(255)
);
GO

-- Module 15 (Hồng): Quản lý Nhà xuất bản
CREATE TABLE NHAXUATBAN (
    MaNXB VARCHAR(20) PRIMARY KEY,
    TenNXB NVARCHAR(100) NOT NULL,
    DiaChi NVARCHAR(255),
    SDT VARCHAR(15)
);
GO

-- Module 16 (Hồng): Quản lý Thể loại
CREATE TABLE THELOAI (
    MaTheLoai VARCHAR(20) PRIMARY KEY,
    TenTheLoai NVARCHAR(100) NOT NULL,
    MoTa NVARCHAR(255)
);
GO

-- Module 17 (Lộc): Quản lý Ngôn ngữ
CREATE TABLE NGONNGU (
    MaNgonNgu VARCHAR(20) PRIMARY KEY,
    TenNgonNgu NVARCHAR(100) NOT NULL,
    MoTa NVARCHAR(255)
);
GO

-- Module 02 (Quang Anh): Quản lý Nhà cung cấp
CREATE TABLE NHACUNGCAP (
    MaNCC VARCHAR(20) PRIMARY KEY,
    TenNCC NVARCHAR(100) NOT NULL,
    DiaChi NVARCHAR(255),
    SDT VARCHAR(15),
    Email VARCHAR(100),
    CONSTRAINT CHK_Email_NCC CHECK (Email LIKE '%@%')
);
GO

-- Module 09 (Thùy Trang): Quản lý Kho lưu trữ
CREATE TABLE KHOSACH (
    MaKho VARCHAR(20) PRIMARY KEY,
    TenKho NVARCHAR(100) NOT NULL,
    SucChua INT DEFAULT 0 CHECK (SucChua >= 0),
    MoTa NVARCHAR(255)
);
GO

-- Module 19 (Lộc): Quản lý Khoa
CREATE TABLE KHOA (
    MaKhoa VARCHAR(20) PRIMARY KEY,
    TenKhoa NVARCHAR(100) NOT NULL,
    MoTa NVARCHAR(255)
);
GO

-- Bảng tham số quy định
CREATE TABLE THAMSOQUYDINH (
    MaThamSo VARCHAR(50) PRIMARY KEY,
    TenThamSo NVARCHAR(100) NOT NULL,
    GiaTri NVARCHAR(255) NOT NULL,
    MoTa NVARCHAR(255)
);
GO

-- ===================================================================
-- BƯỚC 2: TẠO CÁC BẢNG CẤP 1 (PHỤ THUỘC VÀO LEVEL 0)
-- ===================================================================

-- Module 11 (Duy Thành): Quản lý Nhân viên
CREATE TABLE NHANVIEN (
    MaNV VARCHAR(20) PRIMARY KEY,
    HoTen NVARCHAR(100) NOT NULL,
    SDT VARCHAR(15),
    Email VARCHAR(100),
    NgayVaoLam DATE DEFAULT GETDATE(),
    TenDangNhap VARCHAR(50),
    CONSTRAINT FK_NV_TaiKhoan FOREIGN KEY (TenDangNhap) REFERENCES TAIKHOAN(TenDangNhap) ON DELETE SET NULL,
    CONSTRAINT CHK_Email_NV CHECK (Email LIKE '%@%')
);
GO

-- Module 18 (Lộc): Quản lý Vị trí Kệ
CREATE TABLE VITRI (
    MaViTri VARCHAR(20) PRIMARY KEY,
    TenKe NVARCHAR(50) NOT NULL,
    SoTang VARCHAR(20),
    MaKho VARCHAR(20),
    CONSTRAINT FK_ViTri_Kho FOREIGN KEY (MaKho) REFERENCES KHOSACH(MaKho) ON DELETE SET NULL
);
GO

-- Module 19 (Lộc): Quản lý Lớp
CREATE TABLE LOP (
    MaLop VARCHAR(20) PRIMARY KEY,
    TenLop NVARCHAR(100) NOT NULL,
    KhoaHoc VARCHAR(20),
    MaKhoa VARCHAR(20),
    CONSTRAINT FK_Lop_Khoa FOREIGN KEY (MaKhoa) REFERENCES KHOA(MaKhoa) ON DELETE SET NULL
);
GO

-- Module 06 (Thùy Trang): Quản lý Hồ sơ Sách
CREATE TABLE SACH (
    MaSach INT IDENTITY(1,1) PRIMARY KEY,
    TenSach NVARCHAR(255) NOT NULL,
    MaTheLoai VARCHAR(20),
    MaTacGia VARCHAR(20),
    MaNXB VARCHAR(20),
    MaNgonNgu VARCHAR(20),
    NamXB INT CHECK (NamXB > 1900 AND NamXB <= YEAR(GETDATE())),
    GiaTien DECIMAL(18,0) DEFAULT 0 CHECK (GiaTien >= 0),
    AnhBia NVARCHAR(MAX),
    SoLuongTon INT DEFAULT 0 CHECK (SoLuongTon >= 0),
    MoTa NVARCHAR(MAX),
    CONSTRAINT FK_Sach_TheLoai FOREIGN KEY (MaTheLoai) REFERENCES THELOAI(MaTheLoai) ON DELETE SET NULL,
    CONSTRAINT FK_Sach_TacGia FOREIGN KEY (MaTacGia) REFERENCES TACGIA(MaTacGia) ON DELETE SET NULL,
    CONSTRAINT FK_Sach_NXB FOREIGN KEY (MaNXB) REFERENCES NHAXUATBAN(MaNXB) ON DELETE SET NULL,
    CONSTRAINT FK_Sach_NgonNgu FOREIGN KEY (MaNgonNgu) REFERENCES NGONNGU(MaNgonNgu) ON DELETE SET NULL
);
GO

-- Module 10 (Duy Thành): Quản lý Sinh viên (Độc giả)
CREATE TABLE DOCGIA (
    MaSV VARCHAR(20) PRIMARY KEY,
    HoTen NVARCHAR(100) NOT NULL,
    NgaySinh DATE,
    GioiTinh NVARCHAR(10) CHECK (GioiTinh IN (N'Nam', N'Nữ', N'Khác')),
    SDT VARCHAR(15),
    Email VARCHAR(100),
    MaLop VARCHAR(20),
    MaKhoa VARCHAR(20),
    TenDangNhap VARCHAR(50),
    CONSTRAINT FK_DG_Lop FOREIGN KEY (MaLop) REFERENCES LOP(MaLop) ON DELETE SET NULL,
    CONSTRAINT FK_DG_Khoa FOREIGN KEY (MaKhoa) REFERENCES KHOA(MaKhoa) ON DELETE SET NULL,
    CONSTRAINT FK_DG_TaiKhoan FOREIGN KEY (TenDangNhap) REFERENCES TAIKHOAN(TenDangNhap) ON DELETE SET NULL,
    CONSTRAINT CHK_Email_DG CHECK (Email LIKE '%@%')
);
GO

-- ===================================================================
-- BƯỚC 3: TẠO CÁC BẢNG CẤP 2 (PHỤ THUỘC VÀO LEVEL 1)
-- ===================================================================

-- Module 03 (Quang Anh): Quản lý Phiếu nhập
CREATE TABLE PHIEUNHAP (
    MaPhieuNhap VARCHAR(20) PRIMARY KEY,
    MaNV VARCHAR(20),
    MaNCC VARCHAR(20),
    NgayNhap DATETIME DEFAULT GETDATE(),
    TongTien DECIMAL(18,0) DEFAULT 0 CHECK (TongTien >= 0),
    GhiChu NVARCHAR(255),
    CONSTRAINT FK_PN_NV FOREIGN KEY (MaNV) REFERENCES NHANVIEN(MaNV) ON DELETE SET NULL,
    CONSTRAINT FK_PN_NCC FOREIGN KEY (MaNCC) REFERENCES NHACUNGCAP(MaNCC) ON DELETE SET NULL
);
GO

-- Module 07 (Thùy Trang): Quản lý Thanh lý
CREATE TABLE SACHTHANHLY (
    MaThanhLy INT IDENTITY(1,1) PRIMARY KEY,
    MaSach INT NOT NULL,
    LyDo NVARCHAR(255) NOT NULL,
    NgayXuLy DATE DEFAULT GETDATE(),
    NguoiXuLy VARCHAR(20),
    SoLuongThanhLy INT NOT NULL CHECK (SoLuongThanhLy > 0),
    CONSTRAINT FK_STL_Sach FOREIGN KEY (MaSach) REFERENCES SACH(MaSach) ON DELETE CASCADE,
    CONSTRAINT FK_STL_NV FOREIGN KEY (NguoiXuLy) REFERENCES NHANVIEN(MaNV) ON DELETE SET NULL
);
GO

-- Module 08 (Thùy Trang): Quản lý Đặt trước
CREATE TABLE PHIEUDATTRUOC (
    MaPhieuDat INT IDENTITY(1,1) PRIMARY KEY,
    MaSV VARCHAR(20) NOT NULL,
    MaSach INT NOT NULL,
    NgayDat DATETIME DEFAULT GETDATE(),
    TrangThai NVARCHAR(50) DEFAULT N'Đang chờ' CHECK (TrangThai IN (N'Đang chờ', N'Đã có sách', N'Đã hủy')),
    NgayHetHan DATETIME,
    CONSTRAINT FK_PDT_SV FOREIGN KEY (MaSV) REFERENCES DOCGIA(MaSV) ON DELETE CASCADE,
    CONSTRAINT FK_PDT_Sach FOREIGN KEY (MaSach) REFERENCES SACH(MaSach) ON DELETE CASCADE
);
GO

-- Module 12 (Duy Thành): Quản lý Thẻ thư viện
CREATE TABLE THETHUVIEN (
    MaThe VARCHAR(20) PRIMARY KEY,
    MaSV VARCHAR(20) NOT NULL,
    NgayCap DATE DEFAULT GETDATE(),
    NgayHetHan DATE NOT NULL,
    TrangThai NVARCHAR(20) DEFAULT N'Hoạt động' CHECK (TrangThai IN (N'Hoạt động', N'Hết hạn', N'Khóa')),
    CONSTRAINT FK_TheTV_DG FOREIGN KEY (MaSV) REFERENCES DOCGIA(MaSV) ON DELETE CASCADE,
    CONSTRAINT CHK_HanThe CHECK (NgayHetHan > NgayCap)
);
GO

-- Module 13 (Duy Thành): Quản lý Vi phạm
CREATE TABLE VIPHAM (
    MaViPham INT IDENTITY(1,1) PRIMARY KEY,
    MaSV VARCHAR(20) NOT NULL,
    LoiViPham NVARCHAR(255) NOT NULL,
    HinhThucXuLy NVARCHAR(255),
    SoTienPhat DECIMAL(18,0) DEFAULT 0 CHECK (SoTienPhat >= 0),
    NgayViPham DATETIME DEFAULT GETDATE(),
    TrangThai NVARCHAR(50) DEFAULT N'Chưa xử lý',
    CONSTRAINT FK_VP_DG FOREIGN KEY (MaSV) REFERENCES DOCGIA(MaSV) ON DELETE CASCADE
);
GO

-- ===================================================================
-- BƯỚC 4: TẠO CÁC BẢNG CẤP 3 (PHỤ THUỘC VÀO LEVEL 2)
-- ===================================================================

-- Module 03 (Quang Anh): Chi tiết Phiếu nhập
CREATE TABLE CT_PHIEUNHAP (
    MaPhieuNhap VARCHAR(20) NOT NULL,
    MaSach INT NOT NULL,
    SoLuong INT NOT NULL CHECK (SoLuong > 0),
    DonGia DECIMAL(18,0) NOT NULL CHECK (DonGia >= 0),
    ThanhTien AS (SoLuong * DonGia) PERSISTED,
    PRIMARY KEY (MaPhieuNhap, MaSach),
    CONSTRAINT FK_CTPN_PN FOREIGN KEY (MaPhieuNhap) REFERENCES PHIEUNHAP(MaPhieuNhap) ON DELETE CASCADE,
    CONSTRAINT FK_CTPN_Sach FOREIGN KEY (MaSach) REFERENCES SACH(MaSach) ON DELETE CASCADE
);
GO

-- Module 04 (Quang Anh): Quản lý Phiếu mượn
CREATE TABLE PHIEUMUON (
    MaPhieuMuon VARCHAR(20) PRIMARY KEY,
    MaThe VARCHAR(20) NOT NULL,
    MaNV VARCHAR(20),
    NgayMuon DATETIME DEFAULT GETDATE(),
    HanTra DATETIME NOT NULL,
    TrangThai NVARCHAR(50) DEFAULT N'Đang mượn' CHECK (TrangThai IN (N'Đang mượn', N'Đã trả', N'Quá hạn')),
    GhiChu NVARCHAR(255),
    CONSTRAINT FK_PM_TheTV FOREIGN KEY (MaThe) REFERENCES THETHUVIEN(MaThe) ON DELETE CASCADE,
    CONSTRAINT FK_PM_NV FOREIGN KEY (MaNV) REFERENCES NHANVIEN(MaNV) ON DELETE SET NULL,
    CONSTRAINT CHK_HanTra CHECK (HanTra >= NgayMuon)
);
GO

-- Module 04 (Quang Anh): Chi tiết Phiếu mượn
CREATE TABLE CT_PHIEUMUON (
    ID INT IDENTITY(1,1) PRIMARY KEY,
    MaPhieuMuon VARCHAR(20) NOT NULL,
    MaSach INT NOT NULL,
    NgayTra DATETIME,
    TienPhat DECIMAL(18,0) DEFAULT 0 CHECK (TienPhat >= 0),
    TinhTrangKhiTra NVARCHAR(100),
    SoLanGiaHan INT DEFAULT 0 CHECK (SoLanGiaHan >= 0),
    GhiChu NVARCHAR(255),
    CONSTRAINT FK_CTPM_PM FOREIGN KEY (MaPhieuMuon) REFERENCES PHIEUMUON(MaPhieuMuon) ON DELETE CASCADE,
    CONSTRAINT FK_CTPM_Sach FOREIGN KEY (MaSach) REFERENCES SACH(MaSach) ON DELETE CASCADE
);
GO

-- ===================================================================
-- INDEXES ĐỂ TỐI ƯU HIỆU NĂNG
-- ===================================================================

CREATE INDEX IX_Sach_TenSach ON SACH(TenSach);
CREATE INDEX IX_DocGia_HoTen ON DOCGIA(HoTen);
CREATE INDEX IX_PhieuMuon_TrangThai ON PHIEUMUON(TrangThai);
CREATE INDEX IX_PhieuMuon_NgayMuon ON PHIEUMUON(NgayMuon);
CREATE INDEX IX_CTPhieuMuon_NgayTra ON CT_PHIEUMUON(NgayTra);
GO

-- ===================================================================
-- TRIGGERS TỰ ĐỘNG
-- ===================================================================

-- Trigger: Tự động cập nhật số lượng tồn khi nhập sách
CREATE TRIGGER TRG_UpdateSoLuongTon_AfterInsert
ON CT_PHIEUNHAP
AFTER INSERT
AS
BEGIN
    UPDATE SACH
    SET SoLuongTon = SoLuongTon + i.SoLuong
    FROM SACH s
    INNER JOIN inserted i ON s.MaSach = i.MaSach;
END;
GO

-- Trigger: Giảm số lượng tồn khi mượn sách
CREATE TRIGGER TRG_DecreaseSoLuongTon_AfterMuon
ON CT_PHIEUMUON
AFTER INSERT
AS
BEGIN
    UPDATE SACH
    SET SoLuongTon = SoLuongTon - 1
    FROM SACH s
    INNER JOIN inserted i ON s.MaSach = i.MaSach
    WHERE s.SoLuongTon > 0;
END;
GO

-- Trigger: Tăng số lượng tồn khi trả sách
CREATE TRIGGER TRG_IncreaseSoLuongTon_AfterTra
ON CT_PHIEUMUON
AFTER UPDATE
AS
BEGIN
    IF UPDATE(NgayTra)
    BEGIN
        UPDATE SACH
        SET SoLuongTon = SoLuongTon + 1
        FROM SACH s
        INNER JOIN inserted i ON s.MaSach = i.MaSach
        WHERE i.NgayTra IS NOT NULL;
    END
END;
GO

-- Trigger: Tự động cập nhật trạng thái phiếu mượn khi quá hạn
CREATE TRIGGER TRG_UpdateTrangThaiPhieuMuon
ON PHIEUMUON
AFTER INSERT, UPDATE
AS
BEGIN
    UPDATE PHIEUMUON
    SET TrangThai = N'Quá hạn'
    FROM PHIEUMUON pm
    INNER JOIN inserted i ON pm.MaPhieuMuon = i.MaPhieuMuon
    WHERE i.HanTra < GETDATE() AND i.TrangThai = N'Đang mượn';
END;
GO

PRINT N'===================================================================';
PRINT N'TẠO CƠ SỞ DỮ LIỆU THÀNH CÔNG!';
PRINT N'Database: QuanLyThuVienUTT';
PRINT N'===================================================================';
PRINT N'PHÂN CÔNG CHI TIẾT:';
PRINT N'';
PRINT N'📌 Quang Anh (5 modules):';
PRINT N'   ✓ Module 01: Quản lý Tài khoản';
PRINT N'   ✓ Module 02: Quản lý Nhà cung cấp';
PRINT N'   ✓ Module 03: Quản lý Phiếu Nhập';
PRINT N'   ✓ Module 04: Quản lý Phiếu Mượn';
PRINT N'   ✓ Module 05: Quản lý Phiếu Trả (tích hợp trong CT_PHIEUMUON)';
PRINT N'   ✓ Bonus: Dashboard (View Only)';
PRINT N'';
PRINT N'📌 Thùy Trang (4 modules):';
PRINT N'   ✓ Module 06: Quản lý Hồ sơ Sách';
PRINT N'   ✓ Module 07: Quản lý Thanh lý';
PRINT N'   ✓ Module 08: Quản lý Đặt trước';
PRINT N'   ✓ Module 09: Quản lý Kho lưu trữ';
PRINT N'   ✓ Bonus: Tra cứu Sách (View Only)';
PRINT N'';
PRINT N'📌 Duy Thành (4 modules):';
PRINT N'   ✓ Module 10: Quản lý Sinh viên (DOCGIA)';
PRINT N'   ✓ Module 11: Quản lý Nhân viên';
PRINT N'   ✓ Module 12: Quản lý Thẻ thư viện';
PRINT N'   ✓ Module 13: Quản lý Vi phạm';
PRINT N'   ✓ Bonus: Lịch sử GD (View Only)';
PRINT N'';
PRINT N'📌 Hồng (3 modules):';
PRINT N'   ✓ Module 14: Quản lý Tác giả';
PRINT N'   ✓ Module 15: Quản lý Nhà xuất bản';
PRINT N'   ✓ Module 16: Quản lý Thể loại';
PRINT N'';
PRINT N'📌 Lộc (3 modules):';
PRINT N'   ✓ Module 17: Quản lý Ngôn ngữ';
PRINT N'   ✓ Module 18: Quản lý Vị trí Kệ';
PRINT N'   ✓ Module 19: Quản lý Lớp/Khoa';
PRINT N'';
PRINT N'===================================================================';
PRINT N'Tổng số bảng: 20 bảng chính + 1 bảng hệ thống';
PRINT N'===================================================================';
GO