USE QuanLyThuVienUTT;
GO

-- ===================================================================
-- DỮ LIỆU MẪU - THEO THỨ TỰ PHỤ THUỘC
-- ===================================================================

-- BƯỚC 1: Dữ liệu cho các bảng LEVEL 0 (không phụ thuộc)
-- ===================================================================

-- Dữ liệu mẫu cho bảng KHOA (các khoa/viện của UTT)
INSERT INTO KHOA (MaKhoa, TenKhoa, MoTa) VALUES
(N'CKDL', N'Viện Cơ khí Động lực', N'Viện đào tạo chuyên ngành cơ khí'),
(N'CNTT', N'Khoa Công nghệ Thông tin', N'Khoa đào tạo về CNTT'),
(N'CT',   N'Khoa Công trình', N'Khoa đào tạo về công trình'),
(N'KTVT', N'Khoa Kinh tế Vận tải', N'Khoa đào tạo về kinh tế vận tải'),
(N'QTKD', N'Khoa Quản trị kinh doanh', N'Khoa đào tạo về quản trị'),
(N'KHLP', N'Khoa Khoa học Ứng dụng và Luật Chính trị', N'Khoa đào tạo về ứng dụng và luật chính trị');
GO

-- Dữ liệu mẫu cho bảng KHOSACH (kho lưu trữ)
INSERT INTO KHOSACH (MaKho, TenKho, SucChua, MoTa) VALUES
(N'M001', N'Kho Chính', 1000, N'Kho chính của thư viện'),
(N'M002', N'Kho Phụ',    500, N'Kho phụ chứa tài liệu bổ sung');
GO

-- Dữ liệu mẫu cho bảng TACGIA (tác giả)
INSERT INTO TACGIA (MaTacGia, TenTacGia, GhiChu) VALUES
(N'TG001', N'Nguyễn Văn A', N'Chuyên ngành cơ khí'),
(N'TG002', N'Trần Thị Bích', N''),
(N'TG003', N'Vũ Ngọc C',    N'Chuyên ngành CNTT'),
(N'TG004', N'Nguyễn Văn D', N'Chuyên ngành kinh tế');
GO

-- Dữ liệu mẫu cho bảng NHAXUATBAN (nhà xuất bản)
INSERT INTO NHAXUATBAN (MaNXB, TenNXB, DiaChi, SDT) VALUES
(N'NXB01', N'Nhà Xuất Bản Giáo Dục', N'1 Hoàng Diệu, Hà Nội',    N'024111111'),
(N'NXB02', N'Nhà Xuất Bản Khoa Học và Kỹ Thuật', N'435 La Thành, Hà Nội', N'024222222'),
(N'NXB03', N'Nhà Xuất Bản Trẻ',        N'28A CMT8, TP. HCM',          N'028333333');
GO

-- Dữ liệu mẫu cho bảng THELOAI (thể loại sách)
INSERT INTO THELOAI (MaTheLoai, TenTheLoai, MoTa) VALUES
(N'TL01', N'Khoa học kỹ thuật',   N''),
(N'TL02', N'Công nghệ thông tin', N''),
(N'TL03', N'Kinh tế',             N''),
(N'TL04', N'Luật',               N''),
(N'TL05', N'Văn học',            N'');
GO

-- Dữ liệu mẫu cho bảng NGONNGU (ngôn ngữ sách)
INSERT INTO NGONNGU (MaNgonNgu, TenNgonNgu, MoTa) VALUES
(N'NN01', N'Tiếng Việt', N''),
(N'NN02', N'Tiếng Anh',  N''),
(N'NN03', N'Tiếng Pháp', N'');
GO

-- Dữ liệu mẫu cho bảng TAIKHOAN (đăng nhập)
INSERT INTO TAIKHOAN (TenDangNhap, MatKhau, Quyen) VALUES
(N'admin',    N'adminpass', 1),  -- quyền 1 = Admin
(N'thuThu01', N'pass123',   2),  -- quyền 2 = Thủ thư
(N'thuThu02', N'pass234',   2),
(N'sv01',     N'passsv01',  3),  -- quyền 3 = Sinh viên
(N'sv02',     N'passsv02',  3),
(N'sv03',     N'passsv03',  3);
GO

-- Dữ liệu mẫu cho bảng NHACUNGCAP (nhà cung cấp sách)
INSERT INTO NHACUNGCAP (MaNCC, TenNCC, DiaChi, SDT, Email) VALUES
(N'NCC01', N'Công ty Sách An Bình', N'123 Đường A, Hà Nội', N'0243333333', N'anbinh@book.vn'),
(N'NCC02', N'Công ty Kiến Trúc Mới',N'456 Đường B, Hà Nội', N'0244444444', N'archbook@vn'),
(N'NCC03', N'Công ty Giáo Dục ABC',N'789 Đường C, Hà Nội', N'0245555555', N'info@abc.edu.vn');
GO

