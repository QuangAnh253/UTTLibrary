using System;
using System.Data;
using System.Data.SqlClient;
using UTT.Library.DAL.Database;
using UTT.Library.DTO;
using System.Collections.Generic;
using System.Linq;
using System.Text;
using System.Threading.Tasks;

namespace UTT.Library.DAL.Repositories
{
    public class DAL_NhanVien
    {
        private DatabaseHelper _db = new DatabaseHelper();

        // Lấy thông tin nhân viên theo Tên đăng nhập
        public DTO_NhanVien GetByUsername(string username)
        {
            string sql = "SELECT * FROM NHANVIEN WHERE TenDangNhap = @user";
            SqlParameter[] param = new SqlParameter[] { new SqlParameter("@user", username) };

            DataTable dt = _db.GetDataTable(sql, param);
            if (dt.Rows.Count > 0)
            {
                DataRow dr = dt.Rows[0];
                return new DTO_NhanVien()
                {
                    MaNV = dr["MaNV"].ToString(),
                    HoTen = dr["HoTen"].ToString(),
                    Email = dr["Email"].ToString(),
                    SDT = dr["SDT"].ToString(),
                    NgayVaoLam = Convert.ToDateTime(dr["NgayVaoLam"]),
                    TenDangNhap = dr["TenDangNhap"].ToString()
                };
            }
            return null;
        }
    }
}
