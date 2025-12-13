using System;
using System.Collections.Generic;
using System.Linq;
using System.Text;
using System.Threading.Tasks;

namespace UTT.Library.DTO
{
    public class DTO_Khoa
    {
        public string MaKhoa { get; set; }
        public string TenKhoa { get; set; }

        public DTO_Khoa()
        {
        }

        public DTO_Khoa(string maKhoa, string tenKhoa)
        {
            MaKhoa = maKhoa;
            TenKhoa = tenKhoa;
        }
    }
}
