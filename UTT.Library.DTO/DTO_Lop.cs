using System;
using System.Collections.Generic;
using System.Linq;
using System.Text;
using System.Threading.Tasks;

namespace UTT.Library.DTO
{
    public class DTO_Lop
    {
        public string MaLop { get; set; }
        public string TenLop { get; set; }
        public string MaKhoa { get; set; }

        public DTO_Lop()
        {
        }

        public DTO_Lop(string maLop, string tenLop, string maKhoa)
        {
            MaLop = maLop;
            TenLop = tenLop;
            MaKhoa = maKhoa;
        }
    }
}
