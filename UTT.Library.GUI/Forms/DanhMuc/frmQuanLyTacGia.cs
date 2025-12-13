using System;
using System.Collections.Generic;
using System.ComponentModel;
using System.Data;
using System.Drawing;
using System.Linq;
using System.Text;
using System.Threading.Tasks;
using System.Windows.Forms;
using UTT.Library.BLL.Services;
using UTT.Library.DTO;
using UTT.Library.GUI.Utilities;

namespace UTT.Library.GUI.Forms.DanhMuc
{
    public partial class frmQuanLyTacGia : Form
    {
        private BLL_TacGia _bll = new BLL_TacGia();

        public frmQuanLyTacGia()
        {
            InitializeComponent();
            this.AutoScaleMode = AutoScaleMode.None;

        }

        private void frmQuanLyTacGia_Load(object sender, EventArgs e)
        {
            this.Dock = DockStyle.Fill;

            LoadData();
            DecorateDataGridView();
            DecorateSearchBox();
            DecorateButtons();
        }

        // =======================================================
        // =============== TRANG TRÍ DATAGRIDVIEW =================
        // =======================================================

        private void DecorateDataGridView()
        {
            // GENERAL
            dgvDanhSach.BorderStyle = BorderStyle.None;
            dgvDanhSach.BackgroundColor = Color.White;
            dgvDanhSach.AutoSizeColumnsMode = DataGridViewAutoSizeColumnsMode.Fill;
            dgvDanhSach.RowTemplate.Height = 32;
            dgvDanhSach.ReadOnly = true;
            dgvDanhSach.AllowUserToAddRows = false;
            dgvDanhSach.AllowUserToDeleteRows = false;
            dgvDanhSach.MultiSelect = false;
            dgvDanhSach.SelectionMode = DataGridViewSelectionMode.FullRowSelect;
            dgvDanhSach.CellBorderStyle = DataGridViewCellBorderStyle.SingleHorizontal;

            // HEADER
            dgvDanhSach.EnableHeadersVisualStyles = false;
            dgvDanhSach.ColumnHeadersHeight = 40;
            dgvDanhSach.ColumnHeadersBorderStyle = DataGridViewHeaderBorderStyle.None;
            dgvDanhSach.ColumnHeadersDefaultCellStyle.BackColor = Color.FromArgb(38, 34, 98);
            dgvDanhSach.ColumnHeadersDefaultCellStyle.ForeColor = Color.White;
            dgvDanhSach.ColumnHeadersDefaultCellStyle.Font =
                new Font("Segoe UI", 11, FontStyle.Bold);

            // ROWS
            dgvDanhSach.DefaultCellStyle.BackColor = Color.White;
            dgvDanhSach.DefaultCellStyle.ForeColor = Color.FromArgb(38, 34, 98);
            dgvDanhSach.DefaultCellStyle.Font = new Font("Segoe UI", 10);

            dgvDanhSach.AlternatingRowsDefaultCellStyle.BackColor =
                Color.FromArgb(185, 217, 137); // xanh lá nhạt brand

            // SELECTION
            dgvDanhSach.DefaultCellStyle.SelectionBackColor =
                Color.FromArgb(71, 124, 191); // xanh dương brand
            dgvDanhSach.DefaultCellStyle.SelectionForeColor = Color.White;

            dgvDanhSach.GridColor = Color.FromArgb(224, 224, 224);
        }


        // =======================================================
        // =============== TRANG TRÍ Ô TÌM KIẾM ===================
        // =======================================================

        private void DecorateSearchBox()
        {
            txtTimKiem.BorderStyle = BorderStyle.FixedSingle;
            txtTimKiem.Font = new Font("Segoe UI", 10);
            txtTimKiem.ForeColor = Color.FromArgb(38, 34, 98);

            // đặt placeholder nếu cần
            txtTimKiem.Text = "";
        }


        // =======================================================
        // ================= TRANG TRÍ BUTTON =====================
        // =======================================================

        private void DecorateButtons()
        {
            Color primary = Color.FromArgb(247, 148, 33);    // cam brand
            Color primaryDark = Color.FromArgb(200, 120, 20);
            Color blueBrand = Color.FromArgb(71, 124, 191);

            StyleButton(btnThem, primary, primaryDark);
            StyleButton(btnSua, blueBrand, Color.FromArgb(50, 90, 150));
            StyleButton(btnXoa, Color.FromArgb(200, 50, 50), Color.FromArgb(150, 20, 20));
            StyleButton(btnLamMoi, Color.FromArgb(93, 98, 42), Color.FromArgb(70, 75, 30));
            StyleButton(btnTimKiem, Color.FromArgb(38, 34, 98), Color.FromArgb(25, 22, 70));
        }

