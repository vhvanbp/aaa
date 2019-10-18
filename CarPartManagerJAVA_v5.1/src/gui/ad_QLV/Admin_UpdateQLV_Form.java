package gui.ad_QLV;

import java.awt.Color;
import java.awt.EventQueue;
import java.awt.Font;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.WindowAdapter;
import java.awt.event.WindowEvent;

import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JTextField;
import javax.swing.SwingConstants;

import bAL.QuanLiVienBAL;
import bAL.ThanhVienBAL;
import dAL.DataBase;
import dAL.DbUtils;
import entity.users.QuanLiVien;
import entity.users.ThanhVien;
import helpers.UI_Helpers;

public class Admin_UpdateQLV_Form extends JFrame implements ActionListener {

	//Region - TODO - UI fields
	/**
	 * 
	 */
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
	
	private String get_maQLV_Text;
	JButton btnQuayLai = new JButton("Quay lai");
	private JTextField txtTenTV;
	private JTextField txtTaiKhoan;
	private JTextField txtEmail;
	private JTextField txtPassword;
	private JTextField txtMoTaQLV;
	private JTextField txtSDT;
	
	private ThanhVien tv;
	private QuanLiVien qlv;
	private ThanhVienBAL tvBAL = new ThanhVienBAL();
	private QuanLiVienBAL qlvBAL = new QuanLiVienBAL();
	//EndRegion
	
	//Region - TODO - run application
	/**
	 * Launch the application.
	 */
	public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					Admin_UpdateQLV_Form window = new Admin_UpdateQLV_Form();
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
	public Admin_UpdateQLV_Form() {
		initialize();
	}
	
	public Admin_UpdateQLV_Form(String tenLogin, String maQLV) {
		get_Login_Text = tenLogin;
		lblTenlogin.setText(get_Login_Text);
		get_maQLV_Text = maQLV;
		
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
		getFrame().setBounds(100, 100, 650, 350);
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
		btnSua.setBounds(231, 241, 160, 43);
		getFrame().getContentPane().add(btnSua);
		
		btnDangXuat.setBackground(Color.CYAN);
		btnDangXuat.setBounds(456, 241, 160, 43);
		frame.getContentPane().add(btnDangXuat);
		
		txtMaTV = new JTextField();
		txtMaTV.setEditable(false);
		txtMaTV.setBounds(157, 69, 161, 20);
		frame.getContentPane().add(txtMaTV);
		txtMaTV.setColumns(10);
		
		JLabel lblMaTV = new JLabel("Mã thành viên:");
		lblMaTV.setHorizontalAlignment(SwingConstants.RIGHT);
		lblMaTV.setBounds(24, 72, 124, 14);
		frame.getContentPane().add(lblMaTV);
		

		lblTenlogin.setBounds(244, 44, 119, 14);
		frame.getContentPane().add(lblTenlogin);
				
				JLabel lblLoaiTV = new JLabel("Loại thành viên:");
				lblLoaiTV.setHorizontalAlignment(SwingConstants.RIGHT);
				lblLoaiTV.setBounds(24, 100, 119, 23);
				frame.getContentPane().add(lblLoaiTV);
				
				txtLoaiTV = new JTextField();
				txtLoaiTV.setEditable(false);
				txtLoaiTV.setBounds(157, 100, 161, 20);
				frame.getContentPane().add(txtLoaiTV);
				txtLoaiTV.setColumns(10);
				
				btnQuayLai.setBackground(Color.CYAN);
				btnQuayLai.setBounds(24, 241, 160, 43);
				frame.getContentPane().add(btnQuayLai);
				
				JLabel lblNhpTn = new JLabel("Nhập tên nhà cung cấp:");
				lblNhpTn.setHorizontalAlignment(SwingConstants.RIGHT);
				lblNhpTn.setBounds(10, 134, 137, 14);
				frame.getContentPane().add(lblNhpTn);
				
				txtTenTV = new JTextField();
				txtTenTV.setColumns(10);
				txtTenTV.setBounds(157, 131, 161, 20);
				frame.getContentPane().add(txtTenTV);
				
				JLabel lblNhpTK = new JLabel("Nhập tài khoản:");
				lblNhpTK.setHorizontalAlignment(SwingConstants.RIGHT);
				lblNhpTK.setBounds(328, 162, 117, 14);
				frame.getContentPane().add(lblNhpTK);
				
				txtTaiKhoan = new JTextField();
				txtTaiKhoan.setColumns(10);
				txtTaiKhoan.setBounds(455, 159, 161, 20);
				frame.getContentPane().add(txtTaiKhoan);
				
				JLabel lblNhpEmail = new JLabel("Nhập email:");
				lblNhpEmail.setHorizontalAlignment(SwingConstants.RIGHT);
				lblNhpEmail.setBounds(24, 165, 124, 14);
				frame.getContentPane().add(lblNhpEmail);
				
				txtEmail = new JTextField();
				txtEmail.setColumns(10);
				txtEmail.setBounds(157, 162, 161, 20);
				frame.getContentPane().add(txtEmail);
				
				JLabel lblNhpPass = new JLabel("Nhập mật khẩu:");
				lblNhpPass.setHorizontalAlignment(SwingConstants.RIGHT);
				lblNhpPass.setBounds(343, 190, 102, 14);
				frame.getContentPane().add(lblNhpPass);
				
				txtPassword = new JTextField();
				txtPassword.setColumns(10);
				txtPassword.setBounds(455, 187, 161, 20);
				frame.getContentPane().add(txtPassword);
				
				JLabel lblMoTa = new JLabel("Nhập công ty:");
				lblMoTa.setHorizontalAlignment(SwingConstants.RIGHT);
				lblMoTa.setBounds(24, 193, 124, 14);
				frame.getContentPane().add(lblMoTa);
				
				txtMoTaQLV = new JTextField();
				txtMoTaQLV.setColumns(10);
				txtMoTaQLV.setBounds(157, 190, 161, 20);
				frame.getContentPane().add(txtMoTaQLV);
				
				txtSDT = new JTextField();
				txtSDT.setText((String) null);
				txtSDT.setColumns(10);
				txtSDT.setBounds(455, 131, 161, 20);
				frame.getContentPane().add(txtSDT);
				
				JLabel lblSDT = new JLabel("Nhập số điện thoại:");
				lblSDT.setHorizontalAlignment(SwingConstants.RIGHT);
				lblSDT.setBounds(328, 133, 117, 14);
				frame.getContentPane().add(lblSDT);
				
				//TODO - Register before perform action
				btnSua.addActionListener(this);
				btnDangXuat.addActionListener(this);
				btnQuayLai.addActionListener(this);
		
				// methods from support region
				layQLV_tuForm();
				napQLV_VaoTextfields();
	}
	//EndRegion
	
