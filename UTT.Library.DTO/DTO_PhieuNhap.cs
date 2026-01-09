using System;
using System.Collections.Generic;
using System.Linq;
using System.Text;
using System.Threading.Tasks;

namespace UTT.Library.DTO
{
    public class DTO_PhieuNhap
    {
        public string MaPhieuNhap { get; set; }
        public DateTime NgayNhap { get; set; }
        public string MaNCC { get; set; }
        public string MaNhanVien { get; set; }

        public DTO_PhieuNhap() { }

        public DTO_PhieuNhap(string maPhieuNhap, DateTime ngayNhap, string maNCC, string maNhanVien)
        {
            MaPhieuNhap = maPhieuNhap;
            NgayNhap = ngayNhap;
            MaNCC = maNCC;
            MaNhanVien = maNhanVien;
        }
    }
}
