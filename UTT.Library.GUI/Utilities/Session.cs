using System;
using System.Collections.Generic;
using System.Linq;
using System.Text;
using System.Threading.Tasks;
using UTT.Library.DTO;

namespace UTT.Library.GUI.Utilities
{
    public static class Session
    {
        // Lưu thông tin tài khoản đang đăng nhập (Quyền, User, Pass)
        public static DTO_TaiKhoan CurrentAccount = null;

        // Lưu thông tin chi tiết nhân viên (Họ tên, Mã NV...) để hiển thị/in phiếu
        // Nếu là Admin hoặc Thủ thư thì biến này sẽ có dữ liệu
        public static DTO_NhanVien CurrentEmployee = null;

        // Hàm kiểm tra xem đã đăng nhập chưa
        public static bool IsLoggedIn()
        {
            return CurrentAccount != null;
        }

        // Hàm đăng xuất (Xóa session)
        public static void Clear()
        {
            CurrentAccount = null;
            CurrentEmployee = null;
        }
    }
}
