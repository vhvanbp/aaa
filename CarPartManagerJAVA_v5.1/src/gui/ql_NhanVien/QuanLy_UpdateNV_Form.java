package gui.ql_NhanVien;

import java.awt.Color;
import java.awt.EventQueue;
import java.awt.Font;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.WindowAdapter;
import java.awt.event.WindowEvent;
import java.text.DateFormat;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Date;

import javax.swing.DefaultComboBoxModel;
import javax.swing.JButton;
import javax.swing.JComboBox;
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
import gui.ql_NCC.QuanLy_Form_NCC;
import helpers.UI_Helpers;

public class QuanLy_UpdateNV_Form extends JFrame implements ActionListener {

	//Region - TODO - UI fields
	private static final long serialVersionUID = -7106346928488740026L;
	public JFrame frame;
	private JTextField txtMaTV;
	/**
	 *@param Sửa Thông Tin thành viên
	 */
	JButton btnSua = new JButton("Sua");
	/**
	 *@param �?ăng Xuất
	 */
	JButton btnDangXuat = new JButton("Dang Xuat");

	private String get_Login_Text;
	JLabel lblTenlogin = new JLabel("TenLogin");
	private JTextField txtLoaiTV;
	
	private String get_maNV_Text;
	JButton btnQuayLai = new JButton("Quay lai");
	private JTextField txtTenTV;
	private JTextField txtTaiKhoan;
	private JTextField txtEmail;
	private JTextField txtPassword;
	private JTextField txtDay;
	private JTextField txtMonth;
	private JTextField txtYear;
	private JTextField txtLuong;
	private JTextField txtSDT;
	JComboBox<String> cbo_MucLuong = new JComboBox<String>();
	
	DateFormat dateformat = new SimpleDateFormat("dd/MM/yyyy");
	
	private ThanhVien tv;
	private NhanVien nv;
	private ThanhVienBAL tvBal = new ThanhVienBAL();
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
					QuanLy_UpdateNV_Form window = new QuanLy_UpdateNV_Form();
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
	public QuanLy_UpdateNV_Form() {
		initialize();
	}