-- BƯỚC 2: Dữ liệu cho các bảng LEVEL 1 (phụ thuộc LEVEL 0)
-- ===================================================================

-- Dữ liệu mẫu cho bảng LOP (các lớp đại diện theo khoa)
INSERT INTO LOP (MaLop, TenLop, KhoaHoc, MaKhoa) VALUES
(N'CKDL19A', N'Cơ khí Động lực 19A', N'2019', N'CKDL'),
(N'CNTT19A', N'CNTT 19A',           N'2019', N'CNTT'),
(N'CT18A',   N'Công trình 18A',      N'2018', N'CT'),
(N'KTVT20A', N'Kinh tế Vận tải 20A', N'2020', N'KTVT'),
(N'QTKD18B', N'Quản trị kinh doanh 18B', N'2018', N'QTKD'),
(N'KHLP16C', N'Ứng dụng & Luật 16C', N'2016', N'KHLP');
GO

-- Dữ liệu mẫu cho bảng VITRI (vị trí kệ trong kho)
INSERT INTO VITRI (MaViTri, TenKe, SoTang, MaKho) VALUES
(N'V1', N'Kệ Sách 1', N'Tầng 1', N'M001'),
(N'V2', N'Kệ Sách 2', N'Tầng 2', N'M001'),
(N'V3', N'Kệ Sách 3', N'Tầng 1', N'M002');
GO

-- Dữ liệu mẫu cho bảng NHANVIEN (nhân viên thư viện)
INSERT INTO NHANVIEN (MaNV, HoTen, SDT, Email, NgayVaoLam, TenDangNhap) VALUES
(N'NV001', N'Nguyễn Thị Hồng', N'0123456789', N'hongnt@utt.edu.vn', '2020-01-15', N'thuThu01'),
(N'NV002', N'Lê Văn Nam',     N'0987654321', N'namlv@utt.edu.vn',  '2019-05-10', N'thuThu02'),
(N'AD01',  N'Admin UTT',      N'0912345678', N'admin@utt.edu.vn',  '2021-07-01', N'admin');
GO

-- Dữ liệu mẫu cho bảng SACH (hồ sơ sách) - SoLuongTon = 0 ban đầu
INSERT INTO SACH (TenSach, MaTheLoai, MaTacGia, MaNXB, MaNgonNgu, NamXB, GiaTien, SoLuongTon, MoTa) VALUES
(N'Cơ lý máy',                      N'TL01', N'TG001', N'NXB01', N'NN01', 2010, 100000, 0, N'Sách chuyên ngành cơ khí'),
(N'Cấu trúc dữ liệu và giải thuật',  N'TL02', N'TG003', N'NXB02', N'NN01', 2018, 150000, 0, N'Tài liệu CNTT nâng cao'),
(N'Kinh tế học đại cương',         N'TL03', N'TG002', N'NXB01', N'NN02', 2015,  80000, 0, N'Sách kinh tế học'),
(N'Luật Hiến pháp Việt Nam',        N'TL04', N'TG004', N'NXB03', N'NN01', 2020, 200000, 0, N'Sách luật chính trị');
GO

-- Dữ liệu mẫu cho bảng DOCGIA (sinh viên / độc giả)
INSERT INTO DOCGIA (MaSV, HoTen, NgaySinh, GioiTinh, SDT, Email, MaLop, MaKhoa, TenDangNhap) VALUES
(N'SV001', N'Phạm Văn A',  '2000-05-15', N'Nam', N'0911222333', N'avaa@st.utt.edu.vn', N'CKDL19A', N'CKDL', N'sv01'),
(N'SV002', N'Trần Thị B',  '2001-08-20', N'Nữ',  N'0988112233', N'bttran@st.utt.edu.vn', N'CNTT19A', N'CNTT', N'sv02'),
(N'SV003', N'Nguyễn Văn C','2000-02-10', N'Nam', N'0977555333', N'cvnguyen@st.utt.edu.vn', N'KTVT20A', N'KTVT', N'sv03');
GO

-- BƯỚC 3: Dữ liệu cho các bảng LEVEL 2 (phụ thuộc LEVEL 1)
-- ===================================================================

