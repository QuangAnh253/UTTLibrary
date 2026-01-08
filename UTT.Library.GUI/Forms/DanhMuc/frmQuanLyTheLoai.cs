using System;
using System.Drawing;
using System.Windows.Forms;
using UTT.Library.BLL.Services;
using UTT.Library.DTO;
using UTT.Library.GUI.Utilities;
using UTT.Library.GUI.Forms.DanhMuc;

namespace UTT.Library.GUI.Forms.DanhMuc
{
    public partial class frmQuanLyTheLoai : Form
    {
        private BLL_TheLoai _bll = new BLL_TheLoai();

        public frmQuanLyTheLoai()
        {
            InitializeComponent();
            this.AutoScaleMode = AutoScaleMode.None;
        }

        private void frmQuanLyTheLoai_Load(object sender, EventArgs e)
        {
            this.Dock = DockStyle.Fill;
            LoadData();
            DecorateDataGridView();
            DecorateSearchBox();
            DecorateButtons();
        }

        // ================== TRANG TRÍ ==================
        private void DecorateDataGridView()
        {
            dgvDanhSach.BorderStyle = BorderStyle.None;
            dgvDanhSach.BackgroundColor = Color.White;
            dgvDanhSach.AutoSizeColumnsMode = DataGridViewAutoSizeColumnsMode.Fill;
            dgvDanhSach.RowTemplate.Height = 32;
            dgvDanhSach.ReadOnly = true;
            dgvDanhSach.AllowUserToAddRows = false;
            dgvDanhSach.AllowUserToDeleteRows = false;
            dgvDanhSach.MultiSelect = false;
            dgvDanhSach.SelectionMode = DataGridViewSelectionMode.FullRowSelect;

            dgvDanhSach.EnableHeadersVisualStyles = false;
            dgvDanhSach.ColumnHeadersHeight = 40;
            dgvDanhSach.ColumnHeadersDefaultCellStyle.BackColor = Color.FromArgb(38, 34, 98);
            dgvDanhSach.ColumnHeadersDefaultCellStyle.ForeColor = Color.White;
            dgvDanhSach.ColumnHeadersDefaultCellStyle.Font =
                new Font("Segoe UI", 11, FontStyle.Bold);

            dgvDanhSach.DefaultCellStyle.Font = new Font("Segoe UI", 10);
            dgvDanhSach.AlternatingRowsDefaultCellStyle.BackColor =
                Color.FromArgb(185, 217, 137);
        }

        private void DecorateSearchBox()
        {
            txtTimKiem.BorderStyle = BorderStyle.FixedSingle;
            txtTimKiem.Font = new Font("Segoe UI", 10);
        }

        private void DecorateButtons()
        {
            StyleButton(btnThem, Color.FromArgb(247, 148, 33), Color.FromArgb(200, 120, 20));
            StyleButton(btnSua, Color.FromArgb(71, 124, 191), Color.FromArgb(50, 90, 150));
            StyleButton(btnXoa, Color.FromArgb(200, 50, 50), Color.FromArgb(150, 20, 20));
            StyleButton(btnLamMoi, Color.FromArgb(93, 98, 42), Color.FromArgb(70, 75, 30));
            StyleButton(btnTimKiem, Color.FromArgb(38, 34, 98), Color.FromArgb(25, 22, 70));
        }

        private void StyleButton(Button btn, Color back, Color hover)
        {
            btn.FlatStyle = FlatStyle.Flat;
            btn.FlatAppearance.BorderSize = 0;
            btn.BackColor = back;
            btn.ForeColor = Color.White;
            btn.Font = new Font("Segoe UI", 10, FontStyle.Bold);

            btn.MouseEnter += (s, e) => btn.BackColor = hover;
            btn.MouseLeave += (s, e) => btn.BackColor = back;
        }

        // ================== XỬ LÝ ==================
        private void LoadData()
        {
            dgvDanhSach.DataSource = _bll.LayDanhSach();
            ResetInput();
        }

        private void ResetInput()
        {
            txtMa.Enabled = true;
            txtMa.Clear();
            txtTen.Clear();
            txtMoTa.Clear();
            txtMa.Focus();
        }

        private void btnThem_Click(object sender, EventArgs e)
        {
            if (!ValidationHelper.IsRequired(txtMa, "Mã thể loại")) return;
            if (!ValidationHelper.IsRequired(txtTen, "Tên thể loại")) return;

            DTO_TheLoai tl =
                new DTO_TheLoai(txtMa.Text, txtTen.Text, txtMoTa.Text);

            string kq = _bll.Them(tl);
            if (string.IsNullOrEmpty(kq))
            {
                MessageBox.Show("Thêm thể loại thành công!");
                LoadData();
            }
            else MessageBox.Show(kq, "Lỗi");
        }

        private void btnSua_Click(object sender, EventArgs e)
        {
            if (txtMa.Enabled)
            {
                MessageBox.Show("Vui lòng chọn dòng cần sửa!");
                return;
            }

            if (!ValidationHelper.IsRequired(txtTen, "Tên thể loại")) return;

            DTO_TheLoai tl =
                new DTO_TheLoai(txtMa.Text, txtTen.Text, txtMoTa.Text);

            string kq = _bll.Sua(tl);
            if (string.IsNullOrEmpty(kq))
            {
                MessageBox.Show("Cập nhật thành công!");
                LoadData();
            }
            else MessageBox.Show(kq, "Lỗi");
        }

        private void btnXoa_Click(object sender, EventArgs e)
        {
            if (txtMa.Enabled || string.IsNullOrEmpty(txtMa.Text))
            {
                MessageBox.Show("Vui lòng chọn dòng cần xóa!");
                return;
            }

            if (ValidationHelper.ConfirmDelete())
            {
                string kq = _bll.Xoa(txtMa.Text);
                if (string.IsNullOrEmpty(kq))
                {
                    MessageBox.Show("Xóa thành công!");
                    LoadData();
                }
                else MessageBox.Show(kq, "Lỗi");
            }
        }

        private void btnLamMoi_Click(object sender, EventArgs e)
        {
            LoadData();
        }

        private void btnTimKiem_Click(object sender, EventArgs e)
        {
            dgvDanhSach.DataSource =
                _bll.TimKiem(txtTimKiem.Text.Trim());
        }

        private void dgvDanhSach_CellClick(object sender, DataGridViewCellEventArgs e)
        {
            if (e.RowIndex >= 0)
            {
                DataGridViewRow r = dgvDanhSach.Rows[e.RowIndex];
                txtMa.Text = r.Cells["MaTheLoai"].Value.ToString();
                txtTen.Text = r.Cells["TenTheLoai"].Value.ToString();
                txtMoTa.Text = r.Cells["MoTa"].Value.ToString();
                txtMa.Enabled = false;
            }
        }

        private void btnExport_Click(object sender, EventArgs e)
        {
            ExcelHelper.ExportToExcel(dgvDanhSach, "DanhSachTheLoai");
            MessageBox.Show("Xuất Excel thành công!");
        }

        private void dgvDanhSach_CellContentClick(object sender, DataGridViewCellEventArgs e)
        {
            if (e.RowIndex >= 0)
            {
                DataGridViewRow row = dgvDanhSach.Rows[e.RowIndex];
                txtMa.Text = row.Cells["MaTheLoai"].Value.ToString();
                txtTen.Text = row.Cells["TenTheLoai"].Value.ToString();
                txtMoTa.Text = row.Cells["MoTa"].Value.ToString();

                txtMa.Enabled = false;

            }
        }

        
    }
}