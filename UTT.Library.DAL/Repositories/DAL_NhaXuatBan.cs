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
    public class DAL_NhaXuatBan
    {
        private DatabaseHelper _db = new DatabaseHelper();

        public DataTable GetDanhSach()
        {
            return _db.GetDataTable("SELECT * FROM NHAXUATBAN");
        }

        public bool Them(DTO_NhaXuatBan tg)
        {
            string sql = "INSERT INTO NHAXUATBAN (MaNXB, TenNXB, DiaChi,SDT) VALUES(@Ma, @Ten, @DC,@SDT)";
            SqlParameter[] param = {
                new SqlParameter("@Ma", tg.MaNXB),
                new SqlParameter("@Ten", tg.TenNXB),
                 new SqlParameter("@SDT", tg.SDT),
                new SqlParameter("@DC", tg.DiaChi?? (object)DBNull.Value)
            };
            return _db.ExecuteNonQuery(sql, param) > 0;
        }

        public bool Sua(DTO_NhaXuatBan tg)
        {
            string sql = "UPDATE TACGIA SET TenNXB = @Ten, DiaChi = @GC WHERE MaNXB = @Ma";
            SqlParameter[] param = {
               new SqlParameter("@Ma", tg.MaNXB),
                new SqlParameter("@Ten", tg.TenNXB),
                 new SqlParameter("@SDT", tg.SDT),
                new SqlParameter("@DC", tg.DiaChi?? (object)DBNull.Value)
            };
            return _db.ExecuteNonQuery(sql, param) > 0;
        }

        public bool Xoa(string ma)
        {
            string sql = "DELETE FROM THELOAI WHERE MaNXB = @Ma";
            SqlParameter[] param = { new SqlParameter("@Ma", ma) };
            return _db.ExecuteNonQuery(sql, param) > 0;
        }

        public DataTable TimKiem(string keyword)
        {
            string sql = "SELECT * FROM NHAXUATBAN WHERE TenNXB LIKE @Key OR MaNXB LIKE @Key";
            SqlParameter[] param = { new SqlParameter("@Key", "%" + keyword + "%") };
            return _db.GetDataTable(sql, param);
        }
    }
}