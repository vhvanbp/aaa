package gui.ql_NhanVien;

import java.awt.Color;
import java.awt.EventQueue;
import java.awt.Font;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.WindowAdapter;
import java.awt.event.WindowEvent;
import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.Calendar;

import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JTextField;
import javax.swing.SwingConstants;

import bAL.NhanVienBAL;
import bAL.ThanhVienBAL;
import dAL.DataBase;
import dAL.DbUtils;
import entity.users.NhanVien;
import entity.users.ThanhVien;

public class QuanLy_DetailNV_Form extends JFrame implements ActionListener {

	//Region - TODO - UI fields
	/**
	 * 
	 */
	private static final long serialVersionUID = -7106346928488740026L;
	public JFrame frm;
	private JTextField txtTen;
	/**
	 *@param Sửa Thông Tin thành viên
	 */
	JButton btnSua = new JButton("Sua");
	private String get_Login_Text;
	JLabel lblTenlogin = new JLabel("TenLogin");
	private JTextField txtLuong;
	DateFormat dateformat = new SimpleDateFormat("dd/MM/yyyy");
	JButton btnQuayLai = new JButton("Quay lai");
	private JTextField txtEmail;
	private JTextField txtTaiKhoan;
	private JTextField txtPassword;
	JButton btnXoa = new JButton("Xoa");
	private JTextField txtDay;
	private JTextField txtMonth;
	private JTextField txtYear;
	private JTextField txtSDT;
	private JTextField txtLoaiTV;
	private JTextField txtMaTV;
	private JTextField txtMucLuong;

	private String get_maTV_Text;
	private ThanhVien tv;
	private NhanVien nv;
	private ThanhVienBAL tvBAL = new ThanhVienBAL();
	private NhanVienBAL nvBAL = new NhanVienBAL();
	//EndRegion
	
