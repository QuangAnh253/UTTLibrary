using System;
using System.Data;
using System.Data.SqlClient;
using System.Configuration;

namespace UTT.Library.DAL.Database
{
    public class DatabaseHelper
    {
        private readonly string _connectionString;

        public DatabaseHelper()
        {
            // Đảm bảo trong App.config có connectionString tên là "ThuVienUTT" hoặc "QuanLyThuVienUTT"
            // Kiểm tra kỹ lại tên trong file App.config của bạn
            var connSettings = ConfigurationManager.ConnectionStrings["ThuVienUTT"];
            if (connSettings != null)
                _connectionString = connSettings.ConnectionString;
            else
                // Fallback nếu không tìm thấy config (Chữa cháy để không crash)
                _connectionString = @"Data Source=.;Initial Catalog=QuanLyThuVienUTT;Integrated Security=True";
        }

        private SqlConnection GetConnection()
        {
            SqlConnection conn = new SqlConnection(_connectionString);
            conn.Open();
            return conn;
        }

        // --- [SỬA LỖI Ở ĐÂY] ---
        // Đổi tên từ ExecuteQuery -> GetDataTable để khớp với DAL
        public DataTable GetDataTable(string query, SqlParameter[] parameters = null)
        {
            using (SqlConnection conn = GetConnection())
            using (SqlCommand cmd = new SqlCommand(query, conn))
            {
                if (parameters != null)
                    cmd.Parameters.AddRange(parameters);

                using (SqlDataAdapter da = new SqlDataAdapter(cmd))
                {
                    DataTable dt = new DataTable();
                    da.Fill(dt);
                    return dt;
                }
            }
        }

        // Hàm này giữ nguyên để dùng cho Thêm/Sửa/Xóa
        public int ExecuteNonQuery(string query, SqlParameter[] parameters = null)
        {
            using (SqlConnection conn = GetConnection())
            using (SqlCommand cmd = new SqlCommand(query, conn))
            {
                if (parameters != null)
                    cmd.Parameters.AddRange(parameters);

                return cmd.ExecuteNonQuery();
            }
        }

        // Hàm lấy giá trị đơn (Ví dụ: Đếm số dòng)
        public object ExecuteScalar(string query, SqlParameter[] parameters = null)
        {
            using (SqlConnection conn = GetConnection())
            using (SqlCommand cmd = new SqlCommand(query, conn))
            {
                if (parameters != null)
                    cmd.Parameters.AddRange(parameters);

                return cmd.ExecuteScalar();
            }
        }
    }
}