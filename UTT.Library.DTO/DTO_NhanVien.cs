using System;
using System.Collections.Generic;
using System.Linq;
using System.Text;
using System.Threading.Tasks;

namespace UTT.Library.DTO
{
    public class DTO_NhanVien
    {
        public string MaNV { get; set; } // SQL: MaNV
        public string HoTen { get; set; }
        public string SDT { get; set; }
        public string Email { get; set; }
        public DateTime NgayVaoLam { get; set; }
        public string TenDangNhap { get; set; } // Khóa ngoại liên kết TAIKHOAN

        public DTO_NhanVien() { }

        public DTO_NhanVien(string maNV, string hoTen, string sDT, string email, DateTime ngayVaoLam, string tenDangNhap)
        {
            MaNV = maNV;
            HoTen = hoTen;
            SDT = sDT;
            Email = email;
            NgayVaoLam = ngayVaoLam;
            TenDangNhap = tenDangNhap;
        }
    }
}
