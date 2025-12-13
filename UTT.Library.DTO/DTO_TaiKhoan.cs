using System;
using System.Collections.Generic;
using System.Linq;
using System.Text;
using System.Threading.Tasks;

namespace UTT.Library.DTO
{
    public class DTO_TaiKhoan
    {
        public string TenDangNhap { get; set; }
        public string MatKhau { get; set; }
        public int Quyen { get; set; }
        public bool TrangThai { get; set; }
        public DateTime NgayTao { get; set; }

        public DTO_TaiKhoan()
        {
        }

        public DTO_TaiKhoan(string tenDangNhap, string matKhau, int quyen, bool trangThai, DateTime ngayTao)
        {
            TenDangNhap = tenDangNhap;
            MatKhau = matKhau;
            Quyen = quyen;
            TrangThai = trangThai;
            NgayTao = ngayTao;
        }
    }
}
