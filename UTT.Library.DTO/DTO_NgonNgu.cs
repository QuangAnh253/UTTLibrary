using System;
using System.Collections.Generic;
using System.Linq;
using System.Text;
using System.Threading.Tasks;

namespace UTT.Library.DTO
{
    public class DTO_NgonNgu
    {
        public string MaNgonNgu { get; set; }
        public string TenNgonNgu { get; set; }

        public DTO_NgonNgu()
        {
        }

        public DTO_NgonNgu(string maNgonNgu, string tenNgonNgu)
        {
            MaNgonNgu = maNgonNgu;
            TenNgonNgu = tenNgonNgu;
        }
    }
}
