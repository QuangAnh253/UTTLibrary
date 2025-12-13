using System;
using System.Collections.Generic;
using System.Linq;
using System.Text;
using System.Threading.Tasks;

namespace UTT.Library.DTO
{
    public class DTO_CTPhieuNhap
    {
        public string MaPhieuNhap { get; set; }
        public string MaSach { get; set; }
        public int SoLuong { get; set; }
        public decimal DonGia { get; set; }

        public DTO_CTPhieuNhap() { }

        public DTO_CTPhieuNhap(string maPhieuNhap, string maSach, int soLuong, decimal donGia)
        {
            MaPhieuNhap = maPhieuNhap;
            MaSach = maSach;
            SoLuong = soLuong;
            DonGia = donGia;
        }
    }
}
