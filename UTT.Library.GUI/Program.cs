using System;
using System.Windows.Forms;
using UTT.Library.GUI.Forms.DanhMuc;


namespace UTT.Library.GUI
{
    static class Program
    {
        [STAThread]
        static void Main()
        {
            Application.EnableVisualStyles();
            Application.SetCompatibleTextRenderingDefault(false);

            // Chạy Form Đăng Nhập đầu tiên
            Application.Run(new frmQuanLyTacGia


                ());
        }
    }
}
