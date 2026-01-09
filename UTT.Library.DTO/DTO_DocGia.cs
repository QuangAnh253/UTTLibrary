using System;
using System.Collections.Generic;
using System.Linq;
using System.Text;
using System.Threading.Tasks;

namespace UTT.Library.DTO
{
    public class DTO_DocGia
    {
        public string MaDocGia { get; set; }
        public string HoTen { get; set; }
        public string GioiTinh { get; set; }
        public DateTime NgaySinh { get; set; }
        public string MaLop { get; set; }
        public string SDT { get; set; }
        public string Email { get; set; }

        public DTO_DocGia()
        {
        }

        public DTO_DocGia(string maDocGia, string hoTen, string gioiTinh, DateTime ngaySinh, string maLop, string sdt, string email)
        {
            MaDocGia = maDocGia;
            HoTen = hoTen;
            GioiTinh = gioiTinh;
            NgaySinh = ngaySinh;
            MaLop = maLop;
            SDT = sdt;
            Email = email;
        }
    }
}
