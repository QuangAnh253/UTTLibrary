using System;
using System.Collections.Generic;
using System.Linq;
using System.Text;
using System.Threading.Tasks;

namespace UTT.Library.DTO
{
    public class DTO_Sach
    {
        public string MaSach { get; set; }
        public string TenSach { get; set; }
        public string MaTheLoai { get; set; }
        public string MaTacGia { get; set; }
        public string MaNhaXuatBan { get; set; }
        public string MaNgonNgu { get; set; }
        public int NamXuatBan { get; set; }

        public DTO_Sach() { }

        public DTO_Sach(string maSach, string tenSach, string maTheLoai, string maTacGia, string maNhaXuatBan, string maNgonNgu, int namXuatBan)
        {
            MaSach = maSach;
            TenSach = tenSach;
            MaTheLoai = maTheLoai;
            MaTacGia = maTacGia;
            MaNhaXuatBan = maNhaXuatBan;
            MaNgonNgu = maNgonNgu;
            NamXuatBan = namXuatBan;
        }
    }
}
