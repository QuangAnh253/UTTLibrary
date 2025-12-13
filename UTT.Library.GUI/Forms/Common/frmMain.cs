using System;
using System.Collections.Generic;
using System.ComponentModel;
using System.Data;
using System.Drawing;
using System.Linq;
using System.Text;
using System.Threading.Tasks;
using System.Windows.Forms;
using UTT.Library.GUI.Utilities;
using UTT.Library.GUI.Forms.HeThong;
using UTT.Library.GUI.Forms.DanhMuc;
using UTT.Library.GUI.Forms.QuanLySach;
using UTT.Library.GUI.Forms.QuanLyNguoi;
using UTT.Library.GUI.Forms.NghiepVu;

namespace UTT.Library.GUI.Forms.Common
{
    public partial class frmMain : Form
    {
        public frmMain()
        {
            InitializeComponent();
        }

        private void OpenChildForm(Form childForm)
        {
            // 1. Nếu form đã mở → focus lại
            foreach (Form f in this.MdiChildren)
            {
                if (f.GetType() == childForm.GetType())
                {
                    f.WindowState = FormWindowState.Normal;  // tránh bị minimize
                    f.Activate();
                    return;
                }
            }

            // 2. Cấu hình form con
            childForm.MdiParent = this;
            childForm.FormBorderStyle = FormBorderStyle.None;

            // TUYỆT ĐỐI: KHÔNG set WindowState.Maximized (gây lệch)
            // Chỉ dùng Dock Fill
            childForm.Dock = DockStyle.Fill;

            // FIX DPI lỗi lệch (dòng quan trọng)
            childForm.AutoScaleMode = AutoScaleMode.None;

            // 3. Hiển thị
            childForm.Show();
            childForm.BringToFront();
        }


        // --- HÀM HỖ TRỢ: Giao diện ---
        private void CanGiuaLogo()
        {
            // Kiểm tra null để tránh lỗi nếu chưa kéo control vào
            if (logo != null)
            {
                logo.Left = (this.ClientSize.Width - logo.Width) / 2;
                logo.Top = (this.ClientSize.Height - logo.Height) / 2;
            }

            if (lblTitle != null && logo != null)
            {
                lblTitle.Left = (this.ClientSize.Width - lblTitle.Width) / 2;
                lblTitle.Top = logo.Top - lblTitle.Height - 30;
            }
        }

        private void CustomizeDesign()
        {
            // Làm đẹp MenuStrip
            menuStrip1.BackColor = Color.WhiteSmoke;
            menuStrip1.Font = new Font("Segoe UI", 10, FontStyle.Regular);

            // Làm đẹp StatusStrip
            statusStrip1.BackColor = Color.FromArgb(45, 45, 48); // Màu tối
            statusStrip1.ForeColor = Color.White;
        }

        private void FixMDIClient()
        {
            foreach (Control ctl in this.Controls)
            {
                if (ctl is MdiClient client)
                {
                    client.Dock = DockStyle.Fill;
                    client.BackColor = Color.White;   // hoặc màu bạn muốn
                }
            }
        }

