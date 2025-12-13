using System;
using System.Collections.Generic;
using System.Linq;
using System.Text;
using System.Threading.Tasks;

namespace UTT.Library.DTO
{
    public class DTO_CTPhieuMuon
    {
        public string MaPhieuMuon { get; set; }
        public string MaSach { get; set; }
        public bool DaTra { get; set; }
        public DateTime? NgayTra { get; set; }
        public decimal? TienPhat { get; set; }

        public DTO_CTPhieuMuon() { }

        public DTO_CTPhieuMuon(string maPhieuMuon, string maSach, bool daTra, DateTime? ngayTra, decimal? tienPhat)
        {
            MaPhieuMuon = maPhieuMuon;
            MaSach = maSach;
            DaTra = daTra;
            NgayTra = ngayTra;
            TienPhat = tienPhat;
        }
    }
}
