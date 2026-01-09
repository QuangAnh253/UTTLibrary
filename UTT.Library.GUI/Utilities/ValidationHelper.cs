using System;
using System.Collections.Generic;
using System.Linq;
using System.Text;
using System.Threading.Tasks;
using System.Windows.Forms;

namespace UTT.Library.GUI.Utilities
{
    public static class ValidationHelper
    {
        // Kiểm tra rỗng: Trả về False nếu rỗng và hiện thông báo
        public static bool IsRequired(TextBox txt, string fieldName)
        {
            if (string.IsNullOrWhiteSpace(txt.Text))
            {
                MessageBox.Show($"Vui lòng nhập {fieldName}!", "Cảnh báo", MessageBoxButtons.OK, MessageBoxIcon.Warning);
                txt.Focus();
                return false;
            }
            return true;
        }

        // Kiểm tra số: Trả về False nếu không phải số
        public static bool IsNumber(TextBox txt, string fieldName)
        {
            // Cho phép bỏ qua nếu rỗng (dùng IsRequired kết hợp nếu bắt buộc)
            if (string.IsNullOrWhiteSpace(txt.Text)) return true;

            if (!long.TryParse(txt.Text, out _))
            {
                MessageBox.Show($"{fieldName} phải là số!", "Cảnh báo", MessageBoxButtons.OK, MessageBoxIcon.Warning);
                txt.Focus();
                txt.SelectAll();
                return false;
            }
            return true;
        }

        // Xác nhận xóa: Trả về True nếu chọn Yes
        public static bool ConfirmDelete()
        {
            var result = MessageBox.Show("Bạn có chắc chắn muốn xóa dòng này không?", "Xác nhận", MessageBoxButtons.YesNo, MessageBoxIcon.Question);
            return result == DialogResult.Yes;
        }
    }
}