        // --- SỰ KIỆN LOAD & RESIZE ---
        private void frmMain_Load(object sender, EventArgs e)
        {
            CustomizeDesign();
            CanGiuaLogo();
            FixMDIClient();

            // Đổi màu nền MDI Container thành trắng
            foreach (Control ctl in this.Controls)
            {
                if (ctl is MdiClient)
                {
                    ctl.BackColor = Color.White;
                    break;
                }
            }

            // Hiển thị thông tin Session (nếu đã đăng nhập)
            if (Session.IsLoggedIn())
            {
                string role = Session.CurrentAccount.Quyen == 1 ? "Admin" : (Session.CurrentAccount.Quyen == 2 ? "Thủ thư" : "Sinh viên");
                lblTaiKhoan.Text = $"👤 Cán bộ: {Session.CurrentAccount.TenDangNhap} | Quyền: {role}";

                // --- CHÈN HÀM PHÂN QUYỀN VÀO ĐÂY ---
                PhanQuyen();
            }
            else
            {
                lblTaiKhoan.Text = "👤 Chưa đăng nhập (Chế độ Test)";
                // Nếu muốn test giao diện phân quyền mà không cần login, có thể gọi tạm ở đây (nhưng nhớ fake Session trước)
            }

            // Khởi động đồng hồ & Set text tĩnh
            timer1.Start();
            lblDeTai.Text = "📚 Đề tài: Quản lý Thư viện Thông minh UTT";
            lblHocPhan.Text = "🎓 Lập trình trực quan C# | GV: Phạm Đức Anh";
            lblVersion.Text = "Ver: 1.0.2";

            if (logo != null)
            {
                this.Controls.Add(logo); // Đảm bảo nó thuộc form
            }

            // Ẩn logo khi có form con mở ra (Mẹo xử lý triệt để)
            this.MdiChildActivate += FrmMain_MdiChildActivate;
        }
        private void PhanQuyen()
        {
            // Lấy quyền từ Session
            int quyen = Session.CurrentAccount.Quyen;
            // 1: Admin, 2: Thủ thư, 3: Sinh viên

            // --- CASE 1: ADMIN (QUYỀN 1) ---
            if (quyen == 1)
            {
                // Admin thấy hết -> Không cần ẩn gì cả
                return;
            }

            // --- CASE 2: THỦ THƯ (QUYỀN 2) ---
            if (quyen == 2)
            {
                // Ẩn chức năng quản trị hệ thống cao cấp
                mnuQuanLyTaiKhoan.Visible = false; // Thủ thư không được cấp nick cho người khác

                // Các chức năng nghiệp vụ khác vẫn dùng bình thường
            }

            // --- CASE 3: SINH VIÊN (QUYỀN 3) ---
            if (quyen == 3)
            {
                // Ẩn sạch các menu quản lý, chỉ để lại tra cứu
                mnuHeThong.Visible = true; // Để đổi pass/đăng xuất
                mnuQuanLyTaiKhoan.Visible = false;

                mnuDanhMuc.Visible = false;   // Không sửa danh mục
                mnuQLSach.Visible = false;    // Ẩn menu cha Sách

                // Mở lại menu con Tra Cứu (Hack: Vì ẩn cha thì con cũng mất, nên ta phải xử lý khéo)
                // Cách tốt nhất: Tạo một Menu riêng cho Sinh viên hoặc chỉ ẩn các nút con

                // Ẩn từng nút con trong menu Sách
                mnuSach.Visible = false;
                mnuNhapSach.Visible = false;
                mnuKho.Visible = false;
                mnuThanhLy.Visible = false;
                mnuNCC.Visible = false;
                // mnuTraCuu.Visible = true; // Giữ lại cái này

                mnuQLNhanSu.Visible = false;

                // Ẩn nghiệp vụ Mượn/Trả (SV không tự làm được)
                mnuMuonSach.Visible = false;
                mnuTraSach.Visible = false;

                // Hiện Đặt trước
                mnuDatTruoc.Visible = true;
            }
        }

        private void FrmMain_MdiChildActivate(object sender, EventArgs e)
        {
            if (this.ActiveMdiChild != null)
            {
                // Có form con đang mở -> Ẩn logo đi cho đỡ vướng
                if (logo != null) logo.Visible = false;
                if (lblTitle != null) lblTitle.Visible = false;
            }
            else
            {
                // Không có form con (đã đóng hết) -> Hiện lại logo
                if (logo != null) logo.Visible = true;
                if (lblTitle != null) lblTitle.Visible = true;
            }
        }

        private void frmMain_Resize(object sender, EventArgs e)
        {
            CanGiuaLogo();
        }

        private void timer1_Tick(object sender, EventArgs e)
        {
            lblThoiGian.Text = string.Format("Hôm nay: {0:dd/MM/yyyy HH:mm:ss}", DateTime.Now);
        }

        // =================================================================================
        // SỰ KIỆN CLICK MENU (ĐIỀU HƯỚNG)
        // =================================================================================

        private void mnuDoiMatKhau_Click(object sender, EventArgs e)
        {
            frmDoiMatKhau f = new frmDoiMatKhau();
            f.ShowDialog();
        }

