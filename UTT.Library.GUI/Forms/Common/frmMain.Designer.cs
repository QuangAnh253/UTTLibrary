namespace UTT.Library.GUI.Forms.Common
{
    partial class frmMain
    {
        /// <summary>
        /// Required designer variable.
        /// </summary>
        private System.ComponentModel.IContainer components = null;

        /// <summary>
        /// Clean up any resources being used.
        /// </summary>
        /// <param name="disposing">true if managed resources should be disposed; otherwise, false.</param>
        protected override void Dispose(bool disposing)
        {
            if (disposing && (components != null))
            {
                components.Dispose();
            }
            base.Dispose(disposing);
        }

        #region Windows Form Designer generated code

        /// <summary>
        /// Required method for Designer support - do not modify
        /// the contents of this method with the code editor.
        /// </summary>
        private void InitializeComponent()
        {
            this.components = new System.ComponentModel.Container();
            this.menuStrip1 = new System.Windows.Forms.MenuStrip();
            this.mnuHeThong = new System.Windows.Forms.ToolStripMenuItem();
            this.mnuDoiMatKhau = new System.Windows.Forms.ToolStripMenuItem();
            this.mnuQuanLyTaiKhoan = new System.Windows.Forms.ToolStripMenuItem();
            this.mnuDangXuat = new System.Windows.Forms.ToolStripMenuItem();
            this.mnuThoat = new System.Windows.Forms.ToolStripMenuItem();
            this.mnuDanhMuc = new System.Windows.Forms.ToolStripMenuItem();
            this.mnuTacGia = new System.Windows.Forms.ToolStripMenuItem();
            this.mnuNXB = new System.Windows.Forms.ToolStripMenuItem();
            this.mnuTheLoai = new System.Windows.Forms.ToolStripMenuItem();
            this.mnuNgonNgu = new System.Windows.Forms.ToolStripMenuItem();
            this.mnuViTri = new System.Windows.Forms.ToolStripMenuItem();
            this.mnuLopKhoa = new System.Windows.Forms.ToolStripMenuItem();
            this.mnuQLSach = new System.Windows.Forms.ToolStripMenuItem();
            this.mnuSach = new System.Windows.Forms.ToolStripMenuItem();
            this.mnuNhapSach = new System.Windows.Forms.ToolStripMenuItem();
            this.mnuKho = new System.Windows.Forms.ToolStripMenuItem();
            this.mnuThanhLy = new System.Windows.Forms.ToolStripMenuItem();
            this.mnuNCC = new System.Windows.Forms.ToolStripMenuItem();
            this.mnuTraCuu = new System.Windows.Forms.ToolStripMenuItem();
            this.mnuQLNhanSu = new System.Windows.Forms.ToolStripMenuItem();
            this.mnuSinhVien = new System.Windows.Forms.ToolStripMenuItem();
            this.mnuNhanVien = new System.Windows.Forms.ToolStripMenuItem();
            this.mnuTheTV = new System.Windows.Forms.ToolStripMenuItem();
            this.mnuViPham = new System.Windows.Forms.ToolStripMenuItem();
            this.mnuLichSu = new System.Windows.Forms.ToolStripMenuItem();
            this.mnuNghiepVu = new System.Windows.Forms.ToolStripMenuItem();
            this.mnuMuonSach = new System.Windows.Forms.ToolStripMenuItem();
            this.mnuTraSach = new System.Windows.Forms.ToolStripMenuItem();
            this.mnuDatTruoc = new System.Windows.Forms.ToolStripMenuItem();
            this.mnuBaoCao = new System.Windows.Forms.ToolStripMenuItem();
            this.mnuDashboard = new System.Windows.Forms.ToolStripMenuItem();
            this.statusStrip1 = new System.Windows.Forms.StatusStrip();
            this.lblTaiKhoan = new System.Windows.Forms.ToolStripStatusLabel();
            this.lblDeTai = new System.Windows.Forms.ToolStripStatusLabel();
            this.lblHocPhan = new System.Windows.Forms.ToolStripStatusLabel();
            this.lblSpace = new System.Windows.Forms.ToolStripStatusLabel();
            this.lblVersion = new System.Windows.Forms.ToolStripStatusLabel();
            this.lblThoiGian = new System.Windows.Forms.ToolStripStatusLabel();
            this.lblTitle = new System.Windows.Forms.Label();
            this.timer1 = new System.Windows.Forms.Timer(this.components);
            this.mnuLop = new System.Windows.Forms.ToolStripMenuItem();
            this.mnuKhoa = new System.Windows.Forms.ToolStripMenuItem();
            this.toolStripStatusLabel1 = new System.Windows.Forms.ToolStripStatusLabel();
            this.toolStripStatusLabel2 = new System.Windows.Forms.ToolStripStatusLabel();
            this.logo = new System.Windows.Forms.PictureBox();
            this.menuStrip1.SuspendLayout();
            this.statusStrip1.SuspendLayout();
            ((System.ComponentModel.ISupportInitialize)(this.logo)).BeginInit();
            this.SuspendLayout();
            // 
            // menuStrip1
            // 
            this.menuStrip1.Font = new System.Drawing.Font("Segoe UI", 12F, System.Drawing.FontStyle.Regular, System.Drawing.GraphicsUnit.Point, ((byte)(0)));
            this.menuStrip1.ImageScalingSize = new System.Drawing.Size(24, 24);
            this.menuStrip1.Items.AddRange(new System.Windows.Forms.ToolStripItem[] {
            this.mnuHeThong,
            this.mnuDanhMuc,
            this.mnuQLSach,
            this.mnuQLNhanSu,
            this.mnuNghiepVu,
            this.mnuBaoCao});
            this.menuStrip1.Location = new System.Drawing.Point(0, 0);
            this.menuStrip1.Name = "menuStrip1";
            this.menuStrip1.Padding = new System.Windows.Forms.Padding(10, 5, 10, 5);
            this.menuStrip1.RenderMode = System.Windows.Forms.ToolStripRenderMode.Professional;
            this.menuStrip1.Size = new System.Drawing.Size(1264, 35);
            this.menuStrip1.TabIndex = 1;
            this.menuStrip1.Text = "menuStrip1";
            // 
            // mnuHeThong
            // 
            this.mnuHeThong.DropDownItems.AddRange(new System.Windows.Forms.ToolStripItem[] {
            this.mnuDoiMatKhau,
            this.mnuQuanLyTaiKhoan,
            this.mnuDangXuat,
            this.mnuThoat});
            this.mnuHeThong.Name = "mnuHeThong";
            this.mnuHeThong.Size = new System.Drawing.Size(86, 25);
            this.mnuHeThong.Text = "Hệ thống";
            // 
            // mnuDoiMatKhau
            // 
            this.mnuDoiMatKhau.Name = "mnuDoiMatKhau";
            this.mnuDoiMatKhau.Size = new System.Drawing.Size(203, 26);
            this.mnuDoiMatKhau.Text = "Đổi mật khẩu";
            this.mnuDoiMatKhau.Click += new System.EventHandler(this.mnuDoiMatKhau_Click);
            // 
            // mnuQuanLyTaiKhoan
            // 
            this.mnuQuanLyTaiKhoan.Name = "mnuQuanLyTaiKhoan";
            this.mnuQuanLyTaiKhoan.Size = new System.Drawing.Size(203, 26);
            this.mnuQuanLyTaiKhoan.Text = "Quản lý Tài khoản";
            this.mnuQuanLyTaiKhoan.Click += new System.EventHandler(this.mnuQuanLyTaiKhoan_Click);
            // 
            // mnuDangXuat
            // 
            this.mnuDangXuat.Name = "mnuDangXuat";
            this.mnuDangXuat.Size = new System.Drawing.Size(203, 26);
            this.mnuDangXuat.Text = "Đăng xuất";
            this.mnuDangXuat.Click += new System.EventHandler(this.mnuDangXuat_Click);
            // 
            // mnuThoat
            // 
            this.mnuThoat.Name = "mnuThoat";
            this.mnuThoat.Size = new System.Drawing.Size(203, 26);
            this.mnuThoat.Text = "Thoát";
            this.mnuThoat.Click += new System.EventHandler(this.mnuThoat_Click);
            // 
            // mnuDanhMuc
            // 
            this.mnuDanhMuc.DropDownItems.AddRange(new System.Windows.Forms.ToolStripItem[] {
            this.mnuTacGia,
            this.mnuNXB,
            this.mnuTheLoai,
            this.mnuNgonNgu,
            this.mnuViTri,
            this.mnuLopKhoa});
            this.mnuDanhMuc.Name = "mnuDanhMuc";
            this.mnuDanhMuc.Size = new System.Drawing.Size(93, 25);
            this.mnuDanhMuc.Text = "Danh mục";
            // 
            // mnuTacGia
            // 
            this.mnuTacGia.Name = "mnuTacGia";
            this.mnuTacGia.Size = new System.Drawing.Size(172, 26);
            this.mnuTacGia.Text = "Tác giả";
            this.mnuTacGia.Click += new System.EventHandler(this.mnuTacGia_Click);
            // 
            // mnuNXB
            // 
            this.mnuNXB.Name = "mnuNXB";
            this.mnuNXB.Size = new System.Drawing.Size(172, 26);
            this.mnuNXB.Text = "Nhà xuất bản";
            this.mnuNXB.Click += new System.EventHandler(this.mnuNXB_Click);
            // 
            // mnuTheLoai
            // 
            this.mnuTheLoai.Name = "mnuTheLoai";
            this.mnuTheLoai.Size = new System.Drawing.Size(172, 26);
            this.mnuTheLoai.Text = "Thể loại";
            this.mnuTheLoai.Click += new System.EventHandler(this.mnuTheLoai_Click);
            // 
            // mnuNgonNgu
            // 
            this.mnuNgonNgu.Name = "mnuNgonNgu";
            this.mnuNgonNgu.Size = new System.Drawing.Size(172, 26);
            this.mnuNgonNgu.Text = "Ngôn ngữ";
            this.mnuNgonNgu.Click += new System.EventHandler(this.mnuNgonNgu_Click);
            // 
            // mnuViTri
            // 
            this.mnuViTri.Name = "mnuViTri";
            this.mnuViTri.Size = new System.Drawing.Size(172, 26);
            this.mnuViTri.Text = "Vị trí và kệ";
            this.mnuViTri.Click += new System.EventHandler(this.mnuViTri_Click);
            // 
            // mnuLopKhoa
            // 
            this.mnuLopKhoa.DropDownItems.AddRange(new System.Windows.Forms.ToolStripItem[] {
            this.mnuLop,
            this.mnuKhoa});
            this.mnuLopKhoa.Name = "mnuLopKhoa";
            this.mnuLopKhoa.Size = new System.Drawing.Size(172, 26);
            this.mnuLopKhoa.Text = "Lớp và khoa";
            // 
            // mnuQLSach
            // 
            this.mnuQLSach.DropDownItems.AddRange(new System.Windows.Forms.ToolStripItem[] {
            this.mnuSach,
            this.mnuNhapSach,
            this.mnuKho,
            this.mnuThanhLy,
            this.mnuNCC,
            this.mnuTraCuu});
            this.mnuQLSach.Name = "mnuQLSach";
            this.mnuQLSach.Size = new System.Drawing.Size(130, 25);
            this.mnuQLSach.Text = "QL Sách và Kho";
            // 
            // mnuSach
            // 
            this.mnuSach.Name = "mnuSach";
            this.mnuSach.Size = new System.Drawing.Size(197, 26);
            this.mnuSach.Text = "Hồ sơ Đầu sách";
            this.mnuSach.Click += new System.EventHandler(this.mnuSach_Click);
            // 
            // mnuNhapSach
            // 
            this.mnuNhapSach.Name = "mnuNhapSach";
            this.mnuNhapSach.Size = new System.Drawing.Size(197, 26);
            this.mnuNhapSach.Text = "Nhập sách mới";
            this.mnuNhapSach.Click += new System.EventHandler(this.mnuNhapSach_Click);
            // 
            // mnuKho
            // 
            this.mnuKho.Name = "mnuKho";
            this.mnuKho.Size = new System.Drawing.Size(197, 26);
            this.mnuKho.Text = "Quản lý Kho";
            this.mnuKho.Click += new System.EventHandler(this.mnuKho_Click);
            // 
            // mnuThanhLy
            // 
            this.mnuThanhLy.Name = "mnuThanhLy";
            this.mnuThanhLy.Size = new System.Drawing.Size(197, 26);
            this.mnuThanhLy.Text = "Thanh lý sách";
            this.mnuThanhLy.Click += new System.EventHandler(this.mnuThanhLy_Click);
            // 
            // mnuNCC
            // 
            this.mnuNCC.Name = "mnuNCC";
            this.mnuNCC.Size = new System.Drawing.Size(197, 26);
            this.mnuNCC.Text = "Nhà Cung Cấp";
            this.mnuNCC.Click += new System.EventHandler(this.mnuNCC_Click);
            // 
            // mnuTraCuu
            // 
            this.mnuTraCuu.Name = "mnuTraCuu";
            this.mnuTraCuu.Size = new System.Drawing.Size(197, 26);
            this.mnuTraCuu.Text = "Tra cứu nâng cao";
            this.mnuTraCuu.Click += new System.EventHandler(this.mnuTraCuu_Click);
            // 
            // mnuQLNhanSu
            // 
            this.mnuQLNhanSu.DropDownItems.AddRange(new System.Windows.Forms.ToolStripItem[] {
            this.mnuSinhVien,
            this.mnuNhanVien,
            this.mnuTheTV,
            this.mnuViPham,
            this.mnuLichSu});
            this.mnuQLNhanSu.Name = "mnuQLNhanSu";
            this.mnuQLNhanSu.Size = new System.Drawing.Size(104, 25);
            this.mnuQLNhanSu.Text = "QL Nhân sự";
            // 
            // mnuSinhVien
            // 
            this.mnuSinhVien.Name = "mnuSinhVien";
            this.mnuSinhVien.Size = new System.Drawing.Size(209, 26);
            this.mnuSinhVien.Text = "Hồ sơ Sinh viên";
            this.mnuSinhVien.Click += new System.EventHandler(this.mnuSinhVien_Click);
            // 
            // mnuNhanVien
            // 
            this.mnuNhanVien.Name = "mnuNhanVien";
            this.mnuNhanVien.Size = new System.Drawing.Size(209, 26);
            this.mnuNhanVien.Text = "Hồ sơ Nhân viên";
            this.mnuNhanVien.Click += new System.EventHandler(this.mnuNhanVien_Click);
            // 
            // mnuTheTV
            // 
            this.mnuTheTV.Name = "mnuTheTV";
            this.mnuTheTV.Size = new System.Drawing.Size(209, 26);
            this.mnuTheTV.Text = "Thẻ Thư viện";
            this.mnuTheTV.Click += new System.EventHandler(this.mnuTheTV_Click);
            // 
            // mnuViPham
            // 
            this.mnuViPham.Name = "mnuViPham";
            this.mnuViPham.Size = new System.Drawing.Size(209, 26);
            this.mnuViPham.Text = "Vi phạm và Kỷ luật";
            this.mnuViPham.Click += new System.EventHandler(this.mnuViPham_Click);
            // 
            // mnuLichSu
            // 
            this.mnuLichSu.Name = "mnuLichSu";
            this.mnuLichSu.Size = new System.Drawing.Size(209, 26);
            this.mnuLichSu.Text = "Lịch sử Mượn/Trả";
            this.mnuLichSu.Click += new System.EventHandler(this.mnuLichSu_Click);
            // 
            // mnuNghiepVu
            // 
            this.mnuNghiepVu.DropDownItems.AddRange(new System.Windows.Forms.ToolStripItem[] {
            this.mnuMuonSach,
            this.mnuTraSach,
            this.mnuDatTruoc});
            this.mnuNghiepVu.Name = "mnuNghiepVu";
            this.mnuNghiepVu.Size = new System.Drawing.Size(94, 25);
            this.mnuNghiepVu.Text = "Nghiệp vụ";
            // 
            // mnuMuonSach
            // 
            this.mnuMuonSach.Name = "mnuMuonSach";
            this.mnuMuonSach.Size = new System.Drawing.Size(165, 26);
            this.mnuMuonSach.Text = "Mượn sách";
            this.mnuMuonSach.Click += new System.EventHandler(this.mnuMuonSach_Click);
            // 
            // mnuTraSach
            // 
            this.mnuTraSach.Name = "mnuTraSach";
            this.mnuTraSach.Size = new System.Drawing.Size(165, 26);
            this.mnuTraSach.Text = "Trả sách";
            this.mnuTraSach.Click += new System.EventHandler(this.mnuTraSach_Click);
            // 
            // mnuDatTruoc
            // 
            this.mnuDatTruoc.Name = "mnuDatTruoc";
            this.mnuDatTruoc.Size = new System.Drawing.Size(165, 26);
            this.mnuDatTruoc.Text = "Đặt giữ sách";
            this.mnuDatTruoc.Click += new System.EventHandler(this.mnuDatTruoc_Click);
            // 
            // mnuBaoCao
            // 
            this.mnuBaoCao.DropDownItems.AddRange(new System.Windows.Forms.ToolStripItem[] {
            this.mnuDashboard});
            this.mnuBaoCao.Name = "mnuBaoCao";
            this.mnuBaoCao.Size = new System.Drawing.Size(76, 25);
            this.mnuBaoCao.Text = "Báo cáo";
            // 
            // mnuDashboard
            // 
            this.mnuDashboard.Name = "mnuDashboard";
            this.mnuDashboard.Size = new System.Drawing.Size(224, 26);
            this.mnuDashboard.Text = "Dashboard Thống kê";
            this.mnuDashboard.Click += new System.EventHandler(this.mnuDashboard_Click);
            // 
            // statusStrip1
            // 
            this.statusStrip1.Font = new System.Drawing.Font("Segoe UI", 9.75F, System.Drawing.FontStyle.Regular, System.Drawing.GraphicsUnit.Point, ((byte)(0)));
            this.statusStrip1.ImageScalingSize = new System.Drawing.Size(24, 24);
            this.statusStrip1.Items.AddRange(new System.Windows.Forms.ToolStripItem[] {
            this.lblTaiKhoan,
            this.toolStripStatusLabel2,
            this.lblDeTai,
            this.toolStripStatusLabel1,
            this.lblHocPhan,
            this.lblSpace,
            this.lblVersion,
            this.lblThoiGian});
            this.statusStrip1.Location = new System.Drawing.Point(0, 735);
            this.statusStrip1.Name = "statusStrip1";
            this.statusStrip1.Size = new System.Drawing.Size(1264, 26);
            this.statusStrip1.TabIndex = 3;
            this.statusStrip1.Text = "statusStrip1";
            // 
            // lblTaiKhoan
            // 
            this.lblTaiKhoan.BorderSides = System.Windows.Forms.ToolStripStatusLabelBorderSides.Right;
            this.lblTaiKhoan.BorderStyle = System.Windows.Forms.Border3DStyle.SunkenOuter;
            this.lblTaiKhoan.Name = "lblTaiKhoan";
            this.lblTaiKhoan.Size = new System.Drawing.Size(65, 21);
            this.lblTaiKhoan.Text = "Sẵn sàng";
            this.lblTaiKhoan.TextAlign = System.Drawing.ContentAlignment.MiddleLeft;
            // 
            // lblDeTai
            // 
            this.lblDeTai.BorderStyle = System.Windows.Forms.Border3DStyle.SunkenOuter;
            this.lblDeTai.Name = "lblDeTai";
            this.lblDeTai.Size = new System.Drawing.Size(167, 21);
            this.lblDeTai.Text = "📚 Đề tài: QL Thư viện UTT";
            // 
            // lblHocPhan
            // 
            this.lblHocPhan.Name = "lblHocPhan";
            this.lblHocPhan.Size = new System.Drawing.Size(141, 21);
            this.lblHocPhan.Text = "🎓 Lập trình trực quan";
            // 
            // lblSpace
            // 
            this.lblSpace.Name = "lblSpace";
            this.lblSpace.Size = new System.Drawing.Size(173, 21);
            this.lblSpace.Spring = true;
            this.lblSpace.Text = "                                       ";
            // 
            // lblVersion
            // 
            this.lblVersion.Name = "lblVersion";
            this.lblVersion.Size = new System.Drawing.Size(211, 21);
            this.lblVersion.Text = "v1.0 Beta                   |                  ";
            // 
            // lblThoiGian
            // 
            this.lblThoiGian.Name = "lblThoiGian";
            this.lblThoiGian.Size = new System.Drawing.Size(144, 21);
            this.lblThoiGian.Text = "dd/MM/yyyy HH:mm:ss";
            // 
            // lblTitle
            // 
            this.lblTitle.AutoSize = true;
            this.lblTitle.Font = new System.Drawing.Font("Segoe UI", 20.25F, System.Drawing.FontStyle.Bold);
            this.lblTitle.ForeColor = System.Drawing.Color.FromArgb(((int)(((byte)(255)))), ((int)(((byte)(128)))), ((int)(((byte)(0)))));
            this.lblTitle.Location = new System.Drawing.Point(85, 110);
            this.lblTitle.Name = "lblTitle";
            this.lblTitle.Size = new System.Drawing.Size(1084, 37);
            this.lblTitle.TabIndex = 8;
            this.lblTitle.Text = "CHÀO MỪNG BẠN ĐÃ ĐẾN VỚI - HỆ THỐNG QUẢN LÝ THƯ VIỆN THÔNG MINH UTT";
            // 
            // timer1
            // 
            this.timer1.Tick += new System.EventHandler(this.timer1_Tick);
            // 
            // mnuLop
            // 
            this.mnuLop.Name = "mnuLop";
            this.mnuLop.Size = new System.Drawing.Size(173, 26);
            this.mnuLop.Text = "Quản lý Lớp";
            this.mnuLop.Click += new System.EventHandler(this.mnuLop_Click);
            // 
            // mnuKhoa
            // 
            this.mnuKhoa.Name = "mnuKhoa";
            this.mnuKhoa.Size = new System.Drawing.Size(173, 26);
            this.mnuKhoa.Text = "Quản lý Khoa";
            this.mnuKhoa.Click += new System.EventHandler(this.mnuKhoa_Click);
            // 
            // toolStripStatusLabel1
            // 
            this.toolStripStatusLabel1.Name = "toolStripStatusLabel1";
            this.toolStripStatusLabel1.Size = new System.Drawing.Size(173, 21);
            this.toolStripStatusLabel1.Spring = true;
            this.toolStripStatusLabel1.Text = "                                       ";
            // 
            // toolStripStatusLabel2
            // 
            this.toolStripStatusLabel2.Name = "toolStripStatusLabel2";
            this.toolStripStatusLabel2.Size = new System.Drawing.Size(173, 21);
            this.toolStripStatusLabel2.Spring = true;
            this.toolStripStatusLabel2.Text = "                                       ";
            // 
            // logo
            // 
            this.logo.BackColor = System.Drawing.Color.White;
            this.logo.Image = global::UTT.Library.GUI.Properties.Resources.Logo_vuong_03_3x;
            this.logo.Location = new System.Drawing.Point(531, 227);
            this.logo.Name = "logo";
            this.logo.Size = new System.Drawing.Size(250, 250);
            this.logo.SizeMode = System.Windows.Forms.PictureBoxSizeMode.Zoom;
            this.logo.TabIndex = 4;
            this.logo.TabStop = false;
            // 
            // frmMain
            // 
            this.AutoScaleDimensions = new System.Drawing.SizeF(6F, 13F);
            this.AutoScaleMode = System.Windows.Forms.AutoScaleMode.Font;
            this.BackColor = System.Drawing.Color.White;
            this.ClientSize = new System.Drawing.Size(1264, 761);
            this.Controls.Add(this.lblTitle);
            this.Controls.Add(this.logo);
            this.Controls.Add(this.statusStrip1);
            this.Controls.Add(this.menuStrip1);
            this.IsMdiContainer = true;
            this.MainMenuStrip = this.menuStrip1;
            this.Name = "frmMain";
            this.StartPosition = System.Windows.Forms.FormStartPosition.CenterScreen;
            this.Text = "frmMain";
            this.WindowState = System.Windows.Forms.FormWindowState.Maximized;
            this.Load += new System.EventHandler(this.frmMain_Load);
            this.Resize += new System.EventHandler(this.frmMain_Resize);
            this.menuStrip1.ResumeLayout(false);
            this.menuStrip1.PerformLayout();
            this.statusStrip1.ResumeLayout(false);
            this.statusStrip1.PerformLayout();
            ((System.ComponentModel.ISupportInitialize)(this.logo)).EndInit();
            this.ResumeLayout(false);
            this.PerformLayout();

        }

        #endregion

        private System.Windows.Forms.MenuStrip menuStrip1;
        private System.Windows.Forms.ToolStripMenuItem mnuHeThong;
        private System.Windows.Forms.ToolStripMenuItem mnuDanhMuc;
        private System.Windows.Forms.ToolStripMenuItem mnuQLSach;
        private System.Windows.Forms.ToolStripMenuItem mnuQLNhanSu;
        private System.Windows.Forms.ToolStripMenuItem mnuNghiepVu;
        private System.Windows.Forms.ToolStripMenuItem mnuBaoCao;
        private System.Windows.Forms.ToolStripMenuItem mnuDoiMatKhau;
        private System.Windows.Forms.ToolStripMenuItem mnuQuanLyTaiKhoan;
        private System.Windows.Forms.ToolStripMenuItem mnuDangXuat;
        private System.Windows.Forms.ToolStripMenuItem mnuThoat;
        private System.Windows.Forms.ToolStripMenuItem mnuTacGia;
        private System.Windows.Forms.ToolStripMenuItem mnuNXB;
        private System.Windows.Forms.ToolStripMenuItem mnuTheLoai;
        private System.Windows.Forms.ToolStripMenuItem mnuNgonNgu;
        private System.Windows.Forms.ToolStripMenuItem mnuViTri;
        private System.Windows.Forms.ToolStripMenuItem mnuLopKhoa;
        private System.Windows.Forms.ToolStripMenuItem mnuSach;
        private System.Windows.Forms.ToolStripMenuItem mnuNhapSach;
        private System.Windows.Forms.ToolStripMenuItem mnuKho;
        private System.Windows.Forms.ToolStripMenuItem mnuThanhLy;
        private System.Windows.Forms.ToolStripMenuItem mnuNCC;
        private System.Windows.Forms.ToolStripMenuItem mnuTraCuu;
        private System.Windows.Forms.ToolStripMenuItem mnuSinhVien;
        private System.Windows.Forms.ToolStripMenuItem mnuNhanVien;
        private System.Windows.Forms.ToolStripMenuItem mnuTheTV;
        private System.Windows.Forms.ToolStripMenuItem mnuViPham;
        private System.Windows.Forms.ToolStripMenuItem mnuLichSu;
        private System.Windows.Forms.ToolStripMenuItem mnuMuonSach;
        private System.Windows.Forms.ToolStripMenuItem mnuTraSach;
        private System.Windows.Forms.ToolStripMenuItem mnuDatTruoc;
        private System.Windows.Forms.ToolStripMenuItem mnuDashboard;
        private System.Windows.Forms.StatusStrip statusStrip1;
        private System.Windows.Forms.ToolStripStatusLabel lblTaiKhoan;
        private System.Windows.Forms.PictureBox logo;
        private System.Windows.Forms.Label lblTitle;
        private System.Windows.Forms.ToolStripStatusLabel lblThoiGian;
        private System.Windows.Forms.Timer timer1;
        private System.Windows.Forms.ToolStripStatusLabel lblDeTai;
        private System.Windows.Forms.ToolStripStatusLabel lblHocPhan;
        private System.Windows.Forms.ToolStripStatusLabel lblSpace;
        private System.Windows.Forms.ToolStripStatusLabel lblVersion;
        private System.Windows.Forms.ToolStripMenuItem mnuLop;
        private System.Windows.Forms.ToolStripMenuItem mnuKhoa;
        private System.Windows.Forms.ToolStripStatusLabel toolStripStatusLabel2;
        private System.Windows.Forms.ToolStripStatusLabel toolStripStatusLabel1;
    }
}