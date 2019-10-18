package gui.main;

import java.awt.Color;
import java.awt.EventQueue;

import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JTextField;

import bAL.ThanhVienBAL;
import gui.ad_QLV.Admin_Form_QLV;
import gui.ncc_KhuyenMai.Ncc_Form_KM;

import java.awt.Font;
import java.awt.Image;
import java.awt.event.ActionListener;
import java.awt.event.ActionEvent;
import javax.swing.JPasswordField;

public class LoginGUI extends JFrame implements ActionListener{
	//Region - UI fields
	private static final long serialVersionUID = -5542783088745535845L;
	public JFrame frame;
	private JTextField textField;
	private JPasswordField passwordField;
	/**
	 *@param đăng nhập
	 */
	JButton btn_Login = new JButton("\u0110\u0103ng Nh\u1EADp");
	//EndRegion

	//Region - run application
	/**
	 * Launch the application.
	 */
	public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					LoginGUI window = new LoginGUI();
					window.frame.setVisible(true);
				} catch (Exception e) {
					e.printStackTrace();
				}
			}
		});
	}
	//EndRegion
	
	//Region - UI components
	/**
	 * Create the application.
	 */
	public LoginGUI() {
		initialize();
	}
	//EndRegion
	
	//Region - init components
	/**
	 * Initialize the contents of the frame.
	 */
	private void initialize() {
		frame = new JFrame();
		frame.setResizable(false);
		frame.setBounds(100, 100, 450, 310);
		frame.setTitle("Quản Lý Phụ Tùng Xe");
		frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		frame.getContentPane().setLayout(null);

		btn_Login.setForeground(Color.BLACK);
		btn_Login.setBackground(Color.CYAN);
		btn_Login.setBounds(284, 147, 114, 23);
		frame.getContentPane().add(btn_Login);
			
		textField = new JTextField();
		textField.setBounds(260, 74, 138, 20);
		frame.getContentPane().add(textField);
		textField.setColumns(10);
		
		JLabel lblUserName = new JLabel("Họ, tên : ");
		lblUserName.setBounds(184, 77, 66, 14);
		frame.getContentPane().add(lblUserName);
		
		JLabel lblPassword = new JLabel("Mật khẩu:");
		lblPassword.setBounds(184, 109, 66, 14);
		frame.getContentPane().add(lblPassword);
		
		JLabel lblQuanLyPTxe = new JLabel("Cửa Hàng Quản Lý Phụ Tùng Xe");
		lblQuanLyPTxe.setFont(new Font("Tahoma", Font.BOLD, 16));
		lblQuanLyPTxe.setBounds(87, 11, 281, 29);
		frame.getContentPane().add(lblQuanLyPTxe);
		
		passwordField = new JPasswordField();
		passwordField.setBounds(260, 105, 138, 20);
		frame.getContentPane().add(passwordField);
		
		JLabel lblAvatar = new JLabel("");
		Image img = new ImageIcon(this.getClass().getResource("/login.jpg")).getImage();
		lblAvatar.setIcon(new ImageIcon(img));
		lblAvatar.setBounds(10, 42, 138, 115);
		frame.getContentPane().add(lblAvatar);
		
		btn_Login.addActionListener(this);
		

	}
	//EndRegion
	
	//Region - support methods
	//TODO Cac phuong thuc ho tro
	// --> implement missing piece or delete
	
	//EndRegion
	
	//Region - event handler
	// TODO Chuc Nang Button
	@SuppressWarnings("deprecation")
	@Override
	public void actionPerformed(ActionEvent e) {
		Object o = e.getSource();
		if(o.equals(btn_Login)){ //Chuc nang Dang nhap
			String taiKhoan_tv = textField.getText();
			String pass_tv = passwordField.getText();
			
			ThanhVienBAL tvbal = new ThanhVienBAL();
			String loaiTV = tvbal.Retrieve(taiKhoan_tv);
	
			if(tvbal.Check_ID(taiKhoan_tv,pass_tv, loaiTV)==1){
				frame.dispose();
				
				//Chon Form
				String ms = textField.getText();
				
				QuanLy_Form_Main QL = new QuanLy_Form_Main(ms);
				NhanVien_Form_Main NV = new NhanVien_Form_Main(ms);
//				NCC_Form_Main NCC = new NCC_Form_Main(ms);
				Ncc_Form_KM NCC = new Ncc_Form_KM(ms);
				Admin_Form_QLV Ad = new Admin_Form_QLV(ms);
//				KhachHang_Form QL = new KhachHang_Form(ms);
				
				if(loaiTV.equals("Quan li"))
					QL.frame.setVisible(true);
				else if(loaiTV.equals("Nhan vien"))
					NV.frame.setVisible(true);
			}else if( textField.getText().equals("") || passwordField.getText().equals(""))
				JOptionPane.showMessageDialog(null, "Bạn chưa điền đủ thông tin");
			else if( tvbal.Check_name(taiKhoan_tv)!=1)
				JOptionPane.showMessageDialog(null, taiKhoan_tv + " không tồn tại");
			else if( tvbal.Check_pass(pass_tv)!=1)
				JOptionPane.showMessageDialog(null, "Sai mật khẩu");
			else
				JOptionPane.showMessageDialog(null, "Tài khoản không tồn tại");
		}
		
	}
	//EndRegion
}
