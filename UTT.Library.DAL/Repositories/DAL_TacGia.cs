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
    public class DAL_TacGia
    {
        private DatabaseHelper _db = new DatabaseHelper();

        public DataTable GetDanhSach()
        {
            return _db.GetDataTable("SELECT * FROM TACGIA");
        }

        public bool Them(DTO_TacGia tg)
        {
            string sql = "INSERT INTO TACGIA(MaTacGia, TenTacGia, GhiChu) VALUES(@Ma, @Ten, @GC)";
            SqlParameter[] param = {
                new SqlParameter("@Ma", tg.MaTacGia),
                new SqlParameter("@Ten", tg.TenTacGia),
                new SqlParameter("@GC", tg.GhiChu ?? (object)DBNull.Value)
            };
            return _db.ExecuteNonQuery(sql, param) > 0;
        }

        public bool Sua(DTO_TacGia tg)
        {
            string sql = "UPDATE TACGIA SET TenTacGia = @Ten, GhiChu = @GC WHERE MaTacGia = @Ma";
            SqlParameter[] param = {
                new SqlParameter("@Ma", tg.MaTacGia),
                new SqlParameter("@Ten", tg.TenTacGia),
                new SqlParameter("@GC", tg.GhiChu ?? (object)DBNull.Value)
            };
            return _db.ExecuteNonQuery(sql, param) > 0;
        }

        public bool Xoa(string ma)
        {
            string sql = "DELETE FROM TACGIA WHERE MaTacGia = @Ma";
            SqlParameter[] param = { new SqlParameter("@Ma", ma) };
            return _db.ExecuteNonQuery(sql, param) > 0;
        }

        public DataTable TimKiem(string keyword)
        {
            string sql = "SELECT * FROM TACGIA WHERE TenTacGia LIKE @Key OR MaTacGia LIKE @Key";
            SqlParameter[] param = { new SqlParameter("@Key", "%" + keyword + "%") };
            return _db.GetDataTable(sql, param);
        }
    }
}
