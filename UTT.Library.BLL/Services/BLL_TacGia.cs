using System;
using System.Data;
using UTT.Library.DAL.Repositories;
using UTT.Library.DTO;
using System.Collections.Generic;
using System.Linq;
using System.Text;
using System.Threading.Tasks;

namespace UTT.Library.BLL.Services
{
    public class BLL_TacGia
    {
        private DAL_TacGia _dal = new DAL_TacGia();

        public DataTable LayDanhSach()
        {
            return _dal.GetDanhSach();
        }

        public DataTable TimKiem(string keyword)
        {
            return _dal.TimKiem(keyword);
        }

        public string Them(DTO_TacGia tg)
        {
            // Có thể thêm check trùng mã ở đây nếu cần
            if (_dal.Them(tg)) return ""; // Rỗng là thành công
            return "Không thể thêm tác giả (có thể trùng mã)!";
        }

        public string Sua(DTO_TacGia tg)
        {
            if (_dal.Sua(tg)) return "";
            return "Cập nhật thất bại!";
        }

        public string Xoa(string ma)
        {
            if (_dal.Xoa(ma)) return "";
            return "Xóa thất bại (Dữ liệu đang được sử dụng)!";
        }
    }
}