-- Dữ liệu mẫu cho bảng THETHUVIEN (thẻ thư viện cho sinh viên)
INSERT INTO THETHUVIEN (MaThe, MaSV, NgayCap, NgayHetHan, TrangThai) VALUES
(N'TH01', N'SV001', '2022-09-01', '2023-09-01', N'Hoạt động'),
(N'TH02', N'SV002', '2022-11-01', '2023-11-01', N'Hoạt động'),
(N'TH03', N'SV003', '2022-10-01', '2023-10-01', N'Hoạt động');
GO

-- Dữ liệu mẫu cho bảng PHIEUNHAP (phiếu nhập sách)
-- Lưu ý: TongTien sẽ được tính sau khi insert CT_PHIEUNHAP
INSERT INTO PHIEUNHAP (MaPhieuNhap, MaNV, MaNCC, NgayNhap, TongTien, GhiChu) VALUES
(N'PN001', N'NV001', N'NCC01', '2025-04-01', 0, N'Nhập bộ sách kỹ thuật'),
(N'PN002', N'NV002', N'NCC02', '2025-04-15', 0, N'Nhập sách CNTT và kinh tế'),
(N'PN003', N'AD01',  N'NCC03', '2025-04-20', 0, N'Nhập sách luật và cơ khí');
GO

-- BƯỚC 4: Dữ liệu cho các bảng LEVEL 3 (phụ thuộc LEVEL 2)
-- ===================================================================

-- Dữ liệu mẫu cho bảng CT_PHIEUNHAP (chi tiết phiếu nhập)
-- Trigger sẽ tự động cập nhật SoLuongTon trong bảng SACH
INSERT INTO CT_PHIEUNHAP (MaPhieuNhap, MaSach, SoLuong, DonGia) VALUES
(N'PN001', 1, 3, 100000),  -- Sách 1: thêm 3 cuốn
(N'PN001', 2, 2, 150000),  -- Sách 2: thêm 2 cuốn
(N'PN002', 2, 1, 150000),  -- Sách 2: thêm 1 cuốn (tổng 3)
(N'PN002', 3, 4,  80000),  -- Sách 3: thêm 4 cuốn
(N'PN003', 1, 5, 100000),  -- Sách 1: thêm 5 cuốn (tổng 8)
(N'PN003', 4, 2, 200000);  -- Sách 4: thêm 2 cuốn
GO

-- Cập nhật TongTien cho các phiếu nhập
UPDATE PHIEUNHAP 
SET TongTien = (SELECT SUM(ThanhTien) FROM CT_PHIEUNHAP WHERE MaPhieuNhap = PHIEUNHAP.MaPhieuNhap)
WHERE MaPhieuNhap IN (N'PN001', N'PN002', N'PN003');
GO

-- Dữ liệu mẫu cho bảng PHIEUMUON (phiếu mượn sách)
INSERT INTO PHIEUMUON (MaPhieuMuon, MaThe, MaNV, NgayMuon, HanTra, TrangThai, GhiChu) VALUES
(N'PM001', N'TH01', N'NV001', '2025-04-01', '2025-05-01', N'Đang mượn', N''),
(N'PM002', N'TH02', N'NV002', '2024-11-15', '2024-12-15', N'Quá hạn', N'Quá hạn trả sách');
GO

-- Dữ liệu mẫu cho bảng CT_PHIEUMUON (chi tiết phiếu mượn)
-- Trigger sẽ tự động giảm SoLuongTon
INSERT INTO CT_PHIEUMUON (MaPhieuMuon, MaSach, NgayTra, TinhTrangKhiTra, TienPhat, GhiChu) VALUES
(N'PM001', 1, NULL, NULL, 0, N''),           -- Đang mượn
(N'PM001', 2, NULL, NULL, 0, N''),           -- Đang mượn
(N'PM002', 2, NULL, NULL, 0, N''),           -- Đang mượn quá hạn
(N'PM002', 4, NULL, NULL, 50000, N'Quá hạn'); -- Đang mượn quá hạn + phạt
GO

-- Cập nhật trả sách: sinh viên đã trả sách MaSach=1 của PM001
UPDATE CT_PHIEUMUON 
SET NgayTra = '2025-04-05', 
    TinhTrangKhiTra = N'Nguyên vẹn',
    TienPhat = 0,
    GhiChu = N'Trả đúng hạn'
WHERE MaPhieuMuon = N'PM001' AND MaSach = 1;
GO

-- BƯỚC 5: Dữ liệu cho các bảng phụ trợ
-- ===================================================================

-- Dữ liệu mẫu cho bảng PHIEUDATTRUOC (đặt trước sách)
INSERT INTO PHIEUDATTRUOC (MaSV, MaSach, NgayDat, NgayHetHan, TrangThai) VALUES
(N'SV003', 4, '2025-04-16', '2025-04-30', N'Đang chờ');
GO

