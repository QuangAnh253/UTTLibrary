using System;
using System.Collections.Generic;
using System.Linq;
using System.Text;
using System.Threading.Tasks;

namespace UTT.Library.DTO
{
    public class DTO_ViTri
    {
        public string MaViTri { get; set; }
        public string TenViTri { get; set; }

        public DTO_ViTri()
        {
        }

        public DTO_ViTri(string maViTri, string tenViTri)
        {
            MaViTri = maViTri;
            TenViTri = tenViTri;
        }
    }
}