	//Region - TODO - run application
	/**
	 * Launch the application.
	 */
	public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					QuanLy_DetailNV_Form window = new QuanLy_DetailNV_Form();
					window.getFrame().setVisible(true);
				} catch (Exception e) {
					e.printStackTrace();
				}
			}
		});
	}
	//EndRegion

	//Region - TODO - UI components
	/**
	 * Create the application.
	 */
	public QuanLy_DetailNV_Form() {
		initialize();
	}
	
	public QuanLy_DetailNV_Form(String tenLogin, String maTV) {
		get_Login_Text =tenLogin;
		lblTenlogin.setText(get_Login_Text);
		get_maTV_Text = maTV;
		
		initialize();
	}
	//EndRegion

	//Region - TODO - init components
	/**
	 * Initialize the contents of the frame.
	 */
	private void initialize() {
		//TODO close DB
		addWindowListener(new WindowAdapter() {
			@Override
			public void windowClosing(WindowEvent e) {
				DbUtils.close(DataBase.getConnection());
				System.exit(0);
			}
		});
		
		setFrame(new JFrame());
		getFrame().setBounds(100, 100, 636, 477);
		getFrame().setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		getFrame().getContentPane().setLayout(null);
		
		JLabel lblQL_CHPT = new JLabel("Cửa Hàng Quản Lý Phụ Tùng Xe");
		lblQL_CHPT.setFont(new Font("Tahoma", Font.BOLD, 16));
		lblQL_CHPT.setBounds(90, 11, 310, 22);
		lblQL_CHPT.setHorizontalAlignment(SwingConstants.CENTER);
		getFrame().getContentPane().add(lblQL_CHPT);
		
		JLabel lblNgiQunL = new JLabel(" Người Quản Lý:");
		lblNgiQunL.setBounds(132, 44, 102, 14);
		getFrame().getContentPane().add(lblNgiQunL);

		//TODO - need ICON
//		Image img = new ImageIcon(this.getClass().getResource("/add_icon.jpg")).getImage().getScaledInstance(84, 64, java.awt.Image.SCALE_SMOOTH);
//		btnThem.setIcon(new ImageIcon(img));
		btnSua.setBackground(Color.CYAN);
		btnSua.setBounds(430, 283, 165, 43);
		getFrame().getContentPane().add(btnSua);
		
		JLabel lblNhpTn = new JLabel("Tên nhân viên:");
		lblNhpTn.setHorizontalAlignment(SwingConstants.RIGHT);
		lblNhpTn.setBounds(14, 136, 112, 14);
		frm.getContentPane().add(lblNhpTn);
		
		txtTen = new JTextField();
		txtTen.setEditable(false);
		txtTen.setBounds(136, 133, 161, 20);
		frm.getContentPane().add(txtTen);
		txtTen.setColumns(10);
		

		lblTenlogin.setBounds(244, 44, 119, 14);
		frm.getContentPane().add(lblTenlogin);
				
				JLabel lblNgay = new JLabel("Ngày vào làm:");
				lblNgay.setHorizontalAlignment(SwingConstants.RIGHT);
				lblNgay.setBounds(14, 202, 112, 14);
				frm.getContentPane().add(lblNgay);
				
				JLabel lblLuong = new JLabel("Lương:");
				lblLuong.setHorizontalAlignment(SwingConstants.RIGHT);
				lblLuong.setBounds(14, 236, 113, 14);
				frm.getContentPane().add(lblLuong);
				
				txtLuong = new JTextField();
				txtLuong.setEditable(false);
				txtLuong.setColumns(10);
				txtLuong.setBounds(136, 233, 161, 20);
				frm.getContentPane().add(txtLuong);

				btnQuayLai.setBackground(Color.CYAN);
				btnQuayLai.setBounds(14, 283, 165, 43);
				frm.getContentPane().add(btnQuayLai);
				
				JLabel lblNhpEmail = new JLabel("Email:");
				lblNhpEmail.setHorizontalAlignment(SwingConstants.RIGHT);
				lblNhpEmail.setBounds(14, 164, 112, 14);
				frm.getContentPane().add(lblNhpEmail);
				
				txtEmail = new JTextField();
				txtEmail.setEditable(false);
				txtEmail.setColumns(10);
				txtEmail.setBounds(136, 161, 161, 20);
				frm.getContentPane().add(txtEmail);
				
				txtTaiKhoan = new JTextField();
				txtTaiKhoan.setEditable(false);
				txtTaiKhoan.setColumns(10);
				txtTaiKhoan.setBounds(434, 133, 161, 20);
				frm.getContentPane().add(txtTaiKhoan);
				
				JLabel lblNhpTK = new JLabel("Tài khoản:");
				lblNhpTK.setHorizontalAlignment(SwingConstants.RIGHT);
				lblNhpTK.setBounds(322, 136, 102, 14);
				frm.getContentPane().add(lblNhpTK);
				
				JLabel lblNhpPass = new JLabel("Mật khẩu:");
				lblNhpPass.setHorizontalAlignment(SwingConstants.RIGHT);
				lblNhpPass.setBounds(322, 167, 102, 14);
				frm.getContentPane().add(lblNhpPass);
				
				txtPassword = new JTextField();
				txtPassword.setEditable(false);
				txtPassword.setColumns(10);
				txtPassword.setBounds(434, 164, 161, 20);
				frm.getContentPane().add(txtPassword);

				btnXoa.setBackground(Color.CYAN);
				btnXoa.setBounds(216, 283, 165, 43);
				frm.getContentPane().add(btnXoa);
				
				JLabel lbl_sday = new JLabel("/");
				lbl_sday.setFont(new Font("Tahoma", Font.PLAIN, 14));
				lbl_sday.setHorizontalAlignment(SwingConstants.RIGHT);
				lbl_sday.setBounds(165, 199, 12, 21);
				frm.getContentPane().add(lbl_sday);
				
				JLabel lbl_smonth = new JLabel("/");
				lbl_smonth.setFont(new Font("Tahoma", Font.PLAIN, 14));
				lbl_smonth.setHorizontalAlignment(SwingConstants.RIGHT);
				lbl_smonth.setBounds(216, 199, 12, 21);
				frm.getContentPane().add(lbl_smonth);
				
				txtDay = new JTextField();
				txtDay.setEditable(false);
				txtDay.setColumns(10);
				txtDay.setBounds(136, 199, 30, 20);
				frm.getContentPane().add(txtDay);
				
				txtMonth = new JTextField();
				txtMonth.setEditable(false);
				txtMonth.setColumns(10);
				txtMonth.setBounds(187, 199, 30, 20);
				frm.getContentPane().add(txtMonth);
				
				txtYear = new JTextField();
				txtYear.setEditable(false);
				txtYear.setColumns(10);
				txtYear.setBounds(238, 199, 59, 20);
				frm.getContentPane().add(txtYear);
				
				txtSDT = new JTextField();
				txtSDT.setEditable(false);
				txtSDT.setColumns(10);
				txtSDT.setBounds(434, 100, 161, 20);
				frm.getContentPane().add(txtSDT);
				
				JLabel lblSdt = new JLabel("Số điện thoại:");
				lblSdt.setHorizontalAlignment(SwingConstants.RIGHT);
				lblSdt.setBounds(312, 103, 112, 14);
				frm.getContentPane().add(lblSdt);
				
				txtLoaiTV = new JTextField();
				txtLoaiTV.setEditable(false);
				txtLoaiTV.setColumns(10);
				txtLoaiTV.setBounds(137, 100, 161, 20);
				frm.getContentPane().add(txtLoaiTV);
				
				JLabel lblLoaiTV = new JLabel("Loại thành viên:");
				lblLoaiTV.setHorizontalAlignment(SwingConstants.RIGHT);
				lblLoaiTV.setBounds(14, 100, 112, 23);
				frm.getContentPane().add(lblLoaiTV);

				JLabel lblMaTV = new JLabel("Mã thành viên:");
				lblMaTV.setHorizontalAlignment(SwingConstants.RIGHT);
				lblMaTV.setBounds(14, 72, 113, 14);
				frm.getContentPane().add(lblMaTV);
				
				txtMaTV = new JTextField();
				txtMaTV.setText("ma TV");
				txtMaTV.setEditable(false);
				txtMaTV.setColumns(10);
				txtMaTV.setBounds(137, 69, 160, 20);
				frm.getContentPane().add(txtMaTV);
				
				txtMucLuong = new JTextField();
				txtMucLuong.setText("0");
				txtMucLuong.setEditable(false);
				txtMucLuong.setColumns(10);
				txtMucLuong.setBounds(436, 194, 159, 20);
				frm.getContentPane().add(txtMucLuong);
				
				JLabel lblMucLuong = new JLabel("Mức lương:");
				lblMucLuong.setHorizontalAlignment(SwingConstants.RIGHT);
				lblMucLuong.setBounds(322, 193, 104, 23);
				frm.getContentPane().add(lblMucLuong);	
								
				//TODO - Register before perform action
				btnSua.addActionListener(this);
				btnQuayLai.addActionListener(this);
				btnXoa.addActionListener(this);
				
				// methods from support region
				layTV_tuForm();
				napTV_VaoTextfields();
		
	}
	//EndRegion
	
	//Region - TODO - support methods
	// lay du lieu NV tu form truoc
	private void layTV_tuForm() {
		 tv = nvBAL.get_NVByID(get_maTV_Text);
		 nv = (NhanVien)tv;
	}

	// nap du lieu tu bang vao Textfield
	private void napTV_VaoTextfields() {
		txtMaTV.setText(get_maTV_Text);
		txtTen.setText(nv.getHoTen());
		txtLoaiTV.setText(nv.getLoaiThanhVien()); 
		txtEmail.setText(nv.getEmail());
		txtSDT.setText(nv.getSdt());
		txtTaiKhoan.setText(nv.getTaiKhoan());
		txtPassword.setText(nv.getMatKhau());
		Calendar cal = Calendar.getInstance(); // get day, month, year
			cal.setTime(nv.getNgayVaoLam());
			int year = cal.get(Calendar.YEAR);
			int month = cal.get(Calendar.MONTH) + 1; // do thang (cua Calendar) bat dau tu 0
			int day = cal.get(Calendar.DAY_OF_MONTH);
		txtDay.setText(day + "");
		txtMonth.setText(month  + "");
		txtYear.setText(year + "");
		txtMucLuong.setText(nv.getMucLuong() + "");
		txtLuong.setText(nv.getTienLuong() + "");

	}
	//EndRegion
		
	//Region - TODO - getter setter for frame
	public JFrame getFrame() {
		return frm;
	}

	public void setFrame(JFrame frame) {
		this.frm = frame;
		frm.setTitle("Chi tiết nhân viên");
		frame.setResizable(false);
	}
	//EndRegion

	//Region - TODO - event handler
	@Override
	public void actionPerformed(ActionEvent arg0) {
		Object o = arg0.getSource();
		
		if(o.equals(btnSua)){ //Sua thong tin nhan vien
			frm.dispose();
			QuanLy_UpdateNV_Form ql_UpdatNV = new QuanLy_UpdateNV_Form(get_Login_Text,get_maTV_Text);
			ql_UpdatNV.frame.setVisible(true);
		}else if(o.equals(btnQuayLai)){ // Quay lai
			frm.dispose();
			QuanLy_Form_NV qlNV = new QuanLy_Form_NV(get_Login_Text);
			qlNV.frame.setVisible(true);
		}else if(o.equals(btnXoa)){ // xoa nhan vien
			int dialogButton = JOptionPane.YES_NO_OPTION;
			int dialogResult = JOptionPane.showConfirmDialog(this, "Bạn có muốn xóa nhân viên này?", 
																	"Title on Box", 
																	dialogButton);
			if(dialogResult == 0) {
				String matv = txtMaTV.getText();
				if(tvBAL.delete(matv)){
					JOptionPane.showMessageDialog(null, "xóa thành công!");
					
					frm.dispose();
					QuanLy_Form_NV qlNV = new QuanLy_Form_NV(get_Login_Text);
					qlNV.frame.setVisible(true);
				}else
					JOptionPane.showMessageDialog(null, "Nhân viên này không xóa được");
			}
		}
		
	}
	//EndRegion
	
}
