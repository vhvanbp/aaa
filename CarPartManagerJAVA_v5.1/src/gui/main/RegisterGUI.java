package gui.main;

import java.awt.Color;
import java.awt.EventQueue;

import javax.swing.DefaultComboBoxModel;
import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JComboBox;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JTextField;

import bAL.KhachHangBAL;
import bAL.NhaCungCapBAL;
import bAL.ThanhVienBAL;
import dAL.DataBase;
import dAL.DbUtils;
import entity.users.KhachHang;
import entity.users.ThanhVien;

import java.awt.Font;
import java.awt.Image;
import java.awt.event.ActionListener;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.awt.event.ActionEvent;
import javax.swing.JPasswordField;
import javax.swing.SwingConstants;

public class RegisterGUI extends JFrame implements ActionListener{
	//Region - UI fields
	private static final long serialVersionUID = -5542783088745535845L;
	JFrame frame;
	private JTextField txtAccount;
	private JPasswordField passwordField_UserPass;
	/**
	 *@param Chon thành viên
	 */
	JButton btnDangKy = new JButton("Đăng ký");
	private JTextField txtUserName;
	private JTextField txtEmail;
	
	private ThanhVienBAL tvBal = new ThanhVienBAL();
	private KhachHangBAL khBal = new KhachHangBAL();
	private NhaCungCapBAL nccBal = new NhaCungCapBAL();
	private JTextField txt_LoaiXeDangDung;
	private JTextField txtSDT;
	//EndRegion

	//Region - run application
	/**
	 * Launch the application.
	 */
	public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					RegisterGUI window = new RegisterGUI();
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
	public RegisterGUI() {
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
		frame.setBounds(100, 100, 510, 310);
		frame.setTitle("Quản Lý Phụ Tùng Xe");
		frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		frame.getContentPane().setLayout(null);
			
		txtAccount = new JTextField();
		txtAccount.setBounds(307, 51, 138, 20);
		frame.getContentPane().add(txtAccount);
		txtAccount.setColumns(10);
		
		JLabel lblAccount = new JLabel("Nhập tài khoản:");
		lblAccount.setHorizontalAlignment(SwingConstants.RIGHT);
		lblAccount.setBounds(185, 54, 112, 14);
		frame.getContentPane().add(lblAccount);
		
		JLabel lblPassword = new JLabel("Nhập mật khẩu:");
		lblPassword.setHorizontalAlignment(SwingConstants.RIGHT);
		lblPassword.setBounds(185, 86, 112, 14);
		frame.getContentPane().add(lblPassword);
		
		JLabel lblQuanLyPTxe = new JLabel("Cửa Hàng Quản Lý Phụ Tùng Xe");
		lblQuanLyPTxe.setFont(new Font("Tahoma", Font.BOLD, 16));
		lblQuanLyPTxe.setBounds(87, 11, 281, 29);
		frame.getContentPane().add(lblQuanLyPTxe);
		
		passwordField_UserPass = new JPasswordField();
		passwordField_UserPass.setBounds(307, 82, 138, 20);
		frame.getContentPane().add(passwordField_UserPass);
		
		JLabel lblAvatar = new JLabel("");
		Image img = new ImageIcon(this.getClass().getResource("/login.jpg")).getImage();
		lblAvatar.setIcon(new ImageIcon(img));
		lblAvatar.setBounds(10, 42, 138, 115);
		frame.getContentPane().add(lblAvatar);
		btnDangKy.setBackground(Color.CYAN);
		

		btnDangKy.setBounds(310, 235, 115, 23);
		frame.getContentPane().add(btnDangKy);
		
		JLabel lbl_LoaiXe = new JLabel("Chọn loại xe đang dùng:");
		lbl_LoaiXe.setHorizontalAlignment(SwingConstants.RIGHT);
		lbl_LoaiXe.setBounds(158, 201, 139, 23);
		frame.getContentPane().add(lbl_LoaiXe);
		
		JLabel lblNhpHTn = new JLabel("Nhập họ, tên: ");
		lblNhpHTn.setHorizontalAlignment(SwingConstants.RIGHT);
		lblNhpHTn.setBounds(185, 116, 112, 14);
		frame.getContentPane().add(lblNhpHTn);
		
		txtUserName = new JTextField();
		txtUserName.setColumns(10);
		txtUserName.setBounds(307, 111, 138, 20);
		frame.getContentPane().add(txtUserName);
		
		JLabel lblNhpEmail = new JLabel("Nhập email: ");
		lblNhpEmail.setHorizontalAlignment(SwingConstants.RIGHT);
		lblNhpEmail.setBounds(185, 146, 112, 14);
		frame.getContentPane().add(lblNhpEmail);
		
		txtEmail = new JTextField();
		txtEmail.setColumns(10);
		txtEmail.setBounds(307, 142, 138, 20);
		frame.getContentPane().add(txtEmail);
		
		txt_LoaiXeDangDung = new JTextField();
		txt_LoaiXeDangDung.setColumns(10);
		txt_LoaiXeDangDung.setBounds(307, 202, 138, 20);
		frame.getContentPane().add(txt_LoaiXeDangDung);
		
		JLabel lblSdt = new JLabel("Nhập số điện thoại:");
		lblSdt.setHorizontalAlignment(SwingConstants.RIGHT);
		lblSdt.setBounds(185, 176, 112, 14);
		frame.getContentPane().add(lblSdt);
		
		txtSDT = new JTextField();
		txtSDT.setColumns(10);
		txtSDT.setBounds(307, 171, 138, 20);
		frame.getContentPane().add(txtSDT);
		btnDangKy.addActionListener(this);
		

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
		if(o.equals(btnDangKy)){ //Chuc nang Dang nhap
			String taiKhoan_tv = txtAccount.getText();
			String ten_tv = txtUserName.getText();
			String pass_tv = passwordField_UserPass.getText();
			String email_tv = txtEmail.getText();
			String sdt = txtSDT.getText();
			String loaiXeDangDung = txt_LoaiXeDangDung.getText();
			
			if( taiKhoan_tv.equals("") 	|| ten_tv.equals("")
				|| pass_tv.equals("") 	|| email_tv.equals(""))
				JOptionPane.showMessageDialog(null, "Bạn chưa điền đủ thông tin");
			else if( tvBal.Check_name(ten_tv)==1)
				JOptionPane.showMessageDialog(null, ten_tv + " đã tồn tại, hãy nhập tài khoản khác");
			else{
				int dialogButton = JOptionPane.YES_NO_OPTION;
				int dialogResult = JOptionPane.showConfirmDialog(this, "Bạn có muốn đăng ký?", "Title on Box", dialogButton);
				if(dialogResult == 0) {
					int countTV_ByType = tvBal.Count("ThanhVien"); // dem thanh vien theo loai
					String matv = "TV" + countTV_ByType;
					
					KhachHang tv = new KhachHang(matv, ten_tv, "KhachHang", email_tv, 
												 sdt, taiKhoan_tv, pass_tv, loaiXeDangDung);
					khBal.create(tv);						
					
				}
			}
		}
		
	}
}
