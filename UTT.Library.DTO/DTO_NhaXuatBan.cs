using System;
using System.Collections.Generic;
using System.Linq;
using System.Text;
using System.Threading.Tasks;

namespace UTT.Library.DTO
{
    public class DTO_NhaXuatBan
    {
        public string MaNhaXuatBan { get; set; }
        public string TenNhaXuatBan { get; set; }

        public DTO_NhaXuatBan()
        {
        }

        public DTO_NhaXuatBan(string maNhaXuatBan, string tenNhaXuatBan)
        {
            MaNhaXuatBan = maNhaXuatBan;
            TenNhaXuatBan = tenNhaXuatBan;
        }
    }
}
