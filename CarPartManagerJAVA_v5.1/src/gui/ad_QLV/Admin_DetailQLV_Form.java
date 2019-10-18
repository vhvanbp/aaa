package gui.ad_QLV;

import java.awt.Color;
import java.awt.EventQueue;
import java.awt.Font;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.WindowAdapter;
import java.awt.event.WindowEvent;
import java.text.DateFormat;
import java.text.SimpleDateFormat;

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

public class Admin_DetailQLV_Form extends JFrame implements ActionListener {

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
	DateFormat dateformat = new SimpleDateFormat("dd/MM/yyyy");
	JButton btnQuayLai = new JButton("Quay lai");
	private JTextField txtEmail;
	private JTextField txtTaiKhoan;
	private JTextField txtPassword;
	JButton btnXoa = new JButton("Xoa");
	private JTextField txtSDT;
	private JTextField txtLoaiTV;
	private JTextField txtMaTV;
	private JTextField txtMoTaQLV;

	private String get_maTV_Text;
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
					Admin_DetailQLV_Form window = new Admin_DetailQLV_Form();
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
	public Admin_DetailQLV_Form() {
		initialize();
	}
	
	public Admin_DetailQLV_Form(String tenLogin, String maTV) {
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
		getFrame().setBounds(100, 100, 636, 330);
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
		btnSua.setBounds(430, 221, 165, 43);
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

				btnQuayLai.setBackground(Color.CYAN);
				btnQuayLai.setBounds(14, 221, 165, 43);
				frm.getContentPane().add(btnQuayLai);
				
				JLabel lblNhpEmail = new JLabel("Email:");
				lblNhpEmail.setHorizontalAlignment(SwingConstants.RIGHT);
				lblNhpEmail.setBounds(14, 167, 112, 14);
				frm.getContentPane().add(lblNhpEmail);
				
				txtEmail = new JTextField();
				txtEmail.setEditable(false);
				txtEmail.setColumns(10);
				txtEmail.setBounds(136, 164, 161, 20);
				frm.getContentPane().add(txtEmail);
				
				txtTaiKhoan = new JTextField();
				txtTaiKhoan.setEditable(false);
				txtTaiKhoan.setColumns(10);
				txtTaiKhoan.setBounds(434, 102, 161, 20);
				frm.getContentPane().add(txtTaiKhoan);
				
				JLabel lblNhpTK = new JLabel("Tài khoản:");
				lblNhpTK.setHorizontalAlignment(SwingConstants.RIGHT);
				lblNhpTK.setBounds(322, 105, 102, 14);
				frm.getContentPane().add(lblNhpTK);
				
				JLabel lblNhpPass = new JLabel("Mật khẩu:");
				lblNhpPass.setHorizontalAlignment(SwingConstants.RIGHT);
				lblNhpPass.setBounds(322, 136, 102, 14);
				frm.getContentPane().add(lblNhpPass);
				
				txtPassword = new JTextField();
				txtPassword.setEditable(false);
				txtPassword.setColumns(10);
				txtPassword.setBounds(434, 133, 161, 20);
				frm.getContentPane().add(txtPassword);

				btnXoa.setBackground(Color.CYAN);
				btnXoa.setBounds(216, 221, 165, 43);
				frm.getContentPane().add(btnXoa);
				
				txtSDT = new JTextField();
				txtSDT.setEditable(false);
				txtSDT.setColumns(10);
				txtSDT.setBounds(434, 69, 161, 20);
				frm.getContentPane().add(txtSDT);
				
				JLabel lblSdt = new JLabel("Số điện thoại:");
				lblSdt.setHorizontalAlignment(SwingConstants.RIGHT);
				lblSdt.setBounds(322, 72, 102, 14);
				frm.getContentPane().add(lblSdt);
				
				txtLoaiTV = new JTextField();
				txtLoaiTV.setEditable(false);
				txtLoaiTV.setColumns(10);
				txtLoaiTV.setBounds(137, 100, 161, 20);
				frm.getContentPane().add(txtLoaiTV);
				
				JLabel lblLoaiTV = new JLabel("Loại thành viên:");
				lblLoaiTV.setHorizontalAlignment(SwingConstants.RIGHT);
				lblLoaiTV.setBounds(14, 105, 112, 14);
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
				
				txtMoTaQLV = new JTextField();
				txtMoTaQLV.setEditable(false);
				txtMoTaQLV.setText((String) null);
				txtMoTaQLV.setColumns(10);
				txtMoTaQLV.setBounds(434, 169, 161, 20);
				frm.getContentPane().add(txtMoTaQLV);
				
				JLabel lblMoTa = new JLabel("Mô tả:");
				lblMoTa.setHorizontalAlignment(SwingConstants.RIGHT);
				lblMoTa.setBounds(322, 172, 103, 14);
				frm.getContentPane().add(lblMoTa);
								
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
		 tv = qlvBAL.get_QLVByID(get_maTV_Text);
		 qlv = (QuanLiVien)tv;
	}

	// nap du lieu tu bang vao Textfield
	private void napTV_VaoTextfields() {
		txtMaTV.setText(get_maTV_Text);
		txtTen.setText(qlv.getHoTen());
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
		return frm;
	}

	public void setFrame(JFrame frame) {
		this.frm = frame;
		frm.setTitle("Chi tiết quản lí viên");
		frame.setResizable(false);
	}
	//EndRegion

	//Region - TODO - event handler
	@Override
	public void actionPerformed(ActionEvent arg0) {
		Object o = arg0.getSource();
		
		if(o.equals(btnSua)){ //Sua thong tin quan li vien
			frm.dispose();
			Admin_UpdateQLV_Form ad_UpdateQLV = new Admin_UpdateQLV_Form(get_Login_Text,get_maTV_Text);
			ad_UpdateQLV.frame.setVisible(true);
		}else if(o.equals(btnQuayLai)){ // Quay lai
			frm.dispose();
			Admin_Form_QLV ad_QLV = new Admin_Form_QLV(get_Login_Text);
			ad_QLV.frame.setVisible(true);
		}else if(o.equals(btnXoa)){ // xoa nha cung cap
			int dialogButton = JOptionPane.YES_NO_OPTION;
			int dialogResult = JOptionPane.showConfirmDialog(this, "Bạn có muốn xóa quản lí viên này?", 
																	"Title on Box", 
																	dialogButton);
			if(dialogResult == 0) {
				String matv = txtMaTV.getText();
				if(tvBAL.delete(matv)){
					JOptionPane.showMessageDialog(null, "xóa thành công!");
					
					frm.dispose();
					Admin_Form_QLV ad_QLV = new Admin_Form_QLV(get_Login_Text);
					ad_QLV.frame.setVisible(true);
				}else
					JOptionPane.showMessageDialog(null, "Quản lí viên này không xóa được");
			}
		}
		
	}
	//EndRegion
	
}