-- Dữ liệu mẫu cho bảng VIPHAM (vi phạm)
INSERT INTO VIPHAM (MaSV, LoiViPham, HinhThucXuLy, SoTienPhat, NgayViPham, TrangThai) VALUES
(N'SV002', N'Quá hạn trả sách', N'Phạt tiền', 50000,  '2024-12-16', N'Đã xử lý'),
(N'SV001', N'Làm hư hỏng sách', N'Phạt tiền', 150000, '2025-04-20', N'Chưa xử lý');
GO

-- Dữ liệu mẫu cho bảng SACHTHANHLY (sách bị thanh lý)
-- Lưu ý: chỉ thanh lý sách có trong kho, không thanh lý sách đang được mượn
INSERT INTO SACHTHANHLY (MaSach, LyDo, NgayXuLy, NguoiXuLy, SoLuongThanhLy) VALUES
(3, N'Rách nát không thể sử dụng', '2025-04-25', N'NV002', 1);
GO

-- Cập nhật giảm SoLuongTon sau khi thanh lý
UPDATE SACH 
SET SoLuongTon = SoLuongTon - 1
WHERE MaSach = 3 AND SoLuongTon > 0;
GO

-- Dữ liệu mẫu cho bảng THAMSOQUYDINH (các tham số thư viện)
INSERT INTO THAMSOQUYDINH (MaThamSo, TenThamSo, GiaTri, MoTa) VALUES
(N'TS01', N'Số ngày mượn tối đa', N'30', N'Sinh viên có thể mượn tối đa 30 ngày'),
(N'TS02', N'Số sách mượn tối đa', N'5',  N'Sinh viên có thể mượn tối đa 5 cuốn'),
(N'TS03', N'Số lần gia hạn tối đa', N'2', N'Sinh viên có thể gia hạn tối đa 2 lần'),
(N'TS04', N'Tiền phạt quá hạn/ngày', N'5000', N'Phạt 5,000 VNĐ mỗi ngày quá hạn');
GO

-- ===================================================================
-- KIỂM TRA DỮ LIỆU SAU KHI IMPORT
-- ===================================================================

PRINT N'===================================================================';
PRINT N'IMPORT DỮ LIỆU MẪU THÀNH CÔNG!';
PRINT N'===================================================================';
PRINT N'';
PRINT N'THỐNG KÊ SỐ LƯỢNG:';
SELECT N'KHOA' AS BangDuLieu, COUNT(*) AS SoLuong FROM KHOA
UNION ALL SELECT N'LOP', COUNT(*) FROM LOP
UNION ALL SELECT N'TAIKHOAN', COUNT(*) FROM TAIKHOAN
UNION ALL SELECT N'NHANVIEN', COUNT(*) FROM NHANVIEN
UNION ALL SELECT N'DOCGIA', COUNT(*) FROM DOCGIA
UNION ALL SELECT N'THETHUVIEN', COUNT(*) FROM THETHUVIEN
UNION ALL SELECT N'SACH', COUNT(*) FROM SACH
UNION ALL SELECT N'TACGIA', COUNT(*) FROM TACGIA
UNION ALL SELECT N'THELOAI', COUNT(*) FROM THELOAI
UNION ALL SELECT N'NHAXUATBAN', COUNT(*) FROM NHAXUATBAN
UNION ALL SELECT N'NHACUNGCAP', COUNT(*) FROM NHACUNGCAP
UNION ALL SELECT N'PHIEUNHAP', COUNT(*) FROM PHIEUNHAP
UNION ALL SELECT N'CT_PHIEUNHAP', COUNT(*) FROM CT_PHIEUNHAP
UNION ALL SELECT N'PHIEUMUON', COUNT(*) FROM PHIEUMUON
UNION ALL SELECT N'CT_PHIEUMUON', COUNT(*) FROM CT_PHIEUMUON
UNION ALL SELECT N'VIPHAM', COUNT(*) FROM VIPHAM;
GO

PRINT N'';
PRINT N'TÌNH TRẠNG TỒN KHO SÁCH:';
SELECT 
    MaSach,
    TenSach,
    SoLuongTon,
    CASE 
        WHEN SoLuongTon = 0 THEN N'HẾT SÁCH'
        WHEN SoLuongTon <= 2 THEN N'SẮP HẾT'
        ELSE N'CÒN NHIỀU'
    END AS TrangThai
FROM SACH
ORDER BY MaSach;
GO

PRINT N'===================================================================';
GO