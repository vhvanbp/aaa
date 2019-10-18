package gui.ql_NhanVien;

import java.awt.Color;
import java.awt.EventQueue;
import java.awt.Font;
import java.awt.Image;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.WindowAdapter;
import java.awt.event.WindowEvent;
import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.Date;

import javax.swing.DefaultComboBoxModel;
import javax.swing.ImageIcon;
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
import helpers.UI_Helpers;

import java.text.ParseException;;

public class QuanLy_AddNV_Form extends JFrame implements ActionListener {

	//Region - TODO - UI fields
	/**
	 * 
	 */
	private static final long serialVersionUID = -7106346928488740026L;
	public JFrame frm;
	private JTextField txtTenTV;
	/**
	 *@param Sửa Thông Tin thành viên
	 */
	JButton btnThem = new JButton("Them");
	/**
	 *@param Đăng Xuất
	 */
	JButton btnDangXuat = new JButton("Dang Xuat");

	private String get_Login_Text;
	JLabel lblTenlogin = new JLabel("TenLogin");
	JComboBox<String> cbo_MucLuong = new JComboBox<String>();
	private JTextField txtLuong;
	DateFormat dateformat = new SimpleDateFormat("dd/MM/yyyy");
	JButton btnQuayLai = new JButton("Quay lai");
	private JTextField txtEmail;
	private JTextField txtTaiKhoan;
	private JTextField txtPassword;
	private JTextField txtDay;
	private JTextField txtMonth;
	private JTextField txtYear;
	private JTextField txtSDT;
	private JTextField txtLoaiTV;
	
	private ThanhVienBAL tvBal = new ThanhVienBAL();
	private NhanVienBAL nvBal = new NhanVienBAL();
	String loaiTV = "Nhan vien"; // loai la mac dinh
	//EndRegion
	
	//Region - TODO - run application
	/**
	 * Launch the application.
	 */
	public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					QuanLy_AddNV_Form window = new QuanLy_AddNV_Form();
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
	public QuanLy_AddNV_Form() {
		initialize();
	}
	