	public QuanLy_UpdateNV_Form(String tenLogin, String maNV) {
		get_Login_Text = tenLogin;
		lblTenlogin.setText(get_Login_Text);
		get_maNV_Text = maNV;
		
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
		getFrame().setBounds(100, 100, 675, 396);
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

		btnSua.setBackground(Color.CYAN);
		btnSua.setBounds(241, 278, 160, 43);
		getFrame().getContentPane().add(btnSua);
		
		btnDangXuat.setBackground(Color.CYAN);
		btnDangXuat.setBounds(458, 278, 160, 43);
		frame.getContentPane().add(btnDangXuat);
		
		txtMaTV = new JTextField();
		txtMaTV.setEditable(false);
		txtMaTV.setBounds(147, 69, 160, 20);
		frame.getContentPane().add(txtMaTV);
		txtMaTV.setColumns(10);
		
		JLabel lblMaTV = new JLabel("Mã thành viên:");
		lblMaTV.setHorizontalAlignment(SwingConstants.RIGHT);
		lblMaTV.setBounds(24, 72, 113, 14);
		frame.getContentPane().add(lblMaTV);
		

		lblTenlogin.setBounds(244, 44, 119, 14);
		frame.getContentPane().add(lblTenlogin);
				
				JLabel lblLoaiTV = new JLabel("Loại thành viên:");
				lblLoaiTV.setHorizontalAlignment(SwingConstants.RIGHT);
				lblLoaiTV.setBounds(24, 100, 112, 23);
				frame.getContentPane().add(lblLoaiTV);
				
				txtLoaiTV = new JTextField();
				txtLoaiTV.setEditable(false);
				txtLoaiTV.setBounds(147, 100, 161, 20);
				frame.getContentPane().add(txtLoaiTV);
				txtLoaiTV.setColumns(10);
				
				btnQuayLai.setBackground(Color.CYAN);
				btnQuayLai.setBounds(28, 278, 160, 43);
				frame.getContentPane().add(btnQuayLai);
				
				JLabel lblNhpTn = new JLabel("Nhập tên nhân viên:");
				lblNhpTn.setHorizontalAlignment(SwingConstants.RIGHT);
				lblNhpTn.setBounds(24, 134, 112, 14);
				frame.getContentPane().add(lblNhpTn);
				
				txtTenTV = new JTextField();
				txtTenTV.setColumns(10);
				txtTenTV.setBounds(146, 131, 161, 20);
				frame.getContentPane().add(txtTenTV);
				
				JLabel lblNhpTK = new JLabel("Nhập tài khoản:");
				lblNhpTK.setHorizontalAlignment(SwingConstants.RIGHT);
				lblNhpTK.setBounds(353, 166, 109, 14);
				frame.getContentPane().add(lblNhpTK);
				
				txtTaiKhoan = new JTextField();
				txtTaiKhoan.setColumns(10);
				txtTaiKhoan.setBounds(471, 165, 161, 20);
				frame.getContentPane().add(txtTaiKhoan);
				
				JLabel lblNhpEmail = new JLabel("Nhập email:");
				lblNhpEmail.setHorizontalAlignment(SwingConstants.RIGHT);
				lblNhpEmail.setBounds(24, 165, 112, 14);
				frame.getContentPane().add(lblNhpEmail);
				
				txtEmail = new JTextField();
				txtEmail.setColumns(10);
				txtEmail.setBounds(146, 162, 161, 20);
				frame.getContentPane().add(txtEmail);
				
				JLabel lblNhpPass = new JLabel("Nhập mật khẩu:");
				lblNhpPass.setHorizontalAlignment(SwingConstants.RIGHT);
				lblNhpPass.setBounds(353, 197, 109, 14);
				frame.getContentPane().add(lblNhpPass);
				
				txtPassword = new JTextField();
				txtPassword.setColumns(10);
				txtPassword.setBounds(471, 196, 161, 20);
				frame.getContentPane().add(txtPassword);
				
				JLabel lblNgay = new JLabel("Nhập ngày vào làm:");
				lblNgay.setHorizontalAlignment(SwingConstants.RIGHT);
				lblNgay.setBounds(24, 202, 112, 14);
				frame.getContentPane().add(lblNgay);
				
				txtDay = new JTextField();
				txtDay.setColumns(10);
				txtDay.setBounds(146, 199, 30, 20);
				frame.getContentPane().add(txtDay);
				
				JLabel lbl_sday = new JLabel("/");
				lbl_sday.setHorizontalAlignment(SwingConstants.RIGHT);
				lbl_sday.setFont(new Font("Tahoma", Font.PLAIN, 14));
				lbl_sday.setBounds(175, 199, 12, 21);
				frame.getContentPane().add(lbl_sday);
				
				txtMonth = new JTextField();
				txtMonth.setColumns(10);
				txtMonth.setBounds(197, 199, 30, 20);
				frame.getContentPane().add(txtMonth);
				
				JLabel lbl_smonth = new JLabel("/");
				lbl_smonth.setHorizontalAlignment(SwingConstants.RIGHT);
				lbl_smonth.setFont(new Font("Tahoma", Font.PLAIN, 14));
				lbl_smonth.setBounds(226, 199, 12, 21);
				frame.getContentPane().add(lbl_smonth);
				
				txtYear = new JTextField();
				txtYear.setColumns(10);
				txtYear.setBounds(248, 199, 59, 20);
				frame.getContentPane().add(txtYear);
				
				JLabel lblChnMucL = new JLabel("Chọn mức lương:");
				lblChnMucL.setHorizontalAlignment(SwingConstants.RIGHT);
				lblChnMucL.setBounds(353, 225, 113, 23);
				frame.getContentPane().add(lblChnMucL);

				cbo_MucLuong.setModel(new DefaultComboBoxModel<String>(new String[] {"1", "2", "3"}));
				cbo_MucLuong.setBounds(472, 227, 160, 22);
				frame.getContentPane().add(cbo_MucLuong);
				
				JLabel lblLuong = new JLabel("Nhập lương:");
				lblLuong.setHorizontalAlignment(SwingConstants.RIGHT);
				lblLuong.setBounds(24, 236, 113, 14);
				frame.getContentPane().add(lblLuong);
				
				txtLuong = new JTextField();
				txtLuong.setColumns(10);
				txtLuong.setBounds(146, 233, 161, 20);
				frame.getContentPane().add(txtLuong);
				
				txtSDT = new JTextField();
				txtSDT.setColumns(10);
				txtSDT.setBounds(472, 130, 161, 20);
				frame.getContentPane().add(txtSDT);
				
				JLabel lblSdt = new JLabel("Nhập số điện thoại:");
				lblSdt.setHorizontalAlignment(SwingConstants.RIGHT);
				lblSdt.setBounds(343, 132, 119, 14);
				frame.getContentPane().add(lblSdt);
				
				//TODO - Register before perform action
				btnSua.addActionListener(this);
				btnDangXuat.addActionListener(this);
				btnQuayLai.addActionListener(this);
				
				// methods from support region
				layNV_tuForm();
				napNV_VaoTextfields();
		
	}
	//EndRegion
	