	//Region - TODO - support methods
	private void layQLV_tuForm() {
		 tv = qlvBAL.get_QLVByID(get_maQLV_Text);
		 qlv = (QuanLiVien)tv;
	}
		
	// nap du lieu tu bang vao Textfield
	private void napQLV_VaoTextfields() {
		txtMaTV.setText(get_maQLV_Text);
		txtTenTV.setText(qlv.getHoTen());
		txtLoaiTV.setText(qlv.getLoaiThanhVien());
		txtEmail.setText(qlv.getEmail());
		txtSDT.setText(qlv.getSdt());
		txtTaiKhoan.setText(qlv.getTaiKhoan());
		txtPassword.setText(qlv.getMatKhau());
		txtMoTaQLV.setText(qlv.getMoTaQLV());
	}
	//EndRegion
		
	//Region - TODO - getter setter for frame
	public JFrame getFrame() {
		return frame;
	}

	public void setFrame(JFrame frame) {
		this.frame = frame;
		frame.setTitle("Sửa quản lí viên");
		frame.setResizable(false);
	}
	//EndRegion

	//Region - TODO - event handler
	@Override
	public void actionPerformed(ActionEvent arg0) {
		Object o = arg0.getSource();
		
		if(o.equals(btnSua)){ //Sua thong tin quan li vien

			//Region - get text from ui
			String loaiTV = "Quan li";
			String tenTV =	txtTenTV.getText();
			String email =	txtEmail.getText();
			String sdt =	txtSDT.getText();
			String taiKhoan =	txtTaiKhoan.getText();
			String password =	txtPassword.getText();
			String moTaQLV =	txtMoTaQLV.getText();
			//EndRegion

			//Region - function create with condition
			if( tenTV.equals("") 	|| email.equals("") 	|| sdt.equals("")
			|| taiKhoan.equals("") 	|| password.equals("")	|| moTaQLV.equals(""))
				JOptionPane.showMessageDialog(null, "Bạn chưa điền đủ thông tin");
			else if( tvBAL.Check_name(taiKhoan)==1 && !taiKhoan.equals(qlv.getTaiKhoan()))
				JOptionPane.showMessageDialog(null, "Tài khoản " + taiKhoan + " đã tồn tại, hãy nhập tài khoản khác");
			else{
				int dialogButton = JOptionPane.YES_NO_OPTION;
				int dialogResult = JOptionPane.showConfirmDialog(this, "Bạn có muốn sửa thông tin quản lí viên này?"
						, "Title on Box"
						, dialogButton);
				
				if(dialogResult == 0) {
					QuanLiVien qlvNew = new QuanLiVien(qlv.getMaTV(), tenTV, loaiTV, email
														, sdt, taiKhoan, password, moTaQLV);

					if (qlvBAL.update(qlvNew)) {
						JOptionPane.showMessageDialog(null, "Bạn vừa sửa thông tin quản lí viên!");

						frame.dispose();
						Admin_Form_QLV ad_QLV = new Admin_Form_QLV(get_Login_Text);
						ad_QLV.frame.setVisible(true);
					}
				}else { 
					dialogResult = JOptionPane.showConfirmDialog(this, "Bạn có muốn giữ nội dung đang sửa?"
																	, "Title on Box"
																	, dialogButton);
					if(dialogResult == 1) {
						napQLV_VaoTextfields();						
					}
				} 		
			}
			//EndRegion

		}else if(o.equals(btnDangXuat)){ // Dang Xuat
			UI_Helpers.DangXuat(frame);
		}else if(o.equals(btnQuayLai)){ // Quay Lai
			frame.dispose();
			Admin_Form_QLV frm_QLV = new Admin_Form_QLV(get_Login_Text);
			frm_QLV.frame.setVisible(true);
		}

	}
	//EndRegion
	
}
