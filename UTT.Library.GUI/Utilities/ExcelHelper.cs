using System;
using System.IO;
using System.Windows.Forms;
using System.Collections.Generic;
using System.Linq;
using System.Text;
using System.Threading.Tasks;

namespace UTT.Library.GUI.Utilities
{
    public static class ExcelHelper
    {
        /// <summary>
        /// Xuất dữ liệu từ DataGridView ra file Excel (dạng CSV)
        /// </summary>
        public static void ExportToExcel(DataGridView dgv, string fileName)
        {
            if (dgv.Rows.Count == 0)
            {
                MessageBox.Show("Không có dữ liệu để xuất!", "Thông báo", MessageBoxButtons.OK, MessageBoxIcon.Warning);
                return;
            }

            SaveFileDialog sfd = new SaveFileDialog();
            sfd.Filter = "Excel Documents (*.csv)|*.csv";
            sfd.FileName = fileName + "_" + DateTime.Now.ToString("yyyyMMdd_HHmmss") + ".csv";

            if (sfd.ShowDialog() == DialogResult.OK)
            {
                try
                {
                    StringBuilder sb = new StringBuilder();

                    // 1. Lấy tên cột
                    for (int i = 0; i < dgv.Columns.Count; i++)
                    {
                        sb.Append(dgv.Columns[i].HeaderText + ",");
                    }
                    sb.AppendLine();

                    // 2. Lấy dữ liệu dòng
                    foreach (DataGridViewRow row in dgv.Rows)
                    {
                        if (!row.IsNewRow)
                        {
                            for (int i = 0; i < dgv.Columns.Count; i++)
                            {
                                // Xử lý nếu dữ liệu có chứa dấu phẩy thì bao quanh bằng ngoặc kép
                                string cellValue = row.Cells[i].Value?.ToString() ?? "";
                                if (cellValue.Contains(","))
                                {
                                    cellValue = "\"" + cellValue + "\"";
                                }
                                sb.Append(cellValue + ",");
                            }
                            sb.AppendLine();
                        }
                    }

                    // 3. Ghi file (Dùng Encoding UTF8 để không lỗi font tiếng Việt)
                    File.WriteAllText(sfd.FileName, sb.ToString(), Encoding.UTF8);

                    MessageBox.Show("Xuất Excel thành công!\nĐường dẫn: " + sfd.FileName, "Thông báo");
                }
                catch (Exception ex)
                {
                    MessageBox.Show("Lỗi khi xuất file: " + ex.Message, "Lỗi");
                }
            }
        }
    }
}
