using System;
using System.Collections.Generic;
using System.Linq;
using System.Text;
using System.Threading.Tasks;

namespace UTT.Library.DTO
{
    public class DTO_PhieuMuon
    {
        public string MaPhieuMuon { get; set; }
        public string MaDocGia { get; set; }
        public string MaNhanVien { get; set; }
        public DateTime NgayMuon { get; set; }
        public DateTime HanTra { get; set; }
        public bool TrangThai { get; set; }

        public DTO_PhieuMuon() { }

        public DTO_PhieuMuon(string maPhieuMuon, string maDocGia, string maNhanVien, DateTime ngayMuon, DateTime hanTra, bool trangThai)
        {
            MaPhieuMuon = maPhieuMuon;
            MaDocGia = maDocGia;
            MaNhanVien = maNhanVien;
            NgayMuon = ngayMuon;
            HanTra = hanTra;
            TrangThai = trangThai;
        }
    }

}