	//Region - TODO - support methods
	// lay du lieu NV tu form truoc
	private void layNV_tuForm() {
		 tv = nvBAL.get_NVByID(get_maNV_Text);
		 nv = (NhanVien)tv;
	}

	// nap du lieu tu bang vao Textfield
	private void napNV_VaoTextfields() {
		txtMaTV.setText(get_maNV_Text);
		txtTenTV.setText(nv.getHoTen());
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
		cbo_MucLuong.setSelectedItem(nv.getMucLuong() + "");
		txtLuong.setText(nv.getTienLuong() + "");

	}
	
	// xet loai thanh vien -> dung cho ham chuyenUI_theoLoaiTV
	@SuppressWarnings("unused")
	private void xetLoaiTV(String txtLoaiTV) {
		switch (txtLoaiTV) {
			case "Quan li vien":
				// QuanLy_Form_QLV qlNV = new QuanLy_Form_NV(get_Login_Text);
				// qlNV.frame.setVisible(true);
				break;
	
			case "Nhan vien":
				QuanLy_Form_NV qlNV = new QuanLy_Form_NV(get_Login_Text);
				qlNV.frame.setVisible(true);
				break;
	
			case "Khach hang":
				// QuanLy_Form_K qlNV = new QuanLy_Form_NV(get_Login_Text);
				// qlNV.frame.setVisible(true);
				break;
	
			case "Nha cung cap":
				QuanLy_Form_NCC qlNCC = new QuanLy_Form_NCC(get_Login_Text);
				qlNCC.frame.setVisible(true);
				break;
			default:
				break;
		}
	}
	
	// chuyen den giao dien theo loai thanh vien
	@SuppressWarnings("unused")
	private void chuyenUI_theoLoaiTV(String txtLoaiTV, String loaiTV, int dialogButton, int dialogResult){
		frame.dispose();
		QuanLy_Form_NV qlNV = new QuanLy_Form_NV(get_Login_Text);
		if (txtLoaiTV.equals(loaiTV)) {	//"Nhan vien"
			qlNV.frame.setVisible(true);
		} else {
			dialogResult = JOptionPane.showConfirmDialog(this, 
														"Loại thành viên đã thay đổi" 
														+ ", bạn có muốn đến giao diện quản lí: " 
														+ txtLoaiTV + "?",
														"Title on Box", dialogButton);

			if (dialogResult == 0) {
//				xetLoaiTV(txtLoaiTV);
			}else {
				qlNV.frame.setVisible(true);
			}
		}
	}
	//EndRegion
		
	//Region - TODO - getter setter for frame
	public JFrame getFrame() {
		return frame;
	}

	public void setFrame(JFrame frame) {
		this.frame = frame;
		frame.setTitle("Sửa nhân viên");
		frame.setResizable(false);
	}
	//EndRegion

