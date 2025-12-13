using System;
using System.Collections.Generic;
using System.Linq;
using System.Text;
using System.Threading.Tasks;

namespace UTT.Library.DTO
{
    public class DTO_TheLoai
    {
        public string MaTheLoai { get; set; }
        public string TenTheLoai { get; set; }

        public DTO_TheLoai() { }

        public DTO_TheLoai(string maTheLoai, string tenTheLoai)
        {
            MaTheLoai = maTheLoai;
            TenTheLoai = tenTheLoai;
        }
    }
}