        private void mnuQuanLyTaiKhoan_Click(object sender, EventArgs e)
        {
            OpenChildForm(new frmQuanLyTaiKhoan()); // Module 01
        }

        private void mnuDangXuat_Click(object sender, EventArgs e)
        {
            if (MessageBox.Show("Bạn muốn đăng xuất?", "Xác nhận", MessageBoxButtons.YesNo, MessageBoxIcon.Question) == DialogResult.Yes)
            {
                Session.Clear();
                this.Hide();
                frmDangNhap login = new frmDangNhap();
                login.ShowDialog();
                this.Close();
            }   
        }

        private void mnuThoat_Click(object sender, EventArgs e)
        {
            Application.Exit();
        }

        private void mnuTacGia_Click(object sender, EventArgs e)
        {
            OpenChildForm(new frmQuanLyTacGia()); // Module 14
        }

        private void mnuNXB_Click(object sender, EventArgs e)
        {
            OpenChildForm(new frmQuanLyNhaXuatBan()); // Module 15
        }

        private void mnuTheLoai_Click(object sender, EventArgs e)
        {
            OpenChildForm(new frmQuanLyTheLoai()); // Module 16
        }

        private void mnuNgonNgu_Click(object sender, EventArgs e)
        {
            OpenChildForm(new frmQuanLyNgonNgu()); // Module 17
        }

        private void mnuViTri_Click(object sender, EventArgs e)
        {
            OpenChildForm(new frmQuanLyViTri()); // Module 18
        }



        private void mnuSach_Click(object sender, EventArgs e)
        {
            OpenChildForm(new frmQuanLySach()); // Module 06
        }

        private void mnuNhapSach_Click(object sender, EventArgs e)
        {
            OpenChildForm(new frmQuanLyNhapSach()); // Module 03
        }

        private void mnuKho_Click(object sender, EventArgs e)
        {
            OpenChildForm(new frmQuanLyKhoVaViTri()); // Module 09
        }

        private void mnuThanhLy_Click(object sender, EventArgs e)
        {
            OpenChildForm(new frmQuanLyThanhLy()); // Module 07
        }

        private void mnuNCC_Click(object sender, EventArgs e)
        {
            OpenChildForm(new frmQuanLyNhaCungCap()); // Module 02
        }

        private void mnuTraCuu_Click(object sender, EventArgs e)
        {
            OpenChildForm(new frmTraCuuNangCao()); // Bonus
        }

        private void mnuSinhVien_Click(object sender, EventArgs e)
        {
            OpenChildForm(new frmQuanLyDocGia()); // Module 10
        }

        private void mnuNhanVien_Click(object sender, EventArgs e)
        {
            OpenChildForm(new frmQuanLyNhanVien()); // Module 11
        }

        private void mnuTheTV_Click(object sender, EventArgs e)
        {
            OpenChildForm(new frmQuanLyTheTV()); // Module 12
        }

        private void mnuViPham_Click(object sender, EventArgs e)
        {
            OpenChildForm(new frmQuanLyViPham()); // Module 13
        }

        private void mnuLichSu_Click(object sender, EventArgs e)
        {
            OpenChildForm(new frmLichSuMuonTra()); // Bonus
        }

        private void mnuMuonSach_Click(object sender, EventArgs e)
        {
            OpenChildForm(new frmQuanLyMuonSach()); // Module 04
        }

        private void mnuTraSach_Click(object sender, EventArgs e)
        {
            OpenChildForm(new frmQuanLyTraSach()); // Module 05
        }

        private void mnuDatTruoc_Click(object sender, EventArgs e)
        {
            OpenChildForm(new frmQuanLyDatTruoc()); // Module 19 (Lộc)
        }

        private void mnuDashboard_Click(object sender, EventArgs e)
        {
            OpenChildForm(new frmDashboard()); // Bonus
        }

        private void mnuLop_Click(object sender, EventArgs e)
        {
            OpenChildForm(new frmQuanLyLop());
        }

        private void mnuKhoa_Click(object sender, EventArgs e)
        {
            OpenChildForm(new frmQuanLyKhoa());
        }
    }
}