	//Region - TODO - event handler
	@Override
	public void actionPerformed(ActionEvent arg0) {
		Object o = arg0.getSource();
		
		if(o.equals(btnSua)){ //Sua thong tin TV

			//Region - get text from ui
			String loaiTV = "Nhan vien"; //-> old code
//			String loaiTV = cboLoaiTV.getSelectedItem().toString(); // Jun 29 code
			String tenTV =	txtTenTV.getText();
			String email =	txtEmail.getText();
			String sdt =	txtSDT.getText();
			String taiKhoan =	txtTaiKhoan.getText();
			String password =	txtPassword.getText();
			String cboMuc = (String) cbo_MucLuong.getSelectedItem();
			String day = txtDay.getText();
			String month = txtMonth.getText();
			String year = txtYear.getText();
			String tien= txtLuong.getText();

			//EndRegion

			//Region - function create with condition
			if( tenTV.equals("") 	|| email.equals("") 	|| sdt.equals("")
			|| day.equals("")		|| month.equals("")		|| year.equals("")
			|| tien.equals("")  || taiKhoan.equals("") 	|| password.equals("")	
			|| cboMuc.equals("") // need cboMuc? 
			)
				JOptionPane.showMessageDialog(null, "Bạn chưa điền đủ thông tin");
			else if( tvBal.Check_name(taiKhoan)==1 && !taiKhoan.equals(nv.getTaiKhoan()))
				JOptionPane.showMessageDialog(null, "Tài khoản " + taiKhoan + " đã tồn tại, hãy nhập tài khoản khác");
			else{
				//Region - make conversion when data is available
				String ngay_Vao = day + "/" + month + "/" + year;
				Date ngayVaoLam=null;
				try {
						ngayVaoLam = dateformat.parse(ngay_Vao);
				} catch (ParseException e) {
					e.printStackTrace();
					JOptionPane.showMessageDialog(null, "Bạn chưa điền ... - error" + e);
				}
				
				int mucLuong = Integer.parseInt(cboMuc);
				double tienLuong = Double.parseDouble(tien);
				//EndRegion
				
				int dialogButton = JOptionPane.YES_NO_OPTION;
				int dialogResult = JOptionPane.showConfirmDialog(this, "Bạn có muốn sửa thông tin nhân viên này?"
																	, "Title on Box"
																	, dialogButton);
				
				if(dialogResult == 0) {
					NhanVien nvNew = new NhanVien(nv.getMaTV(), tenTV, loaiTV, email, 
													sdt, taiKhoan, password, ngayVaoLam, 
													mucLuong, tienLuong);
					
					 if(nvBAL.update(nvNew)){
						 JOptionPane.showMessageDialog(null, "Bạn vừa sửa thông tin nhân viên!");
						 
						//Region - use later
//						 chuyenUI_theoLoaiTV(loaiTV, 
//											 "Nhan vien", 
//											 dialogButton, 
//											 dialogResult); // chon loai mac dinh 
//									 						// theo ban dau
						//EndRegion
						 
						frame.dispose();
						QuanLy_Form_NV qlNV = new QuanLy_Form_NV(get_Login_Text);
						qlNV.frame.setVisible(true);
					 }  
				}else { 
					dialogResult = JOptionPane.showConfirmDialog(this, "Bạn có muốn giữ nội dung đang sửa?"
							, "Title on Box"
							, dialogButton);
					if(dialogResult == 1) {
						napNV_VaoTextfields();
					}
				} 			
			}
			//EndRegion

		}else if(o.equals(btnDangXuat)){ // Dang Xuat
			UI_Helpers.DangXuat(frame);
		}else if(o.equals(btnQuayLai)){ // Quay Lai
			frame.dispose();
			QuanLy_Form_NV frmNV = new QuanLy_Form_NV(get_Login_Text);
			frmNV.frame.setVisible(true);
		}
		//Region - for test func
//		else if(o.equals(btnTest)){ //test
//			Calendar cal = Calendar.getInstance();
//			cal.setTime(nv.getNgayVaoLam());
//			int year = cal.get(Calendar.YEAR);
//			int month = cal.get(Calendar.MONTH);
//			int day = cal.get(Calendar.DAY_OF_MONTH);
//			 JOptionPane.showMessageDialog(null, "get id  : "+ get_maNV_Text 
//					 							+ " - acc: " + nv.getTaiKhoan()
//					 							+ "\n - muc L: " + nv.getMucLuong()
//					 							+ "\n - year: " + year  + "");
//		}
		//EndRegion

	}
	//EndRegion
}
