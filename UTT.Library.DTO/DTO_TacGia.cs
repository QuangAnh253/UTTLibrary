using System;
using System.Collections.Generic;
using System.Linq;
using System.Text;
using System.Threading.Tasks;

namespace UTT.Library.DTO
{
    public class DTO_TacGia
    {
        public string MaTacGia { get; set; }
        public string TenTacGia { get; set; }
        public string GhiChu { get; set; }

        public DTO_TacGia() { }

        public DTO_TacGia(string maTacGia, string tenTacGia, string ghiChu)
        {
            MaTacGia = maTacGia;
            TenTacGia = tenTacGia;  
            GhiChu = ghiChu;
        }
    }
}