        private void StyleButton(Button btn, Color back, Color backHover)
        {
            btn.FlatStyle = FlatStyle.Flat;
            btn.FlatAppearance.BorderSize = 0;
            btn.BackColor = back;
            btn.ForeColor = Color.White;
            btn.Font = new Font("Segoe UI", 10, FontStyle.Bold);

            btn.MouseEnter += (s, e) => btn.BackColor = backHover;
            btn.MouseLeave += (s, e) => btn.BackColor = back;
        }


        // =======================================================
        // ================== LOAD & CRUD =========================
        // =======================================================

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
            txtGhiChu.Clear();
            txtMa.Focus();
        }

        private void btnThem_Click(object sender, EventArgs e)
        {
            if (!ValidationHelper.IsRequired(txtMa, "Mã tác giả")) return;
            if (!ValidationHelper.IsRequired(txtTen, "Tên tác giả")) return;

            DTO_TacGia tg = new DTO_TacGia(txtMa.Text, txtTen.Text, txtGhiChu.Text);

            string ketQua = _bll.Them(tg);
            if (string.IsNullOrEmpty(ketQua))
            {
                MessageBox.Show("Thêm mới thành công!", "Thông báo", MessageBoxButtons.OK, MessageBoxIcon.Information);
                LoadData();
            }
            else MessageBox.Show(ketQua, "Lỗi", MessageBoxButtons.OK, MessageBoxIcon.Error);
        }

        private void btnSua_Click(object sender, EventArgs e)
        {
            if (txtMa.Enabled)
            {
                MessageBox.Show("Vui lòng chọn dòng cần sửa!", "Thông báo");
                return;
            }

            if (!ValidationHelper.IsRequired(txtTen, "Tên tác giả")) return;

            DTO_TacGia tg = new DTO_TacGia(txtMa.Text, txtTen.Text, txtGhiChu.Text);
            string ketQua = _bll.Sua(tg);

            if (string.IsNullOrEmpty(ketQua))
            {
                MessageBox.Show("Cập nhật thành công!");
                LoadData();
            }
            else MessageBox.Show(ketQua, "Lỗi", MessageBoxButtons.OK, MessageBoxIcon.Error);
        }

        private void btnXoa_Click(object sender, EventArgs e)
        {
            if (txtMa.Enabled || string.IsNullOrEmpty(txtMa.Text))
            {
                MessageBox.Show("Vui lòng chọn dòng cần xóa!", "Thông báo");
                return;
            }

            if (ValidationHelper.ConfirmDelete())
            {
                string ketQua = _bll.Xoa(txtMa.Text);
                if (string.IsNullOrEmpty(ketQua))
                {
                    MessageBox.Show("Xóa thành công!");
                    LoadData();
                }
                else MessageBox.Show(ketQua, "Lỗi", MessageBoxButtons.OK, MessageBoxIcon.Error);
            }
        }

        private void btnLamMoi_Click(object sender, EventArgs e)
        {
            LoadData();
        }

        private void txtTimKiem_TextChanged(object sender, EventArgs e)
        {

        }

        private void btnTimKiem_Click(object sender, EventArgs e)
        {
            string key = txtTimKiem.Text.Trim();
            dgvDanhSach.DataSource = _bll.TimKiem(key);
        }

        private void dgvDanhSach_CellContentClick(object sender, DataGridViewCellEventArgs e)
        {
            if (e.RowIndex >= 0)
            {
                DataGridViewRow row = dgvDanhSach.Rows[e.RowIndex];
                txtMa.Text = row.Cells["MaTacGia"].Value.ToString();
                txtTen.Text = row.Cells["TenTacGia"].Value.ToString();
                txtGhiChu.Text = row.Cells["GhiChu"].Value.ToString();

                txtMa.Enabled = false;
            }
        }

        private void btnExport_Click(object sender, EventArgs e)
        {
            try
            {
                ExcelHelper.ExportToExcel(dgvDanhSach, "DanhSachTacGia");
                MessageBox.Show("Xuất Excel thành công!", "Thông báo", MessageBoxButtons.OK, MessageBoxIcon.Information);
            }
            catch (Exception ex)
            {
                MessageBox.Show("Lỗi khi xuất Excel: " + ex.Message, "Lỗi", MessageBoxButtons.OK, MessageBoxIcon.Error);
            }
        }
    }
}
