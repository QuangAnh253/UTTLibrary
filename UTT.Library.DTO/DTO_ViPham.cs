using System;
using System.Collections.Generic;
using System.Linq;
using System.Text;
using System.Threading.Tasks;

namespace UTT.Library.DTO
{
    public class DTO_ViPham
    {
        public string MaViPham { get; set; }
        public string MaDocGia { get; set; }
        public string NoiDung { get; set; }
        public decimal TienPhat { get; set; }
        public DateTime NgayViPham { get; set; }

        public DTO_ViPham()
        {
        }

        public DTO_ViPham(string maViPham, string maDocGia, string noiDung, decimal tienPhat, DateTime ngayViPham)
        {
            MaViPham = maViPham;
            MaDocGia = maDocGia;
            NoiDung = noiDung;
            TienPhat = tienPhat;
            NgayViPham = ngayViPham;
        }
    }
}
