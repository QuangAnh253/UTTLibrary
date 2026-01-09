using System;
using System.Collections.Generic;
using System.Linq;
using System.Text;
using System.Threading.Tasks;

namespace UTT.Library.DTO
{
    public class DTO_SachThanhLy
    {
        public string MaThanhLy { get; set; }
        public string MaSach { get; set; }
        public DateTime NgayThanhLy { get; set; }
        public string LyDo { get; set; }

        public DTO_SachThanhLy() { }

        public DTO_SachThanhLy(string maThanhLy, string maSach, DateTime ngayThanhLy, string lyDo)
        {
            MaThanhLy = maThanhLy;
            MaSach = maSach;
            NgayThanhLy = ngayThanhLy;
            LyDo = lyDo;
        }
    }
}
