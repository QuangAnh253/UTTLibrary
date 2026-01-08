using System;
using System.Collections.Generic;
using System.Data;
using System.Data.SqlClient;
using System.Linq;
using System.Text;
using System.Threading.Tasks;
using UTT.Library.DAL.Database;
using UTT.Library.DTO;

namespace UTT.Library.DAL.Repositories
{
    public class DAL_TheLoai
    {
        private DatabaseHelper _db = new DatabaseHelper();

        public DataTable GetDanhSach()
        {
            return _db.GetDataTable("SELECT * FROM THELOAI");
        }

        public bool Them(DTO_TheLoai tg)
        {
            string sql = "INSERT INTO THELOAI (MaTheLoai, TenTheLoai, MoTa) VALUES(@Ma, @Ten, @MT)";
            SqlParameter[] param = {
                new SqlParameter("@Ma", tg.MaTheLoai),
                new SqlParameter("@Ten", tg.TenTheLoai),
                new SqlParameter("@MT", tg.MoTa?? (object)DBNull.Value)
            };
            return _db.ExecuteNonQuery(sql, param) > 0;
        }

        public bool Sua(DTO_TheLoai tg)
        {
            string sql = "UPDATE TACGIA SET TenTheLoai = @Ten, MoTa = @GC WHERE MaTheLoai = @Ma";
            SqlParameter[] param = {
                new SqlParameter("@Ma", tg.MaTheLoai),
                new SqlParameter("@Ten", tg.TenTheLoai),
                new SqlParameter("@MT", tg.MoTa ?? (object)DBNull.Value)
            };
            return _db.ExecuteNonQuery(sql, param) > 0;
        }

        public bool Xoa(string ma)
        {
            string sql = "DELETE FROM THELOAI WHERE MaTheLoai = @Ma";
            SqlParameter[] param = { new SqlParameter("@Ma", ma) };
            return _db.ExecuteNonQuery(sql, param) > 0;
        }

        public DataTable TimKiem(string keyword)
        {
            string sql = "SELECT * FROM THELOAI WHERE TenTheLoai LIKE @Key OR MaTacGia LIKE @Key";
            SqlParameter[] param = { new SqlParameter("@Key", "%" + keyword + "%") };
            return _db.GetDataTable(sql, param);
        }
    }
}