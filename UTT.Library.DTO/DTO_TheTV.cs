using System;
using System.Collections.Generic;
using System.Linq;
using System.Text;
using System.Threading.Tasks;

namespace UTT.Library.DTO
{
    public class DTO_TheTV
    {
        public string MaThe { get; set; }
        public string MaDocGia { get; set; }
        public DateTime NgayCap { get; set; }
        public DateTime NgayHetHan { get; set; }
        public bool TrangThai { get; set; }

        public DTO_TheTV()
        {
        }

        public DTO_TheTV(string maThe, string maDocGia, DateTime ngayCap, DateTime ngayHetHan, bool trangThai)
        {
            MaThe = maThe;
            MaDocGia = maDocGia;
            NgayCap = ngayCap;
            NgayHetHan = ngayHetHan;
            TrangThai = trangThai;
        }
    }
}
