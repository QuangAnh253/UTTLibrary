using System;
using System.Collections.Generic;
using System.Linq;
using System.Text;
using System.Threading.Tasks;

namespace UTT.Library.DTO
{
    public class DTO_KhoSach
    {
        public string MaSach { get; set; }
        public int SoLuongTon { get; set; }
        public string MaViTri { get; set; }

        public DTO_KhoSach() { }

        public DTO_KhoSach(string maSach, int soLuongTon, string maViTri)
        {
            MaSach = maSach;
            SoLuongTon = soLuongTon;
            MaViTri = maViTri;
        }
    }
}
