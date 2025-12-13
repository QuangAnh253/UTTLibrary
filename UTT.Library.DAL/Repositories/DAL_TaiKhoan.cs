using System;
using System.Data;
using System.Data.SqlClient;
using UTT.Library.DAL.Database; // Sử dụng DatabaseHelper
using UTT.Library.DTO;
using System.Collections.Generic;
using System.Linq;
using System.Text;
using System.Threading.Tasks;

namespace UTT.Library.DAL.Repositories
{
    public class DAL_TaiKhoan
    {
        private DatabaseHelper _db = new DatabaseHelper();

        // Kiểm tra đăng nhập: Trả về DTO_TaiKhoan nếu đúng, null nếu sai
        public DTO_TaiKhoan CheckLogin(string user, string pass)
        {
            string sql = "SELECT * FROM TAIKHOAN WHERE TenDangNhap = @user AND MatKhau = @pass AND TrangThai = 1";
            SqlParameter[] param = new SqlParameter[] {
                new SqlParameter("@user", user),
                new SqlParameter("@pass", pass)
            };

            DataTable dt = _db.GetDataTable(sql, param);
            if (dt.Rows.Count > 0)
            {
                DataRow dr = dt.Rows[0];
                return new DTO_TaiKhoan()
                {
                    TenDangNhap = dr["TenDangNhap"].ToString(),
                    MatKhau = dr["MatKhau"].ToString(),
                    Quyen = Convert.ToInt32(dr["Quyen"]),
                    TrangThai = Convert.ToBoolean(dr["TrangThai"])
                };
            }
            return null;
        }
    }
}