	public QuanLy_AddNV_Form(String tenLogin) {
		get_Login_Text =tenLogin;
		lblTenlogin.setText(get_Login_Text);
		
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
		getFrame().setBounds(100, 100, 636, 403);
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
//		Image img = new ImageIcon(this.getClass().getResource("/login.jpg")).getImage().getScaledInstance(34, 34, java.awt.Image.SCALE_SMOOTH);
//		btnThem.setIcon(new ImageIcon(img));
//		btnQuayLai.setIcon(new ImageIcon(img));
		btnThem.setBackground(Color.CYAN);
		btnThem.setBounds(223, 294, 165, 43);
		getFrame().getContentPane().add(btnThem);
		
		btnDangXuat.setBackground(Color.CYAN);
		btnDangXuat.setBounds(430, 294, 165, 43);
		frm.getContentPane().add(btnDangXuat);
		
		JLabel lblNhpTn = new JLabel("Nhập tên nhân viên:");
		lblNhpTn.setHorizontalAlignment(SwingConstants.RIGHT);
		lblNhpTn.setBounds(14, 136, 112, 14);
		frm.getContentPane().add(lblNhpTn);
		
		txtTenTV = new JTextField();
		txtTenTV.setBounds(136, 133, 161, 20);
		frm.getContentPane().add(txtTenTV);
		txtTenTV.setColumns(10);
		
		JLabel lblChnMucL = new JLabel("Chọn mức lương:");
		lblChnMucL.setHorizontalAlignment(SwingConstants.RIGHT);
		lblChnMucL.setBounds(322, 199, 102, 23);
		frm.getContentPane().add(lblChnMucL);
		

		lblTenlogin.setBounds(244, 44, 119, 14);
		frm.getContentPane().add(lblTenlogin);
		

				cbo_MucLuong.setModel(new DefaultComboBoxModel<String>(new String[] {"1", "2", "3"}));
				

				cbo_MucLuong.setBounds(444, 199, 151, 22);
				frm.getContentPane().add(cbo_MucLuong);
				
				JLabel lblNgay = new JLabel("Nhập ngày vào làm:");
				lblNgay.setHorizontalAlignment(SwingConstants.RIGHT);
				lblNgay.setBounds(14, 202, 112, 14);
				frm.getContentPane().add(lblNgay);
				
				JLabel lblLuong = new JLabel("Nhập lương:");
				lblLuong.setHorizontalAlignment(SwingConstants.RIGHT);
				lblLuong.setBounds(14, 236, 113, 14);
				frm.getContentPane().add(lblLuong);
				
				txtLuong = new JTextField();
				txtLuong.setColumns(10);
				txtLuong.setBounds(136, 233, 161, 20);
				frm.getContentPane().add(txtLuong);

				btnQuayLai.setBackground(Color.CYAN);
				btnQuayLai.setBounds(14, 294, 165, 43);
				frm.getContentPane().add(btnQuayLai);
				
				JLabel lblNhpEmail = new JLabel("Nhập email:");
				lblNhpEmail.setHorizontalAlignment(SwingConstants.RIGHT);
				lblNhpEmail.setBounds(14, 164, 112, 14);
				frm.getContentPane().add(lblNhpEmail);
				
				txtEmail = new JTextField();
				txtEmail.setColumns(10);
				txtEmail.setBounds(136, 161, 161, 20);
				frm.getContentPane().add(txtEmail);
				
				txtTaiKhoan = new JTextField();
				txtTaiKhoan.setColumns(10);
				txtTaiKhoan.setBounds(434, 133, 161, 20);
				frm.getContentPane().add(txtTaiKhoan);
				
				JLabel lblNhpTK = new JLabel("Nhập tài khoản:");
				lblNhpTK.setHorizontalAlignment(SwingConstants.RIGHT);
				lblNhpTK.setBounds(322, 136, 102, 14);
				frm.getContentPane().add(lblNhpTK);
				
				JLabel lblNhpPass = new JLabel("Nhập mật khẩu:");
				lblNhpPass.setHorizontalAlignment(SwingConstants.RIGHT);
				lblNhpPass.setBounds(322, 167, 102, 14);
				frm.getContentPane().add(lblNhpPass);
				
				txtPassword = new JTextField();
				txtPassword.setColumns(10);
				txtPassword.setBounds(434, 164, 161, 20);
				frm.getContentPane().add(txtPassword);
				
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
				txtDay.setColumns(10);
				txtDay.setBounds(136, 199, 30, 20);
				frm.getContentPane().add(txtDay);
				
				txtMonth = new JTextField();
				txtMonth.setColumns(10);
				txtMonth.setBounds(187, 199, 30, 20);
				frm.getContentPane().add(txtMonth);
				
				txtYear = new JTextField();
				txtYear.setColumns(10);
				txtYear.setBounds(238, 199, 59, 20);
				frm.getContentPane().add(txtYear);
				
				txtSDT = new JTextField();
				txtSDT.setColumns(10);
				txtSDT.setBounds(434, 100, 161, 20);
				frm.getContentPane().add(txtSDT);
				
				JLabel lblSdt = new JLabel("Nhập số điện thoại:");
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
				
				//TODO - Register before perform action
				btnThem.addActionListener(this);
				btnDangXuat.addActionListener(this);
				btnQuayLai.addActionListener(this);

				txtLoaiTV.setText(loaiTV);
		
	}
	//EndRegion
	
	//Region - TODO - support methods
	//xoa rong
	private void xoaRong_Txt(){
		txtTenTV.setText("");
		txtEmail.setText("");
		txtDay.setText("");
		txtMonth.setText("");
		txtYear.setText("");
		txtLuong.setText("");
		txtSDT.setText("");
		txtTaiKhoan.setText("");
		txtPassword.setText("");
		cbo_MucLuong.setSelectedIndex(0);
		txtTenTV.requestFocus();
	}
	//EndRegion
		
