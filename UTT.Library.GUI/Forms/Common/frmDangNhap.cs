using System;
using System.Windows.Forms;
// Thêm các using cần thiết
using UTT.Library.BLL.Services;
using UTT.Library.DTO;
using UTT.Library.GUI.Utilities;

namespace UTT.Library.GUI.Forms.Common
{
    public partial class frmDangNhap : Form
    {
        // Khai báo BLL để xử lý nghiệp vụ
        private BLL_TaiKhoan _bllTaiKhoan = new BLL_TaiKhoan();

        // Nếu bạn chưa code    BLL_NhanVien thì tạm thời comment dòng này lại
        // private BLL_NhanVien _bllNhanVien = new BLL_NhanVien(); 
        bool isPasswordVisible = false;

        public frmDangNhap()
        {
            InitializeComponent();

            this.Load += (s, e) => txtUsername.Focus();

            // Hover effects
            AddHover(lblExit);
            AddHover(lblClear);

            picTogglePassword.Click += PicTogglePassword_Click;

            this.AcceptButton = btnLogin;

            this.txtPassword.UseSystemPasswordChar = false;
            isPasswordVisible = false;
            picTogglePassword.Image = Properties.Resources.eye_close;

        }

        private void PicTogglePassword_Click(object sender, EventArgs e)
        {
            isPasswordVisible = !isPasswordVisible;

            if (isPasswordVisible)
            {
                txtPassword.UseSystemPasswordChar = true;
                picTogglePassword.Image = Properties.Resources.eye_open;  // icon mắt mở
            }
            else
            {
                txtPassword.UseSystemPasswordChar = false;
                picTogglePassword.Image = Properties.Resources.eye_close; // icon mắt đóng
            }
        }


        private void AddHover(Label lbl)
        {
            lbl.Cursor = Cursors.Hand;

            lbl.MouseEnter += (s, e) =>
            {
                lbl.ForeColor = System.Drawing.Color.Red;
                lbl.Font = new System.Drawing.Font(lbl.Font, System.Drawing.FontStyle.Bold);
            };

            lbl.MouseLeave += (s, e) =>
            {
                lbl.ForeColor = System.Drawing.Color.Black;
                lbl.Font = new System.Drawing.Font(lbl.Font, System.Drawing.FontStyle.Regular);
            };
        }


        private void btnLogin_Click(object sender, EventArgs e)
        {
            string user = txtUsername.Text.Trim();
            string pass = txtPassword.Text.Trim();

            // 1. Validate đầu vào
            if (string.IsNullOrEmpty(user) || string.IsNullOrEmpty(pass))
            {
                MessageBox.Show("Vui lòng nhập tài khoản và mật khẩu!", "Cảnh báo", MessageBoxButtons.OK, MessageBoxIcon.Warning);
                return;
            }

            try
            {
                // 2. Gọi BLL kiểm tra đăng nhập
                // Lưu ý: Bạn cần chắc chắn class BLL_TaiKhoan đã có hàm CheckLogin
                DTO_TaiKhoan tk = _bllTaiKhoan.CheckLogin(user, pass);

                if (tk != null)
                {
                    // 3. Đăng nhập thành công -> Lưu vào Session
                    Session.CurrentAccount = tk;

                    // (Tùy chọn) Lấy thông tin nhân viên nếu cần hiển thị tên
                    // if (tk.Quyen == 1 || tk.Quyen == 2) { Session.CurrentEmployee = ... }

                    MessageBox.Show("Đăng nhập thành công!", "Thông báo", MessageBoxButtons.OK, MessageBoxIcon.Information);

                    // 4. Mở Form Main
                    this.Hide();
                    frmMain f = new frmMain();
                    f.ShowDialog(); // ShowDialog để khi tắt Main thì quay lại Login hoặc tắt hẳn

                    // Khi Form Main đóng lại thì đóng luôn ứng dụng
                    this.Close();
                }
                else
                {
                    MessageBox.Show("Sai tên đăng nhập hoặc mật khẩu!", "Lỗi", MessageBoxButtons.OK, MessageBoxIcon.Error);
                    txtPassword.Text = "";
                    txtPassword.Focus();
                }
            }
            catch (Exception ex)
            {
                MessageBox.Show("Lỗi kết nối CSDL: " + ex.Message, "Lỗi Hệ Thống", MessageBoxButtons.OK, MessageBoxIcon.Error);
            }
        }

        private void lblExit_Click(object sender, EventArgs e)
        {
            Application.Exit();
        }

        private void lblClear_Click(object sender, EventArgs e)
        {
            txtUsername.Text = "";
            txtPassword.Text = "";
            txtUsername.Focus();
        }
    }
}