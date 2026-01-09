using System;
using System.Collections.Generic;
using System.Linq;
using System.Text;
using System.Threading.Tasks;

namespace UTT.Library.DTO
{
    public class DTO_PhieuDatTruoc
    {
        public string MaPhieuDat { get; set; }
        public string MaDocGia { get; set; }
        public string MaSach { get; set; }
        public DateTime NgayDat { get; set; }
        public bool TrangThai { get; set; }

        public DTO_PhieuDatTruoc() { }

        public DTO_PhieuDatTruoc(string maPhieuDat, string maDocGia, string maSach, DateTime ngayDat, bool trangThai)
        {
            MaPhieuDat = maPhieuDat;
            MaDocGia = maDocGia;
            MaSach = maSach;
            NgayDat = ngayDat;
            TrangThai = trangThai;
        }
    }
}