	//Region - TODO - getter setter for frame
	public JFrame getFrame() {
		return frm;
	}

	public void setFrame(JFrame frame) {
		this.frm = frame;
		frm.setTitle("Thêm nhân viên");
		frame.setResizable(false);
	}
	//EndRegion

	//Region - TODO - event handler
	@Override
	public void actionPerformed(ActionEvent arg0) {
		Object o = arg0.getSource();
		
		if(o.equals(btnThem)){ //Them thong tin nhan vien
			//Region - get text from ui
			int newID = UI_Helpers.addNewID("ThanhVien"); //tao index tiep theo cho list
			String manv = "TV" + newID;
			String tenTV =	txtTenTV.getText();
			String email =	txtEmail.getText();
			String sdt =	txtSDT.getText();

			String taiKhoan =	txtTaiKhoan.getText();
			String password =	txtPassword.getText();
			String cboMuc = (String) cbo_MucLuong.getSelectedItem();
			//Region - get date from ui
			String day = txtDay.getText();
			String month = txtMonth.getText();
			String year = txtYear.getText();
			//EndRegion
			String tien= txtLuong.getText();

			//EndRegion
			
			//Region - function create with condition
			if( tenTV.equals("") 	|| email.equals("") 	|| sdt.equals("")
			|| day.equals("")		|| month.equals("")		|| year.equals("")
			|| tien.equals("")  || taiKhoan.equals("") 	|| password.equals("")	
			|| cboMuc.equals("") // need cboMuc? 
			)
				JOptionPane.showMessageDialog(null, "Bạn chưa điền đủ thông tin!");
			else if( tvBal.Check_name(taiKhoan)==1 )
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
				int dialogResult = JOptionPane.showConfirmDialog(this, "Bạn có muốn thêm nhân viên này?"
																	, "Title on Box"
																	, dialogButton);
					
				if(dialogResult == 0) {
					NhanVien nv = new NhanVien(manv,tenTV,loaiTV,email,sdt,
												taiKhoan,password,ngayVaoLam,mucLuong,
												tienLuong);
						
					if(nvBal.create(nv)){
						JOptionPane.showMessageDialog(null, "Bạn vừa thêm nhân viên!");
						UI_Helpers.updateNewID("ThanhVien", newID);
						
						frm.dispose();
						QuanLy_Form_NV qlNV = new QuanLy_Form_NV(get_Login_Text);
						qlNV.frame.setVisible(true);
					}
				}else{
					dialogResult = JOptionPane.showConfirmDialog(this, "Bạn có muốn xóa rỗng để nhập lại?"
																	, "Title on Box"
																	, dialogButton);
					if(dialogResult == 0) {
						xoaRong_Txt();
					}
				}
			}
			//EndRegion
		
		}else if(o.equals(btnDangXuat)){ // Dang Xuat
			UI_Helpers.DangXuat(frm);
		}else if(o.equals(btnQuayLai)){ // Quay lai
			frm.dispose();
			QuanLy_Form_NV qlNV = new QuanLy_Form_NV(get_Login_Text);
			qlNV.frame.setVisible(true);
		}
		//Region - for test func
//		else if(o.equals(btnTestDate)){ // test
//			String ngay = txtDay.getText() + "/" 
//						+ txtMonth.getText() + "/" 
//						+ txtYear.getText();
////			SimpleDateFormat dateFormat = new SimpleDateFormat("dd/MM/yyyy");
//			Date ngayVaoLam=null;
//			try {
//				ngayVaoLam = dateformat.parse(ngay);
//				JOptionPane.showMessageDialog(null, "date = " + ngayVaoLam);
//			} catch (ParseException e) {
//				// TODO Auto-generated catch block
//				JOptionPane.showMessageDialog(null, "date not valid ");
//				e.printStackTrace();
//			}
//		}
		//EndRegion
		
	}
	//EndRegion
}
