using System;
using System.Collections.Generic;
using System.Linq;
using System.Text;
using System.Threading.Tasks;
using UTT.Library.DAL.Repositories; // Kết nối với DAL
using UTT.Library.DTO; // Kết nối với DTO

namespace UTT.Library.BLL.Services
{
    public class BLL_TaiKhoan
    {
        // Gọi lớp DAL để truy vấn dữ liệu
        private DAL_TaiKhoan _dal = new DAL_TaiKhoan();

        /// <summary>
        /// Hàm kiểm tra đăng nhập dùng cho GUI
        /// </summary>
        public DTO_TaiKhoan CheckLogin(string user, string pass)
        {
            // Kiểm tra sơ bộ dữ liệu đầu vào (Validation)
            if (string.IsNullOrEmpty(user) || string.IsNullOrEmpty(pass))
            {
                return null;
            }

            // Gọi xuống tầng DAL để kiểm tra trong CSDL
            return _dal.CheckLogin(user, pass);
        }
    }
}
